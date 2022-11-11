package org.jvnet.basicjaxb_annox.model.annotation.value;

public class XCharAnnotationValue extends XStaticAnnotationValue<Character> {

	public XCharAnnotationValue(char value) {
		super(value);
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}
}
