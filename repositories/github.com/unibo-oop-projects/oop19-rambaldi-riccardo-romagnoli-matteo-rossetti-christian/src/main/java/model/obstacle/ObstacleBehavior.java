package model.obstacle;

/**
 * Behaviour based on Obstacles's color. Each color has a different score. Only the GREEN
 * activates the superpower.
 */

public enum ObstacleBehavior {

    /**
     * Red obstacle' score.
     */
    RED(100),

    /**
     * Blue obstacle' score.
     */
    BLU(10),

    /**
     * Green obstacle' score.
     */
    GREEN(10),

    /**
     * Purple obstacle' score.
     */
    PURPLE(500);

    private final int score;

    /**
     * @param score the score of the obstacle.
     */
    ObstacleBehavior(final int score) {
        this.score = score;
    }

    /**
     * @return the score of the obstacle.
     */
    public int getScore() {
        return this.score;
    }

}
