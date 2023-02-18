package org.jvnet.basicjaxb_annox.samples.po.tests;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.glassfish.jaxb.runtime.api.JAXBRIContext;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb_annox.model.XClass;
import org.jvnet.basicjaxb_annox.model.XPackage;
import org.jvnet.basicjaxb_annox.reader.resourced.NParser;
import org.jvnet.basicjaxb_annox.reader.resourced.ResourcedXReader;
import org.jvnet.basicjaxb_annox.reflect.AnnotatedElementException;
import org.jvnet.basicjaxb_annox.reflect.ResourcedAnnotatedElementFactory;
import org.jvnet.basicjaxb_annox.samples.po.Items.Item;
import org.jvnet.basicjaxb_annox.samples.po.ObjectFactory;
import org.jvnet.basicjaxb_annox.samples.po.PurchaseOrderType;
import org.jvnet.basicjaxb_annox.xml.bind.AnnoxAnnotationReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.Unmarshaller;

/**
 * <p>In one sentence, Annox allows you to load Java annotations from XML resources instead 
 * of reading them directly from packages, classes, fields, constructors and methods.</p>
 * 
 * <p>Java 1.5 introduced annotations and they are heavily used in Java EE frameworks.
 * A Java {@link java.lang.annotation.Annotation} is metadata about the program 
 * embedded in the program itself. It can be parsed by the annotation parsing tool
 * or by the compiler.</p>
 * 
 * <p>A {@link org.jvnet.basicjaxb_annox.xml.bind.AnnoxAnnotationReader} </p>
 * 
 * <p>Annox reads annotations from XML resources associated with classes and packages.
 * By default, the XML resource for a given class name is derived by adding the suffix
 * <code>.ann.xml.</code></p>
 * 
 */
public class AnnotationReaderTest
{
    private Logger logger = LoggerFactory.getLogger(AnnotationReaderTest.class);
    public Logger getLogger() { return logger; }
    
    /**
     * <b>Annox</b> can parse Java annotations from XML files and bind them to their
     * associated classes, fields, methods, etc. <b>Annox</b> defines its own JAXB
     * context using classes in its <code>org.jvnet.basicjaxb_annox.reader.resourced</code>
     * package. This test verifies the <b>Annox</b> context and logs its generated schema.
     */
    @Test
    public void testNParser()
    {
    	try
    	{
	    	NParser nParser = new NParser();
	    	assertNotNull(nParser.getContext(), "NParser must have a JAXB context.");
			String xmlSchema = createXmlSchema(nParser.getContext());
	        getLogger().debug("ANNOX Xml Schema from String:\n\n" + xmlSchema);
    	}
		catch (IOException ex)
		{
			String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
			getLogger().error(msg, ex);
			fail(msg);
		}
    }
    
    /**
     * <p><b>Annox</b> can apply Java annotations to an existing classes in a
     * given package using an XML resource file in the package path.
     * The resource file is named <code>package-info.ann.xml</code>.</p>
     */
	@Test
    public void testReadPackageAnnoxXml()
    {
		ClassLoader poClassLoader = ObjectFactory.class.getClassLoader();
		Package poPackage = ObjectFactory.class.getPackage();
		String poPackageAnnoxXmlName = getAnnoxResourceName(poPackage);
    	try ( InputStream is = poClassLoader.getResourceAsStream(poPackageAnnoxXmlName) )
    	{
    		assertNotNull(is, "PO ObjectFactory should have an annox xml stream.");
    		if ( getLogger().isDebugEnabled())
    		{
        		String poPackageAnnoxXml = new String(is.readAllBytes(), UTF_8);
        		getLogger().debug("ANNOX " + poPackageAnnoxXmlName + ":\n" + poPackageAnnoxXml);
    		}
    	}
		catch (IOException ex)
		{
			getLogger().error(ex.getClass().getSimpleName()+": "+ex.getMessage(), ex);
		}
    }
    
    /**
     * <p><b>Annox</b> can apply Java annotations to an existing class using
     * a XML resource file found in the same class path. The resource file
     * appends the suffix <code>.ann.xml</code> to the class name.</p>
     */
	@Test
    public void testReadItemsItemAnnoxXml()
    {
		ClassLoader poClassLoader = ObjectFactory.class.getClassLoader();
		Class<Item> poItemsItemClass = org.jvnet.basicjaxb_annox.samples.po.Items.Item.class;
		String poItemItemsAnnoxXmlName = getAnnoxResourceName(poItemsItemClass);
    	try ( InputStream is = poClassLoader.getResourceAsStream(poItemItemsAnnoxXmlName) )
    	{
    		assertNotNull(is, "PO Item Items Class should have an annox xml stream.");
    		if ( getLogger().isDebugEnabled())
    		{
        		String poItemsItemAnnoxXml = new String(is.readAllBytes(), UTF_8);
        		getLogger().debug("ANNOX " + poItemItemsAnnoxXmlName + ":\n" + poItemsItemAnnoxXml);
    		}
    	}
		catch (IOException ex)
		{
			getLogger().error(ex.getClass().getSimpleName()+": "+ex.getMessage(), ex);
		}
    }
    
