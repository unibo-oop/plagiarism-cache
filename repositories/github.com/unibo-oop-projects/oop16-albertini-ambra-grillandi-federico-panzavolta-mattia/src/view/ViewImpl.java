package view;

import controller.Controller;
import controller.ControllerImpl;

/**
 * This is an implementation of the interface View.
 */
public class ViewImpl implements View {

    private static final Controller CONTROLLER = new ControllerImpl();
    private static final View VIEW = new ViewImpl();

    @Override
    public void cinemaBalance() {
        new CinemaBalance();
    }

    /**
     * @return the controller to use in every view class.
     */
    public static Controller getController() {
        return CONTROLLER;
    }

    /**
     * @return the view to use in every view class.
     */
    public static View getView() {
        return VIEW;
    }

    @Override
    public void ownerMenu() {
        new OwnerMenu();
    }

    @Override
    public void roomManagement() {
        new RoomManagement();
    }

    @Override
    public void selectFilm(final int nTickets, final int nUnder14) {
        new SelectFilm(nTickets, nUnder14);
    }

    @Override
    public void selectSeatsInRoom(final int selectedRoom, final int nTickets) {
        new SelectSeatsInRoom(selectedRoom, nTickets);
    }

    @Override
    public void setDiscount(final boolean online) {
        new SetDiscounts(online);
    }

    @Override
    public void setOwnerPassword() {
        new SetPasswordImpl().listenerForOwner();
    }

    @Override
    public void setStaffPassword() {
        new SetPasswordImpl().listenerForStaff();
    }

    @Override
    public void startingView() {
        new StartingView();
    }

    @Override
    public void verifyOwnerPassword(final StartingView startingView) {
        new VerifyPasswordImpl().listenerForOwner(startingView);
    }

    @Override
    public void verifyStaffPassword(final StartingView startingView) {
        new VerifyPasswordImpl().listenerForStaff(startingView);
    }
}
