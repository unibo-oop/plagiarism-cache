package game;

/**
 * A class with methods to get the current level, to reset the level and to set 
 * the level to the next level {@link Level}.
 * 
 */
public interface InterfaceLevel {
    /**
     * Method to get the current level.
     * @return level current of the {@link Level}.
     */
    int getLevel();
    /**
     * Method to reset the level.
     */
    void resetLevel();
    /**
     * Method to go to the next level in singleplayer.
     */
    void nextLevelSingle();
    /**
     * Method to go to the next level in multiplayer.
     */
    void nextLevelMulti();
}