	/**
	 * <p><b>Annox</b> reads <em>resourced</em> package and class annotations
	 * into an {@link XPackage}.
	 */
	@Test
    public void testGetXPackage()
    {
		try
		{
	    	ResourcedXReader rxr = new ResourcedXReader();
	    	XPackage poXPackage = rxr.getXPackage(ObjectFactory.class.getPackage());
	    	assertNotNull(poXPackage, "PO XPackage should exist.");
	    	assertNotEquals(XPackage.VOID, poXPackage, "PO XPackage should not by VOID");
	    	
	    	getLogger().debug("Package: " + poXPackage.getPackage());
	    	
	    	for ( Annotation annotation : poXPackage.getAnnotations() )
	    	{
	    		getLogger().debug("Package Annotation: " + annotation);
	    	}
	    	
	    	for ( XClass xclass : poXPackage.getClasses() )
	    	{
	    		getLogger().debug("Package XClass: " + xclass.getTargetClass());
	    	}
		}
		catch (AnnotatedElementException ex)
		{
			getLogger().error(ex.getClass().getSimpleName()+": "+ex.getMessage(), ex);
		}
    }

	/**
	 * <p><b>Annox</b> reads <em>resourced</em> class annotations
	 * into an {@link XClass} for {@link org.jvnet.basicjaxb_annox.samples.po.Items.Item}
	 */
	@Test
    public void testGetXClassItemItems()
    {
		try
		{
	    	ResourcedXReader rxr = new ResourcedXReader();
	    	XClass poItemItemsXClass = rxr.getXClass(org.jvnet.basicjaxb_annox.samples.po.Items.Item.class);
	    	assertNotNull(poItemItemsXClass, "PO Item.Items XClass should exist.");
	    	assertNotEquals(XClass.VOID, poItemItemsXClass, "PO Item.Items XClass should not by VOID");

    		getLogger().debug("PO Item.Items XClass: " + poItemItemsXClass.getTargetClass());
	    	for ( Annotation annotation : poItemItemsXClass.getAnnotations() )
	    		getLogger().debug("PO Item.Items Annotation: " + annotation);
		}
		catch (AnnotatedElementException ex)
		{
			getLogger().error(ex.getClass().getSimpleName()+": "+ex.getMessage(), ex);
		}
    }

	/**
	 * <p>Construct a default {@link AnnoxAnnotationReader}. The default constructor
	 * binds a {@link DualAnnotatedElementFactory} which itself binds to a
	 * {@link ResourcedAnnotatedElementFactory} and a {@link DirectAnnotatedElementFactory}.</p>
	 * 
	 * <ul>
	 * <li>The {@link ResourcedAnnotatedElementFactory} reads annotations from XML files.</li>
	 * <li>The {@link DirectAnnotatedElementFactory} reflects annotations from Java classes.</li>
	 * </ul>
	 * 
	 * <p>A {@link JAXBContext} accepts provider-specific properties including a property
	 * to specify to an alternative {@link RuntimeAnnotationReader} implementation.</p>
	 */
	@Test
	public void testAnnotationReader()
	{
		final RuntimeAnnotationReader annotationReader = new AnnoxAnnotationReader();
		final Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(JAXBRIContext.ANNOTATION_READER, annotationReader);
		
		try
		{
			String pn = ObjectFactory.class.getPackageName();
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			final JAXBContext context = JAXBContext.newInstance(pn, cl, properties);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			final JAXBElement<PurchaseOrderType> purchaseOrderElement = 
				(JAXBElement<PurchaseOrderType>) unmarshaller.unmarshal(getClass().getResource("po.xml"));
			
			final PurchaseOrderType purchaseOrder = purchaseOrderElement.getValue();
			assertNotNull(purchaseOrder.getOrderDate());
			assertNotNull(purchaseOrder.getShipTo());
			assertNotNull(purchaseOrder.getBillTo());
			assertEquals("Hurry, my lawn is going wild!", purchaseOrder.getComment());
			assertNotNull(purchaseOrder.getItems());
			assertEquals(2, purchaseOrder.getItems().getItem().size());
			assertEquals("Confirm this is electric", purchaseOrder.getItems().getItem().get(0).getComment());

			String xmlSchema = createXmlSchema(context);
            getLogger().debug("PO Xml Schema from String:\n\n" + xmlSchema);
		}
		catch (JAXBException | IOException ex)
		{
			String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
			getLogger().error(msg, ex);
			fail(msg);
		}
	}
	
	// Convert a package name into a Annox resource name.
	private String getAnnoxResourceName(Package thePackage)
	{
		return (thePackage == null) ? "" : getAnnoxResourceName(thePackage.getName(), "/package-info.ann.xml");
	}

	// Convert a class name into a Annox resource name.
	private String getAnnoxResourceName(Class<?> theClass)
	{
		return getAnnoxResourceName(theClass.getName(), ".ann.xml");
	}
	
	// Convert a 'dot' name into a Annox resource name.
	private String getAnnoxResourceName(String name, String suffix)
	{
		return name.replace('.', '/') + suffix;
	}
	
	// Create an XML Schema from a JAXBContext context.
    private String createXmlSchema(JAXBContext context) throws IOException
    {
		StringWriter sw = new StringWriter();
        SchemaOutputResolver sor = new SchemaOutputResolver()
		{
			@Override
			public Result createOutput(String namespaceURI, String systemId)
				throws IOException
			{
				StreamResult sr = new StreamResult(sw);
				sr.setSystemId(systemId);
		        return sr;
			}
		};
        context.generateSchema(sor);
        return sw.toString();
    }
}
