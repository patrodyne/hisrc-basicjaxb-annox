package org.jvnet.basicjaxb_annox.parser.value;

import org.jvnet.basicjaxb_annox.model.annotation.value.XAnnotationValue;
import org.jvnet.basicjaxb_annox.model.annotation.value.XByteAnnotationValue;
import org.jvnet.basicjaxb_annox.parser.exception.ValueParseException;
import org.jvnet.basicjaxb_annox.parser.java.visitor.ByteExpressionVisitor;
import org.jvnet.basicjaxb_annox.parser.java.visitor.ExpressionVisitor;

public class XByteAnnotationValueParser extends
		XAnnotationValueParser<Byte, Byte> {

	@Override
	public XAnnotationValue<Byte> parse(String value, Class<?> type)
			throws ValueParseException {
		return construct(Byte.valueOf(value), type);
	}

	@Override
	public XAnnotationValue<Byte> construct(Byte value, Class<?> type) {
		return new XByteAnnotationValue(value);
	}

	@Override
	public ExpressionVisitor<XAnnotationValue<Byte>> createExpressionVisitor(
			Class<?> type) {
		return new ByteExpressionVisitor(type);
	}

}
