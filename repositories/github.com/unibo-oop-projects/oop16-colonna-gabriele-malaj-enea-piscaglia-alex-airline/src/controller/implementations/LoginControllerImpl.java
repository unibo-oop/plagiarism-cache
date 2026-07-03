package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Optional;

import controller.interfaces.LoginController;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Staff;
import view.implementations.MainMenuViewImpl;
import view.interfaces.LoginView;

/**
 * Login Controller implementation.
 */
public class LoginControllerImpl implements LoginController {

    private final LoginView loginView;
    private final Airport airport;

    /**
     * Login Controller constructor.
     * 
     * @param view the login view
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public LoginControllerImpl(final LoginView view) throws ClassNotFoundException, IOException {
        this.loginView = view;
        this.airport = AirportImpl.getAirport();

        UtilitiesImpl.getInstance().loadData();
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addExitListener(new ExitListener());
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            checkUser();
        }

    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            loginView.close();
        }

    }

    @Override
    public void checkUser() {
        if (this.loginView.getUsername().isEmpty() || this.loginView.getPassword().isEmpty()) {
            this.loginView.displayErrorMessage("Inserire username e password!");
        } else {
            final Optional<Staff> staff = this.airport.searchStaff(this.loginView.getUsername());

            if (staff.isPresent() && staff.get().getPassword().equals(this.loginView.getPassword())) {
                this.loginView.displayErrorMessage("Benvenuto " + staff.get().getUsername());
                this.loginView.close();
                new MainControllerImpl(new MainMenuViewImpl(), this.loginView.getUsername());
            } else {
                this.loginView.displayErrorMessage("Username o password non corretti!");
            }
        }
    }

}
