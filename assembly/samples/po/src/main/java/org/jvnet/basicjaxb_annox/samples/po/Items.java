package org.jvnet.basicjaxb_annox.samples.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

public class Items
{
	protected List<Items.Item> item;
	public List<Items.Item> getItem()
	{
		if (item == null)
			item = new ArrayList<Items.Item>();
		return this.item;
	}

	public static class Item
	{
		protected String productName;
		public String getProductName() { return productName; }
		public void setProductName(String value) { this.productName = value; }

		protected int quantity;
		public int getQuantity() { return quantity; }
		public void setQuantity(int value) { this.quantity = value; }

		protected BigDecimal usPrice;
		public BigDecimal getUSPrice() { return usPrice; }
		public void setUSPrice(BigDecimal value) { this.usPrice = value; }

		protected String comment;
		public String getComment() { return comment; }
		public void setComment(String value) { this.comment = value; }

		protected XMLGregorianCalendar shipDate;
		public XMLGregorianCalendar getShipDate() { return shipDate; }
		public void setShipDate(XMLGregorianCalendar value) { this.shipDate = value; }

		protected String partNum;
		public String getPartNum() { return partNum; }
		public void setPartNum(String value) { this.partNum = value; }
	}
}
