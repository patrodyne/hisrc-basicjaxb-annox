package org.jvnet.basicjaxb_annox.parser.value;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.parser.exception.ValueParseException;
import org.jvnet.basicjaxb_annox.parser.java.visitor.ExpressionVisitor;

public abstract class XAnnotationValueParser<T, V> {

	public abstract XAnnotationValue<T> parse(String value, Class<?> type)
			throws ValueParseException;

	public abstract XAnnotationValue<T> construct(V value, Class<?> type);

	public abstract ExpressionVisitor<XAnnotationValue<T>> createExpressionVisitor(
			Class<?> type);

}
