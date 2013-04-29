package org.opml.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.opml.LinkOutline;
import org.opml.Opml;
import org.opml.Outline;

public class OpmlReaderWithLinksTest {

    @Test
    public void testReadDirectoryFile() throws FileNotFoundException {

        Opml opml = new OpmlReader(new File("src/test/resources/examples/link/directory.opml")).read();
        OpmlReaderCommonAssertions.checkFile(opml);
        List<Outline> outlines = opml.getBody().getOutlines();
        Assert.assertEquals(8, outlines.size());
        for (Outline outline : outlines) {
            Assert.assertEquals(LinkOutline.class, outline.getClass());
            LinkOutline link = (LinkOutline) outline;
            Assert.assertNotNull(link.getUrl());
        }
    }

    @Test
    public void testReadPublicationsFile() throws FileNotFoundException {

        Opml opml = new OpmlReader(new File("src/test/resources/examples/link/publications.opml")).read();
        OpmlReaderCommonAssertions.checkFile(opml);
        List<Outline> outlines = opml.getBody().getOutlines();
        Assert.assertEquals(4, outlines.size());
        Outline directories = outlines.get(0);
        Assert.assertEquals("Directories", directories.getText());
        Assert.assertEquals(5, directories.getOutlines().size());
        Outline dmoz = directories.getOutlines().get(0);
        Assert.assertEquals(Outline.class, dmoz.getClass());
        Assert.assertEquals(4, dmoz.getOutlines().size());
        for (Outline outline : dmoz.getOutlines()) {
            Assert.assertEquals(LinkOutline.class, outline.getClass());
            LinkOutline link = (LinkOutline) outline;
            Assert.assertNotNull(link.getUrl());
        }
    }

}
