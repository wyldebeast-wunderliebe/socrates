package com.w20e.socrates.submission;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNoneSubmissionHandler {

	@Test
	public void testSubmit() {

		NoneSubmissionHandler handler = new NoneSubmissionHandler();
		
		try {
			handler.submit(null, null, null);
		} catch (SubmissionException e) {
			fail("Shouldn;t have failed doing nothing...");
		}
	}

}
