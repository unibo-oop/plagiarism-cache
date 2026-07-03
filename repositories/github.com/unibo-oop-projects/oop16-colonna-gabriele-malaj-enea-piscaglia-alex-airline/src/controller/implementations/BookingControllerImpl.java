package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.BookingController;
import model.enumerations.Status;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Booking;
import view.implementations.AddBookingViewImpl;
import view.implementations.BookingsViewImpl;
import view.implementations.MainMenuViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Booking Controller implementation.
 */
public class BookingControllerImpl implements BookingController {

    private final AddRemoveView bookingView;
    private final Airport airport;
    private final String username;

    /**
     * Booking Controller constructor.
     * 
     * @param view the booking view
     * @param usrname the staff member username
     */
    public BookingControllerImpl(final AddRemoveView view, final String usrname) {
        this.bookingView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showReservations();
        this.bookingView.addButtonListener(new InsertBookingListener());
        this.bookingView.removeButtonListener(new RemoveBookingListener());
        this.bookingView.backButtonListener(new BackListener());
    }

    private class InsertBookingListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            bookingView.close();
            new AddBookingControllerImpl(new AddBookingViewImpl(), username);
        }

    }

    private class RemoveBookingListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removeBooking();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            bookingView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removeBooking() {
        final int i = this.bookingView.getSelectedIndexOfList();
        if (i < 0) {
            this.bookingView.displayErrorMessage("Selezionare una prenotazione!");

        } else if (this.airport.getReservations().get(i).getFlight().getPlane().getStatus() == Status.TAKEN_OFF) {
            this.bookingView.displayErrorMessage("L'aereo relativo è in volo.\nAttendere l'atterraggio!");

        } else {
            final Booking b = this.airport.getReservations().get(i);
            this.airport.removeReservation(b);
            try {
                UtilitiesImpl.getInstance().saveReservations();
            } catch (IOException e) {
                this.bookingView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.bookingView.close();
            new BookingControllerImpl(new BookingsViewImpl(), username);
        }
    }

    private void showReservations() {
        this.airport.getReservations().forEach(r -> this.bookingView.addItemsToList(r));
    }

}
