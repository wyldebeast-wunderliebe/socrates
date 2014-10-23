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

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class Option {

    /**
     * Hold value for the option.
     */
    private String value;

    /**
     * Hold label.
     */
    private String label;

    /**
     * Hold hint.
     */
    private String hint = "";

    /**
     * Create Option.
     * 
     * @param optionValue
     *            value for this option
     * @param optionLabel
     *            label for this option
     */
    public Option(final String optionValue, final String optionLabel) {

        this.value = optionValue;
        this.label = optionLabel;
    }

    /**
     * Creator for digester.
     */
    public Option() {

        this.value = null;  
        this.label = "";
    }   

    /**
     * @return Returns the label.
     */
    public final String getLabel() {

        return this.label;
    }

    /**
     * @param optionLabel
     *            The label to set.
     */
    public final void setLabel(final String optionLabel) {

        this.label = optionLabel;
    }

    /**
     * @return Returns the name.
     */
    public final String getValue() {

        return this.value;
    }

    /**
     * @param optionValue
     *            The value to set.
     */
    public final void setValue(final String optionValue) {

        this.value = optionValue;
    }

    /**
     * @return Returns the hint.
     */
    public final String getHint() {

        return this.hint;
    }

    /**
     * @param newHint
     *            The hint to set.
     */
    public final void setHint(final String newHint) {

        this.hint = newHint;
    }
}
