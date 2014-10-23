package com.w20e.socrates.data;

import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XObject;

import junit.framework.TestCase;

public class TestTypeChecker extends TestCase {

	
	@Before
	public void setUp() throws Exception {

		TypeChecker.clear();
	}

	@Test
	public void testConstructor() {

		try {
			Class.forName(TypeChecker.class.getName()).getConstructors()[0]
					.newInstance();
			fail("This is impossible");
		} catch (Exception e) {
			// as expected...
		}
	}

	public void testEvaluate() {

		try {
			XObject value = TypeChecker.evaluate(TypeChecker.class, "");
			assertEquals("", value.toString());
		} catch (Exception e) {
			fail("This is impossible");
		}

		try {
			XObject value = TypeChecker.evaluate(DataType.class, "");
			assertEquals("", value.toString());
		} catch (Exception e) {
			fail("This is impossible");
		}

		// try {
		assertEquals(Undef.UNDEF, TypeChecker.evaluate(XSInteger.class, ""));
		// fail("Shouldn't be possible");
		// } catch (TransformationException e1) {
		// } catch (RestrictionViolation e1) {
		// TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		try {
			assertEquals(false, TypeChecker.evaluate(XSBoolean.class, "")
					.toBoolean());
			assertEquals(false, TypeChecker.evaluate(XSBoolean.class, "false")
					.toBoolean());
			assertEquals(true, TypeChecker.evaluate(XSBoolean.class, "falszze")
					.toBoolean());
			assertEquals(true, TypeChecker.evaluate(XSBoolean.class, "true")
					.toBoolean());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	
	public void testValidate() {
		try {
			TypeChecker.validate(TypeChecker.class, "");
			fail("This is impossible");
		} catch (Exception e) {
			// as expected...
		}

		try {
			TypeChecker.validate(DataType.class, "");
			fail("This is impossible");
		} catch (Exception e) {
			// as expected...
		}

		try {
			TypeChecker.validate(XSBoolean.class, "");			
		} catch (Exception e) {
			fail();
		}
		
		try {
				TypeChecker.validate(XSAmount.class, "pipo");	
				fail();	
		} catch (Exception e) {
		}
		
	}
	
	public void testEvaluateLexical() {

		try {
			TypeChecker.evaluateLexical(TypeChecker.class, "", null);
			fail("This is impossible");
		} catch (Exception e) {
			// as expected...
		}

		try {
			TypeChecker.evaluateLexical(DataType.class, "", null);
			fail("This is impossible");
		} catch (Exception e) {
			// as expected...
		}

		try {
			assertEquals("false", TypeChecker.evaluateLexical(XSBoolean.class,
					"", null));
			assertEquals("true", TypeChecker.evaluateLexical(XSBoolean.class,
					"true", null));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
