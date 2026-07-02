package it.unibo.controller.interfaces;

/**
 * Interface representing the controller for managing the score in the game.
 * Provides a method to add points to the current score based on a given power value.
 */
public interface ScoreControllerInterface {

    /**
     * Adds points to the current score based on the given power value.
     * The power value determines how many points should be added to the score.
     * 
     * @param power The value representing the amount of points to add to the score.
     */
    void addPoints(int power);
}
