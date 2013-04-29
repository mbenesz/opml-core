package org.opml.reader;

import java.util.Date;

import javax.xml.stream.XMLStreamReader;

import org.opml.Outline;

/**
 * Read attributes of an Outline and return a populated instance
 * 
 */
public class OutlineReader {

    public Outline read(XMLStreamReader streamReader) {

        Outline outline = new Outline();
        this.parseAndSetAttributes(streamReader, outline);
        return outline;
    }

    protected void parseAndSetAttributes(XMLStreamReader streamReader, Outline outline) {

        outline.setType(this.getOutlineType());

        String text = streamReader.getAttributeValue(null, "text");
        outline.setText(text);

        String createdValue = streamReader.getAttributeValue(null, "created");
        Date parsedDate = OpmlReaderUtils.parseDate("created", createdValue);
        outline.setCreated(parsedDate);

        String category = streamReader.getAttributeValue(null, "category");
        outline.setCategory(category);

        String commentedValue = streamReader.getAttributeValue(null, "isComment");
        outline.setCommented(commentedValue == null || Boolean.parseBoolean(commentedValue));
    }

    protected String getOutlineType() {

        return "text";
    }

}
