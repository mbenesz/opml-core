package org.opml.reader;

/**
 * Exception for problems occurring during reading OPML contents
 */
public class OpmlReaderException extends RuntimeException {

	private static final long serialVersionUID = 3669833048789213607L;

	public OpmlReaderException(String message, Exception cause) {
		super(message, cause);
	}

}
