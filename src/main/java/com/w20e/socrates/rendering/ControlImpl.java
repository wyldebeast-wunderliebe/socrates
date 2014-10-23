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
     * Hold the control type.
     */
    private String type;

    /**
     * Provide the bind expression glue.
     */
    private String bind = "";

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
    public final String getBind() {
        return this.bind;
    }

    /**
     * Set the bind expression.
     * 
     * @param newBind
     *            bind expression
     */
    public final void setBind(final String newBind) {
        this.bind = newBind;
    }

    /**
     * @return Returns the alert.
     */
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
    public final String getType() {
        return this.type;
    }

}
