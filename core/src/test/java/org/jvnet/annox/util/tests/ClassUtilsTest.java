package org.jvnet.annox.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.jvnet.annox.util.ClassUtils;

public class ClassUtilsTest {

	@Test
	public void testwrapperArrayToPrimitiveArray() {
		assertEquals(int[].class, ClassUtils.wrapperArrayToPrimitiveArray(Integer[].class));
		assertEquals(int[].class, ClassUtils.wrapperArrayToPrimitiveArray(int[].class));
		assertEquals(Object[].class, ClassUtils.wrapperArrayToPrimitiveArray(Object[].class));
	}

	@Test
	public void testForName() throws Exception {

		assertEquals(Integer.TYPE, ClassUtils.forName("int"), "Wrong class.");
		assertEquals(byte[].class, ClassUtils.forName("byte[]"), "Wrong class.");
		assertEquals(String.class, ClassUtils.forName("java.lang.String"), "Wrong class.");
		assertEquals(URL[].class, ClassUtils.forName("java.net.URL[]"), "Wrong class.");

	}

	@Test
	public void testForNames() throws Exception {

		assertTrue(
				Arrays.equals(
					new Class<?>[] { Integer.TYPE, String.class, int[].class, String[].class, Map.Entry.class },
					ClassUtils.forNames("int, java.lang.String, int[], java.lang.String[], java.util.Map$Entry")),
				"Class arrays must be equal.");
	}
}
