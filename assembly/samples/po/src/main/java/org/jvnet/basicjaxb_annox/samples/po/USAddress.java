package org.jvnet.basicjaxb_annox.samples.po;

import java.math.BigDecimal;

public class USAddress
{
	protected String name;
	public String getName() { return name; }
	public void setName(String value) { this.name = value; }

	protected String street;
	public String getStreet() { return street; }
	public void setStreet(String value) { this.street = value; }

	protected String city;
	public String getCity() { return city; }
	public void setCity(String value) { this.city = value; }

	protected String state;
	public String getState() { return state; }
	public void setState(String value) { this.state = value; }

	protected BigDecimal zip;
	public BigDecimal getZip() { return zip; }
	public void setZip(BigDecimal value) { this.zip = value; }

	protected String country;
	public String getCountry()
	{
		if (country == null)
			return "US";
		else
			return country;
	}
	public void setCountry(String value)
	{
		this.country = value;
	}
}
