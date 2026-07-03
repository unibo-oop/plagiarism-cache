package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.AddPlaneController;
import model.implementations.AirportImpl;
import model.implementations.PlaneImpl;
import model.interfaces.Airport;
import model.interfaces.Plane;
import view.implementations.PlaneViewImpl;
import view.interfaces.AddPlaneView;

/**
 * Add Plane Controller implementation.
 */
public class AddPlaneControllerImpl implements AddPlaneController {

    private final AddPlaneView addPlaneView;
    private final Airport airport;
    private final String username;

    /**
     * Add Plane Controller constructor.
     * 
     * @param view the add plane view
     * @param usrname the staff member username
     */
    public AddPlaneControllerImpl(final AddPlaneView view, final String usrname) {
        this.addPlaneView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.addPlaneView.addConfirmListener(new ConfirmListener());
        this.addPlaneView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                addPlane();
            } catch (NumberFormatException ex) {
                addPlaneView.displayErrorMessage("I posti devono contenere degli interi!");
            }
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addPlaneView.close();
            new PlaneControllerImpl(new PlaneViewImpl(), username);
        }

    }

    @Override
    public void addPlane() throws NumberFormatException {
        if (this.checkMissingInfo()) {
            this.addPlaneView.displayErrorMessage("Inserire tutte le informazioni!");

        } else {
            final Plane p = new PlaneImpl(this.addPlaneView.getAirlineName(),
                    Integer.parseInt(this.addPlaneView.getNEconomyClassTotalSeats()),
                    Integer.parseInt(this.addPlaneView.getNBusinessClassTotalSeats()),
                    Integer.parseInt(this.addPlaneView.getNFirstClassTotalSeats()));

            this.airport.addPlane(p);
            this.addPlaneView.displayErrorMessage("Aereo " + p.getPlaneId() + " inserito.");

            try {
                UtilitiesImpl.getInstance().savePlanes();
                UtilitiesImpl.getInstance().saveId();
            } catch (IOException e) {
                this.addPlaneView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }

            this.addPlaneView.close();
            new PlaneControllerImpl(new PlaneViewImpl(), username);
        }
    }

    private boolean checkMissingInfo() {
        return this.addPlaneView.getAirlineName().isEmpty()
            || this.addPlaneView.getNEconomyClassTotalSeats().isEmpty()
            || this.addPlaneView.getNBusinessClassTotalSeats().isEmpty()
            || this.addPlaneView.getNFirstClassTotalSeats().isEmpty();
    }

}
