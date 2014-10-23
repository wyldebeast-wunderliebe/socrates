/*
 * File      : TestRunnerImpl.java
 * Classname : TestRunnerImpl
 * Author    : Duco Dokter
 * Created   : Fri Jan 21 10:23:42 2005
 * Version   : $Revision: 1.34 $
 * Copyright : Wyldebeast & Wunderliebe
 * License   : GPL
 */

package com.w20e.socrates.process;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import junit.framework.TestCase;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationBuilder;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.expression.XBoolean;
import com.w20e.socrates.formatting.FormatException;
import com.w20e.socrates.formatting.Formatter;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.ModelImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.model.NodeValidator;
import com.w20e.socrates.model.SubmissionImpl;
import com.w20e.socrates.rendering.Control;
import com.w20e.socrates.rendering.ControlImpl;
import com.w20e.socrates.rendering.Input;
import com.w20e.socrates.rendering.RenderConfig;
import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.RenderStateImpl;
import com.w20e.socrates.rendering.Renderable;
import com.w20e.socrates.rendering.StateManager;
import com.w20e.socrates.submission.HandlerManager;
import com.w20e.socrates.submission.SubmissionHandler;
import com.w20e.socrates.submission.XMLFileSubmissionHandler;
import com.w20e.socrates.workflow.ActionResultImpl;
import com.w20e.socrates.workflow.Failure;
import com.w20e.socrates.workflow.Mapping;
import com.w20e.socrates.workflow.ProcessAction;
import com.w20e.socrates.workflow.ProcessException;

public class TestRunnerImpl extends TestCase {

	private InstanceImpl instance = new InstanceImpl();

	private ModelImpl model;

	private RunnerImpl runner;

	private RunnerContextImpl ctx;

	public TestRunnerImpl(String name) {
		super(name);
	}

