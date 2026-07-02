package it.unibo.oop.supermario.enemies;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.enemies.KoopaImpl.State;

/**
 * This class models Koopa and manages its states and movements.
 */
interface Koopa {
    /**
     * Update Koopa's model every frame.
     *
     * @param dt delta time of the frame
     */
    void update(float dt);

    /**
     *  Define the body of Koopa from head to movements.
     */
    void defBody();

    /** Checks the state where Koomba is smashed by Mario. 
     * 
     * @param mario contains all informations of class Mario.
     * */
    void hitOnHead(Mario mario);

    /** Checks the state where Goomba collides with another enemy. 
     * 
     * @param enemy contains all informations of class Enemy.
     * */
    void hitByEnemy(Enemy enemy);

    /** 
     * Checks the state where Goomba is hit by fire. 
     */
    void hitByFire();

    /** 
     * Goomba let's move quickly. 
     * 
     * @param direction set horizontal verse to go
     */
    void kick(int direction);

    /** 
     * Get the current state of Koopa.
     * 
     *  @return currentState return the current value
     */
    State getCurrentState();

    /** 
     * Get the previous state of Koopa. 
     * 
     * @return previousState return the previous value
     */
    State getPreviousState();

    /** 
     * Set the current state of Koopa. 
     * 
     *  @param currentState set current state value
     */
    void setCurrentState(State currentState);

    /** 
     * Set the previous state of Koopa. 
     * 
     * @param previousState set previous state value
     */
    void setPreviousState(State previousState);
}
