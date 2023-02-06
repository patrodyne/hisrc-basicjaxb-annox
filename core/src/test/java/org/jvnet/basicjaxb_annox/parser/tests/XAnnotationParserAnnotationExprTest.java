package org.jvnet.basicjaxb_annox.parser.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.lang.annotation.Annotation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.model.XAnnotation;
import org.jvnet.basicjaxb_annox.parser.XAnnotationParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XAnnotationParserAnnotationExprTest
{
	private Logger logger = LoggerFactory.getLogger(XAnnotationParserAnnotationExprTest.class);
	public Logger getLogger() { return logger; }
	
	public Element getElement(final String resourceName) throws Exception
	{
		try ( InputStream is = getClass().getResourceAsStream(resourceName) )
		{
			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			final Document document = documentBuilder.parse(is);
			return document.getDocumentElement();
		}
	}

	public String getAnnotationString(final String resourceName) throws Exception
	{
		try ( InputStream is = getClass().getResourceAsStream(resourceName) )
		{
			final String text = IOUtils.toString(is, "UTF-8");
			return text;
		}
	}

	public void check(String javaResourceName, String xmlResourceName, Class<?> clazz, Class<? extends Annotation> annotationClazz)
		throws Exception
	{
		final String annotationExpr = getAnnotationString(javaResourceName);
		final Element element = getElement(xmlResourceName);
		final Annotation annotation = clazz.getAnnotation(annotationClazz);
		final XAnnotationParser parser = new XAnnotationParser();
		final XAnnotation<?> one = parser.parse(annotation);
		final XAnnotation<?> two = parser.parse(annotationExpr);
		final XAnnotation<?> three = parser.parse(element);
		
		getLogger().debug(one.toString());
		getLogger().debug(two.toString());
		getLogger().debug(three.toString());
		
		assertEquals(one, two, "Annotations should be identical.");
		assertEquals(two, three, "Annotations should be identical.");
		assertEquals(one.getResult(), two.getResult(), "Annotations should be identical.");
		assertEquals(two.getResult(), three.getResult(), "Annotations should be identical.");
		assertEquals(annotation, one.getResult(), "Annotations should be identical.");
		assertEquals(annotation, two.getResult(), "Annotations should be identical.");
		assertEquals(annotation, three.getResult(), "Annotations should be identical.");
	}

	@Test
	public void testOne() throws Exception
	{
		check("one.txt", "one.xml", One.class, A.class);
	}

	@Test
	public void testTwo() throws Exception
	{
		check("two.txt", "two.xml", Two.class, A.class);
	}

	@Test
	public void testThree() throws Exception
	{
		check("three.txt", "three.xml", Three.class, D.class);
	}

	@Test
	public void testFive() throws Exception
	{
		check("five.txt", "five.xml", Five.class, F.class);
	}

	@Test
	public void testSix() throws Exception
	{
		check("six.txt", "six.xml", Six.class, G.class);
	}

	@Test
	public void testSeven() throws Exception
	{
		check("seven.txt", "seven.xml", Seven.class, J.class);
	}

	@Test
	public void testEight() throws Exception
	{
		check("eight.txt", "eight.xml", Eight.class, K.class);
	}

	@Test
	public void testNine() throws Exception
	{
		check("nine.txt", "nine.xml", Nine.class, L.class);
	}

	@Test
	public void testTen() throws Exception
	{
		check("ten.txt", "ten.xml", Ten.class, M.class);
	}

	@Test
	public void testEleven() throws Exception
	{
		check("eleven.txt", "eleven.xml", Eleven.class, H.class);
	}

	@Test
	public void testTwelve() throws Exception
	{
		check("twelve.txt", "twelve.xml", Twelve.class, B.class);
	}
}
