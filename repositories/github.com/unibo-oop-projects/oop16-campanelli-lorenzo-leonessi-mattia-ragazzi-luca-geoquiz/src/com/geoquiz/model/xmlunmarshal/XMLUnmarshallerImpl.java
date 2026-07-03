package com.geoquiz.model.xmlunmarshal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.transform.stream.StreamSource;

import com.geoquiz.utility.ResourceLoader;

//package-private
final class XMLUnmarshallerImpl implements XMLUnmarshaller {
    private static JAXBContext jc;
    private static Unmarshaller unmarshaller;

    private XMLUnmarshallerImpl() {
        try {
            jc = JAXBContext.newInstance(Wrapper.class, CountryInfo.class, MonumentInfo.class, TypicalDish.class);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static class Holder {
        private static final XMLUnmarshallerImpl INSTANCE = new XMLUnmarshallerImpl();
    }

    public static XMLUnmarshaller get() {
        return Holder.INSTANCE;
    }

    public <T> List<T> unmarshal(final XMLFiles fileInfo) throws JAXBException {
        return doUnmarshal(unmarshaller, fileInfo.getClazz(), ResourceLoader.loadResource(fileInfo.getPathName()).toString());
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> doUnmarshal(final Unmarshaller unmarshaller, final Class<T> clazz, final String xmlLocation) throws JAXBException {
        final StreamSource xml = new StreamSource(xmlLocation);
        final Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(xml, Wrapper.class).getValue();
        return wrapper.getItems();
    }

    private static class Wrapper<T> {

        private final List<T> items;

        @SuppressWarnings("unused")
        Wrapper() {
                this.items = new ArrayList<>();
        }

        @SuppressWarnings("unused")
        Wrapper(final List<T> items) {
                this.items = items;
        }

        @XmlAnyElement(lax = true)
        public List<T> getItems() {
            return items;
        }

    }

}
