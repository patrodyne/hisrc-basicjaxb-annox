package org.jvnet.basicjaxb_annox.model.annotation.value;

public class XFloatAnnotationValue extends XStaticAnnotationValue<Float> {

	public XFloatAnnotationValue(float value) {
		super(value);
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}
}
