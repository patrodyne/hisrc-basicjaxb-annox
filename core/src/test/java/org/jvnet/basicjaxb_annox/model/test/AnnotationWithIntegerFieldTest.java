package org.jvnet.basicjaxb_annox.model.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.model.XAnnotation;
import org.jvnet.basicjaxb_annox.model.annotation.field.XAnnotationField;
import org.jvnet.basicjaxb_annox.parser.XGenericFieldParser;

public class AnnotationWithIntegerFieldTest {

	@Test
	public void test() {

		@SuppressWarnings("unchecked")
		final XAnnotationField<Integer> integerField = XGenericFieldParser.GENERIC
				.construct("integerField", Integer.valueOf(42), Integer.class);

		@SuppressWarnings("unchecked")
		final XAnnotationField<Integer[]> integerFields = XGenericFieldParser.GENERIC
				.construct("integerFields", new Integer[] { 37, 73 },
						Integer[].class);

		final XAnnotation<AnnotationWithIntegerField> annotation = new XAnnotation<AnnotationWithIntegerField>(
				AnnotationWithIntegerField.class, integerField, integerFields);

		AnnotationWithIntegerField result = annotation.getResult();
		assertEquals(42, result.integerField());
		assertArrayEquals(new int[] { 37, 73 }, result.integerFields());
	}
}
