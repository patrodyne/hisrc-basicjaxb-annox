package org.jvnet.basicjaxb_annox.util.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.util.ArrayUtils;

public class ArrayUtilsTest
{
	@Test
	public void testToObjectArray()
		throws Exception
	{
		final int[] ein = null;
		final Integer[] one = ArrayUtils.asObjectArray(ein);
		final int[] zwei = new int[] {};
		final Integer[] two = ArrayUtils.asObjectArray(zwei);
		final int[] drei = new int[] { 1, 2 };
		final Integer[] three = ArrayUtils.asObjectArray(drei);
		final int[] un = ArrayUtils.asPrimitiveArray(one);
		final int[] deux = ArrayUtils.asPrimitiveArray(two);
		final int[] troi = ArrayUtils.asPrimitiveArray(three);
		assertTrue(Arrays.equals(ein, un), "Wrong value.");
		assertTrue(Arrays.equals(zwei, deux), "Wrong value.");
		assertTrue(Arrays.equals(drei, troi), "Wrong value.");
	}
}
