package org.opml.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.opml.Opml;

public class OpmlReaderConstructionsTest {

    @Test
    public void testOpmlReaderFile() throws FileNotFoundException {

        Opml opml = new OpmlReader(new File("src/test/resources/examples/sample.opml")).read();
        OpmlReaderCommonAssertions.checkFile(opml);
        Assert.assertEquals("String", opml.getHead().getTitle());
    }

    @Test
    public void testOpmlReaderString() throws FileNotFoundException {

        @SuppressWarnings("resource")
        String contents = new Scanner(new File("src/test/resources/examples/sample.opml")).useDelimiter("\\Z").next();
        Opml opml = new OpmlReader(contents).read();
        OpmlReaderCommonAssertions.checkFile(opml);
    }

    @Test
    public void testOpmlReaderReader() throws FileNotFoundException {

        FileReader reader = new FileReader("src/test/resources/examples/sample.opml");
        Opml opml = new OpmlReader(reader).read();
        OpmlReaderCommonAssertions.checkFile(opml);
    }

}
