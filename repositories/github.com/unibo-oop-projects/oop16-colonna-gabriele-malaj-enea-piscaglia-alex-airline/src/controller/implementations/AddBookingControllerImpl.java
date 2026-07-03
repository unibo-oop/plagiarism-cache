package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Locale;

import controller.interfaces.AddBookingController;
import model.enumerations.TravelClass;
import model.implementations.AirportImpl;
import model.implementations.BookingImpl;
import model.implementations.LuggageImpl;
import model.implementations.PassengerImpl;
import model.interfaces.Airport;
import model.interfaces.Booking;
import model.interfaces.Flight;
import model.interfaces.Luggage;
import model.interfaces.Passenger;
import view.implementations.BookingsViewImpl;
import view.interfaces.AddBookingView;

/**
 * Add Booking Controller implementation.
 */
public class AddBookingControllerImpl implements AddBookingController {

    private final AddBookingView addBookingView;
    private final Airport airport;
    private final String username;
    private final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

    /**
     * Add Booking Controller constructor.
     * 
     * @param view the add booking view
     * @param usrname the staff member username
     */
    public AddBookingControllerImpl(final AddBookingView view, final String usrname) {
        this.addBookingView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.insertFlights();
        this.addBookingView.addConfirmListener(new ConfirmListener());
        this.addBookingView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                addBooking();
            } catch (NumberFormatException e1) {
                addBookingView.displayErrorMessage("L'età deve contenere un intero!");
            }
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addBookingView.close();
            new BookingControllerImpl(new BookingsViewImpl(), username);
        }

    }

    @Override
    public void addBooking() throws NumberFormatException {
        if (this.checkMissingInfo()) {
            this.addBookingView.displayErrorMessage("Inserire tutte le informazioni!");

        } else {
            final Flight f = this.airport.searchFlight(this.estractFlightId((String) this.addBookingView.getFlight())).get().getFirst();

            final TravelClass tc = this.convertToTravelClass(this.addBookingView.getTravelClass());

            if (f.getPlane().isClassFull(tc)) {
                this.addBookingView.displayErrorMessage(tc + " è piena!");
            } else {
                f.getPlane().bookSeat(tc);

                final Passenger p = new PassengerImpl(this.addBookingView.getDocumentId(), this.addBookingView.getName(),
                        this.addBookingView.getSurname(), null, null, Integer.parseInt(this.addBookingView.getAge()),
                        this.addBookingView.getFiscalCode(), this.addBookingView.getTelephoneNumber(), this.addBookingView.getEmail());

                final Luggage l = new LuggageImpl(this.addBookingView.getWeight());


                final Booking b = new BookingImpl(f, tc, p);

                this.airport.addLuggage(f, l, p);
                this.airport.addReservation(b);
                this.airport.calculateFinalPrice(b, l);
                this.addBookingView.displayErrorMessage("Prenotazione " + b.getBookingId() + " inserita.");
                this.addBookingView.displayErrorMessage("Saldo: " + b.getFinalPrice() + "€");
                try {
                    UtilitiesImpl.getInstance().saveReservations();
                    UtilitiesImpl.getInstance().saveId();
                } catch (IOException e) {
                    this.addBookingView.displayErrorMessage("Errore durante il salvataggio dei dati!");
                }
                this.addBookingView.close();
                new BookingControllerImpl(new BookingsViewImpl(), username);
            }
        }
    }

    private boolean checkMissingInfo() {
        return this.addBookingView.getFlight() == null
            || this.addBookingView.getName().isEmpty()
            || this.addBookingView.getSurname().isEmpty()
            || this.addBookingView.getFiscalCode().isEmpty()
            || this.addBookingView.getTelephoneNumber().isEmpty()
            || this.addBookingView.getEmail().isEmpty()
            || this.addBookingView.getDocumentId().isEmpty()
            || this.addBookingView.getAge().isEmpty();
    }

    private TravelClass convertToTravelClass(final String s) {
        TravelClass tc = TravelClass.ECONOMY;
        switch (s) {
            case "Prima" : tc = TravelClass.FIRST; break;
            case "Business" : tc = TravelClass.BUSINESS; break;
            default:
        }
        return tc;
    }

    private String estractFlightId(final String s) {
        final int i = s.indexOf(' ');
        return s.substring(0, i);
    }

    private void insertFlights() {
        for (final Flight f : this.airport.getFlights().keySet()) {
            if (!f.getPlane().isPlaneFull()) {
                this.addBookingView.addFlightToCmbx(f.getFlightId() + " " + f.getDestination().getAirportName()
                        + " " + f.getDestination().getCity() + " " + f.getDestination().getCountry()
                        + " " + this.format.format(f.getDepartureDate()));
            }
        }
    }

}
