package model;

import model.WaveInfo.Difficulty;

/**
 * This Class provides a data structure for storing
 * information about a Level and its waves.
 */
public interface LevelData {

	/**
	 * Returns the Difficulty used to initialize Level.
	 * 
	 * @return Level Difficulty
	 */
    Difficulty getLevelDifficulty();

    /**
     * Returns the total amount of Waves this Level will reach.
     * 
     * @return Total amount of Waves
     */
    int getTotalWaves();

    /**
     * Returns the number of the last wave reached and completed.
     * 
     * @return Number of Last Wave Completed
     */
    int getLastWaveNumber();

    /**
     * This method will set the number of the last wave reached and completed.
     * 
     * @param lastWaveNumber = Number of Last Wave Completed
     */
    void setLastWaveNumber(int lastWaveNumber);
    
    /**
     * This method will increase by 1 the number of waves completed.
     */
    void increaseLastWaveNumber();

    /**
     * This method will give the amount of score points accumulated.
     * 
     * @return Total amount of Score Points
     */
    int getTotalScore();
    
    /**
     * This method will set the amount of score points accumulated.
     * 
     * @param totalScore = Total amount of Score Points
     */
    void setTotalScore(int totalScore);

    /**
     * This method will tell if the Level is in Endless Mode.
     * 
     * @return true if Endless Mode is active, false otherwise
     */
    boolean isEndless();

}
