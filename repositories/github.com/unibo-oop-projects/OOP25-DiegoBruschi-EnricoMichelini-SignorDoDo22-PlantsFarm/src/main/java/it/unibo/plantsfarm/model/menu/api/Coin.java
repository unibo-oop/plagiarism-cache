package it.unibo.plantsfarm.model.menu.api;

/**
 * Interface representing the coin model of the game.
 */
public interface Coin {

    /**
     * Adds a specified amount to the balance and saves it.
     *
     * @param amount The amount to add.
     */
    void addAmount(int amount);

    /**
     * Removes a specified amount from the balance and saves it.
     *
     * @param amount The amount to remove.
     * @return True if the transaction was successful, false otherwise.
     */
    boolean removeAmount(int amount);

    /**
     * Resets the wallet to the initial value.
     */
    void reset();

    /**
     * Gets the current amount of coins.
     *
     * @return The current balance.
     */
    int getValue();

}
