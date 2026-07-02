package model.ship.basic;

import model.ship.SpaceShip;

/**
 * Models a basic SpaceShip with a simple behavior depending on basic functions.
 */
public interface BasicSpaceShip extends SpaceShip {

    /**
     * Returns the maximum amount of health obtainable by this ship.
     * @return the maximum amount of health obtainable by this ship.
     */
    int getMaxHealth();

    /**
     * Returns the amount of health remained to this ship.
     * @return the amount of health remained to this ship.
     */
    double getHealth();

    /**
     * Returns the maximum amount of speed reachable by this ship.
     * @return the maximum amount of speed reachable by this ship.
     */
    double getMaxSpeed();

    /**
     * Returns the fastest this ship can accelerate.
     * @return the fastest this ship can accelerate.
     */
    double getMaxAcceleration();

    /**
     * Returns the fastest this ship can rotate.
     * @return the fastest this ship can rotate.
     */
    double getMaxAngularSpeed();

    /**
     * Returns the amount of drag received by this ship.
     * @return the amount of drag received by this ship.
     */
    double getDrag();

}
