/*
 * Created on Mar 10, 2005
 *
 * @todo To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.w20e.socrates.model;


import junit.framework.TestCase;

import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.XNumber;
import com.w20e.socrates.model.ExpressionCompiler;


/**
 * @author dokter
 *
 * @todo To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestExpressionCompiler extends TestCase {

  private ExpressionCompiler factory;
  
  /*
   * @see TestCase#setUp()
   */
  protected void setUp() throws Exception {

    super.setUp();
    
    this.factory = ExpressionCompiler.getInstance();
  }

  /**
   * Constructor for TestExpressionFactory.
   * @param arg0
   */
  public TestExpressionCompiler(String arg0) {

    super(arg0);
  }

  public void testNumber() {

    assertEquals(this.factory.number("20.0").toString(), "20.0");
    assertEquals(this.factory.number("20").toString(), "20");
  }

  public void testLiteral() {

    assertEquals(this.factory.literal("xxx").toString(), "xxx");
  }

  public void testQname() {

    //@todo Implement qname().
  }
  

  /*
   * Class under test for Object minus(Object, Object)
   */
  public void testMinusObjectObject() {

	  assertEquals(0, ((Expression) this.factory.minus(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(5)))).eval().toNumber().intValue());
  }

  public void testMultiply() {

    assertTrue(((Expression) this.factory.multiply(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(4)))).eval().equals(new XNumber(new Float(20))));
  }

  public void testDivide() {

    assertTrue(((Expression) this.factory.divide(new XNumber(Integer.valueOf(20)), new XNumber(Integer.valueOf(4)))).eval().equals(new XNumber(new Float(5))));
  }

  public void testMod() {

	  assertEquals(1, ((Expression) this.factory.mod(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(2)))).eval().toNumber().intValue());
  }

  public void testLessThan() {

	  assertTrue(((Expression) this.factory.lessThan(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(10)))).eval().toBoolean());
  }

  public void testLessThanOrEqual() {

	  assertTrue(((Expression) this.factory.lessThanOrEqual(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(5)))).eval().toBoolean());
  }

  public void testGreaterThan() {

	  assertFalse(((Expression) this.factory.greaterThan(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(10)))).eval().toBoolean());
  }

  public void testGreaterThanOrEqual() {

	  assertTrue(((Expression) this.factory.greaterThanOrEqual(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(5)))).eval().toBoolean());
  }

  public void testEqual() {

	  assertTrue(((Expression) this.factory.equal(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(5)))).eval().toBoolean());
  }

  public void testNotEqual() {

	  assertFalse(((Expression) this.factory.notEqual(new XNumber(Integer.valueOf(5)), new XNumber(Integer.valueOf(5)))).eval().toBoolean());
  }

  /*
   * Class under test for Object minus(Object)
   */
  public void testMinusObject() {

    //@todo Implement minus().
  }

  public void testVariableReference() {

    //@todo Implement variableReference().
  }

  /*
   * Class under test for Object function(int, Object[])
   */
  public void testFunctionintObjectArray() {

    //@todo Implement function().
  }

  /*
   * Class under test for Object function(Object, Object[])
   */
  public void testFunctionObjectObjectArray() {

    //@todo Implement function().
  }

  public void testAnd() {

    //@todo Implement and().
  }

  public void testOr() {

    //@todo Implement or().
  }

  public void testUnion() {

    //@todo Implement union().
  }

  public void testNodeNameTest() {

    //@todo Implement nodeNameTest().
  }

  public void testNodeTypeTest() {

    //@todo Implement nodeTypeTest().
  }

  public void testProcessingInstructionTest() {

    //@todo Implement processingInstructionTest().
  }

  public void testStep() {

    //@todo Implement step().
  }

  public void testLocationPath() {

    //@todo Implement locationPath().
  }

  public void testExpressionPath() {

    //@todo Implement expressionPath().
  }

  public void testObject() {

    //@todo Implement Object().
  }

  public void testGetClass() {

    //@todo Implement getClass().
  }

  public void testHashCode() {

    //@todo Implement hashCode().
  }

  public void testEquals() {

    //@todo Implement equals().
  }

  public void testClone() {

    //@todo Implement clone().
  }

  public void testToString() {

    //@todo Implement toString().
  }

  public void testNotify() {

    //@todo Implement notify().
  }

  public void testNotifyAll() {

    //@todo Implement notifyAll().
  }

  /*
   * Class under test for void wait(long)
   */
  public void testWaitlong() {

    //@todo Implement wait().
  }

  /*
   * Class under test for void wait(long, int)
   */
  public void testWaitlongint() {

    //@todo Implement wait().
  }

  /*
   * Class under test for void wait()
   */
  public void testWait() {

    //@todo Implement wait().
  }

  public void testFinalize() {

    //@todo Implement finalize().
  }

}
