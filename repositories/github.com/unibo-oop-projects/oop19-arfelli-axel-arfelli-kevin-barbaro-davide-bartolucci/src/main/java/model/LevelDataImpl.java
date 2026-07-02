package model;

import java.io.Serializable;
import model.WaveInfo.Difficulty;

/**
 * This Class provides a data structure for storing
 * information about a Level and its waves.
 * 
 */
public class LevelDataImpl implements Serializable, LevelData {

    private static final long serialVersionUID = 1L;

    private Difficulty levelDifficulty;
    private int totalWaves;
    private int lastWaveNumber;
    private int totalScore;
    private boolean endlessMode;

    /**
     * Constructor for LevelDataImpl structure.
     * 
     * @param levelDifficulty = Difficulty of Level
     * @param totalWaves = Total number of waves (if Endless, make it 0)
     * @param lastWaveNumber = Number of ongoing Wave
     * @param totalScore = Total score reached
     * @param endlessMode = If it's an Endless Mode Level
     */
    public LevelDataImpl(final Difficulty levelDifficulty, final int totalWaves, final int lastWaveNumber, final int totalScore, final boolean endlessMode) {
        this.levelDifficulty = levelDifficulty;
        this.totalWaves = totalWaves;
        this.lastWaveNumber = lastWaveNumber;
        this.totalScore = totalScore;
        this.totalScore = totalScore;
        this.endlessMode = endlessMode;
    }

    @Override
    public final Difficulty getLevelDifficulty() {
        return this.levelDifficulty;
    }
    @Override
    public final int getTotalWaves() {
        return this.totalWaves;
    }
    @Override
    public final int getLastWaveNumber() {
        return this.lastWaveNumber;
    }
    @Override
    public final void setLastWaveNumber(final int lastWaveNumber) {
        this.lastWaveNumber = lastWaveNumber;
    }
    @Override
    public final void increaseLastWaveNumber() {
        this.lastWaveNumber++;
    }
    @Override
    public final int getTotalScore() {
        return this.totalScore;
    }
    @Override
    public final void setTotalScore(final int totalScore) {
        this.totalScore = totalScore;
    }
    @Override
    public final boolean isEndless() {
        return this.endlessMode;
    }
    @Override
    public final String toString() {
        return ("Level Data for Debug _ Difficulty: [" + this.levelDifficulty.toString() + "] "
                                        + "Total Waves: [" + this.totalWaves + "] "
                                        + "Last Saved Wave: [" + this.lastWaveNumber + "] "
                                        + "Final Score: [" + this.totalScore + "] ");
    }

}
