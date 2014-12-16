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

import java.util.Locale;
import java.util.Map;

/**
 * Control interface that defines a single control in a user interface.
 * The Control is the public part of a questionnaire and may be rendered as a
 * visible component, which may or may not be the usual setup, but it may also
 * be an audible component, when a questionnaire is taken over a voice-medium.
 * 
 * Specific attributes of the Control interface are taken from the XForms
 * concept of a GUI control.
 * 
 * @author dokter
 */
public interface Control extends Renderable {

    /**
     * Get the label for the control. This is usually the 'question' to be
     * asked.
     * 
     * @return label
     */
    Label getLabel();

    /**
     * Get the control's hint. This is usually displayed following the label,
     * and should provide basic help.
     * 
     * @return hint
     */
    String getHint();

    /**
     * Get the control's error message.
     * 
     * @return The error message, usually only when an actual error has
     *         occurred.
     */
    String getAlert();

    /**
     * Get the control help text.
     * 
     * @return Help text for control. The help text may be used when a user asks
     *         for additional help other than that directly provided by the
     *         label and hint.
     */
    String getHelp();

    /**
     * Get the node binding. This is the expression that binds the control to
     * one or more data nodes.
     */
    String getBind();

    /**
     * The control is responsible for processing the data it receives from a
     * front-end: only the control can know in what way data is rendered to some
     * user interface. If the data map doesn't hold any value for the control's
     * id, a runtime exception should be thrown; if the control can't handle the
     * input null should be returned.
     * 
     * @param data
     *            Map containing data that may (or may not) hold data relevant
     *            for the control
     * @param type
     *            Node type.
     * @throws RuntimeException
     */
    Object processInput(Map<String, Object> data);

    /**
     * Get the display value for the given XObject. This is the reverse of the
     * processInput method: it should format the underlying value into the user
     * interface value (lexical value). This may (or may not) involve the use of
     * a locale... For a Select control, the display value usually would be the
     * option label provided for the select, not the actual underlying value.
     * For an input that is bound to a node with a currency or numeric datatype,
     * the locale may be used to provide a currency symbol, or numeric
     * formatting.
     * 
     * @param value
     *            Object to be converted into lexical value
     * @param locale
     *            Locale used for formatting
     */
    Object getDisplayValue(Object value, Class<?> datatype, Locale locale);
}
