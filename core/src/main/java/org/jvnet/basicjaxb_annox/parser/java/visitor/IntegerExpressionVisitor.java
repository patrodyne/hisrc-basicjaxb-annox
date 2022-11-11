package org.jvnet.basicjaxb_annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XIntAnnotationValue;

public final class IntegerExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<Integer>> {
	public IntegerExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public XAnnotationValue<Integer> visitDefault(StringLiteralExpr n, Void arg) {
		return new XIntAnnotationValue(Integer.valueOf(n.getValue()));
	}
}