package controller;

import utilities.CreateCinema;

/**
 * It implements CinemaBalance.
 *
 */
public final class CinemaBalance {

    /**
     * It is the initial balance of the cinema.
     */
    public static final double MAX_BUDGET = 10000.00;

    private CinemaBalance() {
    }

    /**
     * @return the budget of the cinema.
     */
    public static double getTotBalance() {
        Double budget = CreateCinema.getCinema().getBudget();
        final SaveAndReadBalance readBalance = new SaveAndReadBalance();
        if (budget.equals(MAX_BUDGET)) {
            readBalance.setCheck(SaveAndReadBalance.BALANCE_STR);
            budget = readBalance.read();
            if (budget.equals(0.0)) {
                budget = MAX_BUDGET;
            }
        }
        return budget;
    }

    /**
     * @return the gain of the cinema considering how much is earned with the tickets sold.
     */
    public static double getCinemaBoxOffice() {
        Double boxOffice = CreateCinema.getCinema().getBoxOffice();
        final SaveAndReadBalance readBalance = new SaveAndReadBalance();
        if (boxOffice.equals(0.0)) {
            readBalance.setCheck(SaveAndReadBalance.BOX_OFFICE_STR);
            boxOffice = readBalance.read();
        }
        return boxOffice;
    }

    /**
     * @return the expense of the cinema considering the cost of the movies.
     */
    public static double getCinemaExpense() {
        Double expense = CreateCinema.getCinema().getExpense();
        final SaveAndReadBalance readBalance = new SaveAndReadBalance();
        if (expense.equals(0.0)) {
            readBalance.setCheck(SaveAndReadBalance.EXPENSE_STR);
            expense = readBalance.read();
        }
        return expense;
    }

}
