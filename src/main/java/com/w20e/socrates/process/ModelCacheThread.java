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
 * File      : SessionCleanupThread
 * Author    : G.J.C.M Braas
 * Created   : 03 march 2004
 * Version   : $Version: $
 * Copyright : Wyldebeast & Wunderliebe
 *
 */

package com.w20e.socrates.process;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Check on model updates.
 * 
 * @author dokter
 */
public class ModelCacheThread extends Thread {

    /**
     * One hour.
     */
    public static final long ONE_HOUR = 3600000;

    /**
     * One day.
     */
    public static final long ONE_DAY = 86400000;

    /**
     * One minute.
     */
    public static final long ONE_MINUTE = 60000;

    /**
     * Number of seconds between checks.
     */
    private long checkInterval;

    /**
     * Initialize this class' logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ModelCacheThread.class.getName());

    /**
     * Handlers for different types of models.
     */
    private Map<String, ModelUpToDateCheck> handlers;

    /**
     * Construct new thread for model checks.
     */
    public ModelCacheThread() {

        LOGGER.info("Initializing model cache thread");

        this.checkInterval = ModelCacheThread.ONE_MINUTE;

        this.handlers = new HashMap<String, ModelUpToDateCheck>();
    }

    /**
     * Start the thread.
     */
    public final void run() {

        LOGGER.finer("Starting thread");

        while (true) {

            LOGGER.finer("Starting check");
            ArrayList<URI> remove = new ArrayList<URI>();
            
            for (Iterator<URI> i = ModelResource.getInstance().getModelIds()
                    .iterator(); i.hasNext();) {

                try {

                    URI id = i.next();
                    String proto = id.toString().substring(0,
                            id.toString().indexOf(":"));

                    LOGGER.fine("Checking model with id " + id);

                    if (this.handlers.containsKey(proto)) {
                        ModelUpToDateCheck handler = this.handlers.get(proto);
                        if (!handler.isUpToDate(id)) {
                            LOGGER.fine("Model not up to date for id " + id);
                            remove.add(id);
                        }
                    } else {
                        LOGGER.log(Level.WARNING, "No handler specified for " + proto);
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Exception in up-to-date-check", e);
                    e.printStackTrace();
                }
            }
            
            // Cleaning up...
            for (Iterator<URI> i = remove.iterator(); i.hasNext(); ) {
            	ModelResource.getInstance().removeModel(i.next());
            }

            try {
                LOGGER.finest("Sleeping for: " + this.checkInterval
                        + " milliseconds");
                Thread.sleep(this.checkInterval);
            } catch (InterruptedException ie) {
                LOGGER.log(Level.SEVERE, "Exception trying to sleep", ie);
            }
        }
    }

    /**
     * Sets the time between the checking. The time must be given in
     * milliseconds.
     * 
     * @param newCheckInterval
     *            time in milliseconds.
     */
    public final void setCheckInterval(final long newCheckInterval) {

        this.checkInterval = newCheckInterval;
    }

    /**
     * Returns the time between the checking. The time will be given in
     * milliseconds.
     * 
     * @return time in milleseconds
     */
    public final long getCheckInterval() {

        return this.checkInterval;
    }

    /**
     * Add a handler for the given protocol.
     * 
     * @param proto
     *            protocol (file, http, ...)
     * @param handler
     *            handler to use for uptodate check.
     */
    public final void register(final String proto,
            final ModelUpToDateCheck handler) {

        this.handlers.put(proto, handler);
    }
}
