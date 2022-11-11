package org.jvnet.basicjaxb_annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XStringAnnotationValue;

public final class StringExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<String>> {
	public StringExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public XAnnotationValue<String> visitDefault(StringLiteralExpr n, Void arg) {
		return new XStringAnnotationValue(String.valueOf(n.getValue()));
	}
}