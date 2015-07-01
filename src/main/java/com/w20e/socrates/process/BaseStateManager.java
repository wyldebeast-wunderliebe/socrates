package com.w20e.socrates.process;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.InvalidPathExpression;
import com.w20e.socrates.model.ItemProperties;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.NodeValidator;
import com.w20e.socrates.model.XRefSolver;
import com.w20e.socrates.rendering.Control;
import com.w20e.socrates.rendering.Group;
import com.w20e.socrates.rendering.Renderable;
import com.w20e.socrates.rendering.Select;
import com.w20e.socrates.rendering.StateManager;

public abstract class BaseStateManager implements StateManager {

	/**
	 * Initialize this class' logging.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(BaseStateManager.class.getName());

	/**
	 * Hold instance for this state manager.
	 */
	protected Instance instance;

	/**
	 * Hold the model for this state manager.
	 */
	protected Model model;

	/**
	 * Hold global configuration.
	 */
	protected Configuration cfg;

	/**
	 * Check on relevance. Top level irrelevant stuff is not added to state.
	 * 
	 * @param r
	 * @return
	 */
	protected final boolean isRelevant(final Renderable rItem) {

		if (rItem instanceof Group) {
			return isRelevant((Group) rItem);
		} else if (rItem instanceof Control) {
			return isRelevant((Control) rItem, false);
		}

		return true;
	}

	/**
	 * Determine whether this group is relevant. This is true iff one of the
	 * contained controls is relevant. If the group happens to be matrix, see if
	 * it has options and items.
	 * 
	 * @param grp
	 * @return whether relevant or not.
	 */
	private boolean isRelevant(final Group grp) {

		Renderable rItem;

		for (final Iterator<Renderable> i = grp.getItems().iterator(); i
				.hasNext();) {

			rItem = i.next();

			if (isRelevant(rItem)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Is the item relevant, and useable?
	 * 
	 * @param node
	 *            Node to check
	 * @return whether to use this item or no
	 */
	protected boolean isRelevant(final Control ctrl,
			final boolean check_rule_only) {

		Node node;

		LOGGER.fine("Trying to find node for bind " + ctrl.getBind());

		try {
			node = this.instance.getNode(ctrl.getBind());
		} catch (InvalidPathExpression e1) {
			// If it doesn't have a node, it is relevant...
			LOGGER.warning("Node " + ctrl.getBind()
					+ " not found; assuming relevant = true");
			return true;
		}

		// No proper binding...
		if (node == null) {
			LOGGER.warning("Node is null; assuming relevant = true");
			return true;
		}

		ItemProperties props = this.model.getItemProperties(node.getName());

		LOGGER.fine("Checking rule: " + props.getRelevant());

		try {
			LOGGER.finer("After resolve: "
					+ XRefSolver.resolve(this.model, this.instance,
							props.getRelevant(), node).toString());
			LOGGER.finer("After resolve does it look relevant?: "
					+ XRefSolver.resolve(this.model, this.instance,
							props.getRelevant(), node).toBoolean());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Couldn't resolve", e);
		}

		if (!NodeValidator.isRelevant(props, this.instance, this.model)) {
			LOGGER.fine(node.getName() + " is not relevant");
			return false;
		}

		if (check_rule_only) {
			return true;
		}

		if ("select".equals(ctrl.getType())) {

			if ("true".equals(((Select) ctrl).getProperty("wisapi"))) {
				return true;
			}

			if (((Select) ctrl).getOptions().size() == 0) {
				return false;
			}
		}

		return true;
	}

}
