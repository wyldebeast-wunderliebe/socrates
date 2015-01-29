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
    private Translatable help = TranslatableImpl.EMPTY;

    /**
     * Alert in case of errors.
     */
    private Translatable alert = TranslatableImpl.EMPTY;

    /**
	 * Control label. Default is the empty string.
	 */
	private Translatable label = TranslatableImpl.EMPTY;


	/**
	 * Hold the control hint. Default is set to the empty string.
	 */
	private Translatable hint = TranslatableImpl.EMPTY;

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
	public final Translatable getAlert() {

        return this.alert;
    }

    /**
     * @param newAlert
     *            The alert to set.
     */
    public final void setAlert(final Translatable newAlert) {

        this.alert = newAlert;
    }

	/**
	 * Set the alert.
	 * @param newAlert
	 */
	public void setAlert(final String newAlert) {

		this.alert = new TranslatableImpl(newAlert);
	}

    /**
     * Get the set help text.
     * 
     * @return Returns the help.
     */
    @Override
	public final Translatable getHelp() {

        return this.help;
    }

    /**
     * Set the help text for this item.
     * 
     * @param newHelp
     *            The help to set.
     */
    public final void setHelp(final Translatable newHelp) {

        this.help = newHelp;
    }
    
	/**
	 * Set the help text
	 * @param newHelp
	 */
	public void setHelp(final String newHelp) {

		this.help = new TranslatableImpl(newHelp);
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
	public Translatable getHint() {

		return this.hint;
	}

	/**
	 * Return this item's label.
	 */
	public Translatable getLabel() {

		return this.label;
	}

	/**
	 * Set the hint.
	 * @param newHint
	 */
	public void setHint(final Translatable newHint) {

		this.hint = newHint;
	}

	
	/**
	 * Set the hint.
	 * @param newHint
	 */
	public void setHint(final String newHint) {

		this.hint = new TranslatableImpl(newHint);
	}

	/**
	 * Set the label.
	 * @param newLabel
	 */
	public void setLabel(final Translatable newLabel) {
		
		this.label = newLabel;
	}

	/**
	 * Set the label.
	 * @param newLabel
	 */
	public void setLabel(final String newLabel) {
		
		this.label = new TranslatableImpl(newLabel);
	}

	public String toString() {
		
		return getType() + "[@id=" + getId() + "; @bind=" + getBind() + "]";
	}
}
