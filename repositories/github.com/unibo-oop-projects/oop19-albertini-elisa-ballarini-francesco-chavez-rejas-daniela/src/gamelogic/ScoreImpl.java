package gamelogic;

import level.Levels;

/**
 * This class implements the interface {@link Score}.
 *
 */
public final class ScoreImpl implements Score {

    private int score;
    private int pointsToNextLevel;
    private static final int LEVELUP = 150;
    private Levels level;

    /**
     * @param level : the starting level of this game
     */
    public ScoreImpl(final Levels level) {
        this.level = level;
        this.pointsToNextLevel = LEVELUP;
    }

    @Override
    public void addPoints(final int combo) {
        int points = 0;
        for (int i = 0; i < combo; i++) {
            points += (combo - i) * 10;
        }
        this.score = this.score + points;
        this.pointsToNextLevel = this.pointsToNextLevel - points;
        while (this.pointsToNextLevel <= 0) {
            if (!this.level.getNextLevel().equals(this.level)) {
                this.level = this.level.getNextLevel();
            }
            this.pointsToNextLevel = this.pointsToNextLevel + LEVELUP;
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Levels getLevel() {
        return this.level;
    }

}
