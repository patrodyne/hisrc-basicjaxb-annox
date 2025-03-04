package org.jvnet.basicjaxb_annox.util;

import static java.util.Objects.requireNonNull;
import static org.jvnet.basicjaxb_annox.util.ClassUtils.EMPTY_CLASS_ARRAY;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils
{
	public static Constructor<?> getConstructor(Class<?> theClass, Class<?>[] parameterTypes)
		throws NoSuchMethodException
	{
		requireNonNull(theClass);
		if ( parameterTypes == null )
		{
			try
			{
				return theClass.getDeclaredConstructor(EMPTY_CLASS_ARRAY);
			}
			catch (NoSuchMethodException ignored)
			{
				// ignored
			}
			
			Constructor<?> foundMethod = null;
			for ( final Constructor<?> method : theClass.getDeclaredConstructors() )
			{
				if ( foundMethod == null )
					foundMethod = method;
				else
				{
					throw new NoSuchMethodException(
						"Duplicate constructors [" + foundMethod + "] and [" + method + "].");
				}
			}
			
			if ( foundMethod != null )
				return foundMethod;
			else
				throw new NoSuchMethodException();
		}
		else
			return theClass.getDeclaredConstructor(parameterTypes);
	}

	public static Method getMethod(Class<?> theClass, String methodName, Class<?>[] parameterTypes)
		throws NoSuchMethodException
	{
		requireNonNull(theClass);
		requireNonNull(methodName);
		if ( parameterTypes == null )
		{
			try
			{
				return theClass.getMethod(methodName, EMPTY_CLASS_ARRAY);
			}
			catch (NoSuchMethodException ignored)
			{
				// ignored
			}
			Method foundMethod = null;
			for ( final Method method : theClass.getDeclaredMethods() )
			{
				if ( methodName.equals(method.getName()) )
				{
					if ( foundMethod == null )
						foundMethod = method;
					else
					{
						throw new NoSuchMethodException(
							"Duplicate methods [" + foundMethod + "] and [" + method + "].");
					}
				}
			}
			
			if ( foundMethod != null )
				return foundMethod;
			else
				throw new NoSuchMethodException();
		}
		else
			return theClass.getDeclaredMethod(methodName, parameterTypes);
	}

	public static Field getField(Class<?> theClass, String fieldName)
		throws NoSuchFieldException
	{
		return theClass.getDeclaredField(fieldName);
	}
}
