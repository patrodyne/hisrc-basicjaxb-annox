package org.jvnet.basicjaxb_annox.reflect;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.jvnet.basicjaxb_annox.reader.XReader;
import org.jvnet.basicjaxb_annox.reader.resourced.ResourcedXReader;

public class ResourcedAnnotatedElementFactory implements AnnotatedElementFactory
{
	private final XReader xreader;

	public ResourcedAnnotatedElementFactory(XReader xreader)
	{
		requireNonNull(xreader);
		this.xreader = xreader;
	}

	public ResourcedAnnotatedElementFactory()
	{
		this(new ResourcedXReader());
	}

	public XReader getXReader()
	{
		return xreader;
	}

	@Override
	public AnnotatedElement getAnnotatedElement(AnnotatedElement annotatedElement)
		throws AnnotatedElementException
	{
		requireNonNull(annotatedElement);
		if ( annotatedElement instanceof Package )
			return getXReader().getXPackage((Package) annotatedElement);
		else if ( annotatedElement instanceof Class<?> )
			return getXReader().getXClass((Class<?>) annotatedElement);
		else if ( annotatedElement instanceof Field )
			return getXReader().getXField((Field) annotatedElement);
		else if ( annotatedElement instanceof Constructor )
			return getXReader().getXConstructor((Constructor<?>) annotatedElement);
		else if ( annotatedElement instanceof Method )
			return getXReader().getXMethod((Method) annotatedElement);
		else
			throw new AssertionError("Unexpected annotated element [" + annotatedElement + "]");
	}

	@Override
	public ParameterizedAnnotatedElement getAnnotatedElement(@SuppressWarnings("rawtypes") Constructor constructor)
		throws AnnotatedElementException
	{
		requireNonNull(constructor);
		return getXReader().getXConstructor(constructor);
	}

	@Override
	public ParameterizedAnnotatedElement getAnnotatedElement(Method method)
		throws AnnotatedElementException
	{
		requireNonNull(method);
		return getXReader().getXMethod(method);
	}
}
