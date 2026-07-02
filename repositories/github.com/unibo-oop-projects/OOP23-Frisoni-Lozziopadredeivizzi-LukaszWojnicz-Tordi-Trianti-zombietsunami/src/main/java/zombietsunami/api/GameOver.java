package zombietsunami.api;

/**
 * This interface manages the Game Over of the game if the zombie
 * reaches zero of strenght or don't have enough strenght to break
 * the breakable object in the map.
 */
public interface GameOver {

    /**
     * @param isEnough
     * @return true if the zombie's strenght is not enough to break the breakable
     *         object in the map
     */
    boolean isNotEnough(boolean isEnough);

    /**
     * @param strenght the zombie's strenght
     * @return true if the zombie's strenght is zero
     */
    boolean isStrenghtZero(int strenght);
}
