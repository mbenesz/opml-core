package org.opml.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.opml.Opml;
import org.opml.Outline;

/**
 * Test parsing of the various example OPML files
 * 
 */
public class OpmlReaderExamplesTest {

    private static Logger log = Logger.getLogger(OpmlReaderExamplesTest.class.getName());

    @Test
    public void testReadCategoryFile() throws FileNotFoundException {

        Opml opml = this.readFile("src/test/resources/examples/category.opml");
        Assert.assertEquals("/Philosophy/Baseball/Mets,/Tourism/New York", opml.getBody().getOutlines().get(0)
                .getCategory());

    }

    @Test
    public void testReadPresentationFile() throws FileNotFoundException {

        Opml opml = this.readFile("src/test/resources/examples/presentation.opml");
        Assert.assertEquals(17, opml.getBody().getOutlines().size());
        for (Outline outline : opml.getBody().getOutlines()) {
            Assert.assertFalse(outline.getOutlines().isEmpty());
        }
    }

    @Test
    public void testReadSpecificationFile() throws FileNotFoundException {

        Opml opml = this.readFile("src/test/resources/examples/specification.opml");
        Assert.assertEquals(4, opml.getBody().getOutlines().size());
        for (Outline outline : opml.getBody().getOutlines()) {
            Assert.assertFalse(outline.getOutlines().isEmpty());
        }
    }

    @Test
    public void testReadStatesFile() throws FileNotFoundException {

        Opml opml = this.readFile("src/test/resources/examples/states.opml");
        Assert.assertEquals(1, opml.getBody().getOutlines().size());
        Outline secondLevel = opml.getBody().getOutlines().get(0);
        Assert.assertEquals(8, secondLevel.getOutlines().size());
        Outline thirdLevel = secondLevel.getOutlines().get(0);
        Assert.assertEquals(6, thirdLevel.getOutlines().size());
        Outline fourthLevel = thirdLevel.getOutlines().get(3);
        Assert.assertEquals(4, fourthLevel.getOutlines().size());
        Assert.assertEquals("Nevada", fourthLevel.getText());
    }

    private Opml readFile(String fileName) throws FileNotFoundException {

        OpmlReaderExamplesTest.log.log(Level.INFO, "Testing read of file: " + fileName);
        Opml opml = new OpmlReader(new File(fileName)).read();
        OpmlReaderCommonAssertions.checkFile(opml);
        return opml;
    }

}
