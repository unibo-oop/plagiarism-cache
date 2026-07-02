package controller;

import enumerators.Level;

/**
 * Interface for a entity generator.
 */
public interface Generator {

    /**
     * Initializes the generator.
     * @param level - the level to generate
     */
    void init(Level level);

    /**
     * Does an update in the generator, check if new entities are needed.
     */
    void update();
}
