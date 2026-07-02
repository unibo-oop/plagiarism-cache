package it.unibo.model.documentextractor;

import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Implementation of the DocumentExtractor interface that extracts information from a TMX document.
 */
public class DocumentExtractorImpl implements DocumentExtractor {

    private final String tmx;
    private Document document;
    private int numRows;
    private int numColumns;

    /**
     * Constructs a new instance of DocumentExtractorImpl with the specified TMX file path.
     *
     * @param tmx The path to the TMX file.
     */
    public DocumentExtractorImpl(final String tmx) {
        this.tmx = tmx;
        extractDocument();
    }

    private void extractDocument() {
        try {
            this.document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(getClass()
                    .getClassLoader()
                    .getResourceAsStream(this.tmx));
            final NodeList mapAttributes = this.document
                .getElementsByTagName(TagType.MAP.toString());
            if (mapAttributes.getLength() > 0) {
                this.numRows = Integer.parseInt(
                    ((Element) mapAttributes.item(0))
                    .getAttribute(TagType.HEIGHT.toString()));
                this.numColumns = Integer.parseInt(
                    ((Element) mapAttributes.item(0))
                    .getAttribute(TagType.WIDTH.toString()));
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            Logger.getLogger(DocumentExtractorImpl.class.getName())
                .severe("An error occurred while extracting document: " + e.getMessage());
        }
    }

    /**
     * Retrieves the elements associated with a given TagName.
     *
     * @param tagType The name of the tag to search for.
     * @return The elements associated with the specified tag name.
     */
    @Override
    public NodeList getElements(final TagType tagType) {
        return this.document.getElementsByTagName(tagType.toString());
    }

    /**
     * Retrieves the number of rows in the document.
     *
     * @return The number of rows in the document.
     */
    @Override
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * Retrieves the number of columns in the document.
     *
     * @return The number of columns in the document.
     */
    @Override
    public int getNumColumns() {
        return this.numColumns;
    }

}
