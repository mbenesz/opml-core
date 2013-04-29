package org.opml;

import java.net.URL;
import java.text.MessageFormat;

/**
 * Outline of type RSS
 * 
 */
public class RssOutline extends Outline {

    private String title;
    private String language;
    private String version;
    private String description;
    private URL htmlUrl;
    private URL xmlUrl;

    public RssOutline() {

        this.setText("rss");
    }

    public String getTitle() {

        return this.title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getLanguage() {

        return this.language;
    }

    public void setLanguage(String language) {

        this.language = language;
    }

    public String getVersion() {

        return this.version;
    }

    public void setVersion(String version) {

        this.version = version;
    }

    public String getDescription() {

        return this.description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public URL getHtmlUrl() {

        return this.htmlUrl;
    }

    public void setHtmlUrl(URL htmlUrl) {

        this.htmlUrl = htmlUrl;
    }

    public URL getXmlUrl() {

        return this.xmlUrl;
    }

    public void setXmlUrl(URL xmlUrl) {

        this.xmlUrl = xmlUrl;
    }

    @Override
    public String toString() {

        String pattern = "<outline text=\"{0}\" title=\"{1}\" type=\"{2}\" xmlUrl=\"{3}\" htmlUrl=\"{4}\" description\"{5}\" />";
        return MessageFormat.format(pattern, this.getText() != null ? this.getText() : "",
                this.title != null ? this.title : "", this.getType(), this.xmlUrl != null ? this.xmlUrl : "",
                this.htmlUrl != null ? this.htmlUrl : "", this.description != null ? this.description : "");
    }
}
