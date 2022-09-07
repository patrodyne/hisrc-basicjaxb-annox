package org.jvnet.annox.demos.guide.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.AnnotatedElement;

import org.junit.jupiter.api.Test;
import org.jvnet.annox.demos.guide.MyAnnotation;
import org.jvnet.annox.demos.guide.MyClass;
import org.jvnet.annox.reflect.AnnotatedElementFactory;
import org.jvnet.annox.reflect.DualAnnotatedElementFactory;

public class MyClassTest {

	@Test
	public void testNormal() throws Exception {
		final AnnotatedElement myClass = MyClass.class;
		final AnnotatedElement myField = MyClass.class.getDeclaredField("myField");
		final MyAnnotation myClassAnnotation = myClass.getAnnotation(MyAnnotation.class);
		final MyAnnotation myFieldAnnotation = myField.getAnnotation(MyAnnotation.class);

		assertEquals("My class", myClassAnnotation.printName());
		assertEquals("My field", myFieldAnnotation.printName());
	}
	
	@Test
	public void testAnnox() throws Exception {
		
		final AnnotatedElementFactory aef = new DualAnnotatedElementFactory();
		
		final AnnotatedElement myClass = aef.getAnnotatedElement(MyClass.class);
		final AnnotatedElement myField = aef.getAnnotatedElement(MyClass.class.getDeclaredField("myField"));
		final MyAnnotation myClassAnnotation = myClass.getAnnotation(MyAnnotation.class);
		final MyAnnotation myFieldAnnotation = myField.getAnnotation(MyAnnotation.class);

		assertEquals("My annotated class", myClassAnnotation.printName());
		assertEquals("My annotated field", myFieldAnnotation.printName());
	}

}
