package org.jvnet.basicjaxb_annox.model.annotation.value;

import static java.util.Objects.requireNonNull;

public abstract class XStaticAnnotationValue<T> extends XAnnotationValue<T> {

	private final T value;

	public XStaticAnnotationValue(T value) {
		requireNonNull(value);
		this.value = value;
	}

	@Override
	public T getValue() {
		return this.value;
	}
	
	@Override
	protected Object getInternalValue() {
		return getValue();
	}

}
