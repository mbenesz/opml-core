package org.opml;

/**
 * Root object for an OPML heirarchy
 */
public class Opml {

    private String version;
    private Head head;
    private Body body;

    public Head getHead() {

        return this.head;
    }

    public void setHead(Head head) {

        this.head = head;
    }

    public Body getBody() {

        return this.body;
    }

    public void setBody(Body body) {

        this.body = body;
    }

    public void setVersion(String version) {

        this.version = version;
    }

    public String getVersion() {

        return this.version;
    }

}
