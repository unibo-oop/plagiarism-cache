package controller;

/**
 * Handles score, coins and the max height reached.
 */
public interface HUD {

    void init(int maxHeight, int coins);

    /**
     * Add coins to the actual value.
     * 
     * @param coins to add.
     */
    void addCoins(int coins);

    /**
     * Remove coins to the actual value.
     * 
     * @param coins to remove.
     */
    void removeCoins(int coins);

    /**
     * Returns the coins the player already got.
     * 
     * @return coins available
     */
    int getCoins();

    /**
     * Sets the max height reached.
     * 
     * @param currentY - height reached
     */
    void setMaxHeight(int maxHeight);

    /**
     * Returns the greatest height the player has reached.
     * 
     * @return the max height reached
     */
    int getMaxHeight();


}
