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
 * File      : Form.java
 * Classname : Form
 * Author    : Duco Dokter
 * Date      : 17 Jan 2006
 * Version   : $Revision: 1.7 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.model;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.rendering.RenderConfig;

/**
 * <p>
 * Wrapper class for model, instance and rendering configuration. This is a
 * convenience interface, that may be used to define a <em>runnable</em>
 * instantiation, that holds the relationship between the data instance, the
 * control of that instance, as defined by the model's bindings, and the view,
 * defined by the rendering configuration.
 * </p>
 * <p>
 * The Questionnaire interface is written in such a way, keeping in mind a
 * flyweight pattern, in that many questionnaires may share the rendering
 * configuration, and the bindings, in that the Instance that is normally a
 * child of the Model, has been decoupled.
 */
public interface Questionnaire {

	/**
	 * Return the model identified by id.
	 * 
	 * @param id
	 *            Id of the model
	 * @return The model, or null if none found.
	 */
	Model getModel(String modelId);

	/**
	 * Get the named instance.
	 * 
	 * @param id
	 *            Instance to fetch
	 * @return The named instance, or null if not found.
	 */
	Instance getInstance(String instanceId);

	/**
	 * Get the rendering info for this form. This holds all the controls that
	 * could be resulting in some view.
	 * 
	 * @return The RenderConfig object.
	 */
	RenderConfig getRenderConfig();
}
