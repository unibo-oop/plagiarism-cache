package view;

/**
 * This is the View interface.
 */
public interface View {

    /**
     * This method initialize a new instance of class CinemaBalance.
     */
    void cinemaBalance();

    /**
     * This method initialize a new instance of class OwnerMenu.
     */
    void ownerMenu();

    /**
     * This method initialize a new instance of class RoomManagement.
     */
    void roomManagement();

    /**
     * This method initialize a new instance of class SelectFilm.
     *
     * @param nTickets
     *            the parameter to know the number of booked tickets.
     * @param nUnder14
     *            the parameter to know the number of Under14 booked tickets.
     */
    void selectFilm(int nTickets, int nUnder14);

    /**
     * This method initialize a new instance of class SelectSeatsInRoom.
     *
     * @param selectedRoom
     *            the parameter to know the room in which the film are projected.
     * @param nTickets
     *            the parameter to know the number of booked tickets.
     */
    void selectSeatsInRoom(int selectedRoom, int nTickets);

    /**
     * This method initialize a new instance of class SetDiscount.
     *
     * @param online
     *            the parameter to know if the booking is online or not.
     */
    void setDiscount(boolean online);

    /**
     * This method initialize a new instance of class SetPasswordImpl to set the owner's password.
     */
    void setOwnerPassword();

    /**
     * This method initialize a new instance of class SetPasswordImpl to set the staff's password.
     */
    void setStaffPassword();

    /**
     * This method initialize a new instance of class StartingView.
     */
    void startingView();

    /**
     * This method initialize a new instance of class VerifyPasswordImpl to verify the owner's password.
     * 
     * @param startingView
     *            the starting view to set invisible after the insert of the correct password.
     */
    void verifyOwnerPassword(StartingView startingView);

    /**
     * This method initialize a new instance of class VerifyPasswordImpl to verify the staff's password.
     * 
     * @param startingView
     *            the starting view to set invisible after the insert of the correct password.
     */
    void verifyStaffPassword(StartingView startingView);
}
