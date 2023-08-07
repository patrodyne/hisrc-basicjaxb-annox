package org.jvnet.basicjaxb_annox.model.annotation.value;

import static java.util.Objects.requireNonNull;

import java.lang.annotation.Annotation;

import org.jvnet.basicjaxb_annox.model.XAnnotation;

public class XXAnnotationAnnotationValue<A extends Annotation> extends
		XDynamicAnnotationValue<A> {

	private final A annotation;
	private final XAnnotation<A> xannotation;

	public XXAnnotationAnnotationValue(XAnnotation<A> xannotation) {
		this.xannotation = requireNonNull(xannotation);
		this.annotation = null;
	}

	public XXAnnotationAnnotationValue(A annotation, XAnnotation<A> xannotation) {
		this.xannotation = requireNonNull(xannotation);
		this.annotation = null;
	}

	public XAnnotation<A> getXAnnotation() {
		return this.xannotation;
	}

	@Override
	public A getValue() {
		if (this.annotation == null) {
			return this.xannotation.getResult();
		} else {
			return this.annotation;
		}
	}

	@Override
	protected Object getInternalValue() {
		return this.xannotation;
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}

}
