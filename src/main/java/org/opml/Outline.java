package org.opml;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Outline {

    private String text;
    private String type;
    private boolean isCommented;
    private Date created;
    private String category;
    private List<Outline> outlines;

    public Outline() {

        this.outlines = new ArrayList<Outline>();
    }

    public String getText() {

        return this.text;
    }

    public void setText(String text) {

        this.text = text;
    }

    public String getType() {

        return this.type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public boolean isCommented() {

        return this.isCommented;
    }

    public void setCommented(boolean isCommented) {

        this.isCommented = isCommented;
    }

    public Date getCreated() {

        return this.created;
    }

    public void setCreated(Date created) {

        this.created = created;
    }

    public String getCategory() {

        return this.category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public void addOutline(Outline outline) {

        this.outlines.add(outline);
    }

    public List<Outline> getOutlines() {

        return Collections.unmodifiableList(this.outlines);
    }

    @Override
    public String toString() {

        String pattern = "<outline text=\"{0}\" type=\"{1}\" />";
        return MessageFormat.format(pattern, this.getText(), this.getType());
    }
}
