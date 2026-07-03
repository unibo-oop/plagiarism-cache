package controller.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import controller.interfaces.MonthlyIncomeController;
import model.enumerations.Status;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Flight;
import view.implementations.BookingsViewImpl;
import view.implementations.DestinationViewImpl;
import view.implementations.FlightAttendantViewImpl;
import view.implementations.FlightsViewImpl;
import view.implementations.LoginViewImpl;
import view.implementations.MonthlyIncomeViewImpl;
import view.implementations.PilotViewImpl;
import view.implementations.PlaneViewImpl;
import view.implementations.StaffViewImpl;
import view.interfaces.MainMenuView;

/**
 * Main Controller implementation.
 */
public class MainControllerImpl {

    private final MainMenuView mainView;
    private final Airport airport;
    private final String username;

    /**
     * Main Controller constructor.
     * 
     * @param view the main menu view
     * @param usrname the staff member username
     */
    public MainControllerImpl(final MainMenuView view, final String usrname) {
        this.mainView = view;
        this.airport = AirportImpl.getAirport();
        this.username = usrname;

        this.updateFlights();
        this.mainView.addPlaneListener(new PlaneListener());
        this.mainView.addDestinationListener(new DestinationListener());
        this.mainView.addPilotListener(new PilotListener());
        this.mainView.addFlightListener(new FlightListener());
        this.mainView.addBookingListener(new BookingListener());
        this.mainView.addStaffListener(new StaffListener());
        this.mainView.addFlightAttendantListener(new FlightAttendantListener());
        this.mainView.addMonthlyIncomeListener(new GraphicListener());
        this.mainView.addLogoutListener(new LogoutListener());
    }

    private class PlaneListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new PlaneControllerImpl(new PlaneViewImpl(), username);
        }

    }

    private class DestinationListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new DestinationControllerImpl(new DestinationViewImpl(), username);
        }

    }

    private class PilotListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new PilotControllerImpl(new PilotViewImpl(), username);
        }

    }

    private class FlightListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new FlightControllerImpl(new FlightsViewImpl(), username);
        }

    }

    private class BookingListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new BookingControllerImpl(new BookingsViewImpl(), username);
        }

    }

    private class StaffListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new StaffControllerImpl(new StaffViewImpl(), username);
        }

    }

    private class FlightAttendantListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            new FlightAttendantControllerImpl(new FlightAttendantViewImpl(), username);
        }

    }

    private class GraphicListener implements ActionListener {

        private final MonthlyIncomeController miController = new MonthlyIncomeControllerImpl();

        @Override
        public void actionPerformed(final ActionEvent e) {
            new MonthlyIncomeViewImpl(this.miController.createMap());
        }

    }

    private class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            mainView.close();
            try {
                new LoginControllerImpl(new LoginViewImpl());
            } catch (ClassNotFoundException | IOException e1) {
                mainView.displayErrorMessage("Error!");
            }
        }

    }

    private void updateFlights() {
        final Date today = new Date();
        final Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 3);

        for (final Flight f : this.airport.getFlights().keySet()) {
            if (f.getPlane().getStatus() == Status.BOOKED && today.after(f.getDepartureDate())) {
                f.getPlane().setStatus(Status.TAKEN_OFF);
            }
            if (f.getPlane().getStatus() == Status.TAKEN_OFF && today.after(f.getArrivalDate())) {
                f.getPlane().setStatus(Status.LANDED);
            }
            if (f.getPlane().getStatus() == Status.LANDED && today.after(cal.getTime())) {
                f.getPlane().setStatus(Status.AT_AIRPORT);
                f.getPlane().resetSeats();
            }
        }
    }

}
