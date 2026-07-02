package it.unibo.model.documentextractor;

import org.w3c.dom.NodeList;

/**
 * The DocumentExtractor interface declares methods to extract information about the structure of a document.
 * It provides methods to retrieve the number of rows and columns in the document.
 */
public interface DocumentExtractor {

    /**
     * Retrieves the number of rows in the document.
     *
     * @return The number of rows in the document.
     */
    int getNumRows();

    /**
     * Retrieves the number of columns in the document.
     *
     * @return The number of columns in the document.
     */
    int getNumColumns();

    /**
     * Retrieves the elements associated with a given TagName.
     *
     * @param tagType The name of the tag to search for.
     * @return The elements associated with the specified tag name.
     */
    NodeList getElements(TagType tagType);

}
