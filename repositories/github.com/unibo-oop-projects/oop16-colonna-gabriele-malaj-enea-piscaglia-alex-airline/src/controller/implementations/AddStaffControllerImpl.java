package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import controller.interfaces.AddStaffController;
import model.implementations.AirportImpl;
import model.implementations.StaffImpl;
import model.interfaces.Airport;
import model.interfaces.Staff;
import view.implementations.StaffViewImpl;
import view.interfaces.AddStaffView;

/**
 * Add Staff Controller implementation.
 */
public class AddStaffControllerImpl implements AddStaffController {

    private final AddStaffView addStaffView;
    private final Airport airport;
    private final String username;
    private String message;

    /**
     * Add Staff Controller constructor.
     * 
     * @param view the add staff view
     * @param usrname the staff member username
     */
    public AddStaffControllerImpl(final AddStaffView view, final String usrname) {
        this.addStaffView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;
        this.message = "";

        this.addStaffView.addConfirmListener(new ConfirmListener());
        this.addStaffView.addBackListener(new BackListener());
    }

    private class ConfirmListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                addStaff();
            } catch (ParseException e1) {
                addStaffView.displayErrorMessage("Il formato della data non è corretto!");
            }
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            addStaffView.close();
            new StaffControllerImpl(new StaffViewImpl(), username);
        }

    }

    @Override
    public void addStaff() throws ParseException {
        if (this.checkMissingInfo()) {
            this.addStaffView.displayErrorMessage("Inserire tutte le informazioni!");

        } else if (this.airport.searchStaff(this.addStaffView.getUsername()).isPresent()) {
            this.addStaffView.displayErrorMessage("Username non disponibile!");

        } else if (this.addStaffView.getPassword().length() < 4) {
            this.addStaffView.displayErrorMessage("La password deve contenere almeno 4 caratteri!");

        } else if (!this.addStaffView.getPassword().equals(this.addStaffView.getConfirmPassWord())) {
            this.addStaffView.displayErrorMessage("Le password non coincidono!");

        } else if (this.addStaffView.getFiscalCode().length() != 16) {
            this.addStaffView.displayErrorMessage("Il codice fiscale deve contenere 16 caratteri!");

        } else if (this.checkInfo()) {
            this.addStaffView.displayErrorMessage(this.message);
            this.message = "";

        } else {
            final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

            final Staff s = new StaffImpl(this.addStaffView.getName(), this.addStaffView.getSurname(),
                    this.addStaffView.getGender(), format.parse(this.addStaffView.getBirthDate()),
                    this.addStaffView.getFiscalCode(), this.addStaffView.getTelephoneNumber(),
                    this.addStaffView.getEmail(), this.addStaffView.getUsername(), this.addStaffView.getPassword());

            this.airport.addStaff(s);
            this.addStaffView.displayErrorMessage(s.getUsername() + " inserito.");

            try {
                UtilitiesImpl.getInstance().saveStaff();
                UtilitiesImpl.getInstance().saveId();
            } catch (IOException e) {
                addStaffView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }

            this.addStaffView.close();
            new StaffControllerImpl(new StaffViewImpl(), username);
        }
    }

    private boolean checkMissingInfo() {
        return this.addStaffView.getName().isEmpty()
            || this.addStaffView.getSurname().isEmpty()
            || this.addStaffView.getFiscalCode().isEmpty()
            || this.addStaffView.getTelephoneNumber().isEmpty()
            || this.addStaffView.getEmail().isEmpty()
            || this.addStaffView.getUsername().isEmpty()
            || this.addStaffView.getPassword().isEmpty()
            || this.addStaffView.getGender().isEmpty()
            || this.addStaffView.getBirthDate().isEmpty();
    }

    private boolean checkInfo() {
        for (final Staff s : this.airport.getStaff()) {
            if (s.getFiscalCode().equals(this.addStaffView.getFiscalCode())) {
                this.message += "[codice fiscale] ";
            }
            if (s.getTelephoneNumber().equals(this.addStaffView.getTelephoneNumber())) {
                this.message += "[cellulare] ";
            }
            if (s.getEmail().equals(this.addStaffView.getEmail())) {
                this.message += "[e-mail] ";
            }
        }
        this.message += "già presente/i!";
        return this.message.equals("già presente/i!") ? false : true;
    }

}
