package org.opml.reader;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utilities for reading Opml files
 * 
 */
public class OpmlReaderUtils {

    private static final Logger log = Logger.getLogger(OpmlReader.class.getName());
    private static final DateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
    private static final DateFormat RFC822_DATE_FORMAT = new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss z");

    public static URL parseUrl(String nodeName, String rawText) {

        if (rawText == null) {
            return null;
        }
        URL parsedUrl;
        try {
            parsedUrl = new URL(rawText);
        } catch (MalformedURLException e) {
            OpmlReaderUtils.log.log(Level.WARNING, nodeName + " was not a valid URL (" + rawText + ")");
            return null;
        }
        return parsedUrl;
    }

    public static Date parseDate(String nodeName, String rawText) {

        if (rawText == null) {
            return null;
        }
        try {
            return OpmlReaderUtils.RFC822_DATE_FORMAT.parse(rawText);
        } catch (ParseException e) {
            try {
                return OpmlReaderUtils.ISO8601_DATE_FORMAT.parse(rawText);
            } catch (ParseException pe) {
                OpmlReaderUtils.log.log(Level.WARNING, nodeName + " was not a valid RFC822 datetime (" + rawText + ")");
                return null;
            }
        }
    }

}
