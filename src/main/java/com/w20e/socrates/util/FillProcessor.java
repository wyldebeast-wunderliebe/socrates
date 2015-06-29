package com.w20e.socrates.util;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.expression.XObject;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.ItemPropertiesImpl;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.NodeValidator;
import com.w20e.socrates.rendering.Control;
import com.w20e.socrates.rendering.RenderConfig;

/**
 * Process fills in given string. For the moment, only straight node values are
 * supported.
 * 
 * @author dokter
 * 
 */
public final class FillProcessor {

    /**
     * Initialize this class' logging.
     */
    private static final Logger LOGGER = Logger
            .getLogger(FillProcessor.class.getName());

    /**
     * Regexp for determining fills.
     */
    public final static String REGEXP = "\\$\\{[^\\}]+\\}";

    /**
     * Regexp for determining fills.
     */
    public final static Pattern PATTERN = Pattern.compile(REGEXP);

    /**
     * Process fills for given string. If any given fill variable is not 
     * found, or no value is found, the variable is replaced with the
     * empty string.
     * 
     * @param str String to replace variables in
     * @param inst Instance to use for values
     * @param model Model for calculations
     * @param cfg Render configuration
     * @param locale Locale for locale specific lexical values
     * @return The processed string.
     */
    public static String processFills(String str, Instance inst,
            Model model, RenderConfig cfg, Locale locale) {

        Matcher m = PATTERN.matcher(str);
        StringBuffer buf = new StringBuffer();

        // Keep those declarations out of the loop...
        Node n;
        ItemProperties props;
        Control c;
        XObject val;
        String displayVal, expr;

        while (m.find()) {

            expr = m.group().substring(2, m.group().length() - 1);

            try {
                if (expr.endsWith(":raw")) {
                    expr = expr.substring(0, expr.length() - 4);
                    n = inst.getNode(expr);
                    displayVal = n.getValue().toString();
                } else if (expr.endsWith(":ctrl")) {
                	expr = expr.substring(0, expr.length() - 5);
                	 c = (Control) cfg.getItem(expr);	
                	 n = inst.getNode(c.getBind());

                     props = model.getItemProperties(expr);

                     if (props == null) {
                         props = new ItemPropertiesImpl(expr);
                     }

                     val = NodeValidator.getValue(n, props, model, inst);
                     
                	 displayVal = c.getDisplayValue(val.toObject(), props.getDatatype(), locale).toString();
                } else {
                    n = inst.getNode(expr);
                    props = model.getItemProperties(expr);

                    if (props == null) {
                        props = new ItemPropertiesImpl(expr);
                    }
                    
                    val = NodeValidator.getValue(n, props, model, inst);
                    
                    displayVal = val.toString();
                }
            } catch (Exception e) {
            	LOGGER.log(Level.WARNING, "Error in fill for " + expr, e);
                displayVal = "";
            }

            m.appendReplacement(buf, displayVal);
        }

        m.appendTail(buf);

        return buf.toString();
    }
    
    /**
     * Utility method to produce list of variables.
     * @return
     */
    public static void findFillRefErrors(String str, List<String> errors, Instance inst, RenderConfig cfg) {
    	
        Matcher matcher = PATTERN.matcher(str);

        // Keep those declarations out of the loop...
        Node node;
        Control ctrl;
        String expr;

        while (matcher.find()) {

            expr = matcher.group().substring(2, matcher.group().length() - 1);

            try {
                if (expr.endsWith(":raw")) {
                    expr = expr.substring(0, expr.length() - 4);
                    ctrl = (Control) cfg.getItem(expr);
                    node = inst.getNode(ctrl.getBind());
                } else {
                    ctrl = (Control) cfg.getItem(expr);
                    node = inst.getNode(ctrl.getBind());
                }
                
                if (node == null) {
                	errors.add(expr);
                }
            } catch (Exception e) {
            	errors.add(expr);
            }
        }
    }
}
