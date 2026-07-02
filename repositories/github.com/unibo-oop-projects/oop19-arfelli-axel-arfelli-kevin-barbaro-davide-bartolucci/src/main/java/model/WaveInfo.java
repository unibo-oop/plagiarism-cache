package model;

/**
 * Interface for the creation of game-play waves,
 * where the player and enemies are actors.
 */
public interface WaveInfo {

    /**
     * Returns an elaborate HP value based on parameters.
     * 
     * @param baseValue
     * @return Enemy HP
     */
    int elaborateInitialHP(int baseValue);

    /**
     * Returns an elaborate DAMAGE value based on parameters.
     * 
     * @param baseValue
     * @return Enemy DAMAGE
     */
    int elaborateInitialDMG(int baseValue);

    /**
     * Returns an elaborate Score Points Amount based on parameters.
     * 
     * @param enemyTier
     * @return Score
     */
    int elaborateScore(int enemyTier);

    /**
     * Returns the difficulty of the WaveInfo.
     * 
     * @return Difficulty
     */
    Difficulty getDifficulty();

    /**
     * Returns the number of this Wave.
     * 
     * @return WaveInfo number
     */
    int getNumber();

    /**
     * Every WaveInfo will have a specific difficulty,
     * with some specific starting parameters.
     * 
     * @return Enemy Level, Enemy Damage Multiplier, Enemy Saturation, Enemy Spawn-cap
     */
    enum Difficulty {
        EASY(1, 1, 10),
        NORMAL(5, 2, 15),
        HARD(10, 3, 20);

        private int enemyLEVEL;
        private int enemyMULTI;
        private int enemySATUR;

        Difficulty(final int enemyLEVEL, final int enemyMULTI, final int enemySPWNcap) {
            this.enemyLEVEL = enemyLEVEL;
            this.enemyMULTI = enemyMULTI;
            this.enemySATUR = enemySPWNcap;
        }

        /**
         * Returns the suggested initial level of enemies.
         * 
         * @return Suggested initial level
         */
        public int getEnemyInitialLEVEL() {
            return enemyLEVEL;
        }
        /**
         * Returns the difficulty-related multi-purpose Value Multiplier.
         * It is used for damage, health and spawn cap elaborations.
         * 
         * @return Multiplier
         */
        public int getEnemyMULTIPLIER() {
            return enemyMULTI;
        }
        /**
         * Returns the suggested limit for screen saturation,
         * also known as "how many enemies should be on screen at a time".
         * 
         * @return Enemy Screen Saturation
         */
        public int getEnemySaturationAmount() {
            return enemySATUR;
        }
        /**
         * Returns the suggested limit of enemies per Wave.
         * 
         * @return Enemy Wave Spawn Cap
         */
        public int getEnemySPAWNcap() {
            return enemySATUR * enemyMULTI;
        }
    }

}
