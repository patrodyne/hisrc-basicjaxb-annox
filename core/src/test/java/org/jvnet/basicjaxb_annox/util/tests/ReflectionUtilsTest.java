package org.jvnet.basicjaxb_annox.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.jvnet.basicjaxb_annox.util.ClassUtils.forNames;
import static org.jvnet.basicjaxb_annox.util.ReflectionUtils.getConstructor;
import static org.jvnet.basicjaxb_annox.util.ReflectionUtils.getMethod;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.util.ClassUtils;

public class ReflectionUtilsTest
{
	public static class TestMe
	{
		public TestMe() { }

		public TestMe(String x) { }

		public void one() { }

		public void two() { }

		public void two(String value) { }

		public void three() { }

		public void three(String value) { }

		public void three(String[] value) { }

		public void three(Object value) { }

		public void three(Object[] value) { }

		public void four(String x, int y, char[] z) { }
	}

	@Test
	public void testGetMethod()
		throws Exception
	{
		assertNotNull(getMethod(TestMe.class, "one", forNames(null)));
		assertNotNull(getMethod(TestMe.class, "two", forNames(null)));
		assertNotNull(getMethod(TestMe.class, "two", forNames("")));
		assertEquals(getMethod(TestMe.class, "two", forNames("")), getMethod(TestMe.class, "two", forNames("")));
		assertNotNull(getMethod(TestMe.class, "two", forNames("java.lang.String")));
		assertNotNull(getMethod(TestMe.class, "three", forNames("java.lang.String[]")));
		assertNotNull(getMethod(TestMe.class, "four", forNames("java.lang.String, int, char[]")));
	}

	@Test
	public void testGetConstructor()
		throws Exception
	{
		getConstructor(TestMe.class, null);
		getConstructor(TestMe.class, ClassUtils.EMPTY_CLASS_ARRAY);
		getConstructor(TestMe.class, forNames(null));
		getConstructor(TestMe.class, forNames(""));
		getConstructor(TestMe.class, forNames("java.lang.String"));
	}
}
