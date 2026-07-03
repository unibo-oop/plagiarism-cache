package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import controller.interfaces.AddFlightAttendantController;
import model.implementations.AirportImpl;
import model.implementations.FlightAttendantImpl;
import model.interfaces.Airport;
import model.interfaces.FlightAttendant;
import view.implementations.FlightAttendantViewImpl;
import view.interfaces.AddFlightAttendantView;

/**
 * Add Flight Attendant Controller implementation.
 */
public class AddFlightAttendantControllerImpl implements AddFlightAttendantController {

    private final AddFlightAttendantView addFAView;
    private final Airport airport;
    private final String username;
    private String message;

    /**
     * Add Flight Attendant Controller constructor.
     * 
     * @param view the add flight attendant view
     * @param usrname the staff member username
     */
    public AddFlightAttendantControllerImpl(final AddFlightAttendantView view, final String usrname) {
        this.addFAView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;
        this.message = "";

        this.addFAView.addConfirmListener(new ConfirmListener());
        this.addFAView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                addFlightAttendant();
            } catch (ParseException e1) {
                addFAView.displayErrorMessage("Il formato della data non è corretto!");
            }
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addFAView.close();
            new FlightAttendantControllerImpl(new FlightAttendantViewImpl(), username);
        }

    }

    @Override
    public void addFlightAttendant() throws ParseException {
        if (this.checkMissingInfo()) {
            this.addFAView.displayErrorMessage("Inserire tutte le informazioni!");

        } else if (this.addFAView.getFiscalCode().length() != 16) {
            this.addFAView.displayErrorMessage("Il codice fiscale deve contenere 16 caratteri!");

        } else if (this.checkInfo()) {
            this.addFAView.displayErrorMessage(this.message);
            this.message = "";

        } else {
            final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

            final FlightAttendant fa = new FlightAttendantImpl(this.addFAView.getName(), this.addFAView.getSurname(),
                    this.addFAView.getAttendantGender(), format.parse(this.addFAView.getAttendantBirthDate()),
                    this.addFAView.getFiscalCode(), this.addFAView.getTelephoneNumber(),
                    this.addFAView.getEmail());

            this.airport.addFlightAttendant(fa);
            this.addFAView.displayErrorMessage("Assistente " + fa.getFlightAttendantId() + " inserito");
            try {
                UtilitiesImpl.getInstance().saveFlightAttendants();
                UtilitiesImpl.getInstance().saveId();
            } catch (IOException e) {
                this.addFAView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }
            this.addFAView.close();
            new FlightAttendantControllerImpl(new FlightAttendantViewImpl(), username);
        }
    }

    private boolean checkMissingInfo() {
        return this.addFAView.getName().isEmpty()
            || this.addFAView.getSurname().isEmpty()
            || this.addFAView.getFiscalCode().isEmpty()
            || this.addFAView.getTelephoneNumber().isEmpty()
            || this.addFAView.getEmail().isEmpty()
            || this.addFAView.getAttendantGender().isEmpty()
            || this.addFAView.getAttendantBirthDate().isEmpty();
    }

    private boolean checkInfo() {
        for (final FlightAttendant fa : this.airport.getFlightAttendants()) {
            if (fa.getFiscalCode().equals(this.addFAView.getFiscalCode())) {
                this.message += "[codice fiscale] ";
            }
            if (fa.getTelephoneNumber().equals(this.addFAView.getTelephoneNumber())) {
                this.message += "[telefono] ";
            }
            if (fa.getEmail().equals(this.addFAView.getEmail())) {
                this.message += "[e-mail] ";
            }
        }
        this.message += "già presente/i!";
        return this.message.equals("già presente/i!") ? false : true;
    }

}
