package com.w20e.socrates.expression;

import junit.framework.TestCase;

import org.junit.Test;

public class TestFormat extends TestCase {

    DateTime dt;
    XNumber num;
    XString str;
    
    public void setUp() {

        Expression[] ops = {new XString("01-04-2009"), new XString("dd-MM-yyyy")};
        this.dt = new DateTime();
        this.dt.setOperands(ops);
        this.num = new XNumber(Integer.valueOf(20));
        this.str = new XString("pipo");
    }


    @Test
    public void testToString() {

        Format fmt = new Format();
        Expression[] args = {new XString("%s %s %s"), this.dt, this.num, this.str};
        
        fmt.setOperands(args);

        assertEquals("format(%s %s %s,datetime(01-04-2009,dd-MM-yyyy,),20,pipo,)", fmt.toString());
    }

    @Test
    public void testEval() {

        Format fmt = new Format();
        Expression[] args = {new XString("%tY"), this.dt};
        
        fmt.setOperands(args);

        assertEquals("2009", fmt.eval().toString());

        Expression args2[] = {new XString("%s %s"), this.num, this.str};

        fmt.setOperands(args2);

        assertEquals("20 pipo", fmt.eval().toString());        
    }

    @Test
    public void testToBoolean() {

        Format fmt = new Format();

        Expression[] args = {new XString("%tY"), this.dt};
        
        fmt.setOperands(args);

        assertTrue(fmt.toBoolean());
        
        Expression[] args2 = {new XString(""), this.dt};
        
        fmt.setOperands(args2);

        assertFalse(fmt.toBoolean());
        
    }

}
