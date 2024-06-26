package org.jvnet.basicjaxb_annox.model.annotation.value;

import static java.util.Objects.requireNonNull;

public class XEnumByNameAnnotationValue<E extends Enum<E>> extends
		XDynamicAnnotationValue<E> {

	private final XClassByNameAnnotationValue<E> enumByNameAnnotationValue;
	private final String name;
	private final String enumName;

	public XEnumByNameAnnotationValue(XClassByNameAnnotationValue<E> enumClass,
			String name) {
		this.enumByNameAnnotationValue = requireNonNull(enumClass);
		this.name = requireNonNull(name);
		this.enumName = enumClass.getClassName() + "." + name;
	}

	public String getEnumClassName() {
		return enumByNameAnnotationValue.getClassName();
	}

	public String getName() {
		return name;
	}

	@Override
	protected Object getInternalValue() {
		return enumName;
	}

	@Override
	public E getValue() {
		final Class<E> enumClass = this.enumByNameAnnotationValue.getValue();
		return Enum.valueOf(enumClass, name);
	}

	@Override
	public <P> P accept(XAnnotationValueVisitor<P> visitor) {
		return visitor.visit(this);
	}
}
