/*
 * File      : TestActionExecException.java
 * Classname : TestActionExecException
 * Author    : Duco Dokter
 * Date      : 13 Jan 2005
 * Version   : $Revision: 1.1 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.workflow;

import junit.framework.TestCase;

public class TestActionExecException extends TestCase {

	private ActionExecException exception;

	public TestActionExecException(final String name) {
		super(name);
	}

	@Override
	public void setUp() {

		this.exception = new ActionExecException();
	}

	public void testGetMessage() {

		assertNull(this.exception.getMessage());

		this.exception = new ActionExecException("pipo");

		assertEquals(this.exception.getMessage(), "pipo");
	}

}
