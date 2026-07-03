package model;

import utilities.Genre;

/**
 * This interface allows the calculation of the budget of the multiplex.
 * 
 */
public interface Balance {

    /**
     * Set the expense to buy a new film.
     * 
     * @param expense
     *        the price to buy the new film
     */
    void setExpense(double expense);

    /**
     * Set the budget of the multiplex.
     * 
     * @param budget
     *        the money owned by the multiplex
     */
    void setBudget(double budget);

    /**
     * Set the box office of the tickets sold.
     * 
     * @param boxOffice
     *        the money gained after the selling of the tickets
     */
    void setBoxOffice(double boxOffice);

    /**
     * Return the budget of the multiplex.
     * 
     * @return the budget of the multiplex
     */
    double getBudget();

    /**
     * Return the total box office.
     * 
     * @return the money earned with the sell of the tickets
     */
    double getBoxOffice();

    /**
     * Return the cost to buy a film.
     * 
     * @return the cost to buy a film
     */
    double getExpense();

    /**
     * Return the cost of the ticket.
     * 
     * @return the cost of the ticket
     */
    double getTicketCost();

    /**
     * Calculate the box office.
     * 
     * @param money
     *        the money to add to the budget
     */
    void incBoxOffice(double money);

    /**
     * Calculate the cost of the film.
     * 
     * @param g
     *         the genre of the film
     */
    void incExpense(Genre g);

    /**
     * Calculate the total budget of the multiplex.
     * 
     * @param expense
     *          the price to buy the film
     */
    void computeBudget(double expense);
}
