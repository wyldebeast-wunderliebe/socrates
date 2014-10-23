/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.process;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Check for model's from http source.
 * 
 * @author dokter
 */
public class HttpModelUpToDateCheck implements ModelUpToDateCheck {

    /**
     * Initialize this class' logging.
     */
    private static final Logger LOGGER = Logger
            .getLogger(HttpModelUpToDateCheck.class.getName());

    /**
     * Map for holding timestamps.
     */
    private Map<URI, String> timestamps;

    /**
     * Offset of time info.
     */
    private static final int OFFSET = 5;

    /**
     * Create a new check instance.
     */
    public HttpModelUpToDateCheck() {

        this.timestamps = new HashMap<URI, String>();
    }

    /**
     * Check whether the model is up to date.
     * 
     * @param id
     *            URL id of model to check.
     * @return whether the model is up to date or not.
     */
    public final boolean isUpToDate(final URI id) {

        String urlStr = id.toString();

        // @todo this should be configurable
        urlStr = urlStr.substring(0, urlStr.length() - OFFSET)
                + "dateLastChanged";

        StringWriter out = new StringWriter();
        String timestamp = "";

        LOGGER.finer("Using url " + urlStr);

        try {
            URL url = new URL(urlStr);

            InputStream in = new BufferedInputStream(url.openStream());

            int j;

            while ((j = in.read()) != -1) {
                out.write(j);
            }

            in.close();
            out.flush();
            out.close();

            timestamp = out.toString().trim();

            LOGGER.finer("Got timestamp for model " + id + ": " + timestamp);

            // check
            if (this.timestamps.containsKey(id)) {
                if (timestamp.compareTo(this.timestamps.get(id)) > 0) {
                    this.timestamps.put(id, timestamp);
                    return false;
                }
            } else {
                this.timestamps.put(id, timestamp);
            }
        } catch (MalformedURLException e) {
            LOGGER.warning("Malformed URL " + id);
        } catch (IOException ioe) {
            LOGGER.warning("Couldn't fetch timestamp for model " + id);
        }

        return true;
    }
}
