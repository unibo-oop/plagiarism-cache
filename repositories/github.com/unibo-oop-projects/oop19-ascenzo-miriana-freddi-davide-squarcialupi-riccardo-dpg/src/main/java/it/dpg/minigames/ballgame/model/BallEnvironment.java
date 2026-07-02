package it.dpg.minigames.ballgame.model;

public interface BallEnvironment {
    /**
     * get ball x position
     */
    double getX();

    /**
     * get ball y position
     */
    double getY();

    /**
     * calculate next frame, based on the user's input
     */
    void nextFrame(boolean isGoingUp, boolean isGoingDown, boolean isGoingLeft, boolean isGoingRight);

    /**
     * @return true if the goal has been reached
     */
    boolean goalReached();
}
