package org.jvnet.basicjaxb_annox.parser.java.visitor;

import japa.parser.ast.expr.FieldAccessExpr;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XEnumAnnotationValue;

public final class EnumExpressionVisitor extends
		ExpressionVisitor<XAnnotationValue<Enum<?>>> {
	public EnumExpressionVisitor(Class<?> targetClass) {
		super(targetClass);
	}

	// TODO Implement handling enums as strings.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public XAnnotationValue<Enum<?>> visit(FieldAccessExpr n, Void arg) {
		try {
			final Class<? extends Enum> enumClass = (Class<? extends Enum>) this.targetClass;
			return new XEnumAnnotationValue(Enum.valueOf(enumClass,
					n.getField()));
		} catch (Exception ex) {
			// BUG
			throw new RuntimeException(ex);
		}
	}
}