package bubbleshooter.model.highscore;

import bubbleshooter.model.game.level.LevelType;

/**
 * Class which represent a basic score with score and his game modality.
 */
public class Score {


    private final int theScore;
    private final LevelType level;

    /**
     * Constructor for a new score specifying the modality.
     * @param score  The score of this game.
     * @param level  The modality of this game.
     */
    public Score(final int score, final LevelType level) {
        this.theScore = score;
        this.level = level;
    }

    /**
     * @return the score.
     */
    public int getScore() {
        return this.theScore;
    }

    /**
     * @return the game modality of this score.
     */
    public final LevelType getGameMode() {
        return this.level;
    }

}
