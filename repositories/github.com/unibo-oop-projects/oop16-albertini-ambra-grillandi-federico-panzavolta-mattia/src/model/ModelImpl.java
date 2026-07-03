package model;

import java.util.List;
import java.util.Map;

import utilities.Genre;

/**
 *
 * This is an implementation of {@link model.Model}.
 *
 */
public class ModelImpl implements Model {

    /**
     * @value MAX_TICKETS max number of tickets that you can book at time
     */
    public static final int MAX_TICKETS = 20;

    private final Balance balance;
    private final Cinema cinema;
    private final Owner owner;

    /**
     * Builds a new {@link ModelImpl}.
     *
     * @param nRooms
     *            true if you want to buy the tickets online
     * @param ticketCost
     *            the price of the ticket
     */
    public ModelImpl(final int nRooms, final double ticketCost) {
        cinema = new CinemaImpl(nRooms);
        balance = new BalanceImpl(ticketCost);
        owner = new OwnerImpl();
    }

    @Override
    public Film createFilm(final String name, final int length, final Genre genre, final boolean over14,
            final boolean threeDimensional) {
        return new FilmImpl(name, length, genre, over14, threeDimensional);
    }

    @Override
    public Room createRoom(final String roomName, final int seats) {
        return new RoomImpl(roomName, seats);
    }

    @Override
    public Booking createBooking(final Boolean online, final int nTickets) {
        return new BookingImpl(online, nTickets);
    }

    @Override
    public Discount createDiscount(final int nUnder14, final int nStudents) {
        return new DiscountImpl(nUnder14, nStudents);
    }

    @Override
    public Balance getBalance() {
        return balance;
    }

    @Override
    public Cinema getCinema() {
        return cinema;
    }

    @Override
    public Owner getOwner() {
        return owner;
    }

    // Balance

    @Override
    public double getExpense() {
        return balance.getExpense();
    }

    @Override
    public void setExpense(final double expense) {
        balance.setExpense(expense);
    }

    @Override
    public double getBudget() {
        return balance.getBudget();
    }

    @Override
    public void setBudget(final double budget) {
        balance.setBudget(budget);
    }

    @Override
    public double getBoxOffice() {
        return balance.getBoxOffice();
    }

    @Override
    public void setBoxOffice(final double boxOffice) {
        balance.setBoxOffice(boxOffice);
    }

    @Override
    public double getTicketCost() {
        return balance.getTicketCost();
    }

    // Booking

    @Override
    public void setRoomForBooking(final Booking booking, final Room room) {
        booking.setRoomForBooking(room);
    }

    @Override
    public Room getRoomForBooking(final Booking booking) {
        return booking.getRoomForBooking();
    }

    @Override
    public double computePrice(final Booking booking, final Discount discount, final Balance balance) {
        return booking.computePrice(discount, balance);
    }

    // Cinema

    @Override
    public List<Film> getFilmList() {
        return cinema.getFilmList();
    }

    @Override
    public List<Room> getRoomList() {
        return cinema.getRoomList();
    }

    @Override
    public void buyFilm(final Film film) {
        this.cinema.buyFilm(film, this.balance);
    }

    @Override
    public void addRoom(final Room room) {
        cinema.addRoom(room);
    }

    @Override
    public void removeFilm(final Film film) {
        cinema.removeFilm(film);
    }

    // Film

    @Override
    public String getName(final Film film) {
        return film.getName();
    }

    @Override
    public int getLength(final Film film) {
        return film.getLength();
    }

    @Override
    public Genre getGenre(final Film film) {
        return film.getGenre();
    }

    @Override
    public Boolean isOver14(final Film film) {
        return film.isOver14();
    }

    @Override
    public Boolean is3D(final Film film) {
        return film.is3D();
    }

    // Owner

    @Override
    public String getOwnerPassword() {
        return owner.getOwnerPassword();
    }

    @Override
    public void setOwnerPassword(final String ownerPassword) {
        owner.setOwnerPassword(ownerPassword);
    }

    @Override
    public void setStaffPassword(final String staffPassword) {
        owner.setStaffPassword(staffPassword);
    }

    @Override
    public String getStaffPassword() {
        return owner.getStaffPassword();
    }

    // Room

    @Override
    public int getScreening(final Room room) {
        return room.getScreening();
    }

    @Override
    public Map<Character, List<Boolean>> getLayoutRoom(final Room room) {
        return room.getLayoutRoom();
    }

    @Override
    public void setLayoutRoom(final Map<Character, List<Boolean>> newMapLayout, final Room room) {
        room.setLayoutRoom(newMapLayout);
    }

    @Override
    public String getRoomName(final Room room) {
        return room.getRoomName();
    }

    @Override
    public int getSeats(final Room room) {
        return room.getSeats();
    }

    @Override
    public Film getFilmScreened(final Room room) {
        return room.getFilmScreened();
    }

    @Override
    public void setFilm(final Film film, final Room room) {
        room.setFilm(film);
    }

    @Override
    public void changeListMapScreening(final Room room, final List<Map<Character, List<Boolean>>> maps) {
        room.changeListMapScreening(maps);
    }

    @Override
    public List<Map<Character, List<Boolean>>> getListMapScreening(final Room room) {
        return room.getListMapScreening();
    }

    @Override
    public void setListMapScreening(final Room room) {
        room.setListMapScreening();
    }
}
