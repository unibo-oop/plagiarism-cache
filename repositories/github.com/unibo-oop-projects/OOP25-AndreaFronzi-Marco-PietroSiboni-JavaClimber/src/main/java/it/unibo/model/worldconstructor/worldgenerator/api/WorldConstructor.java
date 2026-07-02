package it.unibo.model.worldconstructor.worldgenerator.api;

/**
 * Interface for the world constructor.
 * Responsible for populating the game world with entities.
 */
public interface WorldConstructor { // NOPMD this interface is necessary to define the contract for world construction.

    /**
     * Fills the world with platforms and other entities up to a certain point.
     * This method ensures the world is populated.
     */
    void fillWorld();

}
