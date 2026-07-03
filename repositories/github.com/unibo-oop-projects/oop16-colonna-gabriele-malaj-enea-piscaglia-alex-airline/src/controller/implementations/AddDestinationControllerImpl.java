package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.AddDestinationController;
import model.implementations.AirportImpl;
import model.implementations.DestinationImpl;
import model.interfaces.Airport;
import model.interfaces.Destination;
import view.implementations.DestinationViewImpl;
import view.interfaces.AddDestinationView;

/**
 * Add Destination Controller implementation.
 */
public class AddDestinationControllerImpl implements AddDestinationController {

    private final AddDestinationView addDestView;
    private final Airport airport;
    private final String username;

    /**
     * Add Destination Controller constructor.
     * 
     * @param view the add destination view
     * @param usrname the staff member username
     */
    public AddDestinationControllerImpl(final AddDestinationView view, final String usrname) {
        this.addDestView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.addDestView.addConfirmListener(new ConfirmListener());
        this.addDestView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addDestination();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addDestView.close();
            new DestinationControllerImpl(new DestinationViewImpl(), username);
        }

    }

    @Override
    public void addDestination() {
        if (this.checkMissingInfo()) {
            this.addDestView.displayErrorMessage("Inserire tutte le informazioni!");

        } else if (this.airport.searchDestination(this.addDestView.getDestinationId()).isPresent()) {
            this.addDestView.displayErrorMessage("ID già presente!");

        } else {
            final Destination d = new DestinationImpl(this.addDestView.getDestinationId(),
                   this.addDestView.getCountry(), this.addDestView.getCity(), this.addDestView.getAirport());

            this.airport.addDestination(d);
            this.addDestView.displayErrorMessage("Destinazione " + d.getDestinationId() + " inserita");

            try {
                UtilitiesImpl.getInstance().saveDestinations();
                UtilitiesImpl.getInstance().saveId();
            } catch (IOException e) {
                this.addDestView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }

            this.addDestView.close();
            new DestinationControllerImpl(new DestinationViewImpl(), username);
        }
    }

    private boolean checkMissingInfo() {
        return this.addDestView.getDestinationId().isEmpty()
            || this.addDestView.getCountry().isEmpty()
            || this.addDestView.getCity().isEmpty()
            || this.addDestView.getAirport().isEmpty();
    }

}
