package model;

import model.WaveInfo.Difficulty;

/**
 * Implementation of the Wave Generator.
 * It is included in and called through LevelParametizerImpl.
 */
public class WaveInfoGeneratorImpl implements WaveInfoGenerator {

    private final Difficulty waveDiff;

    /**
     * Constructor for WaveGenerator.
     * Must be initialized with Difficulty for parameterization.
     * 
     * @param waveDiff = Difficulty used by every WaveInfo it will generate
     */
    public WaveInfoGeneratorImpl(final Difficulty waveDiff) {
        this.waveDiff = waveDiff;
    }

    @Override
    public final WaveInfo loadWaveStandard(final int stageNUM) {
        return new WaveInfo() {

            @Override
            public int elaborateInitialHP(final int baseValue) {
                return (int) baseValue * (waveDiff.getEnemyInitialLEVEL() + stageNUM);
            }

            @Override
            public int elaborateInitialDMG(final int baseValue) {
                return (int) baseValue * waveDiff.getEnemyMULTIPLIER() + stageNUM;
            }

            @Override
            public int elaborateScore(final int enemyTier) {
                return (int) enemyTier * waveDiff.getEnemyMULTIPLIER();
            }

            @Override
            public Difficulty getDifficulty() {
                return waveDiff;
            }

            @Override
            public int getNumber() {
                return stageNUM;
            }
        };
    }

    @Override
    public final Difficulty getDifficulty() {
        return this.waveDiff;
    }

}
