package zombietsunami.api;

/**
 * This interface manages the winning of the game if the player is near to the
 * flag in the game map.
 */
public interface MightWin {

    /**
     * This method updates the X coordinate of the Pair of Integers that rapresents
     * the
     * flag's position in the game screen.
     * 
     * @param endX is the flag's X coordinate
     */
    void setEndPos(int endX);

    /**
     * @return true if the flag's X position arrives in a certain axis
     */
    boolean isWin();
}
