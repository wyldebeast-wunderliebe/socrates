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
 * File      : Formatter.java
 * Classname : Formatter
 * Author    : Duco Dokter
 * Date      : 19 Jan 2005
 * Version   : $Revision: 1.17 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.formatting;

import java.io.OutputStream;
import java.util.Collection;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.process.RunnerContext;
import com.w20e.socrates.rendering.Renderable;

/**
 * 
 * @author helmantel
 * 
 *         A Formatter is used to render Models according to given media like
 *         PDF, HTML and so on. The Runner usually determines what the formatter
 *         receives, the formatter should then be able to interpret the provided
 *         items, and format away...
 */
public interface Formatter {

	/**
	 * Usually formatters would need some kind of initialization...
	 * 
	 * @param cfg
	 *            Configuration for initialization.
	 * @param options
	 *            Run time per instance options.
	 */
	void init(Configuration cfg);

	/**
	 * Initiate formatting some Item onto some OutputStream.
	 * 
	 * @param items
	 *            Item(s) to format. This is a list of renderable items.
	 * @param out
	 *            the OutputStream to format onto
	 * @param context
	 *            Context for processing.
	 * @throws FormatException
	 *             in case rendering to the outputstream fails. The exception
	 *             should provide some insight into why the formatting failed
	 *             for the specific formatter.
	 */
	void format(Collection<Renderable> items, OutputStream out,
			RunnerContext context) throws FormatException;
}
