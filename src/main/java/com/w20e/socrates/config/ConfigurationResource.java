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
 * File      : ConfigurationResource.java
 * Classname : ConfigurationResource
 * Author    : Duco Dokter
 * Created   : Wed Jan 26 16:19:14 2005
 * Version   : $Revision: 1.13 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */
package com.w20e.socrates.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * Resource for Configuration objects. This provides wrapper functionality
 * around any configuration framework.
 * 
 * @todo Maybe we should 'discover' the actual factory?
 */
public final class ConfigurationResource {

    /**
     * Holder of singleton instance.
     */
    private static ConfigurationResource resource;

    /**
     * Declare constructor as private.
     */
    private ConfigurationResource() {
        // Empty constructor.
    }

    /**
     * Return the single instance of the resource.
     * 
     * @return a <code>ConfigurationResource</code> value
     */
    public static synchronized ConfigurationResource getInstance() {

        if (resource == null) {
            resource = new ConfigurationResource();
        }

        return resource;
    }

    /**
     * Get the global configuration.
     * 
     * @return a <code>Configuration</code> value
     * @exception ConfigurationException
     *                if an error occurs
     * @todo Do we actually need this?
     */
    public Configuration getConfiguration() throws ConfigurationException {

        // First try system var
        if (System.getProperty("socrates.config.url") != null) {

            try {
                return getConfiguration(new URL(System.getProperty("socrates.config.url")));
            } catch (MalformedURLException mfue) {
                throw new ConfigurationException();
            }
        }
        // Resource from class path
        return getConfiguration(getClass().getResource("/socrates.xml"));
    }

    /**
     * Get configuration from URL. If it's a file base configuration (which
     * it is...) make sure to apply the file changed reloading strategy. 
     *
     * @param configUrl
     *            an <code>URL</code> value
     * @throws ConfigurationException
     *             when the configuration couldn't be found
     * @return a <code>Configuration</code> value
     */
    public Configuration getConfiguration(final URL configUrl)
        throws ConfigurationException {
    	
    	DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder(configUrl);

    	builder.setReloadingStrategy(new FileChangedReloadingStrategy());
    	
        return builder.getConfiguration();
    }
}
