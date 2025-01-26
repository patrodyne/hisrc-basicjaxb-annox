package org.jvnet.basicjaxb_annox.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public final class ConstructorAnnotatedElement implements ParameterizedAnnotatedElement
{
	private final Constructor<?> constructor;

	public ConstructorAnnotatedElement(Constructor<?> constructor)
	{
		this.constructor = constructor;
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
	{
		final T annotation = constructor.<T> getAnnotation(annotationClass);
		return annotation;
	}

	@Override
	public Annotation[] getAnnotations()
	{
		return constructor.getAnnotations();
	}

	@Override
	public Annotation[] getDeclaredAnnotations()
	{
		return constructor.getDeclaredAnnotations();
	}

	@Override
	public Annotation[][] getParameterAnnotations()
	{
		return constructor.getParameterAnnotations();
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
	{
		return constructor.isAnnotationPresent(annotationClass);
	}
}