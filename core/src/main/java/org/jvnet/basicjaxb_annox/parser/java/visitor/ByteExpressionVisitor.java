package org.jvnet.basicjaxb_annox.parser.java.visitor;

import japa.parser.ast.expr.StringLiteralExpr;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XByteAnnotationValue;

public final class ByteExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<Byte>> {
	public ByteExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	@Override
	public XAnnotationValue<Byte> visitDefault(StringLiteralExpr n, Void arg) {
		return new XByteAnnotationValue(Byte.valueOf(n.getValue()));
	}
}