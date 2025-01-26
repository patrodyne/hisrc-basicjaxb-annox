package org.jvnet.basicjaxb_annox.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.jvnet.basicjaxb_annox.util.ClassUtils.forName;
import static org.jvnet.basicjaxb_annox.util.ClassUtils.forNames;
import static org.jvnet.basicjaxb_annox.util.ClassUtils.wrapperArrayToPrimitiveArray;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ClassUtilsTest
{
	@Test
	public void testwrapperArrayToPrimitiveArray()
	{
		assertEquals(int[].class, wrapperArrayToPrimitiveArray(Integer[].class));
		assertEquals(int[].class, wrapperArrayToPrimitiveArray(int[].class));
		assertEquals(Object[].class, wrapperArrayToPrimitiveArray(Object[].class));
	}

	@Test
	public void testForName()
		throws Exception
	{
		assertEquals(Integer.TYPE, forName("int"), "Wrong class.");
		assertEquals(byte[].class, forName("byte[]"), "Wrong class.");
		assertEquals(String.class, forName("java.lang.String"), "Wrong class.");
		assertEquals(URL[].class, forName("java.net.URL[]"), "Wrong class.");
	}

	@Test
	public void testForNames()
		throws Exception
	{
		assertTrue
		(
			Arrays.equals
			(
				new Class<?>[] { Integer.TYPE, String.class, int[].class, String[].class, Map.Entry.class },
				forNames("int, java.lang.String, int[], java.lang.String[], java.util.Map$Entry")
			),
			"Class arrays must be equal."
		);
	}
}
