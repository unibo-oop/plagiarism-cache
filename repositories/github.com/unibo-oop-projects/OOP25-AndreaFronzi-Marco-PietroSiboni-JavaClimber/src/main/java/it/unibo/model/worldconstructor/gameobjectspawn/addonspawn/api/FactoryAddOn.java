package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.physics.api.Vector2d;

/**
 * Factory for the creation of add-ons.
 */
public interface FactoryAddOn {

    /**
     * Create a coin at the given position.
     * 
     * @param position the position where the coin will be created.
     * @return the created coin.
     */
    Coin createCoin(Vector2d position);

    /**
     * Create an enemy at the given position.
     * 
     * @param position the position where the enemy will be created.
     * @return the created enemy.
     */
    Enemy createEnemy(Vector2d position);

    /**
     * Create a gadget at the given position.
     * 
     * @param position the position where the gadget will be created.
     * @return the created gadget.
     */
    Gadget createElycap(Vector2d position);

}
