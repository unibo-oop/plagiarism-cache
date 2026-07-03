package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.DestinationController;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Destination;
import model.interfaces.Flight;
import view.implementations.AddDestinationViewImpl;
import view.implementations.DestinationViewImpl;
import view.implementations.MainMenuViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Destination Controller implementation.
 */
public class DestinationControllerImpl implements DestinationController {

    private final AddRemoveView destView;
    private final Airport airport;
    private final String username;

    /**
     * Destination Controller constructor.
     * 
     * @param view the destination view
     * @param usrname the staff member username
     */
    public DestinationControllerImpl(final AddRemoveView view, final String usrname) {
        this.destView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showDestinations();
        this.destView.addButtonListener(new InsertDestinationListener());
        this.destView.removeButtonListener(new RemoveDestinationListener());
        this.destView.backButtonListener(new BackListener());
    }

    private class InsertDestinationListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            destView.close();
            new AddDestinationControllerImpl(new AddDestinationViewImpl(), username);
        }

    }

    private class RemoveDestinationListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removeDestination();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            destView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removeDestination() {
        final int i = this.destView.getSelectedIndexOfList();
        if (i < 0) {
            this.destView.displayErrorMessage("Selezionare una destinazione!");

        } else if (this.isDestinationBooked(this.airport.getDestinations().get(i))) {
            this.destView.displayErrorMessage("La destinazione selezionata è meta di un volo.\nRimuovere prima il volo!");

        } else {
            final Destination d = this.airport.getDestinations().get(i);
            this.airport.removeDestination(d);
            try {
                UtilitiesImpl.getInstance().saveDestinations();
            } catch (IOException e) {
                this.destView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.destView.close();
            new DestinationControllerImpl(new DestinationViewImpl(), username);
        }
    }

    private boolean isDestinationBooked(final Destination d) {
        for (final Flight f : this.airport.getFlights().keySet()) {
            if (f.getDestination().equals(d)) {
                return true;
            }
        }
        return false;
    }

    private void showDestinations() {
        this.airport.getDestinations().forEach(d -> this.destView.addItemsToList(d));
    }

}
