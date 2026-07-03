package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map.Entry;

import controller.interfaces.FlightAttendantController;
import model.implementations.AirportImpl;
import model.implementations.Pair;
import model.interfaces.Airport;
import model.interfaces.Flight;
import model.interfaces.FlightAttendant;
import model.interfaces.Pilot;
import view.implementations.AddFlightAttendantViewImpl;
import view.implementations.FlightAttendantViewImpl;
import view.implementations.MainMenuViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Flight Attendant Controller implementation.
 */
public class FlightAttendantControllerImpl implements FlightAttendantController {


    private final AddRemoveView faView;
    private final Airport airport;
    private final String username;

    /**
     * Flight Attendant Controller constructor.
     * 
     * @param view the flight attendant view
     * @param usrname the staff member username
     */
    public FlightAttendantControllerImpl(final AddRemoveView view, final String usrname) {
        this.faView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showFA();
        this.faView.addButtonListener(new InsertFAListener());
        this.faView.removeButtonListener(new RemoveFAListener());
        this.faView.backButtonListener(new BackListener());
    }

    private class InsertFAListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            faView.close();
            new AddFlightAttendantControllerImpl(new AddFlightAttendantViewImpl(), username);
        }

    }

    private class RemoveFAListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removeFlightAttendant();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            faView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removeFlightAttendant() {
        final int i = this.faView.getSelectedIndexOfList();
        if (i < 0) {
            this.faView.displayErrorMessage("Seleziona un assistente di volo!");

        } else if (this.isFaBooked(i)) {
            this.faView.displayErrorMessage("L'assistente selezionato è prenotato per un volo.\nRimuovere il volo!");

        } else {
            final FlightAttendant fa = this.airport.getFlightAttendants().get(i);
            this.airport.removeFlightAttendant(fa);
            try {
                UtilitiesImpl.getInstance().saveFlightAttendants();
            } catch (IOException e) {
                this.faView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.faView.close();
            new FlightAttendantControllerImpl(new FlightAttendantViewImpl(), username);
        }
    }

    private boolean isFaBooked(final int index) {
        final FlightAttendant fa = this.airport.getFlightAttendants().get(index);
        for (final Entry<Flight, Pair<Pilot, Pilot>> e : this.airport.getFlights().entrySet()) {
            for (final String id : e.getKey().getFlightAttendantIdentifiersList()) {
                if (fa.getFlightAttendantId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void showFA() {
        this.airport.getFlightAttendants().forEach(fa -> this.faView.addItemsToList(fa));
    }

}
