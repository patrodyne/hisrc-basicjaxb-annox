package org.jvnet.basicjaxb_annox.xml.bind;

import static org.jvnet.basicjaxb_annox.xml.bind.LocatableUtils.getLocatable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.jvnet.basicjaxb_annox.reflect.AnnotatedElementException;
import org.jvnet.basicjaxb_annox.reflect.AnnotatedElementFactory;
import org.jvnet.basicjaxb_annox.reflect.DualAnnotatedElementFactory;
import org.jvnet.basicjaxb_annox.reflect.MethodAnnotatedElement;
import org.jvnet.basicjaxb_annox.reflect.ParameterizedAnnotatedElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.runtime.v2.model.annotation.AbstractInlineAnnotationReaderImpl;
import org.glassfish.jaxb.runtime.v2.model.annotation.LocatableAnnotation;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AnnoxAnnotationReader
	extends AbstractInlineAnnotationReaderImpl<Type, Class, Field, Method>
	implements RuntimeAnnotationReader
{
	private AnnotatedElementFactory annotatedElementFactory;

	public AnnoxAnnotationReader()
	{
		this(new DualAnnotatedElementFactory());
	}

	public AnnoxAnnotationReader(AnnotatedElementFactory annotatedElementFactory)
	{
		super();
		this.annotatedElementFactory = annotatedElementFactory;
	}

	public AnnotatedElementFactory getAnnotatedElementFactory()
	{
		return annotatedElementFactory;
	}

	protected String illegalAnnotationMsg(AnnotatedElementException aex, String loadMsg)
	{
		String msg1 = aex.getClass().getSimpleName() + ": " + aex.getMessage();
		Throwable rc = ExceptionUtils.getRootCause(aex);
		String msg2 = rc.getClass().getSimpleName() + ": " + rc.getMessage();
		return msg1 + "; " + msg2 + "; " + loadMsg;
	}

	protected AnnotatedElement getAnnotatedElement(Field field)
	{
		try
		{
			return getAnnotatedElementFactory().getAnnotatedElement(field);
		}
		catch (AnnotatedElementException aex)
		{
			String msg = illegalAnnotationMsg(aex, "Loading field [" + field.getName() + "].");
			getErrorHandler().error(new IllegalAnnotationException(msg, aex, getLocatable(field)));
			return field;
		}
	}

	protected ParameterizedAnnotatedElement getAnnotatedElement(Method theMethod)
	{
		try
		{
			return getAnnotatedElementFactory().getAnnotatedElement(theMethod);
		}
		catch (AnnotatedElementException aex)
		{
			String msg = illegalAnnotationMsg(aex, "Loading method [" + theMethod.getName() + "].");
			getErrorHandler().error(new IllegalAnnotationException(msg, aex, getLocatable(theMethod)));
			return new MethodAnnotatedElement(theMethod);
		}
	}

	protected AnnotatedElement getAnnotatedElement(Class theClass)
	{
		try
		{
			return getAnnotatedElementFactory().getAnnotatedElement(theClass);
		}
		catch (AnnotatedElementException aex)
		{
			String msg = illegalAnnotationMsg(aex, "Loading class [" + theClass.getName() + "].");
			getErrorHandler().error(new IllegalAnnotationException(msg, aex, getLocatable(theClass)));
			return theClass;
		}
	}

	protected AnnotatedElement getAnnotatedElement(Package thePackage)
	{
		try
		{
			return getAnnotatedElementFactory().getAnnotatedElement(thePackage);
		}
		catch (AnnotatedElementException aex)
		{
			String msg = illegalAnnotationMsg(aex, "Loading package [" + thePackage.getName() + "].");
			getErrorHandler().error(new IllegalAnnotationException(msg, aex, getLocatable(thePackage)));
			return thePackage;
		}
	}

	@Override
	public <A extends Annotation> A getFieldAnnotation(Class<A> annotation, Field field, Locatable srcPos)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(field);
		return LocatableAnnotation.create(annotatedElement.getAnnotation(annotation), srcPos);
	}

	@Override
	public boolean hasFieldAnnotation(Class<? extends Annotation> annotationType, Field field)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(field);
		return annotatedElement.isAnnotationPresent(annotationType);
	}

	@Override
	public boolean hasClassAnnotation(Class clazz, Class<? extends Annotation> annotationType)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(clazz);
		return annotatedElement.isAnnotationPresent(annotationType);
	}

	@Override
	public Annotation[] getAllFieldAnnotations(Field field, Locatable srcPos)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(field);
		Annotation[] r = annotatedElement.getAnnotations();
		
		for ( int i = 0; i < r.length; i++ )
			r[i] = LocatableAnnotation.create(r[i], srcPos);
		
		return r;
	}

	@Override
	public <A extends Annotation> A getMethodAnnotation(Class<A> annotation, Method method, Locatable srcPos)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(method);
		return LocatableAnnotation.create(annotatedElement.getAnnotation(annotation), srcPos);
	}

	@Override
	public boolean hasMethodAnnotation(Class<? extends Annotation> annotation, Method method)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(method);
		return annotatedElement.isAnnotationPresent(annotation);
	}

	@Override
	public Annotation[] getAllMethodAnnotations(Method method, Locatable srcPos)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(method);
		Annotation[] r = annotatedElement.getAnnotations();
		
		for ( int i = 0; i < r.length; i++ )
			r[i] = LocatableAnnotation.create(r[i], srcPos);
		
		return r;
	}

	@Override
	public <A extends Annotation> A getMethodParameterAnnotation(Class<A> annotation, Method method, int paramIndex,
		Locatable srcPos)
	{
		final ParameterizedAnnotatedElement annotatedElement = getAnnotatedElement(method);
		Annotation[] pa = annotatedElement.getParameterAnnotations()[paramIndex];
		
		for ( Annotation a : pa )
		{
			if ( a.annotationType() == annotation )
			{
				final A typedA = (A) a;
				return LocatableAnnotation.create(typedA, srcPos);
			}
		}
		
		return null;
	}

	@Override
	public <A extends Annotation> A getClassAnnotation(Class<A> a, Class clazz, Locatable srcPos)
	{
		final AnnotatedElement annotatedElement = getAnnotatedElement(clazz);
		return LocatableAnnotation.create(annotatedElement.getAnnotation(a), srcPos);
	}

	@Override
	public Class getClassValue(Annotation a, String name)
	{
		try
		{
			return (Class) a.annotationType().getMethod(name).invoke(a);
		}
		catch (IllegalAccessException e)
		{
			// impossible
			throw new IllegalAccessError(e.getMessage());
		}
		catch (InvocationTargetException e)
		{
			// impossible
			throw new InternalError(e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			throw new NoSuchMethodError(e.getMessage());
		}
	}

	@Override
	public Class[] getClassArrayValue(Annotation a, String name)
	{
		try
		{
			return (Class[]) a.annotationType().getMethod(name).invoke(a);
		}
		catch (IllegalAccessException e)
		{
			// impossible
			throw new IllegalAccessError(e.getMessage());
		}
		catch (InvocationTargetException e)
		{
			// impossible
			throw new InternalError(e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			throw new NoSuchMethodError(e.getMessage());
		}
	}

	@Override
	protected String fullName(Method m)
	{
		return m.getDeclaringClass().getName() + '#' + m.getName();
	}

	private final Map<Class<? extends Annotation>, Map<Package, Annotation>> packageCache =
		new HashMap<Class<? extends Annotation>, Map<Package, Annotation>>();

	@Override
	public <A extends Annotation> A getPackageAnnotation(Class<A> a, Class clazz, Locatable srcPos)
	{
		Package p = clazz.getPackage();
		if ( p == null )
			return null;
		
		AnnotatedElement annotatedElement = getAnnotatedElement(p);
		Map<Package, Annotation> cache = packageCache.get(a);
		if ( cache == null )
		{
			cache = new HashMap<Package, Annotation>();
			packageCache.put(a, cache);
		}
		
		if ( cache.containsKey(p) )
			return (A) cache.get(p);
		else
		{
			A ann = LocatableAnnotation.create(annotatedElement.getAnnotation(a), srcPos);
			cache.put(p, ann);
			return ann;
		}
	}
}
