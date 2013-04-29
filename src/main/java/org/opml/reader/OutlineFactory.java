package org.opml.reader;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to return Outline classes based on the type attribute
 * 
 */
public class OutlineFactory {

    public static OutlineFactory instance;
    private Map<String, Class<? extends OutlineReader>> typesToClazzes;
    private Class<? extends OutlineReader> defaultOutlineReader = OutlineReader.class;

    public static OutlineFactory getInstance() {

        if (OutlineFactory.instance == null) {
            OutlineFactory.instance = OutlineFactory.newInstance();
        }
        return OutlineFactory.instance;
    }

    public OutlineReader getReaderInstance(String typeAttribute) {

        Class<? extends OutlineReader> clazz = this.getReader(typeAttribute);
        OutlineReader reader;
        try {
            reader = clazz.newInstance();
        } catch (Exception e) {
            throw new OpmlReaderException("Failed to instantiate OutlineReader from class " + clazz.getCanonicalName(),
                    e);
        }
        return reader;
    }

    void register(String typeAttribute, Class<? extends OutlineReader> readerClazz) {

        this.typesToClazzes.put(typeAttribute.toLowerCase(), readerClazz);
    }

    private OutlineFactory() {

        this.typesToClazzes = new HashMap<String, Class<? extends OutlineReader>>();
        // setup defaults
        this.register("link", LinkOutlineReader.class);
        this.register("rss", RssOutlineReader.class);
    }

    private Class<? extends OutlineReader> getReader(String typeAttribute) {

        if (typeAttribute != null) {
            typeAttribute = typeAttribute.toLowerCase();
            if (this.typesToClazzes.containsKey(typeAttribute)) {
                return this.typesToClazzes.get(typeAttribute);
            }
        }
        return this.defaultOutlineReader;
    }

    private static synchronized OutlineFactory newInstance() {

        if (OutlineFactory.instance == null) {
            OutlineFactory.instance = new OutlineFactory();
        }
        return OutlineFactory.instance;
    }

}
