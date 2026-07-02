package giocoscudetto.model.api.match;

/**
 * This interface represents the goal net of the game.
 */
public interface GoalNet {

    /**
     * This method sets the position of the goalkeeper.
     * 
     * @param position the position of the goalkeeper.
     */
    void setGoalKeeperPosition(int position);

    /**
     * This method checks if the ball is in the goal or not.
     * 
     * @param guessPosition the position of the ball,
     * @return a boolean that is true if the ball is in the goal and false otherwise.
     */
    boolean isGoal(int guessPosition);

    /**
     * This method returns the last position of the ball that was shot.
     * 
     * @return an integer that represents the last position of the ball that was shot.
     */
    int getLastShootPosition();
}
