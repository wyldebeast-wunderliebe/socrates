/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.submission;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.Submission;
import com.w20e.socrates.model.SubmissionImpl;

import java.net.URI;
import junit.framework.TestCase;

/**
 * @author dokter
 * 
 * @todo To change the template for this generated type comment go to Window -
 *       Preferences - Java - Code Style - Code Templates
 */
public class TestHandlerManager extends TestCase {

    private HandlerManager mgr;

    /**
     * Constructor for TestHandlerManager.
     * 
     * @param arg0
     */
    public TestHandlerManager(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
	protected void setUp() throws Exception {
        super.setUp();

        this.mgr = HandlerManager.getInstance();
    }

    public void testRegister() {

        Submission submission = new SubmissionImpl();
        InstanceImpl instance = new InstanceImpl();
        ModelImpl model = new ModelImpl();

        try {
            submission.setAction(new URI("file:./file.xml"));
            this.mgr.submit(instance, model, submission);
            fail();
        } catch (Exception e) {
            // As expected.
        }

        this.mgr.register("file", new FileSubmissionHandler());

        try {
            submission.setAction(new URI("file:./file.xml"));

            this.mgr.submit(instance, model, submission);
        } catch (Exception e) {
            fail();
        }

        try {
            submission.setAction(new URI("http:./file.xml"));
            this.mgr.submit(instance, model, submission);
            fail();
        } catch (Exception e) {
            // As expected.
        }

    }

    private static class FileSubmissionHandler implements SubmissionHandler {

        @Override
		public void submit(Instance data, Model model, Submission submission) {
            // We don't need this here.
        }
    }
}
