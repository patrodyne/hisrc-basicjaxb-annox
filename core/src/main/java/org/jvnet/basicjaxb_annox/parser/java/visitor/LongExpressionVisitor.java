package org.jvnet.basicjaxb_annox.parser.java.visitor;

import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.LongLiteralMinValueExpr;
import japa.parser.ast.expr.StringLiteralExpr;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XLongAnnotationValue;

public final class LongExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<Long>> {
	public LongExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public XAnnotationValue<Long> visitDefault(StringLiteralExpr n, Void arg) {
		return new XLongAnnotationValue(Long.valueOf(n.getValue()));
	}

	@Override
	public XAnnotationValue<Long> visit(LongLiteralMinValueExpr n, Void arg) {
		return new XLongAnnotationValue(Long.MIN_VALUE);
	}

	@Override
	public XAnnotationValue<Long> visit(LongLiteralExpr n, Void arg) {
		String value = n.getValue();
		if (value.endsWith("L") || value.endsWith("l")) {
			return new XLongAnnotationValue(Long.valueOf(value.substring(0,
					value.length() - 1)));
		} else {
			return super.visit(n, arg);
		}
	}
}