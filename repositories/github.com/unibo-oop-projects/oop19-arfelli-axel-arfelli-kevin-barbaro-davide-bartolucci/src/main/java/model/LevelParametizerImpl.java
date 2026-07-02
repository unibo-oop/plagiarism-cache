package model;

import java.util.NoSuchElementException;
import model.WaveInfo.Difficulty;

/**
 * This class is used to deliver new instances of WaveInfo
 * and generic level-related information whenever required.
 * It can also return the LevelDataImpl in order to resume the 
 * game-play later.
 */
public class LevelParametizerImpl implements LevelParametizer {

    /* BackBone Logic components */
    private WaveInfoGenerator waveGenerator;
    /* Data for Wave Parameters */
    private LevelData levelData;

    /**
     * Generic constructor for the Parametizer.
     * 
     * @param levelDiff = Difficulty of Level
     * @param totalWaves = Total number of waves (if Endless, make it 0)
     * @param savedWave = Number of ongoing Wave
     * @param savedScore = Total score reached
     * @param endlessMode = If it's an Endless Mode Level
     */
    public LevelParametizerImpl(final Difficulty levelDiff, final int totalWaves, final int savedWave, final int savedScore, final boolean endlessMode) {
        // level data
        this.levelData = new LevelDataImpl(levelDiff, totalWaves, savedWave, savedScore, endlessMode);
        // wave generator
        this.waveGenerator = new WaveInfoGeneratorImpl(levelDiff);
    }
    /**
     * Standard constructor for new Level Parametizer.
     * 
     * @param levelDiff = Difficulty of Level
     * @param totalWaves = Total number of waves (if Endless, make it 0)
     * @param endlessMode = If it's an Endless Mode Level
     */
    public LevelParametizerImpl(final Difficulty levelDiff, final int totalWaves, final boolean endlessMode) {
        this(levelDiff, totalWaves, 0, 0, endlessMode);
    }
    /**
     * Constructor for saved Level Data to resume.
     * 
     * @param savedLevelData = Saved Level Data to resume game-play
     */
    public LevelParametizerImpl(final LevelData savedLevelData) {
        // level data
        this.levelData = savedLevelData;
        // wave generator
        this.waveGenerator = new WaveInfoGeneratorImpl(savedLevelData.getLevelDifficulty());
    }

    /**
     * Calls a new WaveInfo from the generator, if available;
     * otherwise throws an Exception.
     * 
     * @return a new WaveInfo with LastWaveNumber
     * @throws NoSuchElementException
     */
    @Override
	public final WaveInfo newWave() throws NoSuchElementException {
        if (hasNext()) {
            levelData.increaseLastWaveNumber();
            return waveGenerator.loadWaveStandard(this.levelData.getLastWaveNumber());
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Checks if a new Wave can be launched.
     * 
     * @return TRUE if Total Waves >= Last Wave + 1,
     *          TRUE if is an Endless Level,
     *          FALSE otherwise
     */
    @Override
	public final boolean hasNext() {
        return (
                (this.levelData.getTotalWaves() >= this.levelData.getLastWaveNumber() + 1)
                || this.levelData.isEndless()
               );
    }

    /**
     * When the game is to be closed, use this method to recover
     * LevelData (Implementation) and save for later resuming.
     * 
     * @return LevelData relative to ongoing game
     */
    @Override
	public final LevelData closeLevel() {
        this.levelData.setLastWaveNumber(this.levelData.getLastWaveNumber() - 1);
        return this.levelData;
    }

    /**
     * Prints info of LevelParametizer for Debugging.
     * Testing purpose only.
     */
    public final void debugPrintLevelData() {
        System.out.println(this.levelData.toString());
    }
    
    @Override
	public final Difficulty getLevelDifficulty() {
        return this.levelData.getLevelDifficulty();
    }

}
