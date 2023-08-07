package org.jvnet.basicjaxb_annox.reader.resourced;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;

import org.jvnet.basicjaxb_annox.io.NestedIOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

public class NParser {

	private final JAXBContext context;

	public NParser(JAXBContext context) {
		requireNonNull(context);
		this.context = context;
	}

	public NParser() {
		try {
			this.context = JAXBContext.newInstance(NPackage.class);
		} catch (JAXBException jaxbex) {
			throw new ExceptionInInitializerError(jaxbex);
		}
	}

	public JAXBContext getContext() {
		return context;
	}

	public NPackage parseNPackage(InputStream is) throws IOException {
		requireNonNull(is);
		try {
			final Object result = getContext().createUnmarshaller().unmarshal(
					is);
			return (NPackage) result;
		} catch (JAXBException jaxbex) {
			throw new NestedIOException(jaxbex);
		}

	}

	public NClass parseNClass(InputStream is) throws IOException {
		requireNonNull(is);
		try {
			final Object result = getContext().createUnmarshaller().unmarshal(
					is);
			return (NClass) result;
		} catch (JAXBException jaxbex) {
			throw new NestedIOException(jaxbex);
		}
	}

}
