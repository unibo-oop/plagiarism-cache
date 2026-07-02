package it.unibo.oop.supermario.powerup;

import it.unibo.oop.supermario.character.Mario;
/**
 * Item interface.
 *
 */
public interface Item {
    /**Method that defines the collision with items. 
     * @param mario Mario's object
     * */
    void onCollide(Mario mario);
}
