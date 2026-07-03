package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.PilotController;
import model.implementations.AirportImpl;
import model.implementations.Pair;
import model.interfaces.Airport;
import model.interfaces.Pilot;
import view.implementations.AddPilotViewImpl;
import view.implementations.MainMenuViewImpl;
import view.implementations.PilotViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Pilot Controller implementation.
 */
public class PilotControllerImpl implements PilotController {

    private final AddRemoveView pilotView;
    private final Airport airport;
    private final String username;

    /**
     * Pilot Controller constructor.
     * 
     * @param view the pilot view
     * @param usrname the staff member username
     */
    public PilotControllerImpl(final AddRemoveView view, final String usrname) {
        this.pilotView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showPilots();
        this.pilotView.addButtonListener(new InsertPilotListener());
        this.pilotView.removeButtonListener(new RemovePilotListener());
        this.pilotView.backButtonListener(new BackListener());
    }

    private class InsertPilotListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            pilotView.close();
            new AddPilotControllerImpl(new AddPilotViewImpl(), username);
        }

    }

    private class RemovePilotListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removePilot();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            pilotView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removePilot() {
        final int i = this.pilotView.getSelectedIndexOfList();
        if (i < 0) {
            this.pilotView.displayErrorMessage("Selezionare un pilota!");

        } else if (this.isPilotBooked(this.airport.getPilots().get(i))) {
            this.pilotView.displayErrorMessage("Il pilota selezionato è prenotato per un volo.\nRimuovere prima il volo!");

        } else {
            final Pilot p = this.airport.getPilots().get(i);
            this.airport.removePilot(p);
            try {
                UtilitiesImpl.getInstance().savePilots();
            } catch (IOException e) {
                this.pilotView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.pilotView.close();
            new PilotControllerImpl(new PilotViewImpl(), username);
        }
    }

    private boolean isPilotBooked(final Pilot p) {
        for (final Pair<Pilot, Pilot> pair : this.airport.getFlights().values()) {
            if (pair.getFirst().equals(p) || pair.getSecond().equals(p)) {
                return true;
            }
        }
        return false;
    }

    private void showPilots() {
        this.airport.getPilots().forEach(p -> this.pilotView.addItemsToList(p));
    }

}
