package org.jvnet.basicjaxb_annox.reader.resourced.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.model.XClass;
import org.jvnet.basicjaxb_annox.model.XField;
import org.jvnet.basicjaxb_annox.model.XMethod;
import org.jvnet.basicjaxb_annox.reader.XReader;
import org.jvnet.basicjaxb_annox.reader.resourced.ResourcedXReader;

public class ResourcedXReaderTests
{
	protected XReader xreader;

	@BeforeEach
	protected void setUp() throws Exception
	{
		this.xreader = new ResourcedXReader();
	}

	@Test
	public void testOne() throws Exception {

		final XClass xone = xreader.getXClass(One.class);
		assertNotNull(xone);
		assertTrue(xone.isAnnotationPresent(Alpha.class));
		{
			Alpha alpha = xone.getAnnotation(Alpha.class);
			assertEquals(2, alpha.longField());
			assertEquals(3, alpha.intField());
			assertEquals(4, alpha.shortField());
			assertEquals('g', alpha.charField());
			assertEquals(6, alpha.byteField());
			assertEquals(6.0, alpha.doubleField(), 0.001);
			assertEquals(7, alpha.floatField(), 0.001);
			assertEquals(true, alpha.booleanField());
			assertEquals("nine", alpha.stringField());
			assertEquals(Epsilon.TEN, alpha.enumField());
			assertEquals(Twelve.class, alpha.classField());
			assertSame(xone, xreader.getXClass(One.class));
		}
		
		// Check field
		final XField xfieldA =
			xreader.getXField(One.class.getField("fieldA"));
		Alpha ann_fieldA = xfieldA.getAnnotation(Alpha.class);
		assertEquals(16, ann_fieldA.intField());

		// Check methods
		final XMethod xgetFieldA =
			xreader.getXMethod(One.class.getMethod("getFieldA"));
		Alpha ann_getFieldA = xgetFieldA.getAnnotation(Alpha.class);
		assertEquals(32, ann_getFieldA.shortField());

		final XMethod xsetFieldA =
			xreader.getXMethod(One.class.getMethod("setFieldA", int.class));
		Alpha ann_setFieldA = xsetFieldA.getAnnotation(Alpha.class);
		assertEquals('Z', ann_setFieldA.charField());

		final XMethod xsetFieldB1 =
			xreader.getXMethod(One.class .getMethod("setFieldB"));
		Alpha ann_setFieldB1 = xsetFieldB1.getAnnotation(Alpha.class);
		assertEquals(64, ann_setFieldB1.byteField());

		final XMethod xsetFieldB2 =
			xreader.getXMethod(One.class.getMethod( "setFieldB", String.class));
		Alpha ann_setFieldB2 = xsetFieldB2.getAnnotation(Alpha.class);
		assertEquals(65, ann_setFieldB2.doubleField(), 0.001);

		{
			final XMethod xmethod =
				xreader.getXMethod(One.class.getMethod( "setFieldC", String.class, int.class));
			final Alpha alpha = xmethod.getAnnotation(Alpha.class);
			assertEquals(128, alpha.floatField(), 0.001);
			final Annotation[][] pa = xmethod.getParameterAnnotations();
			assertEquals(2, pa.length);
			assertEquals(0, pa[0].length);
			assertEquals(1, pa[1].length);
			assertEquals("int", ((Alpha) pa[1][0]).stringField());
		}

		{
			final XMethod xmethod =
				xreader.getXMethod(One.class.getMethod( "setFieldC", String.class, int.class, byte.class));
			Alpha alpha = xmethod.getAnnotation(Alpha.class);
			assertEquals(129, alpha.floatField(), 0.001);
			final Annotation[][] pa = xmethod.getParameterAnnotations();
			assertEquals(3, pa.length);
			assertEquals(1, pa[0].length);
			assertEquals(0, pa[1].length);
			assertEquals(1, pa[2].length);
			assertEquals("java.lang.String", ((Alpha) pa[0][0]) .stringField());
			assertEquals("byte", ((Alpha) pa[2][0]).stringField());
		}

		{
			final XMethod xmethod =
				xreader.getXMethod(One.class.getMethod( "setFieldC", int.class, byte.class));
			Alpha alpha = xmethod.getAnnotation(Alpha.class);
			assertEquals(130, alpha.floatField(), 0.001);

			final Annotation[][] pa = xmethod.getParameterAnnotations();
			assertEquals(2, pa.length);
			assertEquals(1, pa[0].length);
			assertEquals(1, pa[1].length);
			assertEquals("int", ((Alpha) pa[0][0]).stringField());
			assertEquals("byte", ((Alpha) pa[1][0]).stringField());
		}
	}

}
