package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.interfaces.StaffController;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Staff;
import view.implementations.AddStaffViewImpl;
import view.implementations.LoginViewImpl;
import view.implementations.MainMenuViewImpl;
import view.interfaces.AddRemoveView;

/**
 * Staff Controller implementation.
 */
public class StaffControllerImpl implements StaffController {

    private final AddRemoveView staffView;
    private final Airport airport;
    private final String username;

    /**
     * Staff Controller constructor.
     * 
     * @param view the staff view
     * @param usrname the staff member username
     */
    public StaffControllerImpl(final AddRemoveView view, final String usrname) {
        this.staffView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.showStaff();
        this.staffView.addButtonListener(new InsertStaffListener());
        this.staffView.removeButtonListener(new RemoveStaffListener());
        this.staffView.backButtonListener(new BackListener());
    }

    private class InsertStaffListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            staffView.close();
            new AddStaffControllerImpl(new AddStaffViewImpl(), username);
        }

    }

    private class RemoveStaffListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            removeStaff();
        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            staffView.close();
            new MainControllerImpl(new MainMenuViewImpl(), username);
        }

    }

    @Override
    public void removeStaff() {
        if (this.airport.getStaff().size() < 2) {
            this.staffView.displayErrorMessage("Rimozione impossibile, sei l'unico membro dello staff!");
        } else {
            final Staff s = this.airport.searchStaff(this.username).get();
            this.airport.removeStaff(s);
            this.staffView.displayErrorMessage("Il tuo account è stato eliminato.");

            try {
                UtilitiesImpl.getInstance().saveStaff();
            } catch (IOException e1) {
                this.staffView.displayErrorMessage("Errore durante il salvataggio dei dati!");
            }

            this.staffView.close();
            try {
                new LoginControllerImpl(new LoginViewImpl());
            } catch (ClassNotFoundException | IOException e) {
                this.staffView.displayErrorMessage("Errore!");
            }
        }
    }

    private void showStaff() {
        this.airport.getStaff().forEach(s -> this.staffView.addItemsToList(s));
    }

}
