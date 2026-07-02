package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api;

import it.unibo.model.gameobj.api.Platform;

/**
 * Interface for the AddOnCreator, which is responsible for selecting and managing add-ons in the game.
 */
public interface AddOnCreator {

    /**
     * Selects an add-on based on the given chance and platform.
     *
     * @param chance   the probability of selecting an add-on
     * @param platform the platform for which the add-on is being selected
     */
    void selectAddOn(Double chance, Platform platform);

    /**
     * Sets the AddOnPool to be used by the AddOnCreator.
     *
     * @param addOnPool the AddOnPool to set
     */
    void setAddOnPool(AddOnPool addOnPool);

}
