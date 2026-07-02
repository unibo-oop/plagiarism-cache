package it.unibo.the100dayswar.model.bankaccount.impl;


import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.the100dayswar.model.bankaccount.api.BankAccount;

/**
 * The implementation of a bank account for managing a player's resources.
 */
public class BankAccountImpl implements BankAccount {
    private static final long serialVersionUID = 1L;
    private static final int INITIAL_BALANCE = 250;
    private static final Logger LOGGER = Logger.getLogger(BankAccountImpl.class.getName());
    private int balance;
    /** 
     * Constructor for a bank account.
     */
    public BankAccountImpl() {
        this.balance = INITIAL_BALANCE;
    }
    /**
     * Constructor for a bank account with a given balance.
     * 
     * @param bankAccount the bank account to copy
     */
    public BankAccountImpl(final BankAccount bankAccount) {
        this.balance = bankAccount.getBalance();
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public int getBalance() {
        return this.balance;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void earn(final int amount) {
        setBalance(this.balance + amount);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void purchase(final int amount) {
        if (!canAfford(amount)) {
            LOGGER.log(Level.SEVERE, "Illegal state exception", new IllegalStateException());
            throw new IllegalStateException();
        }
        setBalance(this.balance - amount);
    }
    /** 
     * Change the balance of the bank account.
     * 
     * @param newBalance the new balance to set
     */
    private void setBalance(final int newBalance) {
        this.balance = newBalance;
    }
    /**
     * Checks if the current bank account can spend an amount of resources.
     * 
     * @param amount the amount of resources to check
     * @return true if the player can afford the amount, false otherwise
     */
    private boolean canAfford(final int amount) {
        return this.getBalance() >= amount;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BankAccountImpl)) {
            return false;
        }
        final BankAccountImpl other = (BankAccountImpl) obj;
        return this.getBalance() == other.getBalance();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.getBalance());
    }
}
