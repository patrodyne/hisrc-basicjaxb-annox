package org.jvnet.basicjaxb_annox.parser.value;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XStringAnnotationValue;
import org.jvnet.basicjaxb_annox.parser.exception.ValueParseException;
import org.jvnet.basicjaxb_annox.parser.java.visitor.ExpressionVisitor;
import org.jvnet.basicjaxb_annox.parser.java.visitor.StringExpressionVisitor;

public class XStringAnnotationValueParser extends
		XAnnotationValueParser<String, String> {

	@Override
	public XAnnotationValue<String> parse(String value, Class<?> type)
			throws ValueParseException {
		return construct(String.valueOf(value), type);
	}

	@Override
	public XAnnotationValue<String> construct(String value, Class<?> type) {
		return new XStringAnnotationValue(value);
	}

	@Override
	public ExpressionVisitor<XAnnotationValue<String>> createExpressionVisitor(
			Class<?> type) {
		return new StringExpressionVisitor(type);
	}

}
