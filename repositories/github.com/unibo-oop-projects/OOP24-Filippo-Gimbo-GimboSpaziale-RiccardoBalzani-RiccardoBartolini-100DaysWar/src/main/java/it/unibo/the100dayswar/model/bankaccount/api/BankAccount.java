package it.unibo.the100dayswar.model.bankaccount.api;

import java.io.Serializable;

/**
 * Interface to define the behavior of a bank account for managing a player's resources.
 */
public interface BankAccount extends Serializable {
    /**
     * Retrieves the current balance of the bank account.
     *
     * @return the current balance of resources
     */
    int getBalance();
    /**
     * Adds resources to the player's bank account.
     *
     * @param amount the amount of resources to add
     */
    void earn(int amount);
    /**
     * Spends a specific amount of resources from the player's bank account.
     *
     * @param amount the amount of resources to spend
     * @throws IllegalStateException if the player does not have enough resources
     */
    void purchase(int amount);
}
