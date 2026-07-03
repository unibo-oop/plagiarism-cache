package controller;

import model.Discount;

/**
 * It is the interface used to set the discount.
 *
 */
public interface DiscountController {

    /**
     * It is used to check the discount for the booking.
     *
     * @param nTickets
     *            the number of the tickets that he/she wants to book.
     * @param nUnder14
     *            the number of the people who want to go to the cinema that are under 14.
     * @param nStudents
     *            the number of students who want to go to the cinema.
     * @throws IllegalArgumentException
     *             if the discount can't be set.
     * @return the discount.
     */
    Discount checkDiscount(int nTickets, int nUnder14, int nStudents) throws IllegalArgumentException;
}
