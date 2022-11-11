package org.jvnet.basicjaxb_annox.demos.guide;

@MyAnnotation(printName = "My class")
public class MyClass {
	@MyAnnotation(printName = "My field")
	public String myField;
}
