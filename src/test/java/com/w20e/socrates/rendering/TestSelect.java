package com.w20e.socrates.rendering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XString;

public class TestSelect {

    private Select select;

    @Before
    public void setUp() throws Exception {

        this.select = new Select("sel");
        this.select.addOption(new Option("val0", "lbl0"));
        this.select.addOption(new Option("val1", "lbl1"));
        this.select.addOption(new Option("val2", "lbl2"));
    }

    @Test
    public void testProcessInput() {

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("sel", "");

        assertNull(this.select.processInput(data));

        data.put("sel", null);

        assertNull(this.select.processInput(data));

        data.clear();

        try {
            this.select.processInput(data);
            fail("An exception should have been thrown");
        } catch (RuntimeException re) {
            // whatever.
        }

        data.put("sel", "moi");

        assertEquals("moi", this.select.processInput(data));
    }

    @Test
    public void testGetOptions() {

        assertEquals(3, this.select.getOptions().size());

        OptionList options = new OptionList();

        this.select.addOptions(options);
        assertEquals(options.getOptions(), this.select.getOptions());
    }

    @Test
    public void testAppearance() {

        this.select.setFullAppearance();
        assertEquals("FULL", this.select.getProperty("appearance"));
        this.select.setMinimalAppearance();
        assertEquals("MINIMAL", this.select.getProperty("appearance"));
        this.select.setCompactAppearance();
        assertEquals("COMPACT", this.select.getProperty("appearance"));

    }

    @Test
    public void testIsMultiple() {

        assertFalse(this.select.isMultiple());
        this.select.setMultiple(true);
        assertTrue(this.select.isMultiple());
        this.select.setMultiple(false);
        assertFalse(this.select.isMultiple());
    }

    @Test
    public void testGetDisplayValue() {

        assertEquals("", this.select.getDisplayValue(null, null, null).toString());
        assertEquals("", this.select.getDisplayValue(Undef.UNDEF, null, null).toString());
        assertEquals("lbl1", this.select.getDisplayValue(new XString("val1"), null,
                null).toString());
        assertEquals("lbl1", this.select.getDisplayValue(new XString("val1"), null,
                null).toString());
        assertEquals("val666", this.select.getDisplayValue(new XString("val666"), null,
                null).toString());
        
        assertEquals("", this.select.getDisplayValue(null, null, null));
        assertEquals("", this.select.getDisplayValue(Undef.UNDEF, null, null).toString());
        
        OptionList options0 = new OptionList();
        options0.add("val0", new Option("val0", "label0"));
        options0.add("val1", new Option("val1", "label1"));
        options0.setId("ref0");
        
        OptionList options1 = new OptionList();
        options1.add("val2", new Option("val2", "label2"));
        options1.add("val3", new Option("val3", "label3"));
        options1.setId("ref1");

        this.select.addOptions(options0);
        this.select.addOptions(options1);
        
        assertEquals("label0", this.select.getDisplayValue(new XString("val0"), null, null, "ref0").toString());
        assertEquals("label2", this.select.getDisplayValue(new XString("val2"), null, null, "ref1").toString());
        assertEquals("label2", this.select.getDisplayValue("val2", null, null, "ref1").toString());
        
        assertEquals("pipo", this.select.getDisplayValue(new XString("pipo"), null, null).toString());

    }
    
    @Test
    public void testGetOptionsString() {

        OptionList options = new OptionList();
        options.setId("bar");
        OptionList options2 = new OptionList();
        options2.setId("foo");
        this.select.addOptions(options);
        this.select.addOptions(options2);
        assertEquals(options.getOptions(), this.select.getOptions("bar"));        
    }

}
