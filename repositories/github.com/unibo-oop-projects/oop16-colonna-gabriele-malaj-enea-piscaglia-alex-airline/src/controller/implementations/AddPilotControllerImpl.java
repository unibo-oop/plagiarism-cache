package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import controller.interfaces.AddPilotController;
import model.implementations.AirportImpl;
import model.implementations.PilotImpl;
import model.interfaces.Airport;
import model.interfaces.Pilot;
import view.implementations.PilotViewImpl;
import view.interfaces.AddPilotView;

/**
 * Add Pilot Controller implementation.
 */
public class AddPilotControllerImpl implements AddPilotController {

    private final AddPilotView addPilotView;
    private final Airport airport;
    private final String username;
    private String message;

    /**
     * Add Pilot Controller constructor.
     * 
     * @param view the add pilot view
     * @param usrname the staff member username
     */
    public AddPilotControllerImpl(final AddPilotView view, final String usrname) {
        this.addPilotView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;
        this.message = "";

        this.addPilotView.addConfirmListener(new ConfirmListener());
        this.addPilotView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                addPilot();
            } catch (ParseException e1) {
                addPilotView.displayErrorMessage("Il formato della data non è corretto!");
            }
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addPilotView.close();
            new PilotControllerImpl(new PilotViewImpl(), username);
        }

    }

    @Override
    public void addPilot() throws ParseException {
        if (this.checkMissingInfo()) {
            this.addPilotView.displayErrorMessage("Inserire tutte le informazioni!");

        } else if (this.addPilotView.getFiscalCode().length() != 16) {
            this.addPilotView.displayErrorMessage("Il codice fiscale deve contenere 16 caratteri!");

        } else if (this.checkInfo()) {
            this.addPilotView.displayErrorMessage(this.message);
            this.message = "";

        } else {
            final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

            final Pilot p = new PilotImpl(this.addPilotView.getName(), this.addPilotView.getSurname(),
                    this.addPilotView.getPilotGender(), format.parse(this.addPilotView.getPilotBirthDate()),
                    this.addPilotView.getFiscalCode(), this.addPilotView.getTelephoneNumber(),
                    this.addPilotView.getEmail(), this.addPilotView.getPilotLicenseId());

            this.airport.addPilot(p);
            this.addPilotView.displayErrorMessage("Pilota " + p.getPilotId() + " inserito");
            try {
                UtilitiesImpl.getInstance().savePilots();
                UtilitiesImpl.getInstance().saveId();
            } catch (IOException e) {
                this.addPilotView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.addPilotView.close();
            new PilotControllerImpl(new PilotViewImpl(), username);
        }
    }

    private boolean checkMissingInfo() {
        return this.addPilotView.getName().isEmpty()
            || this.addPilotView.getSurname().isEmpty()
            || this.addPilotView.getFiscalCode().isEmpty()
            || this.addPilotView.getTelephoneNumber().isEmpty()
            || this.addPilotView.getEmail().isEmpty()
            || this.addPilotView.getPilotGender().isEmpty()
            || this.addPilotView.getPilotLicenseId().isEmpty()
            || this.addPilotView.getPilotBirthDate().isEmpty();
    }

    private boolean checkInfo() {
        for (final Pilot p : this.airport.getPilots()) {
            if (p.getFiscalCode().equals(this.addPilotView.getFiscalCode())) {
                this.message += "[codice fiscale] ";
            }
            if (p.getTelephoneNumber().equals(this.addPilotView.getTelephoneNumber())) {
                this.message += "[cellulare] ";
            }
            if (p.getEmail().equals(this.addPilotView.getEmail())) {
                this.message += "[e-mail] ";
            }
            if (p.getFlightLicenseId().equals(this.addPilotView.getPilotLicenseId())) {
                this.message += "[id licenza] ";
            }
        }
        this.message += "già presente/i!";
        return this.message.equals("già presente/i!") ? false : true;
    }

}
