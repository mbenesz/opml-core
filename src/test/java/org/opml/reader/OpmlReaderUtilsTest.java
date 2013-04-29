package org.opml.reader;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class OpmlReaderUtilsTest {

    @Test
    public void testParseUrl() {

        URL url = OpmlReaderUtils.parseUrl("Test", "http://ddg.gg");
        Assert.assertNotNull(url);
        Assert.assertEquals("http://ddg.gg", url.toString());
    }

    @Test
    public void testParseRFC822Date() {

        String input = "2013-02-26T20:15:00+0200";
        Date date = OpmlReaderUtils.parseDate("Test", input);
        System.out.println(date);
        Assert.assertNotNull(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        Assert.assertEquals(27, cal.get(Calendar.DATE));
        Assert.assertEquals(Calendar.FEBRUARY, cal.get(Calendar.MONTH));
        Assert.assertEquals(2013, cal.get(Calendar.YEAR));
        Assert.assertEquals(2, cal.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(15, cal.get(Calendar.MINUTE));
        Assert.assertEquals(0, cal.get(Calendar.SECOND));
    }

    @Test
    public void testParseOtherDate() {

        String input = "Tue, 04 Jun 2013 07:34:44 GMT";
        Date date = OpmlReaderUtils.parseDate("Test", input);
        Assert.assertNotNull(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        Assert.assertEquals(4, cal.get(Calendar.DATE));
        Assert.assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
        Assert.assertEquals(2013, cal.get(Calendar.YEAR));
        Assert.assertEquals(15, cal.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(34, cal.get(Calendar.MINUTE));
        Assert.assertEquals(44, cal.get(Calendar.SECOND));
    }
}
