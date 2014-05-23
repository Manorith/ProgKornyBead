package game.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class DOMfunctions {

	public static Document getDocument() throws ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();

		return doc;
	}

	public static void saveInstance(String fileName, Document doc){
		Transformer transformer;

		try{
		transformer = TransformerFactory.newInstance().newTransformer();

		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		transformer.transform(new DOMSource(doc), new StreamResult( new FileOutputStream(new File(DOMfunctions.class.getResource("/" + fileName).getPath()))));
		
		} catch(TransformerFactoryConfigurationError e1){
			e1.printStackTrace();
		} catch(FileNotFoundException e2){
			e2.printStackTrace();
		} catch(TransformerException e3){
			e3.printStackTrace();
		}
	}
	
	public static XMLEventReader loadXMLReader(String fileName){
		XMLEventReader eventReader = null;
		
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = DOMfunctions.class.getClassLoader().getResourceAsStream(fileName);
			eventReader = inputFactory.createXMLEventReader(in);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		
		return eventReader;
	}
}
