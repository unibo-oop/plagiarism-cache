package it.unibo.oop.supermario.enemies;

import it.unibo.oop.supermario.character.Mario;

/**
 * This class models Goopa and manages its states and movements.
 */
public interface Goomba {
    /**
     * Update Goomba's model every frame.
     *
     * @param dt delta time of the frame
     */
    void update(float dt);

    /**
     *  Define the body of Goomba from head to movements.
     */
    void defBody();

    /** Checks the state where Goomba collides with another enemy. 
     * 
     * @param enemy contains all informations of class Enemy.
     * */
    void hitByEnemy(Enemy enemy);

    /** Checks the state where Goomba is smashed by Mario. 
     * 
     * @param mario contains all informations of class Mario.
     * */
    void hitOnHead(Mario mario);

    /** 
     * Checks the state where Goomba is hit by fire. 
     */
    void hitByFire();
}
