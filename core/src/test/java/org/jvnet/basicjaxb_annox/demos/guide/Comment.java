package org.jvnet.basicjaxb_annox.demos.guide;

public @interface Comment {
	public String lang() default "en";
	public String value();
}
