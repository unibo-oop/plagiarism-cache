package model.difficulty;

/**
 * 
 * represents the increasing difficulty of the game determined by the number of
 * the words of the wave and their related speed.
 *
 */
public interface Difficulty {
    /**
     * this method is used to increase the difficulty.
     */
    void increase();

    /**
     * Method to get the minimum speed the words of the wave can assume.
     * 
     * @return minimum speed
     */
    double getMinSpeed();

    /**
     * Method to get the maximum speed the words of the wave can assume.
     * 
     * @return maximum speed
     */
    double getMaxSpeed();

    /**
     * Method to get the number of short words composing the wave.
     * 
     * @return the number of short words to spawn.
     */
    int getNShort();

    /**
     * Method to get the number of long words composing the wave.
     * 
     * @return the number of long words to spawn.
     */
    int getNLong();


}
