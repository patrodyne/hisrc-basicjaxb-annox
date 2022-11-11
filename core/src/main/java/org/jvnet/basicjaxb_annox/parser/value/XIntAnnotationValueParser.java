package org.jvnet.basicjaxb_annox.parser.value;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XIntAnnotationValue;
import org.jvnet.basicjaxb_annox.parser.exception.ValueParseException;
import org.jvnet.basicjaxb_annox.parser.java.visitor.ExpressionVisitor;
import org.jvnet.basicjaxb_annox.parser.java.visitor.IntegerExpressionVisitor;

public class XIntAnnotationValueParser extends
		XAnnotationValueParser<Integer, Integer> {

	@Override
	public XAnnotationValue<Integer> parse(String value, Class<?> type)
			throws ValueParseException {
		return construct(Integer.valueOf(value), type);
	}

	@Override
	public XAnnotationValue<Integer> construct(Integer value, Class<?> type) {
		return new XIntAnnotationValue(value);
	}

	@Override
	public ExpressionVisitor<XAnnotationValue<Integer>> createExpressionVisitor(
			Class<?> type) {
		return new IntegerExpressionVisitor(type);
	}

}
