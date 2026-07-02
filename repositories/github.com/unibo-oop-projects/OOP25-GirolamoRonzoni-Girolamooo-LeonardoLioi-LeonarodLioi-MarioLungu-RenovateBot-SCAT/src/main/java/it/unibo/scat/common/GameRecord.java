package it.unibo.scat.common;

import java.time.LocalDate;

/**
 * This class represents a single record of the Leaderboard.
 */
public class GameRecord {
    private final String name;
    private final int score;
    private final int level;
    private final LocalDate date;

    /**
     * GameRecord constructor.
     * 
     * @param name  the name.
     * @param score the score of the game.
     * @param level the level.
     * @param date  the date of the game.
     */
    public GameRecord(final String name, final int score, final int level, final LocalDate date) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.date = date;
    }

    /**
     * Name getter.
     * 
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Score getter.
     * 
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Level getter.
     * 
     * @return level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Date getter.
     * 
     * @return the date.
     */
    public LocalDate getDate() {
        return date;
    }
}
