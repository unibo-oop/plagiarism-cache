package model.character;

import java.io.Serializable;

/**
 * 
 * is the wallet of character for buy in the shop.
 *
 */
public class Wallet implements Serializable {

    private static final long serialVersionUID = -1795042879773108382L;
    private int balance;
    private int initialBalance;
    private static final int LIMIT = 9999;

    /**
     * is the constructor.
     * 
     * @param amount
     *            is the initialBalance
     */
    public Wallet(final int amount) {
        this.setInitialBalance(amount);
        this.balance = this.initialBalance;
    }

    /**
     * 
     * @param amount
     *            is the initial balance
     */
    public void setInitialBalance(final int amount) {
        this.initialBalance = amount;
    }

    /**
     * 
     * @param amount
     *            is the amount that modify balance.
     */
    public void useMoney(final int amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Wallet [balance=" + balance + "]";
    }

    /**
     * @param amount
     *            is the credit to add into balance
     * @param age
     *            is the age of character
     */
    public void setBalance(final double age, final int amount) {
        if (balance < LIMIT) {
            this.balance += (age * amount);
            this.balance = balance > LIMIT ? LIMIT : balance;
        }
    }

    /**
     * 
     * @return balance
     */
    public int getBalance() {
        return this.balance;
    }
}
