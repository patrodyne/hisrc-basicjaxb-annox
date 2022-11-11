package org.jvnet.basicjaxb_annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XShortAnnotationValue;

public final class ShortExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<Short>> {
	public ShortExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public XAnnotationValue<Short> visitDefault(StringLiteralExpr n, Void arg) {
		return new XShortAnnotationValue(Short.valueOf(n.getValue()));
	}
}