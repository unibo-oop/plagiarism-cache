package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.PlaneController;
import model.enumerations.Status;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Plane;
import view.implementations.AddPlaneViewImpl;
import view.implementations.MainMenuViewImpl;
import view.implementations.PlaneViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Plane Controller implementation.
 */
public class PlaneControllerImpl implements PlaneController {

    private final AddRemoveView planeView;
    private final Airport airport;
    private final String username;

    /**
     * Plane Controller constructor.
     * 
     * @param view the plane view
     * @param usrname the staff member username
     */
    public PlaneControllerImpl(final AddRemoveView view, final String usrname) {
        this.planeView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showPlanes();
        this.planeView.addButtonListener(new InsertPlaneListener());
        this.planeView.removeButtonListener(new RemovePlaneListener());
        this.planeView.backButtonListener(new BackListener());
    }

    private class InsertPlaneListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            planeView.close();
            new AddPlaneControllerImpl(new AddPlaneViewImpl(), username);
        }

    }

    private class RemovePlaneListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removePlane();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            planeView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removePlane() {
        final int i = this.planeView.getSelectedIndexOfList();
        if (i < 0) {
            this.planeView.displayErrorMessage("Selezionare un aereo!");

        } else if (this.airport.getPlanes().get(i).getStatus() == Status.BOOKED) {
            this.planeView.displayErrorMessage("L'aereo selezionato è prenotato.\nRimuovere prima il volo!");

        } else if (this.airport.getPlanes().get(i).getStatus() == Status.TAKEN_OFF) {
            this.planeView.displayErrorMessage("L'aereo selezionato è in volo.\nAttendere l'atterraggio!");

        } else if (this.airport.getPlanes().get(i).getStatus() == Status.LANDED) {
            this.planeView.displayErrorMessage("L'aereo selezionato non è ancora rientrato!");

        } else {
            final Plane p = this.airport.getPlanes().get(i);
            this.airport.removePlane(p);
            try {
                UtilitiesImpl.getInstance().savePlanes();
            } catch (IOException e) {
                this.planeView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.planeView.close();
            new PlaneControllerImpl(new PlaneViewImpl(), username);
        }
    }

    private void showPlanes() {
        this.airport.getPlanes().forEach(p -> this.planeView.addItemsToList(p));
    }

}
