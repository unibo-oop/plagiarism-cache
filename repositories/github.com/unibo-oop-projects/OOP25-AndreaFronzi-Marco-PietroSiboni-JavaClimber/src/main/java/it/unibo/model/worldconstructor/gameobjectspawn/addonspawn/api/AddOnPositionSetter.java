package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api;

import it.unibo.model.gameobj.api.GameObject;

/**
 * Interface for generating positions for add-ons (coins, enemies, gadgets).
 * Determines where an add-on should be placed relative to a platform.
 */
public interface AddOnPositionSetter { // NOPMD this interface is necessary to define the contract for position
    // generation of add-ons.
    /**
     * Generates a position for an add-on.
     * 
     * @param addOn         The add-on for which to generate the position.
     * @param platformWidth The width of the platform on which the add-on will be
     *                      placed
     * @param <X>           The type of the add-on.
     * 
     * @return The add-on with its position set according to the platform width.
     */
    <X extends GameObject> X generatePosition(X addOn, double platformWidth);
}
