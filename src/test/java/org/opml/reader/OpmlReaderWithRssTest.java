package org.opml.reader;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.opml.Opml;
import org.opml.Outline;
import org.opml.RssOutline;

public class OpmlReaderWithRssTest {

    @Test
    public void testReadSubscriptionListFile() throws Exception {

        Opml opml = new OpmlReader(new File("src/test/resources/examples/rss/subscriptionList.opml")).read();
        OpmlReaderCommonAssertions.checkFile(opml);
        List<Outline> outlines = opml.getBody().getOutlines();
        Assert.assertEquals(13, outlines.size());
        for (Outline outline : outlines) {
            Assert.assertEquals(RssOutline.class, outline.getClass());
            RssOutline rss = (RssOutline) outline;
            Assert.assertNotNull(rss.getText());
            Assert.assertNotNull(rss.getDescription());
            Assert.assertNotNull(rss.getTitle());
            Assert.assertNotNull(rss.getHtmlUrl());
            Assert.assertNotNull(rss.getXmlUrl());
        }

        RssOutline wired = (RssOutline) outlines.get(12);
        Assert.assertEquals("Wired News", wired.getText());
        Assert.assertEquals(
                "Technology, and the way we do business, is changing the world we know. Wired News is a technology - and business-oriented news service feeding an intelligent, discerning audience. What role does technology play in the day-to-day living of your life? Wired News tells you. How has evolving technology changed the face of the international business world? Wired News puts you in the picture.",
                wired.getDescription());
        Assert.assertEquals(new URL("http://www.wired.com/"), wired.getHtmlUrl());
        Assert.assertEquals("RSS", wired.getVersion());
        Assert.assertEquals("unknown", wired.getLanguage());
        Assert.assertEquals(new URL("http://www.wired.com/news_drop/netcenter/netcenter.rdf"), wired.getXmlUrl());
    }

    @Test
    public void testReadBigPicEcoNewsFile() throws Exception {

        Opml opml = new OpmlReader(new File("src/test/resources/examples/rss/BigPicEcoNews.aspx.xml")).read();

        List<Outline> outlines = opml.getBody().getOutlines();
        Assert.assertEquals(1, outlines.size());
        Outline groupOutline = outlines.get(0);
        Assert.assertEquals(Outline.class, groupOutline.getClass());

        outlines = groupOutline.getOutlines();
        Assert.assertEquals(6, outlines.size());
        for (Outline outline : outlines) {
            Assert.assertEquals(RssOutline.class, outline.getClass());
            RssOutline rss = (RssOutline) outline;
            Assert.assertNotNull(rss.getText());
            Assert.assertNotNull(rss.getTitle());
            Assert.assertNotNull(rss.getHtmlUrl());
            Assert.assertNotNull(rss.getXmlUrl());
        }

        RssOutline treehugger = (RssOutline) outlines.get(4);
        Assert.assertEquals("Treehugger", treehugger.getText());
        Assert.assertEquals(new URL("http://www.treehugger.com/"), treehugger.getHtmlUrl());
        Assert.assertEquals(new URL("http://www.treehugger.com/index.xml"), treehugger.getXmlUrl());
    }

    @Test
    public void testReadOpmlFileExampleFile() throws Exception {

        Opml opml = new OpmlReader(new File("src/test/resources/examples/rss/google-reader-subscriptions.xml")).read();

        List<Outline> outlines = opml.getBody().getOutlines();
        Assert.assertEquals(14, outlines.size());
        for (Outline outline : outlines) {
            Assert.assertEquals(RssOutline.class, outline.getClass());
            RssOutline rss = (RssOutline) outline;
            Assert.assertNotNull(rss.getText());
            Assert.assertNotNull(rss.getTitle());
            Assert.assertNotNull(rss.getHtmlUrl());
            Assert.assertNotNull(rss.getXmlUrl());
        }

        RssOutline last = (RssOutline) outlines.get(9);
        Assert.assertEquals("Matt Mullenweg", last.getTitle());
    }
}
