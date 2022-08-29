package org.jvnet.annox.samples.po.tests;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.glassfish.jaxb.runtime.api.JAXBRIContext;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;
import org.jvnet.annox.samples.po.ObjectFactory;
import org.jvnet.annox.samples.po.PurchaseOrderType;
import org.jvnet.annox.xml.bind.AnnoxAnnotationReader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class AnnotationReaderTest extends TestCase {

	public void testAnnotationReader() {

		final RuntimeAnnotationReader annotationReader = new AnnoxAnnotationReader();

		final Map<String, Object> properties = new HashMap<String, Object>();

		properties.put(JAXBRIContext.ANNOTATION_READER, annotationReader);

		try 
		{
			final JAXBContext context = JAXBContext.newInstance
			(
				ObjectFactory.class.getPackageName(),
				Thread.currentThread().getContextClassLoader(),
				properties
			);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();

		@SuppressWarnings("unchecked")
			final JAXBElement<PurchaseOrderType> purchaseOrderElement =
				(JAXBElement<PurchaseOrderType>) unmarshaller.unmarshal(getClass().getResource("po.xml"));

		final PurchaseOrderType purchaseOrder = purchaseOrderElement.getValue();

		assertNotNull(purchaseOrder.getOrderDate());
		assertNotNull(purchaseOrder.getShipTo());
		assertNotNull(purchaseOrder.getBillTo());
		assertEquals("Hurry, my lawn is going wild!", purchaseOrder
				.getComment());
		assertNotNull(purchaseOrder.getItems());
		assertEquals(2, purchaseOrder.getItems().getItem().size());
		assertEquals("Confirm this is electric", purchaseOrder.getItems()
				.getItem().get(0).getComment());
		}
		catch (JAXBException ex)
		{
			ex.printStackTrace();
			fail(ex.getClass().getSimpleName()+": "+ex.getMessage());
		}
	}

}
