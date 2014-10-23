package com.w20e.socrates.rendering;

import java.util.Collection;

/**
 * Marker interface
 * @author dokter
 *
 */
public interface Vocabulary {

    @SuppressWarnings("rawtypes")
	public Collection getOptions();

    @SuppressWarnings("rawtypes")
	public Collection getOptions(String refvalue);

	public String getNodeRef();

}
