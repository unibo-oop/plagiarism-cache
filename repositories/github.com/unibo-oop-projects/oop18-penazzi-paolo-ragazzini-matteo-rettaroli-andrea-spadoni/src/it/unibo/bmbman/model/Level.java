package it.unibo.bmbman.model;
/**
 * 
 * Model the level in this game.
 */
public interface Level {
    /**
     * Used to know the current level.
     * @return the current level
     */
    int getLevel();
    /**
     * Used to increase the level.
     */
    void levelUp();
    /**
     * Return to first level.
     */
    void setLevelOne();
    /**
     * Says how many blocks are in this level.
     * @return the number of blocks 
     */
    int getBlocksNumber();
    /**
     * Says how many monster are in this level.
     * @return the number of monsters
     */
    int getMonstersNumber();
    /**
     * Says how many malus freeze are in this level.
     * @return the number of malus freeze
     */
    int getMalusFreezeNumber();
    /**
     * Says how many malus invert are in this level.
     * @return the number of malus invert
     */
    int getMalusInvertNumber();
    /**
     * Says how many malus life are in this level.
     * @return the number of malus life
     */
    int getMalusLifeNumber();
    /**
     * Says how many malus slow are in this level.
     * @return the number of malus slow
     */
    int getMalusSlowNumber();
    /**
     * Says how many bonus bomb are in this level.
     * @return the number of bonus bomb
     */
    int getBonusBombNumber();
    /**
     * Says how many bonus bomb range are in this level.
     * @return the number of bonus bomb range
     */
    int getBonusRangeNumber();
    /**
     * Says how many bonus life are in this level.
     * @return the number of bonus life
     */
    int getBonusLifeNumber();
    /**
     * Says how many bonus velocity are in this level.
     * @return the number of bonus velocity
     */
    int getBonusVelocityNumber();
}
