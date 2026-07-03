package model;

import java.util.List;
import java.util.Map;

import utilities.Genre;

/**
 * This interface represents the model.
 *
 */
public interface Model {

    /**
     * Create a new Film object.
     *
     * @param name
     *            the film name
     * @param length
     *            the film length
     * @param genre
     *            the film genre
     * @param over14
     *            the film is rated 14
     * @param threeDimensional
     *            the film is 3D
     * @return a new Film object
     */
    Film createFilm(String name, int length, Genre genre, boolean over14, boolean threeDimensional);

    /**
     * Create a new Room object.
     *
     * @param roomName
     *            the name of the room
     * @param seats
     *            the number of seats in this room
     * @return a new Room object
     *
     */
    Room createRoom(String roomName, int seats);

    /**
     * Create a new Booking object.
     *
     * @param online
     *            true if you want to buy the tickets online
     * @param nTickets
     *            the number of tickets to buy
     * @return a new Booking object
     */
    Booking createBooking(Boolean online, int nTickets);

    /**
     * Create a new Discount object.
     *
     * @param nUnder14
     *            the number of people with an age under 14 years
     * @param nStudents
     *            the number of people who are students
     * @return a new Discount object
     */
    Discount createDiscount(int nUnder14, int nStudents);

    /**
     * Return this Balance object.
     *
     * @return this Balance object
     */
    Balance getBalance();

    /**
     * Return this Cinema object.
     *
     * @return this Cinema object
     */
    Cinema getCinema();

    /**
     * Return this Owner object.
     *
     * @return this Owner object
     */
    Owner getOwner();

    // Methods in Balance

    /**
     * Return the cost to buy a film.
     *
     * @return the cost to buy a film
     */
    double getExpense();

    /**
     * Set the expense to buy a new film.
     *
     * @param expense
     *            the price to buy the new film
     */
    void setExpense(double expense);

    /**
     * Return the budget of the multiplex.
     *
     * @return the budget of the multiplex
     */
    double getBudget();

    /**
     * Set the budget of the multiplex.
     *
     * @param budget
     *            the money owned by the multiplex
     */
    void setBudget(double budget);

    /**
     * Return the total box office.
     *
     * @return the money earned with the sell of the tickets
     */
    double getBoxOffice();

    /**
     * Set the box office of the tickets sold.
     *
     * @param boxOffice
     *            the money gained after the selling of the tickets
     */
    void setBoxOffice(double boxOffice);

    /**
     * Return the cost of the ticket.
     *
     * @return the cost of the ticket
     */
    double getTicketCost();

    // Methods in Booking

    /**
     * Set the room where to watch the movie.
     *
     * @param booking
     *            the interested booking
     * @param room
     *            the room where to watch the movie
     */
    void setRoomForBooking(Booking booking, Room room);

    /**
     * Return the room where you watch the movie.
     *
     * @param booking
     *            the interested booking
     * @return the room where you watch the movie
     */
    Room getRoomForBooking(Booking booking);

    /**
     * Return the price of the tickets.
     *
     * @param booking
     *            the Booking object interested
     * @param discount
     *            the number of students and people under 14 years to apply the discount
     * @param balance
     *            used to calculate the money earned
     * @return the total price of the tickets bought with discount applied
     */
    double computePrice(Booking booking, Discount discount, Balance balance);

    // Methods in Cinema

    /**
     * Return a list of film.
     *
     * @return the list of film bought by the director.
     */
    List<Film> getFilmList();

    /**
     * Return a list of rooms.
     *
     * @return the list of rooms inside the cinema.
     */
    List<Room> getRoomList();

    /**
     * @param film
     *            the film to buy.
     *
     *            add a film in the list.
     */
    void buyFilm(Film film);

    /**
     * add a room in the multiplex.
     *
     * @param room
     *            the room to add to the list
     */
    void addRoom(Room room);

    /**
     *
     * remove a film from the list.
     *
     * @param film
     *            the film to remove.
     */
    void removeFilm(Film film);

    // Methods in Film

    /**
     * Return the film's name.
     *
     * @param film
     *            the film whom you want to know the name
     * @return the name of this film
     */
    String getName(Film film);

    /**
     * Return the film's length.
     *
     * @param film
     *            the film whom you want to know the length
     * @return the length of this film
     */
    int getLength(Film film);

    /**
     * Return the film's genre.
     *
     * @param film
     *            the film whom you want to know the genre
     * @return the genre of this film
     */
    Genre getGenre(Film film);

    /**
     * Return true if the film is 14 rated.
     *
     * @param film
     *            the film whom you want to know if is over 14
     * @return true if the film is for people with an age over 14
     */
    Boolean isOver14(Film film);

    /**
     * Return true if the film is in 3D.
     *
     * @param film
     *            the film whom you want to know if is 3D
     * @return true if the film can be seen in 3D
     */
    Boolean is3D(Film film);

    // Methods in Owner

    /**
     * Return the owner's password.
     *
     * @return the password used by the owner
     */
    String getOwnerPassword();

    /**
     * Set the owner's password.
     *
     * @param ownerPassword
     *            the password of the multiplex's owner
     */
    void setOwnerPassword(String ownerPassword);

    /**
     * Set the staff's password.
     *
     * @param staffPassword
     *            the password of the multiplex's staff
     */
    void setStaffPassword(String staffPassword);

    /**
     * Return the staff's password.
     *
     * @return the password used by the staff
     */
    String getStaffPassword();

    // Methods in Room

    /**
     * Return the room layout.
     *
     * @param room
     *            the room whom you want to know the layout
     * @return the disposition of the seats in this room
     */
    Map<Character, List<Boolean>> getLayoutRoom(Room room);

    /**
     * Update the layout of the room with the seats that are booked.
     *
     * @param newMapLayout
     *            the layout of the room
     * @param room
     *            the room to update the layout
     */
    void setLayoutRoom(Map<Character, List<Boolean>> newMapLayout, Room room);

    /**
     * Return the name of the room.
     *
     * @param room
     *            the room whom you want to know the name
     * @return the name of this room
     */
    String getRoomName(Room room);

    /**
     * Return the number of seats.
     *
     * @param room
     *            the room whom you want to know the number of seats
     * @return the number of seats in this room
     */
    int getSeats(Room room);

    /**
     * Return the film screened in this room.
     *
     * @param room
     *            the room whom you want to know the film screened
     * @return the film screened in this room
     */
    Film getFilmScreened(Room room);

    /**
     * Set the film in the room.
     *
     * @param film
     *            the film that you can watch in this room
     * @param room
     *            the room where to set the film
     */
    void setFilm(Film film, Room room);

    /**
     * Return the number of film screened.
     *
     * @param room
     *            the room you want to know the number of time a film is screened
     * @return the number of film screened inside this room
     */
    int getScreening(Room room);

    /**
     * Change the list of maps.
     *
     * @param room
     *         the room with the list of maps to change
     * @param maps
     *         the new list of maps
     */
    void changeListMapScreening(Room room, List<Map<Character, List<Boolean>>> maps);

    /**
     * Return the list of maps.
     *
     * @param room
     *        the room that return the list of maps
     * @return the list of maps
     */
    List<Map<Character, List<Boolean>>> getListMapScreening(Room room);

    /**
     * Set the list of maps for the screenings.
     * 
     * @param room
     *       the room where to set the list of maps
     */
    void setListMapScreening(Room room);
}
