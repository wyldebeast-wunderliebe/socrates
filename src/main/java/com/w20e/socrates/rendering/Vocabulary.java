package com.w20e.socrates.rendering;

import java.util.Collection;

/**
 * Marker interface
 * @author dokter
 *
 */
public interface Vocabulary {

	public Collection<Option> getOptions();

	public Collection<Option> getOptions(String refvalue);
}
