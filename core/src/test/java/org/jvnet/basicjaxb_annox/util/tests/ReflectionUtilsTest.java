package org.jvnet.basicjaxb_annox.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.util.ClassUtils;
import org.jvnet.basicjaxb_annox.util.ReflectionUtils;

public class ReflectionUtilsTest {

	public static class TestMe {
		public TestMe() {
		}

		public TestMe(String x) {
		}

		public void one() {
		}

		public void two() {
		}

		public void two(String value) {
		}

		public void three() {
		}

		public void three(String value) {
		}

		public void three(String[] value) {
		}

		public void three(Object value) {
		}

		public void three(Object[] value) {
		}

		public void four(String x, int y, char[] z) {
		}
	}

	@Test
	public void testGetMethod() throws Exception {

		assertNotNull(ReflectionUtils.getMethod(TestMe.class, "one", ClassUtils.forNames(null)));
		assertNotNull(ReflectionUtils.getMethod(TestMe.class, "two", ClassUtils.forNames(null)));
		assertNotNull(ReflectionUtils.getMethod(TestMe.class, "two", ClassUtils.forNames("")));
		assertEquals(ReflectionUtils.getMethod(TestMe.class, "two", ClassUtils.forNames("")), ReflectionUtils.getMethod(TestMe.class, "two", ClassUtils.forNames("")));
		assertNotNull(ReflectionUtils.getMethod(TestMe.class, "two", ClassUtils.forNames("java.lang.String")));
		assertNotNull(ReflectionUtils.getMethod(TestMe.class, "three", ClassUtils.forNames("java.lang.String[]")));
		assertNotNull(ReflectionUtils.getMethod(TestMe.class, "four", ClassUtils.forNames("java.lang.String, int, char[]")));
	}

	@Test
	public void testGetConstructor() throws Exception {

		ReflectionUtils.getConstructor(TestMe.class, null);
		ReflectionUtils.getConstructor(TestMe.class, ClassUtils.EMPTY_CLASS_ARRAY);
		ReflectionUtils.getConstructor(TestMe.class, ClassUtils.forNames(null));
		ReflectionUtils.getConstructor(TestMe.class, ClassUtils.forNames(""));
		ReflectionUtils.getConstructor(TestMe.class, ClassUtils.forNames("java.lang.String"));

	}
}
