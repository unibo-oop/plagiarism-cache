package model.utilities;

public enum Difficulty {

    /**
     * Use to set the number of lives of the player, the ball velocity 
     * and The value that will be multiplied to the score.
     */
    NORMAL(3, 0.4, 1, 0, 3),

    /**
     * Use to set the number of lives of the player, the ball velocity 
     * and The value that will be multiplied to the score.
     */
    HARD(1, 0.6, 3, 0, 4);

    private int numberOfLives;
    private double ballVelocity;
    private int multiplyScoreValue;
    private int initialScore;
    private int maxNumberScore;

    Difficulty(final int numberOfLives, final double ballVelocity, final int multiplyScoreValue, final int initialScore, 
               final int maxNumberOfLife) {

        this.numberOfLives = numberOfLives;
        this.ballVelocity = ballVelocity;
        this.multiplyScoreValue = multiplyScoreValue;
        this.initialScore = initialScore;
        this.maxNumberScore = maxNumberOfLife;
    }

    /**
     * Return an integer that represent the number of lives that the player have when start the match.
     * @return an integer that represent the number of lives that the player have when start the match.
     */
    public int getNumberOfLives() {
        return this.numberOfLives;
    }

    /**
     * Return an integer that represent the number of the ball velocity when start the match.
     * @return an integer that represent the number of the ball velocity when start the match.
     */
    public double getBallVelocity() {
        return this.ballVelocity;
    }

    /**
     * Return an integer that represent the number that will multiply at the score when the wall will break.
     * @return an integer that represent the number that will multiply at the score when the wall will break.
     */
    public int getMultiplyScoreValue() {
        return this.multiplyScoreValue;
    }

    /**
     * Return an integer that represent the number of the initial score.
     * @return an integer that represent the number of the initial score.
     */
    public int getInitialScore() {
        return this.initialScore;
    }

    /**
     * Return an integer that represent  the maximum number of life that the player can have.
     * @return an integer that represent  the maximum number of life that the player can have.
     */
    public int getMaxNumberOfLife() {
        return this.maxNumberScore;
    }
}
