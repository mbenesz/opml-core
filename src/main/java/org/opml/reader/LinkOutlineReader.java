package org.opml.reader;

import java.net.URL;

import javax.xml.stream.XMLStreamReader;

import org.opml.LinkOutline;
import org.opml.Outline;

/**
 * Read attributes of a Link-type Outline and return a populated instance
 * 
 */
public class LinkOutlineReader extends OutlineReader {

    @Override
    public Outline read(XMLStreamReader streamReader) {

        LinkOutline outline = new LinkOutline();
        this.parseAndSetAttributes(streamReader, outline);
        this.parseUrlAttribute(streamReader, outline);
        return outline;
    }

    private void parseUrlAttribute(XMLStreamReader streamReader, LinkOutline outline) {

        URL url = OpmlReaderUtils.parseUrl("url", streamReader.getAttributeValue(null, "url"));
        outline.setUrl(url);
    }

    @Override
    protected String getOutlineType() {

        return "link";
    }

}
