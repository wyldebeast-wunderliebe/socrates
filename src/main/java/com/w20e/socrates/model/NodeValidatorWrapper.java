package com.w20e.socrates.model;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.expression.Undef;
import com.w20e.socrates.expression.XObject;

public class NodeValidatorWrapper {

	private Model model;
	private Instance inst;

	public NodeValidatorWrapper(Model model, Instance inst) {

		this.model = model;
		this.inst = inst;
	}

	public XObject getValue(String nodename) {

		try {
			return NodeValidator.getValue(this.inst.getNode(nodename),
					this.model.getItemProperties(nodename), this.model, this.inst);
		} catch (InvalidPathExpression e) {
			return Undef.UNDEF;
		}
	}
}
