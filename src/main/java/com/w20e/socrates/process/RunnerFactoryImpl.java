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
 * File      : WoliWebRunnerFactory.java
 * Classname : WoliWebRunnerFactory
 * Author    : Wietze Helmantel
 * Date      : 3 feb 2005
 * Version   : $Revision: 1.9 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.config.ConfigurationResource;
import com.w20e.socrates.data.Instance;
import com.w20e.socrates.formatting.Formatter;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.StateManager;

/**
 * @author helmantel <code>WoliWebRunnerFactory</code> delivers WoliWeb specific
 *         <code>Runner</code> objects.
 * @todo split this bad boy into separate parts for creating all kinds of
 *       things.
 * @todo make caches more intelligent to account for changes of XML defs.
 */
public final class RunnerFactoryImpl implements RunnerFactory {

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(RunnerFactoryImpl.class.getName());

	/**
	 * Hold configuration root directory.
	 */
	private String rootDir = ".";

	/**
	 * Cache for formatters per runner id.
	 */
	private Map<URI, Formatter> formatters;

	/**
	 * Cache for runners.
	 */
	private Map<URI, Runner> runners;

	/**
	 * Creates a runner factory, with the given config root.
	 * 
	 * @param cfgRoot
	 *            Configuration root for runner factory.
	 */
	public RunnerFactoryImpl(final String cfgRoot) {

		if ((new File(cfgRoot)).isDirectory()) {
			this.rootDir = cfgRoot;
		} else {
			LOGGER.severe("Config root is not a directory, using "
					+ this.rootDir);
		}

		this.formatters = new HashMap<URI, Formatter>();
		this.runners = new ConcurrentHashMap<URI, Runner>();
	}

	/**
	 * Create a runner instance, or null if the runner cannot be created. The
	 * runner holds a reference to the model, and will be initialized with a
	 * workflow definition.
	 * 
	 * @param id
	 *            questionnaire id
	 * @param l
	 *            a <code>Locale</code> value
	 * @param medium
	 *            a <code>String</code> value
	 * @return a <code>Runner</code> value
	 * @exception UnsupportedMediumException
	 *                if an error occurs
	 */
	@Override
	public Runner createRunner(final URI url) throws UnsupportedMediumException {

		if (!this.runners.containsKey(url)) {
			LOGGER.fine("Creating new runner for id " + url.toString());
			try {
				Configuration cfg = ConfigurationResource.getInstance()
						.getConfiguration(url.toURL());

				if (cfg == null) {
					return null;
				}

				this.runners.put(
						url,
						new RunnerImpl(new URL((String) cfg
								.getProperty("runner.url"))));
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Couldn't create runner", e);
				throw new UnsupportedMediumException(e.getMessage());
			}
		}

		return this.runners.get(url);
	}

	/**
	 * Create a context for the given id.
	 * 
	 * @param url
	 *            runner config id
	 * @throws UnsupportedMediumException
	 *             in case the medium isn't supported.
	 */
	public RunnerContextImpl createContext(final URI url,
			final Map<String, String> options)
			throws UnsupportedMediumException {

		try {

			Configuration cfg = ConfigurationResource.getInstance()
					.getConfiguration(url.toURL());

			if (cfg == null) {
				return null;
			}

			URI modelId = new URI((String) cfg.getProperty("model.id"));

			LOGGER.fine("Creating model for " + modelId);
			Model model = ModelResource.getInstance().getModel(modelId, cfg);

			LOGGER.finer("Done. Now creating instance");
			Instance inst = ModelResource.getInstance().createInstance(modelId,
					cfg);

			RenderConfig rCfg = ModelResource.getInstance().getRenderConfig(
					modelId, cfg);

			LOGGER.finer("Created. Let's create a new context");
			RunnerContextImpl ctx = new RunnerContextImpl(null, getFormatter(
					url, cfg, options), createStateManager(cfg, rCfg, model,
					inst), model, inst, rCfg);

			// Allow arbitrary properties to be set on the context.
			String key;

			for (final Iterator<?> i = cfg.getKeys(); i.hasNext();) {

				key = (String) i.next();

				if (key.startsWith("context")) {

					ctx.setProperty(key, cfg.getProperty(key));
				}
			}

			return ctx;

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Couldn't create context", e);
			throw new UnsupportedMediumException(e.getMessage());
		}
	}

	/**
	 * Get the formatter for the given id and locale. Formatters can be shared
	 * among instances.
	 * 
	 * @param id
	 *            Questionnaire id
	 * @param cfg
	 *            configuration for creating the formatter
	 * @return the formatter created.
	 * @throws Exception
	 *             whenever the formatter can't be created
	 */
	private Formatter getFormatter(final URI id, final Configuration cfg,
			final Map<String, String> options) throws Exception {

		if (!this.formatters.containsKey(id)) {
			LOGGER.fine("Creating new formatter");
			Formatter formatter = (Formatter) Class.forName(
					cfg.getString("formatter.class")).newInstance();
			formatter.init(cfg);
			this.formatters.put(id, formatter);
		}

		return this.formatters.get(id);
	}

	/**
	 * Create a statemanager for this model.
	 * 
	 * @param m
	 *            Model
	 * @param i
	 *            Instance
	 * @param rCfg
	 *            Rendering info
	 * @param cfg
	 *            Configuration resource
	 * @return Statemanager created
	 * @throws Exception
	 *             when the statemanager can't be created.
	 */
	private StateManager createStateManager(final Configuration cfg,
			final RenderConfig rCfg, final Model model, final Instance instance)
			throws Exception {

		StateManager mgr = (StateManager) Class.forName(
				cfg.getString("statemanager.class",
						"com.w20e.socrates.process.DefaultStateManager"))
				.newInstance();

		mgr.init(cfg, rCfg, model, instance);

		return mgr;
	}
}
