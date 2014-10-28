/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.rendering;

import java.util.Collection;

/**
 * @author dokter 
 * The Matrix is a combination of group and select: for a given set of options, the same
 * set of possible answers is shown. The select is implemented as composition.
 */
public class Matrix extends GroupImpl implements Vocabulary {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	private SelectionControlImpl select;
	
	/**
	 * Construct new Matrix.
	 * @param id
	 */
	public Matrix(String id, OptionList newOptions) {
		super(id);

		this.select = new SelectionControlImpl(id);
		this.setOptions(newOptions);
	}

	/**
	 * Construct new Matrix.
	 * @param id
	 */
	public Matrix(String id) {
		super(id);

		this.select = new SelectionControlImpl(id);
	}

	/**
	 * Add an option to the item.
	 * 
	 * @param opt
	 *            option to add.
	 */
	public final void addOption(final Option opt) {
		
		this.select.addOption(opt);
	}

	/**
	 * Set the OptionList to use for options.
	 */
	public final void setOptions(OptionList newOptions) {
		
		this.select.addOptions(newOptions);
	}
	
	/**
	 * Get all options for this item.
	 * 
	 * @return options.
	 */
	@Override
	public final Collection<Option> getOptions() {
		
		return this.select.getOptions();
	}

	/**
	 * Get all options given a reference value
	 * @param refvalue
	 * @return
	 */
    @Override
	public Collection<Option> getOptions(String refvalue) {

    	return this.select.getOptions(refvalue);
    }
    
	/**
	 * Implement vocabulary like behaviour for the optionlist.
	 * 
	 * @param value
	 *            value to get label for.
	 * @return String (nice) presentation for this option.
	 */
	public final String getOptionLabel(String value) {

		return this.select.getOptionLabel(value);
	}

    /**
     * Return node reference descriptor for the node upon which this control
     * depends for it's vocabulary.
     * @return
     */
    @Override
	public String getNodeRef() {

        return this.getProperty("noderef");
    }

}
