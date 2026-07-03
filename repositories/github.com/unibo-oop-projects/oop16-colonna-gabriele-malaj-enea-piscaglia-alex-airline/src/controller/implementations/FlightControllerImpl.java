package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import controller.interfaces.FlightConttroller;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Booking;
import model.interfaces.Flight;
import view.implementations.AddFlightViewImpl;
import view.implementations.FlightsViewImpl;
import view.implementations.MainMenuViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Flight Controller implementation.
 */
public class FlightControllerImpl implements FlightConttroller {

    private final AddRemoveView flightView;
    private final Airport airport;
    private final String username;

    /**
     * Flight Controller constructor.
     * 
     * @param view the flight view
     * @param usrname the staff member username
     */
    public FlightControllerImpl(final AddRemoveView view, final String usrname) {
        this.flightView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showFlights();
        this.flightView.addButtonListener(new InsertFlightListener());
        this.flightView.removeButtonListener(new RemoveFlightListener());
        this.flightView.backButtonListener(new BackListener());
    }

    private class InsertFlightListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            final Set<String> set = new HashSet<>();
            airport.getFlightAttendants().forEach(fa -> set.add(fa.getFlightAttendantId()));
            flightView.close();
            new AddFlightControllerImpl(new AddFlightViewImpl(set), username);
        }

    }

    private class RemoveFlightListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removeFlight();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            flightView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removeFlight() {
        final Flight f = (Flight) this.flightView.getSelectedObjectOfList();
        if (f == null) {
            this.flightView.displayErrorMessage("Selezionare un volo!");
        } else if (this.isFlightBooked(f)) {
            this.flightView.displayErrorMessage("Il volo selezionato ha delle prenotazioni, rimuoverle.");
        } else {
            this.airport.removeFlight(f);
            try {
                UtilitiesImpl.getInstance().saveFlights();
            } catch (IOException e) {
                this.flightView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.flightView.close();
            new FlightControllerImpl(new FlightsViewImpl(), this.username);
        }
    }

    private void showFlights() {
        this.airport.getFlights().keySet().forEach(f -> this.flightView.addItemsToList(f));
    }

    private boolean isFlightBooked(final Flight f) {
        for (final Booking b : this.airport.getReservations()) {
            if (b.getFlight().equals(f)) {
                return true;
            }
        }
        return false;
    }

}
