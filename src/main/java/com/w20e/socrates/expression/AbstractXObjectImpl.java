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
 * File      : XObjectImpl.java
 * Classname : XObjectImpl
 * Author    : Duco Dokter
 * Date      :
 * Version   : $Id: XObjectImpl.java,v 1.13 2006/11/23 10:48:28 dokter Exp $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.expression;

/**
 * Implementation class for XObject.
 */
public abstract class AbstractXObjectImpl extends AbstractExpressionImpl
		implements XObject {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Evaluate the XObject to an XObject. The default implementation is to
	 * return itself.
	 * 
	 * @return the XObject evaluation of this object.
	 */
	public final XObject eval() {

		return this;
	}

	/**
	 * Override hashCode by using hashCode of embedded object.
	 */
	public final int hashCode() {

		return this.eval().toObject().hashCode();
	}
}
