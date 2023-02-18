package org.jvnet.basicjaxb_annox.samples.po;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;

public class ObjectFactory
{
	private final static QName _PurchaseOrder_QNAME = new QName("", "purchaseOrder");
	private final static QName _Comment_QNAME = new QName("", "comment");

	public ObjectFactory()
	{
	}

	public PurchaseOrderType createPurchaseOrderType()
	{
		return new PurchaseOrderType();
	}

	public Items.Item createItemsItem()
	{
		return new Items.Item();
	}

	public Items createItems()
	{
		return new Items();
	}

	public USAddress createUSAddress()
	{
		return new USAddress();
	}

	public JAXBElement<PurchaseOrderType> createPurchaseOrder(PurchaseOrderType value)
	{
		return new JAXBElement<PurchaseOrderType>(_PurchaseOrder_QNAME, PurchaseOrderType.class, null, value);
	}

	public JAXBElement<String> createComment(String value)
	{
		return new JAXBElement<String>(_Comment_QNAME, String.class, null, value);
	}
}
