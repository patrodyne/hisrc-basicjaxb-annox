package org.jvnet.basicjaxb_annox.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.jvnet.basicjaxb_annox.model.XClass;
import org.jvnet.basicjaxb_annox.model.XConstructor;
import org.jvnet.basicjaxb_annox.model.XField;
import org.jvnet.basicjaxb_annox.model.XMethod;
import org.jvnet.basicjaxb_annox.model.XPackage;
import org.jvnet.basicjaxb_annox.reflect.AnnotatedElementException;

public interface XReader {

	public XPackage getXPackage(Package thePackage)
			throws AnnotatedElementException;

	public XClass getXClass(Class<?> theClass) throws AnnotatedElementException;

	public XField getXField(Field theField) throws AnnotatedElementException;

	public XField getXField(Class<?> theClass, Field theField)
			throws AnnotatedElementException;

	public XConstructor getXConstructor(Constructor<?> theConstructor)
			throws AnnotatedElementException;

	public XConstructor getXConstructor(Class<?> theClass,
			Constructor<?> theConstructor) throws AnnotatedElementException;

	public XMethod getXMethod(Method theMethod)
			throws AnnotatedElementException;

	public XMethod getXMethod(Class<?> theClass, Method theMethod)
			throws AnnotatedElementException;

}
