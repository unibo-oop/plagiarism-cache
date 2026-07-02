package it.unibo.plantsfarm.model.menu.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.plantsfarm.controller.memory.api.DataMemory;
import it.unibo.plantsfarm.controller.memory.impl.DataMemoryImpl;
import it.unibo.plantsfarm.model.menu.api.Coin;

/**
 * Represents the coins of the player.
 */
public final class CoinImpl implements Coin {

    private static final String FILE_NAME = "wallet.txt";
    private static final int INITIAL_COINS = 500;
    private static final Logger LOGGER = Logger.getLogger(CoinImpl.class.getName());

    private int value;
    private final DataMemory memory;

    /**
     * Creates a new Coin wallet.
     * Tries to load previous coins from memory.
     *
     * @param initialValue The starting amount of coins if no save exists.
     */
    public CoinImpl(final int initialValue) {
        this.memory = new DataMemoryImpl();

        try {
            final String savedData = memory.load(FILE_NAME);
            if (savedData != null) {
                this.value = Integer.parseInt(savedData);
            } else {
                this.value = initialValue;
            }
        } catch (final IOException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Coin Load Error", e);
            this.value = initialValue;
        }
    }

    /**
     * Adds a specified amount to the balance and saves it.
     *
     * @param amount The amount to add.
     */
    @Override
    public void addAmount(final int amount) {
        if (amount > 0) {
            this.value += amount;
            save();
        }
    }

    /**
     * Removes a specified amount from the balance and saves it.
     *
     * @param amount The amount to remove.
     * @return True if the transaction was successful, false otherwise.
     */
    @Override
    public boolean removeAmount(final int amount) {
        if (amount > 0 && this.value >= amount) {
            this.value -= amount;
            save();
            return true;
        }
        return false;
    }

    /**
     * Resets the wallet to the initial value.
     */
    @Override
    public void reset() {
        this.value = INITIAL_COINS;
        save();
    }

    /**
     * Helper method to save current status.
     */
    private void save() {
        try {
            this.memory.save(FILE_NAME, this.value);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Coin Save Error", e);
        }
    }

    /**
     * Gets the current amount of coins.
     *
     * @return The current balance.
     */
    @Override
    public int getValue() {
        return this.value;
    }
}
