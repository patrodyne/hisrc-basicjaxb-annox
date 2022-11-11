package org.jvnet.basicjaxb_annox.parser.java.visitor;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XBooleanAnnotationValue;

import japa.parser.ast.expr.BooleanLiteralExpr;

public final class BooleanExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<Boolean>> {
	public BooleanExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public XAnnotationValue<Boolean> visit(BooleanLiteralExpr n, Void arg) {
		return new XBooleanAnnotationValue(n.getValue());
	}
}