package org.opml.reader;

import java.util.List;

import org.junit.Assert;
import org.opml.Body;
import org.opml.Opml;
import org.opml.Outline;

public class OpmlReaderCommonAssertions {

    public static void checkFile(Opml opml) {

        Assert.assertNotNull(opml);
        Assert.assertNotNull(opml.getHead());
        Assert.assertNotNull(opml.getVersion());
        Assert.assertNotNull(opml.getBody());
        Body body = opml.getBody();
        List<Outline> outlines = body.getOutlines();
        Assert.assertNotNull(outlines);
        Assert.assertFalse(outlines.isEmpty());
        Outline outline = outlines.get(0);
        Assert.assertNotNull(outline.getText());
    }

}
