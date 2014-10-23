package com.w20e.socrates.process;

import java.io.OutputStream;
import java.net.URI;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.formatting.Formatter;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.StateManager;
import com.w20e.socrates.workflow.ProcessContext;

/**
 * Define the context needed for Runner instances. The context holds everything
 * that is needed in running a questionnaire.
 * 
 * @author dokter
 */
public interface RunnerContext extends ProcessContext {

	/**
	 * Return the data in the context. This is used as a bridge between user
	 * provided input and the back end procedures.
	 * 
	 * @return
	 */
	Map<String, Object> getData();

	/**
	 * Return the instance for this context.
	 * 
	 * @return instance.
	 */
	Instance getInstance();

	/**
	 * Return the Model used within this context.
	 * 
	 * @return
	 */
	Model getModel();

	/**
	 * What formatter are we using?
	 */
	Formatter getFormatter();

	/**
	 * Runner locale config.
	 * 
	 * @return
	 */
	Locale getLocale();

	/**
	 * Return id for questionnaire.
	 * 
	 * @return
	 */
	URI getQuestionnaireId();

	/**
	 * Get the context's statemanager.
	 */
	StateManager getStateManager();

	/**
	 * Get the output stream used for this questionnaire.
	 */
	OutputStream getOutputStream();

	/**
	 * Get rendering configuration.
	 */
	RenderConfig getRenderConfig();

	/**
	 * Get base configuration.
	 */
	Configuration getConfiguration();
}
