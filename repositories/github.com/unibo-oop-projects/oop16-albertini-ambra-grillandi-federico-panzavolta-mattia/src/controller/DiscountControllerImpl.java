package controller;

import model.Discount;
import model.ModelImpl;
import utilities.CreateCinema;

/**
 * Implementation for DiscountController.
 */
public class DiscountControllerImpl implements DiscountController {

    // The discount on the booking.
    private Discount discount;

    @Override
    public Discount checkDiscount(final int nTickets, final int nUnder14, final int nStudents)
            throws IllegalArgumentException {
        if (nTickets > ModelImpl.MAX_TICKETS || nUnder14 + nStudents > nTickets) {
            throw new IllegalArgumentException();
        }
        setDiscount(nUnder14, nStudents);
        return discount;
    }

    /**
     * It sets the discount for the tickets.
     *
     * @param nUnder14
     *            the number of the people who want to go to the cinema that are under 14.
     * @param nStudents
     *            the number of students who want to go to the cinema.
     */
    private void setDiscount(final int nUnder14, final int nStudents) {
        discount = CreateCinema.getCinema().createDiscount(nUnder14, nStudents);
    }

}
