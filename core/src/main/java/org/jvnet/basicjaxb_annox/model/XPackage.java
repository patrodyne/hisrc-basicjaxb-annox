package org.jvnet.basicjaxb_annox.model;

import org.apache.commons.lang3.Validate;

/**
 * Defines an annotated package.
 * 
 * @author Aleksei Valikov
 */
public class XPackage extends XAnnotatedElement<Package>
{
	/**
	 * VOID package. Signals that package could not be found.
	 */
	public static final XPackage VOID;
	static
	{
		// Deprecated: Package voidPackage = Package.getPackage("java.lang");
		Package voidPackage = java.lang.Package.class.getPackage();
		VOID = new XPackage(voidPackage, XAnnotation.EMPTY_ARRAY, XClass.EMPTY_ARRAY);
	}
	
	/**
	 * Annotated classes.
	 */
	private final XClass[] classes;

	/**
	 * Constructs an annotated package.
	 * 
	 * @param targetPackage target package.
	 * @param xannotations package annotations, may be <code>null</code>.
	 */
	public XPackage(Package targetPackage, XAnnotation<?>[] xannotations, XClass[] xclasses)
	{
		super(targetPackage, xannotations);
		Validate.noNullElements(xclasses);
		this.classes = xclasses;
	}

	/**
	 * Returns the target package.
	 * 
	 * @return Target package.
	 */
	public Package getPackage()
	{
		return getAnnotatedElement();
	}

	/**
	 * Returns annotated classes of the package.
	 * 
	 * @return Annotated classes of the package.
	 */
	public XClass[] getClasses()
	{
		return classes;
	}
}
