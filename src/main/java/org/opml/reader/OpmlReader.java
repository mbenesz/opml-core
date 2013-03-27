package org.opml.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import org.opml.Opml;

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
	public Opml read() throws OpmlReaderException {
		assert this.reader != null;

		return null;

	}
}
