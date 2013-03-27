package org.opml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.opml.reader.OpmlReader;

public class OpmlReaderTest {

	@Test
	public void testOpmlReaderFile() throws FileNotFoundException {
		Opml opml = new OpmlReader(new File("src/test/resources/sample.opml")).read();
		checkFile(opml);
	}

	@Test
	public void testOpmlReaderString() throws FileNotFoundException {
		@SuppressWarnings("resource")
		String contents = new Scanner(new File("src/test/resources/sample.opml")).useDelimiter("\\Z").next();
		Opml opml = new OpmlReader(contents).read();
		checkFile(opml);
	}

	@Test
	public void testOpmlReaderReader() throws FileNotFoundException {
		FileReader reader = new FileReader("src/test/resources/sample.opml");
		Opml opml = new OpmlReader(reader).read();
		checkFile(opml);
	}

	private void checkFile(Opml opml) {
		Assert.assertNotNull(opml);
		Assert.assertEquals("String", opml.getHead().getTitle());
	}

}
