package org.opml.reader;

import java.net.URL;

import javax.xml.stream.XMLStreamReader;

import org.opml.Outline;
import org.opml.RssOutline;

/**
 * Read attributes of a RSS-type Outline and return a populated instance
 * 
 */
public class RssOutlineReader extends OutlineReader {

    @Override
    public Outline read(XMLStreamReader streamReader) {

        RssOutline outline = new RssOutline();
        this.parseAndSetAttributes(streamReader, outline);
        this.parseRssAttributes(streamReader, outline);
        return outline;
    }

    private void parseRssAttributes(XMLStreamReader streamReader, RssOutline outline) {

        String title = streamReader.getAttributeValue(null, "title");
        outline.setTitle(title);

        String language = streamReader.getAttributeValue(null, "language");
        outline.setLanguage(language);

        String version = streamReader.getAttributeValue(null, "version");
        outline.setVersion(version);

        String description = streamReader.getAttributeValue(null, "description");
        outline.setDescription(description);

        URL htmlUrl = OpmlReaderUtils.parseUrl("htmlUrl", streamReader.getAttributeValue(null, "htmlUrl"));
        outline.setHtmlUrl(htmlUrl);

        URL xmlUrl = OpmlReaderUtils.parseUrl("xmlUrl", streamReader.getAttributeValue(null, "xmlUrl"));
        outline.setXmlUrl(xmlUrl);
    }

    @Override
    protected String getOutlineType() {

        return "rss";
    }

}
