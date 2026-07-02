package model.leaderboard;
/**
 *  This interface represent the entity of the player.
 *  */
public interface Player {

    /**
     *  Method that allows to manipulate the score of the player,
     *  only if it's alive.
     *  @param operation - define an operation on the score.
     *  @param value - represents the value that must be added or subtract to the score.
     */
    void scoreOperation(ScoreOperationStrategy operation, int value);

    /**
     *  Return an Integer that represents the value of the life.
     *  @return an Integer that represents the value of the life.
     *
     */
    int getLife();

    /**
     *  Method that allows to manipulate the life of the player.
     *  @param operation - define an operation on the life.
     *  @param value - represents the value that must be added or subtract to the life.
     */
    void lifeOperation(LifeOperationStrategy operation, int value);

    /**
     *  Return the maximum number of life 
     *  that player can have.
     *  @return a maximum number of life that player can have.
     */
    int getMaxNumberOfLife();

    /**
     * Method that allows to set the maximum number of life 
     * that player can have.
     * @param value - use to set the maximum number of life
     */
    void setMaxNumberOfLife(int value);

    /**
     *  Returns true if the value of life is major of 0.
     *  @return true if the value of life is major of 0.
     *
     */
    boolean isAlive();

    /**
     *  Return a String that represents the alias of the player.
     *  @return a String that represents the alias of the player.
     *
     */
    String getAlias();

    /**
     *  Return an Integer that represents the value of the player score.
     *  @return an Integer that represents the value of the player score.
     *
     */
    int getScore();

}
