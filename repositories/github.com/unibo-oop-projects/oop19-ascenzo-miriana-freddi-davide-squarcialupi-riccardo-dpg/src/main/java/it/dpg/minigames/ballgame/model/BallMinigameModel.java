package it.dpg.minigames.ballgame.model;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public interface BallMinigameModel {

    /**
     * setup the world basing on the chosen level
     */
    void setupLevel(BallMinigameLevel level);

    /**
     * indicate if the plater is going left
     *
     * @param isGoingLeft true if the player is going in the direction, false otherwise
     */
    void setGoingLeft(boolean isGoingLeft);

    /**
     * indicate if the plater is going right
     *
     * @param isGoingRight true if the player is going in the direction, false otherwise
     */
    void setGoingRight(boolean isGoingRight);

    /**
     * indicate if the player is going up
     *
     * @param isGoingUp true if the player is going in the direction, false otherwise
     */
    void setGoingUp(boolean isGoingUp);

    /**
     * indicate if the player is going down
     *
     * @param isGoingDown true if the player is going in the direction, false otherwise
     */
    void setGoingDown(boolean isGoingDown);

    /**
     * elaborate the next frame, considering a fixed amount of time passed between the two frames
     *
     * @return the coordinated x and y of the ball, in relation to the 100x100 level
     */
    Pair<Double, Double> calculateNextFrame();

    /**
     * @return the current score, going down as time passes
     */
    int getScore();

    /**
     * @return true if the ball has arrived, false otherwise
     */
    boolean isOver();
}
