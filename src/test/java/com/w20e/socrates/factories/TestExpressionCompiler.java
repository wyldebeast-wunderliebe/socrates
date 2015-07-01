/*
 * Created on Mar 11, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.factories;

import java.text.SimpleDateFormat;

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.jxpath.ri.Compiler;
import org.apache.commons.jxpath.ri.Parser;

import com.w20e.socrates.expression.Eval;
import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.XVar;
import com.w20e.socrates.model.ExpressionCompiler;

/**
 * @author dokter
 */
public class TestExpressionCompiler extends TestCase {

	private Compiler compiler;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {

		super.setUp();
		this.compiler = ExpressionCompiler.getInstance();
	}

	private Expression createExpression(final String expr) {
		return (Expression) Parser.parseExpression(expr, this.compiler);
	}

	public void testCreateExpression() {

		//try {
			assertTrue(this.createExpression("true()").toBoolean());
			assertTrue(this.createExpression("'true'").toBoolean());
			assertFalse(this.createExpression("false()").toBoolean());
			assertTrue(this.createExpression("2").toBoolean());
			assertTrue(this.createExpression("2 = 2").toBoolean());
			assertTrue(this.createExpression("'2' = '2'").toBoolean());
			assertTrue(this.createExpression("2 = '2'").toBoolean());
			assertTrue(this.createExpression("'2' = 2").toBoolean());
			assertFalse(this.createExpression("2 != 2").toBoolean());
			assertFalse(this.createExpression("'2' != 2").toBoolean());
			assertFalse(this.createExpression("0").toBoolean());
			assertEquals(this.createExpression("1 + 5").eval()
					.toString(), "6.0");
			assertEquals(this.createExpression("(1 + 5)").eval()
					.toString(), "6.0");
			assertEquals(this.createExpression("1 - 5").eval()
					.toString(), "-4.0");
			assertEquals(this.createExpression("(10 - 5) * 3").eval()
					.toString(), "15.0");
			assertEquals(this.createExpression("10 - 5 * 3").eval()
					.toString(), "-5.0");
			assertEquals(this.createExpression("4 div 2").eval()
					.toString(), "2.0");
			assertEquals(this.createExpression("4 mod 3").eval()
					.toString(), "1.0");
			assertTrue(this.createExpression("0 or 5").eval()
					.toBoolean());
			assertTrue(this.createExpression("0 or 0 or 5").eval()
					.toBoolean());
			assertFalse(this.createExpression("0 and 5").eval()
					.toBoolean());
			assertFalse(this.createExpression("5 and 0").eval()
					.toBoolean());
			assertEquals(this.createExpression("/a/b").toString(),
					"/a/b");
			assertEquals(this.createExpression("/a/b").getClass(),
					XVar.class);
			assertTrue(this.createExpression("true() or true()")
					.toBoolean());
			assertTrue(this.createExpression("true() or false()")
					.toBoolean());
			assertFalse(this.createExpression("false() or false()")
					.toBoolean());
			assertFalse(this.createExpression(
					"false() or false() or false()").toBoolean());
			assertTrue(this.createExpression(
					"('yy' = 'xx') or 'xx' = 'xx'").toBoolean());
			assertEquals(this.createExpression("1 + 2 + 3 + 4 + 5")
					.eval().toString(), "15.0");
			assertEquals("54.", this.createExpression("exp(4)").eval()
					.toString().substring(0, 3));
			assertEquals(4, this.createExpression("round(4.03)").eval()
					.toNumber().intValue());
			assertEquals(4.0, this.createExpression("round(4.03, 1)")
					.eval().toNumber().doubleValue());
			assertEquals(4.03, this.createExpression("round(4.03, 2)")
					.eval().toNumber().doubleValue());
			assertEquals(
					"4.04",
					this.createExpression("(round(100 * 4.03894)) div 100")
							.eval().toString());
			assertEquals("4.0", this.createExpression("max(4, 0)")
					.eval().toString());
			assertEquals("0.0", this.createExpression("min(4, 0)")
					.eval().toString());
			assertEquals("-16.0", this.createExpression("-8 - 8")
					.eval().toString());
			assertEquals("10 * 5",
					this.createExpression("eval('10 * 5')").toString());
			assertTrue(this.createExpression("eval('10 * 5')") instanceof Eval);
			assertFalse(this.createExpression("(1 <= (0 - 2000)) and (1 >= (0 - 2400))")
					.eval().toBoolean());
			assertFalse(this.createExpression("0 or ((10 != 10) and (10 != 18))")
					.eval().toBoolean());

			assertTrue(this.createExpression("not ('2000' > '2003')")
					.eval().toBoolean());
			assertTrue(this.createExpression("not(2000 > 2003)").eval()
					.toBoolean());

			assertFalse(this.createExpression("not ('2000' < '2003')")
					.eval().toBoolean());
			assertFalse(this.createExpression("not(2000 < 2003)")
					.eval().toBoolean());

			assertFalse(this.createExpression(
							"600 >= 400 and 600 <= 499 or 600 >= 500 and 600 <= 599")
					.eval().toBoolean());

			// If/else test
			assertEquals("5", this.createExpression("test(0, 4, 5)")
					.eval().toString());
			assertEquals("4", this.createExpression("test(1, 4, 5)")
					.eval().toString());
			assertEquals("4", this.createExpression("test(1=1, 4, 5)")
					.eval().toString());
			assertEquals("5", this.createExpression("test(1=0, 4, 5)")
					.eval().toString());

			// More complex if/elif
			assertEquals(
					"b",
					this.createExpression(
									"test(false(), 4, test(false(), 'a', 'b'))")
							.eval().toString());
			assertEquals(
					"6",
					this.createExpression(
									"test(0,1,test(0,2,test(0,3,test(0,5,6))))")
							.eval().toString());

			// Date thingies
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

			assertEquals("-pipo-",
					this.createExpression("format('-%s-', 'pipo')")
							.eval().toString());
			assertEquals(fmt.format(new Date()),
					this.createExpression("format('%tY', datetime())")
							.eval().toString());

			// Mapping
			assertEquals(
					"1",
					this.createExpression(
									"map('x','x',1,'y',2,'z',4,'a',6,'b',7)")
							.eval().toString());
			assertEquals(
					"1",
					this.createExpression(
									"map(12,12,1,13,2,14,4,15,6,16,7)").eval()
							.toString());
			assertEquals(
					"6",
					this.createExpression(
									"map('a','x',1,'y',2,'z',4,'a',6,'b',7)")
							.eval().toString());
			assertEquals(
					"7",
					this.createExpression(
									"map('b','x',1,'y',2,'z',4,'a',-0.1,'b',7)")
							.eval().toString());
			assertEquals(
					"9876543219876512",
					this.createExpression(
									"map('b','a',1234567890123456,'b',9876543219876512)")
							.eval().toString());

			// random
			assertTrue(this.createExpression("random(2, 5)").eval()
					.toNumber().intValue() >= 2);

			// sum
			assertEquals("7.0", this.createExpression("sum(1, 2, 3, 1)").eval().toString());

			// num
			assertEquals(666, this.createExpression("num('666')")
					.eval().toNumber().intValue());

			assertTrue(this.createExpression("matches('6666AA', '.{6}')").eval()
					.toBoolean());
			assertTrue(this.createExpression("matches('6666AA', '[0-9]{4}[A-Z]{2}')")
					.eval().toBoolean());

			assertEquals("5.0", this.createExpression("ceil(4.3)")
					.eval().toString());
			assertEquals("5.0", this.createExpression("ceil(4.8)")
					.eval().toString());
			assertEquals("4.0", this.createExpression("floor(4.3)")
					.eval().toString());
			assertEquals("4.0", this.createExpression("floor(4.8)")
					.eval().toString());

			assertEquals("mamaloe",
					this.createExpression("substr('mamaloe')").eval()
							.toString());
			assertEquals("maloe",
					this.createExpression("substr('mamaloe', 2)")
							.eval().toString());
			assertEquals("mal",
					this.createExpression("substr('mamaloe', 2, 5)")
							.eval().toString());

			// sample
			assertTrue(this.createExpression("sample(3, 1, 2, 3)")
					.eval().toString().indexOf("1") > -1);
			
			// get
			assertEquals("1", this.createExpression("get(0, 1, 2, 3)")
					.eval().toString());
			// get
			assertEquals("2", this.createExpression("get(1, 1, 2, 3)")
					.eval().toString());
			// get
			assertEquals("3", this.createExpression("get(2, 1, 2, 3)")
					.eval().toString());
			
			// in
			assertTrue(this.createExpression("in(3, 1, 2, 3)").eval()
					.toBoolean());

		//} catch (Exception e) {
		//	e.printStackTrace();
		//	fail(e.getMessage());
		//}
	}
}
