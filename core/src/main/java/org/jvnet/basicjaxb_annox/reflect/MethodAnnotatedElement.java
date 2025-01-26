package org.jvnet.basicjaxb_annox.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class MethodAnnotatedElement implements ParameterizedAnnotatedElement
{
	private final Method method;

	public MethodAnnotatedElement(Method method)
	{
		this.method = method;
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
	{
		return method.<T> getAnnotation(annotationClass);
	}

	@Override
	public Annotation[] getAnnotations()
	{
		return method.getAnnotations();
	}

	@Override
	public Annotation[] getDeclaredAnnotations()
	{
		return method.getDeclaredAnnotations();
	}

	@Override
	public Annotation[][] getParameterAnnotations()
	{
		return method.getParameterAnnotations();
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
	{
		return method.isAnnotationPresent(annotationClass);
	}
}