package it.unibo.model.level.factory;

import it.unibo.model.level.Level;

/**
 * The LevelFactory interface represents a factory for creating Level objects.
 * Implementations of this interface are responsible for creating instances of Level
 * based on the provided TMX (Tile Map XML).
 */
public interface LevelFactory {

    /**
     * Creates a Level object based on the provided TMX (Tile Map XML).
     *
     * @param tmx the Tile Map XML data representing the level.
     * @return a Level object created from the provided TMX data.
     */
    Level createLevel(String tmx);

}
