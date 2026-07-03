package com.geoquiz.model.xmlunmarshal;

import java.util.List;

import javax.xml.bind.JAXBException;

/**
 * This is the interface of the XML unmarshaller class.
 *
 */
public interface XMLUnmarshaller {

    /** Gets the instance of XMLUnmarshaller.
     * @return
     *      the instance of the XMLUnmarshaller
     */
    static XMLUnmarshaller getInstance() {
        return XMLUnmarshallerImpl.get();
    }
    /**
     * @param fileInfo
     *          a file info in the form of XMLFiles enum.
     * @return {@link List}<T> the list containing the unmarshalled data
     * @param <T> the type parameter of the unmarshalled data.
     * @throws JAXBException
     *          if something goes wrong during unmarshalling.
     */
    <T> List<T> unmarshal(XMLFiles fileInfo) throws JAXBException;
}