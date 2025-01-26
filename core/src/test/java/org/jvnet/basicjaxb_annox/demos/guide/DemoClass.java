package org.jvnet.basicjaxb_annox.demos.guide;

public class DemoClass
{
	public int value = 0;
	public int getValue()
	{
		return this.value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public void setValue()
	{
		this.value = 0;
	}
	
	public void setValue(String s)
	{
		this.value = Integer.valueOf(s);
	}

	public void setValue(String s, int radix)
	{
		this.value = Integer.valueOf(s, radix);
	}

	public int add(int value)
	{
		return this.value += value;
	}

	public DemoClass()
	{
	}

	public DemoClass(int value)
	{
		this.value = value;
	}
}
