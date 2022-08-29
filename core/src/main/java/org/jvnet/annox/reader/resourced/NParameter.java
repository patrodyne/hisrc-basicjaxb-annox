package org.jvnet.annox.reader.resourced;

import java.util.List;

import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "parameter")
@XmlType(name = "parameterType")
public class NParameter {
	@XmlAttribute
	public int index;
	@XmlAnyElement
	public List<Element> content;

}
