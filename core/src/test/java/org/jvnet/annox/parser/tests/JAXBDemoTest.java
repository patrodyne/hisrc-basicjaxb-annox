package org.jvnet.annox.parser.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.jvnet.annox.model.XAnnotation;
import org.jvnet.annox.parser.XAnnotationParser;
import org.jvnet.annox.parser.exception.AnnotationExpressionParseException;
import org.jvnet.annox.parser.exception.AnnotationStringParseException;

import jakarta.xml.bind.annotation.XmlRootElement;

public class JAXBDemoTest {

	@Test
	public void testXMLRootElement() throws AnnotationStringParseException,
			AnnotationExpressionParseException {
		@SuppressWarnings("unchecked")

		// Parse annotation from the string
		XAnnotation<XmlRootElement> xannotation =
			(XAnnotation<XmlRootElement>) XAnnotationParser.INSTANCE.parse
				("@jakarta.xml.bind.annotation.XmlRootElement(name=\"foo\")");

		// Create an instance of the annotation 
		XmlRootElement xmlRootElement = xannotation.getResult();
		assertEquals("foo", xmlRootElement.name());
		assertEquals("##default", xmlRootElement.namespace());
		
		// Analyze the structure of the annotation
		assertEquals(String.class, xannotation.getFieldsMap().get("name").getType());
		assertEquals("##default", xannotation.getFieldsMap().get("namespace").getResult());
	}
}
