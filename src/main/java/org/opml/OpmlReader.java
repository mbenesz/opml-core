package org.opml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Reader which returns an OPML object from a or string file reference
 */
public class OpmlReader {

	private final Reader reader;

	public OpmlReader(File fileToRead) throws FileNotFoundException {
		this(new FileReader(fileToRead));
	}

	public OpmlReader(String opmlContents) {
		this(new StringReader(opmlContents));
	}

	public OpmlReader(Reader reader) {
		this.reader = reader;
	}

	/**
	 * Unmarshall the OPML contents provided during construction into an OpmlType object model
	 * 
	 * @return the OpmlType object model
	 */
	public OpmlType read() throws OpmlReaderException {
		assert this.reader != null;

		try {

			// Prepare JAXB objects
			JAXBContext jc = JAXBContext.newInstance("org.opml");
			Unmarshaller u = jc.createUnmarshaller();

			// Create an XMLReader to use with our filter
			XMLReader reader = XMLReaderFactory.createXMLReader();

			// Create the filter (to add namespace) and set the xmlReader as its parent.
			NamespaceFilter inFilter = new NamespaceFilter("http://www.dlese.org/Metadata/opml", true);
			inFilter.setParent(reader);

			// Prepare the input, in this case a java.io.File (output)
			InputSource is = new InputSource(this.reader);

			// Create a SAXSource specifying the filter
			SAXSource source = new SAXSource(inFilter, is);

			// Do unmarshalling
			@SuppressWarnings("unchecked")
			JAXBElement<OpmlType> myJaxbObject = (JAXBElement<OpmlType>) u.unmarshal(source);

			return myJaxbObject.getValue();

		} catch (Exception e) {
			throw new OpmlReaderException("Failed to unmarshall OPML file", e);
		}

	}
}
