package model.utils;

/**
 * This Interface represent the oxygen management required by game.
 *
 */
public interface OxygenReserve {

    /**
     * This method restore full oxygen in the reserve of the game.
     */
    void resetOxygen();

    /**
     * This method decrease an amount of oxygen from the reserve.
     *
     * @param decreaseQuantity Quantity of oxigent to decrease.
     */
    void decreaseOxygen(int decreaseQuantity);

    /**
     * This method return the total amount of oxygen in the reserve.
     *
     * @return Oxygen remaining
     */
    int getOxygen();

    /**
     * This method return if the oxygen reserve is empty (in game dynamics equals or under zero).
     *
     * @return boolean
     */
    boolean isEmpty();
}
