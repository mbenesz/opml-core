package org.opml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Body {

    private List<Outline> outlines;

    public Body() {

        this.outlines = new ArrayList<Outline>();
    }

    public void addOutline(Outline outline) {

        this.outlines.add(outline);
    }

    public List<Outline> getOutlines() {

        return Collections.unmodifiableList(this.outlines);
    }

}
