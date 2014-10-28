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
 * File      : ModelWrapper.java
 * Classname : ModelWrapper
 * Author    : Duco Dokter
 * Date      : 17 Jan 2005
 * Version   : $Revision: 1.1.1.1 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.factories;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.w20e.socrates.model.Questionnaire;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.Submission;
import com.w20e.socrates.model.SubmissionImpl;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.RenderConfigImpl;

/**
 * Wrapper class for Socrates Model, so as to be able to add ItemWrapper
 * classes, that will be split into Node and ItemProperties classes. The
 * ModelDefinition is also a factory for Group elements, that will be used to
 * create RenderConfig info.
 */
public class QuestionnaireImpl implements Questionnaire {

	/**
	 * Model to use.
	 */
	private ModelImpl model;

	/**
	 * hold nodes.
	 */
	private InstanceImpl inst;

	/**
	 * Hold Render config.
	 */
	private RenderConfig rCfg;

	/**
	 * Construct a ModelWrapper by using an InstanceImpl.
	 */
	public QuestionnaireImpl() {

		this.model = new ModelImpl();
		this.inst = new InstanceImpl();
		this.rCfg = new RenderConfigImpl();

		final Submission submission = new SubmissionImpl();
		try {
			submission.setAction(new URI("none://"));
		} catch (URISyntaxException e) {
			// Nothing...
		}
		this.model.setSubmission(submission);
	}

	/**
	 * Add node to data
	 * 
	 * @param node
	 *            Node
	 */
	public final void addNode(final Node node) {

		this.inst.addNode(node);
	}

	/**
	 * Add props to model
	 * 
	 * @param props
	 */
	public final void addProperties(final ItemProperties props) {

		this.model.addItemProperties(props);
	}

	/**
	 * Return the wrapped model.
	 * 
	 * @param id
	 *            get the model for this id
	 * @return the wrapped model
	 */
	@Override
	public final Model getModel(final String id) {

		return this.model;
	}

	/**
	 * Get the wrapped instance.
	 * 
	 * @param id
	 *            istance id
	 * @return wrapped instance
	 */
	@Override
	public final Instance getInstance(final String id) {

		return this.inst;
	}

	/**
	 * Get the wrapped rendering info.
	 * 
	 * @return wrapped render configuration
	 */
	@Override
	public final RenderConfig getRenderConfig() {

		return this.rCfg;
	}

	public final void setRenderConfig(RenderConfig cfg) {

		this.rCfg = cfg;
	}

	/**
	 * List wrapped models. Unimplemented!
	 * 
	 * @return null
	 */
	public final List<Model> getModels() {

		return null;
	}

	/**
	 * Return a list of all instances in this definition. Unimplemented!
	 * 
	 * @return list of instances
	 */
	public final List<Instance> getInstances() {

		return null;
	}

	/**
	 * Add meta data to model.
	 * 
	 * @param name
	 *            Name of meta data item
	 * @param value
	 *            value to set
	 */
	public final void setMetaData(final String name, final String value) {

		this.model.getMetaData().put(name, value);
	}
}
