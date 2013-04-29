package org.opml;

import java.net.URL;

/**
 * Outline of type Link
 * 
 */
public class LinkOutline extends Outline {

    private URL url;

    public LinkOutline() {

        this.setType("link");
    }

    public void setUrl(URL url) {

        this.url = url;
    }

    public URL getUrl() {

        return this.url;
    }

}
