package org.jvnet.basicjaxb_annox.annotation;

import static java.util.Objects.requireNonNull;

public class AnnotationClassNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	private String className;

	public AnnotationClassNotFoundException(String className, Throwable cause)
	{
		super("Annotation class [" + className + "] could not be found.", cause);
		requireNonNull(className);
		this.className = className;
	}

	public String getClassName()
	{
		return className;
	}

}
