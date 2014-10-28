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
 * File      : ProcessorFactory.java
 * Classname : ProcessorFactory
 * Author    : Duco Dokter
 * Created   : Fri Jan 28 10:20:50 2005
 * Version   : $Revision: 1.15 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import java.net.URL;
import java.util.Vector;

import org.apache.commons.digester3.Digester;

/**
 * This factory class creates Processor objects from an XML definition. See the
 * workflow package info for details.
 */
public class ProcessorFactory {

	/**
	 * Digester for processing the XML definition.
	 */
	private final Digester dig;

	/**
	 * Creates a new <code>ProcessorFactory</code> instance, and define rules
	 * for digester.
	 * 
	 * @param proc
	 *            the processor to use as root object. May be null, in which
	 *            case a new Processor will be created.
	 */
	public ProcessorFactory(final ProcessorImpl proc) {

		final ActionFactory actionFactory = new ActionFactory();
		final MappingFactory mappingFactory = new MappingFactory();

		this.dig = new Digester();

		if (proc == null) {
			this.dig.addObjectCreate("workflow", ProcessorImpl.class);
		} else {
			this.dig.push(proc);
		}

		this.dig.addFactoryCreate("workflow/actions/action", actionFactory);
		this.dig.addFactoryCreate("workflow/mappings/mapping", mappingFactory);
		this.dig.addFactoryCreate("workflow/initial", actionFactory);
		this.dig.addObjectCreate("workflow/finals", Vector.class);
		this.dig.addFactoryCreate("workflow/finals/final", actionFactory);

		this.dig.addSetNext("workflow/mappings/mapping", "addMapping");
		this.dig.addSetNext("workflow/initial", "setInitial");
		this.dig.addSetNext("workflow/finals/final", "add");
		this.dig.addSetNext("workflow/finals", "setFinals");

		this.dig.addCallMethod("workflow/actions/action/property",
				"setProperty", 2);
		this.dig.addCallParam("workflow/actions/action/property", 0, "name");
		this.dig.addCallParam("workflow/actions/action/property", 1, "value");
	}

	/**
	 * Create processor based on URL holding config.
	 * 
	 * @param url
	 *            a <code>String</code> value
	 * @return a <code>Processor</code> value
	 * @exception Exception
	 *                if an error occurs
	 */
	public final Processor createProcessor(final URL url) throws Exception {

		return (Processor) this.dig.parse(url.toString());
	}
}
