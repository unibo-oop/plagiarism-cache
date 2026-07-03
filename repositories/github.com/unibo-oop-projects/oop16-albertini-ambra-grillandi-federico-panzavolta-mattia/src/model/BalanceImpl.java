package model;

import utilities.Genre;

/**
 * 
 * This is an implementation of {@link model.Balance}.
 * 
 */
public class BalanceImpl implements Balance {

    private static final double INITIAL_BUDGET = 10000;

    private double totalBudget;
    private double boxOffice;
    private double expense;
    private final double ticketCost;

    /**
     * Builds a new {@link BalanceImpl}.
     * 
     * @param ticketCost
     *             the price of the ticket
     * 
     */
    public BalanceImpl(final double ticketCost) {
        this.ticketCost = ticketCost;
        this.totalBudget = INITIAL_BUDGET;
    }

    @Override
    public void setExpense(final double expense) {
        this.expense = expense;
    }

    @Override
    public void setBudget(final double budget) {
        this.totalBudget = budget;
    }

    @Override
    public void setBoxOffice(final double boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Override
    public double getBudget() {
        return this.totalBudget;
    }

    @Override
    public double getBoxOffice() {
        return this.boxOffice;
    }

    @Override
    public double getExpense() {
        return this.expense;
    }

    @Override
    public double getTicketCost() {
        return this.ticketCost;
    }

    @Override
    public void incBoxOffice(final double money) {
        this.boxOffice = money;
        computeBudget(getBoxOffice());
    }

    @Override
    public void incExpense(final Genre g) {
        this.expense = -g.getPrice();
        computeBudget(getExpense());
    }

    @Override
    public void computeBudget(final double expense) {
        this.totalBudget = this.totalBudget + expense;
        if (this.totalBudget < 0) {
            final String msg = "You went bankrupt";
            throw new IllegalArgumentException(msg);
        }
    }

}
