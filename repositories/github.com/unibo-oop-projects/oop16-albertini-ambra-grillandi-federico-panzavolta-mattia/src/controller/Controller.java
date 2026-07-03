package controller;

import java.util.List;
import java.util.Map;

import exceptions.NewFilmException;
import exceptions.PasswordException;
import model.Film;
import model.Room;
import utilities.Genre;

/**
 * This is the Controller interface.
 *
 */
public interface Controller {

    /**
     * It is the getter for the field bookingC.
     *
     * @return the value of the field bookingC.
     */
    BookingController getBookingC();

    // These methods use methods of BookingController.
    /**
     * It searches the room using searchRoom() from BookingController.
     *
     * @param filmTitle
     *            the title of the movie that he wants to book.
     * @param nTickets
     *            the number of tickets booked.
     * @param nUnder14
     *            the number of tickets Under14 booked
     * @throws IllegalArgumentException
     *             if the movie isn't screened in a room.
     * @return the index of required room.
     */
    int searchRoom(String filmTitle, int nTickets, int nUnder14) throws IllegalArgumentException;

    /**
     * It sets the booking calling setBooking() from BookingController.
     *
     * @param isOnline
     *            is true if the booking is done by the client, false if it is done by the owner.
     * @return the price of the booking.
     */
    double booking(boolean isOnline);

    // These methods use methods of CinemaBalance.
    /**
     * It returns the total balance of the cinema using getTotBalance() of CinemaBalance.
     *
     * @return the total balance of the cinema.
     */
    double totBalance();

    /**
     * It returns the box office of the cinema using getCinemaBoxOffice() of CinemaBalance.
     *
     * @return the box office of the cinema.
     */
    double cinemaBoxOffice();

    /**
     * It returns the expense of the cinema using getCinemaExpense() of CinemaBalance.
     *
     * @return the expense of the cinema.
     */
    double cinemaExpense();

    // This one uses methods of DiscountController.
    /**
     * It checks the discount on the booking using checkDiscount() of DiscountController.
     *
     * @param nTickets
     *            the number of the tickets that he/she wants to book.
     * @param nUnder14
     *            the number of the people who want to go to the cinema that are under 14.
     * @param nStudents
     *            the number of students who want to go to the cinema.
     * @return false if the number of tickets are more than the max of tickets that can be booked, if the number of
     *         people under 14 are more than the number of tickets, if the students are more than the ticket that they
     *         are booking.
     */
    boolean checkDiscount(int nTickets, int nUnder14, int nStudents);

    // These methods use methods of FilmController.
    /**
     * It uses addFilmList() of FilmController.
     *
     * @return the list of the possible movies.
     */
    List<Film> addFilmList();

    /**
     * It uses buyNewFilm() of FilmController.
     *
     * @param roomNumber
     *            the position of the room in the roomList.
     * @param name
     *            of the movie.
     * @param length
     *            of the movie.
     * @param genre
     *            of the movie.
     * @param over14
     *            if this movie can't be seen from people that are less than 14.
     * @param threeDim
     *            if this movie can be screening in 3D.
     * @throws NewFilmException
     *             if the film the owner wants to buy is already in the movie list.
     */
    void buyNewFilm(int roomNumber, String name, int length, Genre genre, boolean over14, boolean threeDim)
            throws NewFilmException;

    /**
     * It uses returnTitles() of FilmController.
     *
     * @return the list of titles of all the movies bought by the cinema.
     */
    List<String> getTitlesList();

    // These methods use methods of RoomController.
    /**
     * It uses addRoomList() of RoomController.
     *
     * @return the list of room in the cinema.
     */
    List<Room> addRoomList();

    /**
     * It uses returnFilmsScreened() of RoomController.
     *
     * @return the list of titles of the movies screened in rooms.
     */
    List<String> getScreenedTitlesList();

    // These methods use methods of ScreeningController.
    /**
     * It uses getScreeningList() of ScreeningController.
     *
     * @return the list of screenings.
     */
    List<Integer> getScreeningList();

    /**
     * It uses setSeatsPosition() of ScreeningController.
     *
     * @param map
     *            the map of the position of seats in the room in which we want to book seats.
     * @param screening
     *            the number of the screening in which we want to see the movie.
     * @param room
     *            the position of the room in the room list.
     */
    void setSeatsPosition(Map<Character, List<Boolean>> map, int screening, int room);

    /**
     * It uses getScreeningListForRoom() of ScreeningController.
     *
     * @param pos
     *            the room in which they want to book seats.
     * @return the list of screenings for that room.
     */
    List<Map<Character, List<Boolean>>> getScreeningListForRoom(int pos);

    // These methods use methods of SetUserImpl.

    /**
     * It checks owner's password using checkPassword() of setUserImpl.
     *
     * @return true if the owner has a password, false it is null.
     */
    boolean checkOwnerPassword();

    /**
     * It checks staff's password using checkPassword() of setUserImpl.
     *
     * @return true if the staff has a password, false it is null.
     */
    boolean checkStaffPassword();

    /**
     * It sets the owner's password using insertPassword() of SetOwner.
     *
     * @param password
     *            the password of the owner.
     * @throws PasswordException
     *             if the password is not correct.
     */
    void insertPasswordForOwner(String password) throws PasswordException;

    /**
     * It sets the staff's password using insertPassword() of SetStaff.
     *
     * @param password
     *            the staff's password.
     * @throws PasswordException
     *             if the password is not correct.
     */
    void insertPasswordForStaff(String password) throws PasswordException;

    /**
     * It resets the passwords of owner and staff using resetPassword() of SetUser and film in the film list using
     * resetFilm() in FilmController.
     */
    void reset();
}
