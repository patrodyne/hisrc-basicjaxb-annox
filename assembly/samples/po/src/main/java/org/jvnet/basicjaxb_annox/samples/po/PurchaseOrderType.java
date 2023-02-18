package org.jvnet.basicjaxb_annox.samples.po;

import javax.xml.datatype.XMLGregorianCalendar;

public class PurchaseOrderType
{

	protected USAddress shipTo;
	public USAddress getShipTo() { return shipTo; }
	public void setShipTo(USAddress value) { this.shipTo = value; }

	protected USAddress billTo;
	public USAddress getBillTo() { return billTo; }
	public void setBillTo(USAddress value) { this.billTo = value; }

	protected String comment;
	public String getComment() { return comment; }
	public void setComment(String value) { this.comment = value; }

	protected Items items;
	public Items getItems() { return items; }
	public void setItems(Items value) { this.items = value; }

	protected XMLGregorianCalendar orderDate;
	public XMLGregorianCalendar getOrderDate() { return orderDate; }
	public void setOrderDate(XMLGregorianCalendar value) { this.orderDate = value; }
}
