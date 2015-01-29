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
 * File      : RunnerContext.java
 * Classname : RunnerContext
 * Author    : Duco Dokter
 * Created   : Wed Feb  2 15:06:46 2005
 * Version   : $Revision: 1.15 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;

import com.w20e.socrates.formatting.Formatter;
import com.w20e.socrates.config.ConfigurationResource;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.StateManager;
import com.w20e.socrates.workflow.ProcessContextImpl;

/**
 * The special context for the model processor delivers formatter and renderer
 * capacities to the action classes for this process.
 * 
 * @author <a href="mailto:dokter@wyldebeast">Duco Dokter</a>
 * @version 1.0
 */
public class RunnerContextImpl extends ProcessContextImpl implements
		RunnerContext {

	/**
	 * UID.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7629592103605211467L;

	/**
	 * Formatter holder.
	 */
	private Formatter formatter;

	/**
	 * Model holder.
	 */
	private Model model;

	/**
	 * Holds the current output stream.
	 */
	private OutputStream out;

	/**
	 * Holds the state manager.
	 */
	private StateManager stateMgr;

	/**
	 * Holder for runtime data.
	 */
	private Map<String, Object> data;

	/**
	 * Hold instance for runner.
	 */
	private Instance instance;

	private Locale locale;

	private URI id;

	private RenderConfig cfg;

	/**
	 * Creates a new <code>RunnerContext</code> instance.
	 * 
	 * @param stream
	 *            output stream to use for formatting.
	 * @param newFormatter
	 *            a <code>Formatter</code> value.
	 * @param manager
	 *            the state manager to use.
	 * @param newModel
	 *            the model for this context.
	 * @param inst
	 *            Instance to use.
	 */
	public RunnerContextImpl(final OutputStream stream,
			final Formatter newFormatter, final StateManager manager,
			final Model newModel, final Instance inst,
			final RenderConfig rCfg) {

		super();

		this.formatter = newFormatter;
		this.out = stream;
		this.stateMgr = manager;
		this.instance = inst;
		this.model = newModel;
		this.data = new HashMap<String, Object>();
		this.cfg = rCfg;
	}

	/**
	 * Get the formatter from this context.
	 * 
	 * @return a <code>Formatter</code> value
	 */
	@Override
	public final Formatter getFormatter() {

		return this.formatter;
	}

	/**
	 * Get the model from this context.
	 * 
	 * @return a <code>Model</code> value
	 */
	@Override
	public final Model getModel() {

		return this.model;
	}

	/**
	 * Get the outputstream for this context.
	 * 
	 * @return an <code>OutputStream</code> value
	 */
	@Override
	public final OutputStream getOutputStream() {

		return this.out;
	}

	/**
	 * Set the outputstream for this context.
	 * 
	 * @param newOut
	 *            <code>OutputStream</code> value
	 */
	public final void setOutputStream(final OutputStream newOut) {

		this.out = newOut;
	}

	/**
	 * Get the state manager.
	 * 
	 * @return a <code>StateManager</code> value
	 */
	@Override
	public final StateManager getStateManager() {

		return this.stateMgr;
	}

	/**
	 * Set the data to the context.
	 * 
	 * @param params
	 *            a <code>Map</code> value
	 */
	public final void setData(final Map<String, Object> params) {

		this.data = params;
	}

	/**
	 * Get the data from the context.
	 * 
	 * @return a <code>Map</code> value
	 */
	@Override
	public final Map<String, Object> getData() {

		return this.data;
	}

	/**
	 * Instance in context.
	 * 
	 * @return instance.
	 */
	@Override
	public final Instance getInstance() {

		return this.instance;
	}

	/**
	 * Set the instance for this context.
	 * 
	 * @param inst
	 *            instance to use.
	 */
	public final void setInstance(final Instance inst) {

		this.instance = inst;
	}

	@Override
	public final RenderConfig getRenderConfig() {

		return this.cfg;
	}

	@Override
	public Locale getLocale() {

		return this.locale;
	}

	@Override
	public URI getQuestionnaireId() {

		return this.id;
	}

	public void setQuestionnaireId(URI newId) {

		this.id = newId;
	}

	public void setLocale(Locale newLocale) {
		this.locale = newLocale;
	}
	
	@Override
	public Configuration getConfiguration() {

		URI qUri = getQuestionnaireId();
	        
		try {
			return ConfigurationResource.getInstance().getConfiguration(qUri.toURL());
		} catch (ConfigurationException e) {
			return null;
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
