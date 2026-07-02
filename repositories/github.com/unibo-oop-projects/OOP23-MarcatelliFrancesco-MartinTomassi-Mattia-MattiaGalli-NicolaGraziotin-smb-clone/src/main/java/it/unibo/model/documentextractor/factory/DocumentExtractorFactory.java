package it.unibo.model.documentextractor.factory;

import it.unibo.model.documentextractor.DocumentExtractor;

/**
 * The DocumentExtractorFactory interface represents a factory for creating DocumentExtractor objects.
 * Implementations of this interface are responsible for creating instances of DocumentExtractor
 * based on the provided TMX (Tile Map XML).
 */
public interface DocumentExtractorFactory {

    /**
     * Creates a DocumentExtractor object based on the provided TMX (Tile Map XML).
     *
     * @param tmx the Tile Map XML data representing the document.
     * @return a DocumentExtractor object created with the provided TMX data.
     */
    DocumentExtractor createDocumentExtractor(String tmx);

}
