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
 * File      : RenderItem.java
 * Classname : RenderItem
 * Author    : helmantel
 * Date      : Jan 28, 2005
 * Version   : $Revision: 1.1 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.rendering;



/**
 * @author D.A.Dokter A base control.
 */
public abstract class ControlImpl extends RenderableImpl implements Control {

    /**
     * Default serial id.
     */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

    /**
     * Hold help.
     */
    private String help = "";

    /**
     * Alert in case of errors.
     */
    private String alert = "";

    /**
	 * Control label. Default is the empty string.
	 */
	private Label label = Label.EMPTY;


	/**
	 * Hold the control hint. Default is set to the empty string.
	 */
	private String hint = "";

    /**
     * Hold the control type.
     */
    private String type;

    /**
     * Provide the bind expression glue.
     */
    private String bind = "";

    /**
     * Allow for convenient non argument constructor. This will generate a unique id.
     */
    public ControlImpl() {

        super();
    }
    
    /**
     * Constructor taking a unique id.
     * 
     * @param newId
     */
    public ControlImpl(String id) {

        super(id);
    }

    /**
     * Get the bind string.
     * 
     * @return bind expression
     */
    @Override
	public String getBind() {
        return this.bind;
    }

    /**
     * Set the bind expression.
     * 
     * @param newBind
     *            bind expression
     */
    public void setBind(final String newBind) {

    	this.bind = newBind;
    }

    /**
     * @return Returns the alert.
     */
    @Override
	public final String getAlert() {

        return this.alert;
    }

    /**
     * @param newAlert
     *            The alert to set.
     */
    public final void setAlert(final String newAlert) {

        this.alert = newAlert;
    }

    /**
     * Get the set help text.
     * 
     * @return Returns the help.
     */
    @Override
	public final String getHelp() {

        return this.help;
    }

    /**
     * Set the help text for this item.
     * 
     * @param newHelp
     *            The help to set.
     */
    public final void setHelp(final String newHelp) {

        this.help = newHelp;
    }

    /**
     * Set the control's type.
     * 
     * @param t
     *            Type for this control.
     */
    public final void setType(String t) {

        this.type = t;
    }

    /**
     * Return the control's type.
     */
    @Override
	public final String getType() {

    	if (this.type != null) {
    		return this.type;
    	} else {
    		return super.getType();
    	}
    }

	/**
	 * Return this item's hint.
	 */
	public String getHint() {

		return this.hint;
	}

	/**
	 * Return this item's label.
	 */
	public Label getLabel() {

		return this.label;
	}

	/**
	 * Set the hint.
	 * @param newHint
	 */
	public void setHint(final String newHint) {

		this.hint = newHint;
	}

	/**
	 * Set the label.
	 * @param newLabel
	 */
	public void setLabel(final Label newLabel) {
		
		this.label = newLabel;
	}

	/**
	 * Set the label.
	 * @param newLabel
	 */
	public void setLabel(final String newLabel) {
		
		this.label = new Label(newLabel);
	}

	public String toString() {
		
		return getType() + "[@id=" + getId() + "; @bind=" + getBind() + "]";
	}
}
