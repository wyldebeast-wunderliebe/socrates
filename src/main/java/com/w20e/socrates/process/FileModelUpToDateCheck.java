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

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Check for Model's on file system.
 * 
 * @author dokter
 */
public class FileModelUpToDateCheck implements ModelUpToDateCheck {

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(FileModelUpToDateCheck.class.getName());

	/**
	 * Map for holding timestamps.
	 */
	private Map<URI, Long> timestamps;

	/**
	 * Create a new check instance.
	 */
	public FileModelUpToDateCheck() {

		this.timestamps = new HashMap<URI, Long>();
	}

	/**
	 * Check whether given file is up to date.
	 * 
	 * @param id
	 *            file id
	 * @return whether up to date or not.
	 */
	public final boolean isUpToDate(final URI id) {

		String filename = id.toString().substring(id.toString().indexOf(":") + 1);

		// make sure files are not relative
		if (filename.startsWith(".")) {
			if (System.getProperty("socrates.cfg.root") != null) {
				filename = filename.replaceFirst("^\\.", System.getProperty("socrates.cfg.root"));
			}
		}

		filename = filename.replace("$HOME", System.getProperty("user.home"));

		File file = new File(filename);

		LOGGER.fine("Checking up-to-date status for file " + file);

		Long timestamp = null;

		try {
			timestamp = Long.valueOf(file.lastModified());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Couldn't get timestamp for " + id);
		}

		// check
		if (this.timestamps.containsKey(id)) {
			if (timestamp.compareTo(this.timestamps.get(id)) > 0) {
				this.timestamps.put(id, timestamp);
				return false;
			}
		} else {
			this.timestamps.put(id, timestamp);
		}

		return true;
	}
}
