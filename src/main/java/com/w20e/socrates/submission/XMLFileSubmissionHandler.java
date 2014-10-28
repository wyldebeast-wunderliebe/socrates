/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 * File      : FileSubmissionHandler.java
 * Classname : FileSubmissionHandler
 * Author    : Duco Dokter
 * Date      : 15 Jan 2005
 * Version   : $Revision: 1.11 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.submission;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.NodeValidator;
import com.w20e.socrates.model.Submission;
import com.w20e.socrates.model.util.InstanceXMLSerializer;

/**
 * Submission handler using the file system. In this case, instance data is
 * stored as XML.
 */
public final class XMLFileSubmissionHandler implements SubmissionHandler {

    /**
     * Initialize this class' logging.
     */
    private static final Logger LOGGER = Logger
            .getLogger(XMLFileSubmissionHandler.class.getName());

    /**
     * Hold meta data.
     */
    private Map<String, Object> metaData;

    /**
     * Default extension.
     */
    private static final String EXTENSION = ".xml";

    /**
     * Submit the Instance to the file designated in the constructor.
     * 
     * @param data
     *            Instance to submit. To enable proper submission to relative
     *            files, prepend your filename with /./foo.xml. So the complete
     *            URI would be like: file:///./foo.xml.
     * @param submission
     *            Submission info
     * @throws SubmissionException
     *             in case the data can't be processed.
     */
    @Override
	public void submit(final Instance data, final Model model, final Submission submission)
            throws SubmissionException {

        LOGGER.fine("Storing instance");

        //  Before anything else, we need to traverse all nodes, and see whether
        // there is any calculations left...
        //
        for (Node n: data.getAllNodes()) {
            ItemProperties props;

            props = model.getItemProperties(n.getName());

            if (props == null || props.getCalculate() == null) {
                continue;
            }
            try {
                n.setValue(NodeValidator.getRawValue(n, props, model, data));
            } catch (Exception e) {
                LOGGER.severe("Error in calculating node value.");
                n.setValue(null);
            }
        }
        
        Date now = new Date();
        String filename;

        this.metaData = data.getMetaData();

        if (this.metaData.containsKey("filename")) {
            filename = this.metaData.get("filename").toString();
        } else {
            this.metaData.put("warn01",
                    "put filename in metaData of SubmissionHandler"
                            + " to avoid overwriting the output file");
            filename = "out_" + now.toString();
        }

        String base = submission.getAction().getPath();

        if (base.startsWith("/.")) {
            base = base.substring(1);
        }

        String file = base + File.separator + filename + EXTENSION;

        LOGGER.fine("Try to store data as " + file);

        try {
            InstanceXMLSerializer.serialize(data, new File(file));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't submit data", e);
            throw new SubmissionException(e.getMessage());
        }

        LOGGER.fine("Done");
    }

}
