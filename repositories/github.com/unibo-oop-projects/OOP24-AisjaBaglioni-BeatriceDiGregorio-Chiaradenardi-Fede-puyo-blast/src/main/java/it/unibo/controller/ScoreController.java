package it.unibo.controller;

import it.unibo.controller.interfaces.ScoreControllerInterface;
import it.unibo.model.ScoreModel;

/**
 * The ScoreController class manages score calculations and updates.
 * It applies a scoring formula based on a given power value.
 */
public class ScoreController implements ScoreControllerInterface {
    /**
     * The model managing the game score.
     */
    private final ScoreModel scoreModel;

    /**
     * Constructor for initializing the ScoreController.
     * 
     * @param scoreModel the model tracking the player's score.
     */
    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
    }

    /**
     * Adds points to the score based on the square of the given power value.
     * 
     * @param power the base value used to calculate points.
     */
    @Override
    public void addPoints(int power) {
        int newPoints = (int) Math.pow(power, 2);
        int oldScore = this.scoreModel.getScore();
        this.scoreModel.setScore(oldScore + newPoints);
    }
}