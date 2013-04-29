package org.opml.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.opml.Body;
import org.opml.Head;
import org.opml.Opml;
import org.opml.Outline;

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
     * Unmarshall the OPML contents provided during construction into an
     * OpmlType object model
     * 
     * @return the OpmlType object model
     * @throws OpmlReaderException
     */
    public Opml read() throws OpmlReaderException {

        assert this.reader != null;

        Opml opml = null;

        try {
            XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
            XMLStreamReader streamReader = xmlFactory.createXMLStreamReader(this.reader);

            while (streamReader.hasNext()) {
                int event = streamReader.next();

                switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String localName = streamReader.getLocalName();
                    if ("opml".equalsIgnoreCase(localName)) {
                        opml = new Opml();
                        opml.setVersion(streamReader.getAttributeValue(null, "version"));

                    } else if ("head".equalsIgnoreCase(localName)) {

                        Head head = this.parseHeadElementAndChildren(streamReader);
                        opml.setHead(head);

                    } else if ("body".equalsIgnoreCase(localName)) {

                        Body body = this.parseBodyElementAndChildren(streamReader);
                        opml.setBody(body);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    return opml;
                }
            }

            return null;

        } catch (XMLStreamException e) {
            throw new OpmlReaderException("Caught Exception reading XML stream", e);
        }
    }

    private Head parseHeadElementAndChildren(XMLStreamReader streamReader) throws XMLStreamException {

        Head head = new Head();

        while (streamReader.hasNext()) {
            int event = streamReader.next();

            switch (event) {
            case XMLStreamConstants.START_ELEMENT:
                String localName = streamReader.getLocalName();

                if ("title".equalsIgnoreCase(localName)) {
                    head.setTitle(streamReader.getElementText());

                } else if ("ownerName".equalsIgnoreCase(localName)) {
                    head.setOwnerName(streamReader.getElementText());

                } else if ("ownerEmail".equalsIgnoreCase(localName)) {
                    head.setOwnerEmail(streamReader.getElementText());

                } else if ("ownerId".equalsIgnoreCase(localName)) {
                    URL parsedUrl = OpmlReaderUtils.parseUrl("ownerId", streamReader.getElementText());
                    head.setOwnerId(parsedUrl);

                } else if ("docs".equalsIgnoreCase(localName)) {
                    URL parsedUrl = OpmlReaderUtils.parseUrl("docs", streamReader.getElementText());
                    head.setDocs(parsedUrl);

                } else if ("dateCreated".equalsIgnoreCase(localName)) {
                    Date parsedDate = OpmlReaderUtils.parseDate("dateCreated", streamReader.getElementText());
                    head.setDateCreated(parsedDate);

                } else if ("dateModified".equalsIgnoreCase(localName)) {
                    Date parsedDate = OpmlReaderUtils.parseDate("dateModified", streamReader.getElementText());
                    head.setDateModified(parsedDate);
                }
                break;
            case XMLStreamConstants.END_ELEMENT:
                if ("head".equalsIgnoreCase(streamReader.getLocalName())) {
                    return head;
                }
            }
        }
        return null;
    }

    private Body parseBodyElementAndChildren(XMLStreamReader streamReader) throws XMLStreamException {

        Body body = new Body();

        while (streamReader.hasNext()) {
            int event = streamReader.next();

            switch (event) {
            case XMLStreamConstants.START_ELEMENT:
                String localName = streamReader.getLocalName();

                if ("outline".equalsIgnoreCase(localName)) {
                    Outline outline = this.parseOutlineElementAndChildren(streamReader);
                    body.addOutline(outline);

                }
                break;
            case XMLStreamConstants.END_ELEMENT:
                if ("body".equalsIgnoreCase(streamReader.getLocalName())) {
                    return body;
                }
            }
        }
        return null;
    }

    private Outline parseOutlineElementAndChildren(XMLStreamReader streamReader) throws XMLStreamException {

        String type = streamReader.getAttributeValue(null, "type");
        OutlineReader outlineReader = OutlineFactory.getInstance().getReaderInstance(type);
        Outline outline = outlineReader.read(streamReader);

        while (streamReader.hasNext()) {
            int event = streamReader.next();
            boolean nested = false;

            switch (event) {
            case XMLStreamConstants.START_ELEMENT:
                String localName = streamReader.getLocalName();
                nested = true;

                if ("outline".equalsIgnoreCase(localName)) {
                    Outline childOutline = this.parseOutlineElementAndChildren(streamReader);
                    outline.addOutline(childOutline);

                }
                break;

            case XMLStreamConstants.END_ELEMENT:
                if (!nested) {
                    return outline;
                }
                nested = false;
            }
        }
        return null;
    }

}
