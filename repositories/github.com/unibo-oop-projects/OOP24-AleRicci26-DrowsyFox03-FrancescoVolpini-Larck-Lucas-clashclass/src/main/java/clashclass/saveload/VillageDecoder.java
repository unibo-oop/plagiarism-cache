package clashclass.saveload;

import clashclass.elements.ComponentFactory;
import clashclass.village.Village;

/**
 * Interface for decoding CSV String representations back to Village objects.
 */
public interface VillageDecoder {
    /**
     * Sets the component factory to be used during decoding.
     *
     * @param componentFactory the factory to use
     */
    void setComponentFactory(ComponentFactory componentFactory);

    /**
     * Decodes a string representation into game objects.
     *
     * @param encodedVillage the encoded village string
     * @return a set of game objects
     */
    Village decode(String encodedVillage);
}
