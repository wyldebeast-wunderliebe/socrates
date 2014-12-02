package com.w20e.socrates.process;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.data.TypeChecker;
import com.w20e.socrates.model.ConstraintViolation;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.NodeValidator;
import com.w20e.socrates.rendering.Control;
import com.w20e.socrates.rendering.Group;
import com.w20e.socrates.rendering.RenderState;
import com.w20e.socrates.rendering.Renderable;

/**
 * Handle data for state. This is done as follows: - Loop through items in the
 * render state - for any item - if it's irrelevant, skip it - if the item is
 * not in the data map, skip it - if the item is read-only, skip it - convert
 * the value in the map by calling the item's processInput method - convert the
 * value in the map by calling TypeChecker.evaluate - set the value of the Node
 * to the converted value, or to the original value if any conversion fails -
 * add node to nodes set to check for validation - loop through nodes returned
 * by setting data, for all nodes - validate by calling NodeValidator.validate -
 * if invalid, add error to ValidationException - if validation contains any
 * errors, throw it, otherwise return
 * 
 * @author dokter
 * 
 */
public final class DataHandler {

    /**
     * Privatize constructor.
     */
    private DataHandler() {
        // Empty constructor.
    }

    /**
     * Set the data for this runner from the key-value pairs in the Map given as
     * argument.
     * 
     * @param data
     *            a <code>Map</code> value
     * @param model
     *            Use this model for properties
     * @param inst
     *            an <code>Instance</code> value
     * @param state
     *            the current state
     * @exception ValidationException
     *                if an error occurs
     * @todo The value of the node should really be set to the plain value; the
     *       type should be used in validation only...
     */
    public static void setData(final Map<String, Object> data,
            final Model model, final Instance inst, final RenderState state)
            throws ValidationException {

        ValidationException dataExcp = new ValidationException();
        Map<String, Node> nodes = DataHandler.processNodes(inst, model, state
                .getItems(), data, dataExcp);

        // Now do validation...
        for (Map.Entry<String, Node> entry : nodes.entrySet()) {

            ItemProperties props = model.getItemProperties(entry.getValue()
                    .getName());

            // Nothing to set...
            if (props == null) {
                continue;
            }

            if (NodeValidator.isRelevant(props, inst, model)) {

                try {
                    NodeValidator
                            .validate(entry.getValue(), props, inst, model);
                } catch (ConstraintViolation cv) {
                    dataExcp.addError(entry.getValue().getName(), cv);
                } catch (Exception e) {
                    dataExcp
                            .addError(entry.getValue().getName(),
                                    new ConstraintViolation(
                                            ConstraintViolation.UNKNOWN, entry
                                                    .getKey()));
                }
            }
        }

        // Now, let's see whether we have collected any errors.
        if (dataExcp.getErrors().size() > 0) {
            throw dataExcp;
        }
    }

    /**
     * Find bound nodes for all render items and set data, if the node is not
     * found to be readonly. This method will recurse through all groups.
     * 
     * @param renderItems
     * @return list of nodes contained in the given list of render items.
     */
    private static Map<String, Node> processNodes(Instance inst, Model model,
            Collection<Renderable> renderItems, Map<String, Object> data,
            ValidationException dataExcp) {

        Map<String, Node> nodes = new HashMap<String, Node>();
        Node n;
        Object val;
        Object typedVal;
        ItemProperties props;

        for (Renderable r : renderItems) {
            if (r instanceof Group) {
                nodes.putAll(processNodes(inst, model, ((Group) r).getItems(),
                        data, dataExcp));
            } else if (r instanceof Control) {

                // If there's no data available, don't set.
                //					
                try {
                    n = inst.getNode(((Control) r).getBind());
                    props = model.getItemProperties(n.getName());

                    // If the node has a calculate property set, we may as well
                    // ignore it...
                    // If the value is readonly, we just ignore it!
                    // If the item is irrelevant, same same
                    if (props.getCalculate() == null
                            && NodeValidator.isRelevant(props, inst, model)
                            && !NodeValidator.isReadOnly(props, inst, model)) {

                        val = null;
                        typedVal = null;

                        try {
                            val = ((Control) r).processInput(data);

                            if (val != null) {
                                typedVal = TypeChecker.evaluate(
                                        props.getDatatype(), val).toObject();
                            }

                            if (typedVal != null) {
                                val = typedVal;
                            }

                        } catch (RuntimeException re) {
                            // tough... this may happen in processInput.
                        }

                        // We only set the value if it is not null...
                        
                        n.setValue(val);
                        nodes.put(r.getId(), n);
                    }

                } catch (InvalidPathExpression ipe) {
                    dataExcp.addError(((Control) r).getBind(), ipe);
                }
            }
        }

        return nodes;
    }

}
