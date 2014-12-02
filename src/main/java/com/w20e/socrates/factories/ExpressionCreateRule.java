package com.w20e.socrates.factories;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.FactoryCreateRule;
import org.apache.commons.jxpath.ri.Compiler;
import org.apache.commons.jxpath.ri.Parser;

import com.w20e.socrates.data.DataType;
import com.w20e.socrates.data.XSAmount;
import com.w20e.socrates.data.XSBoolean;
import com.w20e.socrates.data.XSDate;
import com.w20e.socrates.data.XSDecimal;
import com.w20e.socrates.data.XSEmail;
import com.w20e.socrates.data.XSInteger;
import com.w20e.socrates.data.XSList;
import com.w20e.socrates.data.XSString;
import com.w20e.socrates.expression.Expression;
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

		if (!"".equals(wrap.getExpr().trim())) {
			if (name == "required") {
				props.setRequired((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));
			} else if (name == "relevant") {
				props.setRelevant((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));				
			} else if (name == "readonly") {
				props.setReadOnly((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));				
			} else if (name == "calculate") {
				props.setCalculate((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));				
			} else if (name == "constraint") {
				props.setConstraint((Expression) Parser.parseExpression(wrap.getExpr(), this.compiler));				
			} else if (name == "datatype") {
				props.setDatatype(createDataType(wrap.getExpr()));				
			}

		}
		
		dig.pop();
	}

	
	private Class<? extends DataType> createDataType(final String typeName) {

		if ("xs:string".equals(typeName)) {
			return XSString.class;
		} else if ("xs:email".equals(typeName)) {
            return XSEmail.class;
        } else if ("xs:boolean".equals(typeName)) {
			return XSBoolean.class;
		} else if ("xs:decimal".equals(typeName)) {
			return XSDecimal.class;
		} else if ("xs:amount".equals(typeName)) {
			return XSAmount.class;
		} else if ("xs:amount-no-decimal".equals(typeName)) {
			return XSInteger.class;
		} else if ("xs:integer".equals(typeName)) {
			return XSInteger.class;
		} else if ("xs:date".equals(typeName)) {
			return XSDate.class;
			// } else if ("xs:time".equals(typeName)) {
			// return XSHoursQuarters.class;
		} else if ("xs:workweek".equals(typeName)) {
			return XSDecimal.class;
		} else if ("xs:list".equals(typeName)) {
			return XSList.class;
		}

		return XSString.class;
	}

}
