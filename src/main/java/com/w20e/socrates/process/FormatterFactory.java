package com.w20e.socrates.process;

import java.util.HashMap;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.formatting.Formatter;

public final class FormatterFactory {
	
	private HashMap<String, Formatter> formatters;
	
	private static FormatterFactory factory;

	private FormatterFactory() {
		this.formatters = new HashMap<String, Formatter>();
	}
	
	public static synchronized FormatterFactory getInstance() {

		if (factory == null) {
			factory = new FormatterFactory();
		}
		
		return factory;
	}
	
	/**
	 * Get the formatter for the given id and locale. Formatters can be shared
	 * among instances.
	 *
	 * @param id Questionnaire id
	 * @param cfg configuration for creating the formatter
	 * @return the formatter created.
	 * @throws Exception whenever the formatter can't be created
	 */
	public Formatter getFormatter(final String id, final Configuration cfg)
			throws Exception {

		if (!this.formatters.containsKey(id)) {
			Formatter formatter = (Formatter) Class.forName(
					cfg.getString("formatter.class")).newInstance();
			formatter.init(cfg);
			this.formatters.put(id, formatter);
		}

		return this.formatters.get(id);
	}
}
