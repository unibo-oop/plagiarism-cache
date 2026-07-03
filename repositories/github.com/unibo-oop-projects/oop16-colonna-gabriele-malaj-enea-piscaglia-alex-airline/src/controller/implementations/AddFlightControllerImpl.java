package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;

import controller.interfaces.AddFlightController;
import model.enumerations.Status;
import model.implementations.AirportImpl;
import model.implementations.FlightImpl;
import model.implementations.Pair;
import model.interfaces.Airport;
import model.interfaces.Flight;
import model.interfaces.Pilot;
import model.interfaces.Plane;
import view.implementations.FlightsViewImpl;
import view.interfaces.AddFlightView;

/**
 * Add Flight Controller implementation.
 */
public class AddFlightControllerImpl implements AddFlightController {

    private static final int HOURS = 23;
    private static final int MINUTES = 59;
    private static final int LENGHT = 5;

    private final AddFlightView addFlightView;
    private final Airport airport;
    private final String username;
    private final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

    /**
     * Add Flight Controller constructor.
     * 
     * @param view the add flight view
     * @param usrname the staff member username
     */
    public AddFlightControllerImpl(final AddFlightView view, final String usrname) {
        this.addFlightView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showPlanes();
        this.showDestinations();
        this.showPilots();
        this.showCopilots();
        this.addFlightView.addConfirmListener(new ConfirmListener());
        this.addFlightView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                addFlight();
            } catch (NumberFormatException e1) {
                addFlightView.displayErrorMessage("Il prezzo base e gli orari devono contenere dei numeri!");
            } catch (ParseException e2) {
                addFlightView.displayErrorMessage("Il formato della data non è corretto!");
            }
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addFlightView.close();
            new FlightControllerImpl(new FlightsViewImpl(), username);
        }

    }

    @Override
    public void addFlight() throws NumberFormatException, ParseException {
        if (this.checkMissingInfo()) {
            this.addFlightView.displayErrorMessage("Inserire tutte le informazioni!");

        } else if (this.checkDateOrder(new Date(),
                this.format.parse(this.addFlightView.getDepartureDate()))) {
            this.addFlightView.displayErrorMessage("Non puoi inserire una data passata!");

        } else if (this.checkDateOrder(this.format.parse(this.addFlightView.getDepartureDate()),
                this.format.parse(this.addFlightView.getArrivalDate()))) {
            this.addFlightView.displayErrorMessage("La data di partenza non può essere\nsuccessiva alla data di arrivo!");

        } else if (this.addFlightView.getDepartureTime().length() != LENGHT
                || this.addFlightView.getArrivalTime().length() != LENGHT) {
            this.addFlightView.displayErrorMessage("Inserire l'ora nel formato [hh:mm]");

        } else if (this.checkTime(this.addFlightView.getDepartureTime())
                || this.checkTime(this.addFlightView.getArrivalTime())) {
            this.addFlightView.displayErrorMessage("Le ore devono essere comprese tra 00 e 23,\ni minuti tra 00 e 59!");

        } else if (this.checkTimeOrder(this.addFlightView.getDepartureTime(), this.addFlightView.getArrivalTime())) {
            this.addFlightView.displayErrorMessage("L'ora di partenza deve essere precedente all'ora di arrivo!");

        } else if (this.addFlightView.getPilot().equals(this.addFlightView.getCopilot())) {
            this.addFlightView.displayErrorMessage("Non puoi scegliere lo stesso pilota!");

        } else if (!this.isPilotAvailable()) {
            this.addFlightView.displayErrorMessage("Pilota or copilota non disponibile/i nel periodo selezionato!");

        } else if (!this.isPlaneAvailable()) {
            this.addFlightView.displayErrorMessage("L'aereo non è disponibile nel periodo selezionato!");

        } else {
            final Plane p = this.airport.searchPlane(this.addFlightView.getPlane()).get();
            p.setStatus(Status.BOOKED);

            final Flight f = new FlightImpl(p, this.airport.searchDestination(this.addFlightView.getDestination()).get(),
                    this.format.parse(this.addFlightView.getDepartureDate()),
                    this.format.parse(this.addFlightView.getArrivalDate()),
                    this.addFlightView.getDepartureTime(), this.addFlightView.getArrivalTime(),
                    Double.parseDouble(this.addFlightView.getBasicPrice()),
                    Arrays.asList(this.addFlightView.getFlightAttendants()));

            this.airport.addFlight(f, this.airport.searchPilot(this.addFlightView.getPilot()).get(),
                    this.airport.searchPilot(this.addFlightView.getCopilot()).get());
            this.addFlightView.displayErrorMessage("Volo " + f.getFlightId() + " inserito.");
            try {
                UtilitiesImpl.getInstance().saveFlights();
                UtilitiesImpl.getInstance().saveId();
            } catch (IOException e) {
                this.addFlightView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.addFlightView.close();
            new FlightControllerImpl(new FlightsViewImpl(), username);
        }
    }

    private boolean checkMissingInfo() {
        return this.addFlightView.getPlane() == null
            || this.addFlightView.getDestination() == null
            || this.addFlightView.getPilot() == null
            || this.addFlightView.getCopilot() == null
            || this.addFlightView.getDepartureTime().isEmpty()
            || this.addFlightView.getArrivalTime().isEmpty()
            || this.addFlightView.getBasicPrice().isEmpty();
    }

    private boolean checkDateOrder(final Date date1, final Date date2) {
        return date1.after(date2);
    }

    private boolean checkTime(final String time) throws NumberFormatException {
        final int index = time.indexOf(':');
        final int hour = Integer.parseInt(time.substring(0, index));
        final int minute = Integer.parseInt(time.substring(index + 1));
        return hour > HOURS || minute > MINUTES;
    }

    private boolean checkTimeOrder(final String time1, final String time2) throws ParseException {
        final Date departure = this.format.parse(this.addFlightView.getDepartureDate());
        final Date arrival = this.format.parse(this.addFlightView.getArrivalDate());
        return departure.equals(arrival) && this.compareTime(time1, time2) >= 0;
    }

    private int compareTime(final String time1, final String time2) throws NumberFormatException {
        final int index1 = time1.indexOf(':');
        final int hour1 = Integer.parseInt(time1.substring(0, index1));
        final int minute1 = Integer.parseInt(time1.substring(index1 + 1));
        final int index2 = time2.indexOf(':');
        final int hour2 = Integer.parseInt(time2.substring(0, index2));
        final int minute2 = Integer.parseInt(time2.substring(index2 + 1));
        return hour1 != hour2 ? hour1 - hour2 : minute1 - minute2;
    }

    private boolean isPilotAvailable() throws ParseException {
        for (final Entry<Flight, Pair<Pilot, Pilot>> e : this.airport.getFlights().entrySet()) {
            if ((this.addFlightView.getPilot().equals(e.getValue().getFirst().getPilotId())
                    || this.addFlightView.getPilot().equals(e.getValue().getSecond().getPilotId())
                    || this.addFlightView.getCopilot().equals(e.getValue().getFirst().getPilotId())
                    || this.addFlightView.getCopilot().equals(e.getValue().getSecond().getPilotId()))
                    && this.timeOverlay(this.format.parse(this.addFlightView.getDepartureDate()),
                                        this.format.parse(this.addFlightView.getArrivalDate()),
                                        e.getKey().getDepartureDate(),
                                        e.getKey().getArrivalDate())) {
                return false;
            }
        }
        return true;
    }

    private boolean isPlaneAvailable() throws ParseException {
        for (final Entry<Flight, Pair<Pilot, Pilot>> e : this.airport.getFlights().entrySet()) {
            if (e.getKey().getPlane().getPlaneId().equals(this.addFlightView.getPlane())
                    && this.timeOverlay(this.format.parse(this.addFlightView.getDepartureDate()),
                                        this.format.parse(this.addFlightView.getArrivalDate()),
                                        e.getKey().getDepartureDate(),
                                        e.getKey().getArrivalDate())) {
                return false;
            }
        }
        return true;
    }

    private boolean timeOverlay(final Date depDate1, final Date arrDate1, final Date depDate2, final Date arrDate2) {
        return !(arrDate1.before(depDate2) || depDate1.after(arrDate2));
    }

    private void showPlanes() {
        this.airport.getPlanes().forEach(p -> this.addFlightView.addPlaneToCmbx(p.getPlaneId()));
    }

    private void showDestinations() {
        this.airport.getDestinations().forEach(d -> this.addFlightView.addDestinationToCmbx(d.getDestinationId()));
    }

    private void showPilots() {
        this.airport.getPilots().forEach(p -> this.addFlightView.addPilotToCmbx(p.getPilotId()));
    }

    private void showCopilots() {
        this.airport.getPilots().forEach(p -> this.addFlightView.addCopilotToCmbx(p.getPilotId()));
    }

}
