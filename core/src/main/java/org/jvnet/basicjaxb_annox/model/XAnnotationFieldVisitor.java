package org.jvnet.basicjaxb_annox.model;

import org.jvnet.basicjaxb_annox.model.annotation.field.XArrayAnnotationField;
import org.jvnet.basicjaxb_annox.model.annotation.field.XSingleAnnotationField;

/**
 * Annotation visitor.
 * 
 * @param <T>
 *            Visitor return type.
 * @author Aleksei Valikov
 */
public interface XAnnotationFieldVisitor<T> {

	public T visitSingleAnnotationField(
			XSingleAnnotationField<?> singleAnnotationField);

	public T visitArrayAnnotationField(
			XArrayAnnotationField<?> arrayAnnotationField);

}