	public void setUp() {

		// Let's create a model, so as to test some complex
		// requirements.
		Node node0 = new NodeImpl("/a");
		Node node1 = new NodeImpl("/a/b");
		Node node2 = new NodeImpl("/a/b/c1");
		Node node3 = new NodeImpl("/a/b/c2");

		this.instance.addNode(node0);
		this.instance.addNode(node1);
		this.instance.addNode(node2);
		this.instance.addNode(node3);

		this.model = new ModelImpl();

		ItemProperties props0 = new ItemPropertiesImpl("/a");
		ItemProperties props1 = new ItemPropertiesImpl("/a/b");
		ItemProperties props2 = new ItemPropertiesImpl("/a/b/c1");
		ItemProperties props3 = new ItemPropertiesImpl("/a/b/c2");

		props0.setRequired(new XBoolean(true));
		props1.setRelevant(new XBoolean(false));

		this.model.addItemProperties(props0);
		this.model.addItemProperties(props1);
		this.model.addItemProperties(props2);
		this.model.addItemProperties(props3);

		try {
			SubmissionHandler handler = new XMLFileSubmissionHandler();
			HandlerManager.getInstance().register("file", handler);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		SubmissionImpl sub = new SubmissionImpl();
		try {
			sub.setAction(new URI("file:///./"));
		} catch (URISyntaxException e1) {
			fail("Error in setting URI");
		}

		this.model.setSubmission(sub);

		//
		// node0 is now required, node1 cannot have the value "pipo"
		//

		// Now configure the processor
		//
		ProcessAction act0 = new ActionTestImpl("a0");
		ProcessAction act1 = new ActionTestImpl("a1");
		ProcessAction act2 = new ActionTestImpl("a2");

		ProcessAction next = new Next("q0");
		ProcessAction render = new Render("q1");
		ProcessAction validate = new Validate("q2");
		ProcessAction submit = new Submit("q3");

		RenderConfigImpl cfg = new RenderConfigImpl();

		ControlImpl r0 = new Input("r0");
		r0.setBind("/a");

		ControlImpl r1 = new Input("r1");
		r1.setBind("/a/b");

		ControlImpl r2 = new Input("r2");
		r2.setBind("/a/b/c1");

		ControlImpl r3 = new Input("r3");
		r3.setBind("/a/b/c2");

		cfg.addItem(r0);
		cfg.addItem(r1);
		cfg.addItem(r2);
		cfg.addItem(r3);

		try {
			StateManager mgr = new StateManager0();
			mgr.init(null, cfg, this.model, this.instance);

			this.ctx = new RunnerContextImpl(new ByteArrayOutputStream(),
					new Formatter0(), mgr, this.model, this.instance, null);
		} catch (Exception e) {
			fail();
		}

		// Runner without workflow def.
		this.runner = new RunnerImpl(null);

		this.runner.setInitial(act0);

		this.runner.addMapping(new Mapping(render, validate,
				ActionResultImpl.WAIT));
		this.runner.addMapping(new Mapping(validate, render,
				ActionResultImpl.FAIL));
		this.runner
				.addMapping(new Mapping(validate, next, ActionResultImpl.OK));
		this.runner.addMapping(new Mapping(next, render, ActionResultImpl.OK));
		this.runner
				.addMapping(new Mapping(next, submit, ActionResultImpl.FAIL));

		this.runner.addMapping(new Mapping(act0, next, ActionResultImpl.OK));
		this.runner
				.addMapping(new Mapping(submit, act2, ActionResultImpl.FAIL));
		this.runner.addMapping(new Mapping(submit, act1, ActionResultImpl.OK));

		List<ProcessAction> finals = new Vector<ProcessAction>();

		finals.add(act2);
		finals.add(act1);

		this.runner.setFinals(finals);

		// the process is now as follows: start with a0, proceed to
		// questionnaire, after finishing the questionnaire, go to a1 if
		// the questionnaire has been finished ok, else go to a2.
	}

	public void testNext() {

		// Start with next loop, should fail when running required question

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("r0", "foo");

		try {
			this.runner.next(this.ctx);

			assertEquals("ok", this.ctx.getProperty("a0"));

			// We should now start with the questionnaire...
			// This should set the next state
			this.runner.next(this.ctx);

			// This should try rendering
			this.runner.next(this.ctx);

			assertEquals(((ByteArrayOutputStream) this.ctx.getOutputStream())
					.toString(), "r0");

			((ByteArrayOutputStream) (this.ctx).getOutputStream()).reset();

			// Let's not set the data, and see what happens (/a is required).
			this.runner.next(this.ctx);

			assertEquals(ActionResultImpl.FAIL, this.ctx.getResult().toString());

			assertEquals(
					((ValidationException) ((Failure) this.ctx.getResult())
							.getException()).getErrors().size(), 1);

			// we should have returned to rendering...
			this.runner.next(this.ctx);

			// set the data
			this.ctx.setData(data);

			// Should validate
			this.runner.next(this.ctx);

			assertEquals("foo", this.instance.getNode("/a").getValue()
					.toString());

			// next state
			this.runner.next(this.ctx);

			// should skip /a/b
			this.runner.next(this.ctx);

			assertEquals(((ByteArrayOutputStream) this.ctx.getOutputStream())
					.toString(), "r0r2");
			this.runner.next(this.ctx);
			this.runner.next(this.ctx);

			// Ok, let's finish the process
			this.runner.next(this.ctx);
			this.runner.next(this.ctx);
			this.runner.next(this.ctx);

			// Should have finished the model now...
			assertEquals(ActionResultImpl.FAIL, this.ctx.getResult().toString());

			// submission...
			this.runner.next(this.ctx);

			assertEquals(ActionResultImpl.OK, this.ctx.getResult().toString());
			this.runner.next(this.ctx);

			assertEquals(this.ctx.getProperty("a1"), "ok");
		} catch (ProcessException e) {
			fail(e.getMessage());
		} catch (InvalidPathExpression ipe) {
            fail(ipe.getMessage());
        }
	}

	public void testGetCreationTime() {

		Date earlier = new Date();
		RunnerImpl rnr = new RunnerImpl(null);
		Date later = new Date();
		assertTrue(earlier.compareTo(rnr.getCreationTime()) <= 0);
		assertTrue(later.compareTo(rnr.getCreationTime()) >= 0);
	}

	public void testRunnerOnlyModel() {

		ProcessAction next = new Next("q0");
		ProcessAction render = new Render("q1");
		ProcessAction validate = new Validate("q2");
		ProcessAction submit = new Submit("q3");

		try {
			this.runner = new RunnerImpl(null);

			this.runner.setInitial(next);

			this.runner.addMapping(new Mapping(render, validate,
					ActionResultImpl.WAIT));
			this.runner.addMapping(new Mapping(validate, render,
					ActionResultImpl.FAIL));
			this.runner.addMapping(new Mapping(validate, next,
					ActionResultImpl.OK));
			this.runner.addMapping(new Mapping(next, render,
					ActionResultImpl.OK));
			this.runner.addMapping(new Mapping(next, submit,
					ActionResultImpl.FAIL));

			List<ProcessAction> finals = new Vector<ProcessAction>();

			finals.add(submit);

			this.runner.setFinals(finals);

			// we should just have the model now.
			// This should set the next state
			this.runner.next(this.ctx);

			// try broken output
			try {
				this.ctx.setOutputStream(null);

				// This should try rendering
				this.runner.next(this.ctx);
				fail();
			} catch (Exception aee) {
				// As expected.
			}

			this.ctx.setOutputStream(new ByteArrayOutputStream());

			// try again...
			this.runner.next(this.ctx);

			assertEquals("r0", ((ByteArrayOutputStream) this.ctx
					.getOutputStream()).toString());

			((ByteArrayOutputStream) this.ctx.getOutputStream()).reset();

			// Let's not set the data, and see what happens (/a is required).
			this.runner.next(this.ctx);

			assertEquals(this.ctx.getResult().toString(), ActionResultImpl.FAIL);

			assertEquals(
					((ValidationException) ((Failure) this.ctx.getResult())
							.getException()).getErrors().size(), 1);

			// we should have returned to rendering...
			this.runner.next(this.ctx);

			Map<String, Object> data = new HashMap<String, Object>();

			data.put("r0", "foo");

			// set the data
			this.ctx.setData(data);

			// Should validate by now...
			this.runner.next(this.ctx);

			assertEquals(this.instance.getNode("/a").getValue().toString(),
					"foo");

			// next state
			this.runner.next(this.ctx);

			// rendering... should have skipped skip /a/b
			this.runner.next(this.ctx);

			assertEquals(((ByteArrayOutputStream) this.ctx.getOutputStream())
					.toString(), "r0r2");
			this.runner.next(this.ctx);
			this.runner.next(this.ctx);

			// Ok, let's finish the process
			this.runner.next(this.ctx);
			this.runner.next(this.ctx);
			this.runner.next(this.ctx);

			// submission
			this.runner.next(this.ctx);

			File file = new File(this.model.getSubmission().getAction()
					.getPath());

			assertTrue(file.exists());

			assertFalse(this.runner.hasNext(this.ctx));
		} catch (ProcessException e) {
			fail(e.getMessage());
		} catch (InvalidPathExpression ipe) {
		    fail(ipe.getMessage());
		}

		// Should be finished
		try {
			this.runner.next(this.ctx);
			fail();
		} catch (Exception e) {
			// As expected.
		}
	}

	public void testInit() {

		try {
			this.runner.init(new URL(""));
			fail();
		} catch (Exception e) {
			// ok
		}
	}
	
	public void testRunnerFromConfigFile() {

		try {
			this.runner = new RunnerImpl(new URL(
					"file:./target/test-classes/workflow.xml"));

			this.runner.next(this.ctx);

			assertEquals("ok", this.ctx.getProperty("a0"));

			// We should now start with the questionnaire...

			// This should set the next state
			this.runner.next(this.ctx);

			// This should try rendering
			this.runner.next(this.ctx);

			assertEquals(((ByteArrayOutputStream) this.ctx.getOutputStream())
					.toString(), "r0");

			((ByteArrayOutputStream) this.ctx.getOutputStream()).reset();
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (ProcessException pe) {
		    fail(pe.getMessage());
		}
	}

	private static class Formatter0 implements Formatter {

		public void init(Configuration cfg) {
			// We don't care.
		}

		public void format(Collection<Renderable> items, OutputStream out,
				RunnerContext context) throws FormatException {
			
		    if (out == null) {
		        throw new FormatException("Cannot write to outputstream null");
		    }
			PrintWriter writer = new PrintWriter(out);

			writer.print(((Control) items.iterator().next()).getId());
			writer.flush();
		}
	}

	private static class StateManager0 implements StateManager {

		Iterator<Renderable> i;

		RenderStateImpl curr;

		Instance inst;

		RenderConfigImpl cfg;

		Model m;

		public void init(Configuration cfg, RenderConfig newCfg, Model newM, Instance newInst) {
			this.cfg = (RenderConfigImpl) newCfg;
			this.m = newM;
			this.inst = newInst;
			this.i = this.cfg.getItems().iterator();
		}

		public RenderStateImpl next() throws NoSuchElementException {

			if (!this.i.hasNext()) {

				return null;
			}

			ArrayList<Renderable> items = new ArrayList<Renderable>();

			while (this.i.hasNext()) {
				Control rItem = (Control) this.i.next();
				Node n = null;

				try {
					n = this.inst.getNode(rItem.getBind());
				} catch (InvalidPathExpression e) {
					continue;
				}

				ItemProperties props = this.m.getItemProperties(n.getName());

				if (NodeValidator.isRelevant(props, this.inst, this.m)) {

					items.add(rItem);
					break;
				}
			}

			this.curr = new RenderStateImpl("foo", items);

			return this.curr;
		}

		public RenderState previous() {
			return null;
		}

		public RenderState current() {

			return this.curr;
		}

		public boolean setState(RenderState state) {
			return false;
			// whatever...
		}

		public boolean setStateById(String stateId) {
		    return false;
		    // whatever...
	    }

		public boolean hasNext() {
			return true;
		}

		public boolean hasPrevious() {
			return true;
		}

        @Override
        public int getProgressPercentage() {
            return 0;
        }
	}

	private static class RenderConfigImpl implements RenderConfig {

		private List<Renderable> items = new ArrayList<Renderable>();

		public void addItem(Renderable i) {
			this.items.add(i);
		}

		public List<Renderable> getItems() {
			return this.items;
		}

		public Renderable getItem(String id) {
			return null;
		}
	}
	
}
