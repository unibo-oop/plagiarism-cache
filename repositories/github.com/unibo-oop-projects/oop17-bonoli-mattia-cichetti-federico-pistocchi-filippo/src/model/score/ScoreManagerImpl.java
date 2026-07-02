package model.score;

import controller.GameDifficult;
import utilities.Constants;

/**
 * 
 */
public class ScoreManagerImpl implements ScoreManager {

    private static final int POINT_PER_LANE = 5;
    private static final int POINT_PER_DEN = 50;
    private static final int POINT_PER_DEATH = -40;

    private long score;
    private int laneReached;
    private int scoreMultiplier;

    /**
     * 
     */
    public ScoreManagerImpl() {
        this.score = 0;
        this.laneReached = 0;
        this.scoreMultiplier = 0;
    }

    /**
     * 
     */
    @Override
    public void setDifficult(final GameDifficult difficult) {
        switch (difficult) {
        case EASY:
            this.scoreMultiplier = 1;
            break;
        case NORMAL:
            this.scoreMultiplier = 2;
            break;
        case HARD:
            this.scoreMultiplier = 3;
            break;
        default:
            break;
        }
    }

    /**
     * 
     */
    @Override
    public long getScore() {
        return this.score;
    }

    /**
     * 
     */
    @Override
    public void addScore(final long score) {
        this.missingDifficultException();
        this.score += score * this.scoreMultiplier;
    }

    /**
     * 
     */
    @Override
    public void frogMovedSuccesfully(final int lane) {
        this.missingDifficultException();
        if (Constants.WORLD_NUMBER_OF_LANE + 1 == lane) {
            this.laneReached = 0;
            this.score += ScoreManagerImpl.POINT_PER_DEN;
        } else {
            if (laneReached < lane) {
                this.score += ScoreManagerImpl.POINT_PER_LANE * this.scoreMultiplier;
                this.laneReached++;
            }
        }
    }

    /**
     * 
     */
    @Override
    public void frogDies() {
        this.missingDifficultException();
        this.score += ScoreManagerImpl.POINT_PER_DEATH * this.scoreMultiplier;
        this.laneReached = 0;
    }

    private void missingDifficultException() {
        if (this.scoreMultiplier == 0) {
            throw new UnsupportedOperationException();
        }
    }

}
