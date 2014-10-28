package com.w20e.socrates.factories;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.FactoryCreateRule;
import org.apache.commons.digester3.ObjectCreationFactory;
import org.apache.commons.jxpath.ri.Compiler;
import org.apache.commons.jxpath.ri.Parser;

import com.w20e.socrates.expression.Expression;
import com.w20e.socrates.expression.XString;
import com.w20e.socrates.model.ExpressionCompiler;
import com.w20e.socrates.model.ItemPropertiesImpl;

public class ExpressionCreateRule extends FactoryCreateRule {

	public ExpressionCreateRule(ExpressionFactory clazz) {
		super(clazz);
	}

	private Compiler compiler = ExpressionCompiler.getInstance();

	public void end(String namespace, String name) {

		Digester dig = this.getDigester();

		ExpressionWrapper wrap = (ExpressionWrapper) dig.peek(0);
		ItemPropertiesImpl props = (ItemPropertiesImpl) dig.peek(1);

		if (wrap.getExpr() == null) {
			return;
		}

		if ("".equals(wrap.getExpr().trim())) {
			props.setRequired(new XString(""));
		} else {
			if (name == "required") {
				props.setRequired((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));
			} else if (name == "relevant") {
				props.setRelevant((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));				
			}
		}
		
		dig.pop();
	}

}
