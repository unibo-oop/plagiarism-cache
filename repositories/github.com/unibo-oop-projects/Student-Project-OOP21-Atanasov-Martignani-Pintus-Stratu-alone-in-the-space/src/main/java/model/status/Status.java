package model.status;

import controller.playerController.PlayerShipController;

/**
 * This interface show the methods to handle status.
 */
public interface Status {

    /**
     * Set points.
     * 
     * @param value
     */
    void setPoints(int value);

    /**
     * @return points.
     */
    int getPoints();

    /**
     * Set life points.
     * 
     * @param value
     */
    void setLifePoints(int value);

    /**
     * @return life points.
     */
    int getLifePoints();

    /**
     * add points.
     * 
     * @param value
     */
    void addPoints(int value);

    void switchPowerUp();

    /**
     * Set playerShipController.
     *
     * @param playerShipController
     */
    void setPlayerController(PlayerShipController playerShipController);

    /**
     * @return true if the player has the powerUp
     */
    boolean hasPowerUp();
}
