package controller;

import java.util.List;
import java.util.Map;

import exceptions.NewFilmException;
import exceptions.PasswordException;
import model.Discount;
import model.Film;
import model.Room;
import utilities.CreateCinema;
import utilities.Genre;

/**
 * It is the implementation of Controller interface.
 */
public class ControllerImpl implements Controller {

    private static final int IS_OVER14 = -2;
    private final BookingController bookingC;
    private final DiscountController discountC;
    private ScreeningController screeningC;
    private final RoomController roomC;
    private final SetUser ownerC;
    private final SetUser staffC;

    private int nTickets;
    private Discount discount;

    /**
     * This is the constructor of ControllerImpl.
     */
    public ControllerImpl() {
        bookingC = new BookingControllerImpl();
        discountC = new DiscountControllerImpl();
        ownerC = new SetOwner();
        staffC = new SetStaff();
        roomC = new RoomController();
    }

    @Override
    public BookingController getBookingC() {
        return bookingC;
    }

    // They call methods of BookingController.

    @Override
    public int searchRoom(final String filmTitle, final int nTickets, final int nUnder14)
            throws IllegalArgumentException {
        try {
            bookingC.searchRoom(filmTitle);
            if (screeningC == null) {
                addRoomList();
                screeningC = new ScreeningControllerImpl();
            }
            if (nUnder14 > 0 && CreateCinema.getCinema()
                    .isOver14(CreateCinema.getCinema().getFilmScreened(bookingC.getBookedRoom()))) {
                return IS_OVER14;
            }
            if (nTickets < screeningC.emptySeats(CreateCinema.getCinema().getLayoutRoom(bookingC.getBookedRoom()))) {
                final int[] array = addRoomList().stream().filter(r -> r.equals(bookingC.getBookedRoom()))
                        .mapToInt(r -> CreateCinema.getCinema().getRoomList().indexOf(r)).toArray();
                new SaveAndReadBalance().save();
                return array[0];
            }
            return -1;
        } catch (final IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double booking(final boolean isOnline) {
        return bookingC.setBooking(isOnline, nTickets, discount);
    }

    // They call methods of CinemaBalance.

    @Override
    public double totBalance() {
        return CinemaBalance.getTotBalance();
    }

    @Override
    public double cinemaBoxOffice() {
        return CinemaBalance.getCinemaBoxOffice();
    }

    @Override
    public double cinemaExpense() {
        return CinemaBalance.getCinemaExpense();
    }

    // It calls methods of DiscountController.

    @Override
    public boolean checkDiscount(final int nTickets, final int nUnder14, final int nStudents) {
        this.nTickets = nTickets;
        try {
            discount = discountC.checkDiscount(nTickets, nUnder14, nStudents);
            return true;
        } catch (final IllegalArgumentException e) {
            return false;
        }
    }

    // They call methods of FilmController.

    @Override
    public List<Film> addFilmList() {
        return FilmController.addFilmList();
    }

    @Override
    public void buyNewFilm(final int roomNumber, final String name, final int length, final Genre genre,
            final boolean over14, final boolean threeDim) throws NewFilmException {
        FilmController.buyNewFilm(roomNumber, name, length, genre, over14, threeDim);
        new SaveAndReadBalance().save();
    }

    @Override
    public List<String> getTitlesList() {
        return FilmController.returnTitles();
    }

    // They use methods of RoomController

    @Override
    public List<Room> addRoomList() {
        final List<Room> roomList = roomC.addRoomList();
        screeningC = new ScreeningControllerImpl();
        return roomList;
    }

    @Override
    public List<String> getScreenedTitlesList() {
        addFilmList();
        return roomC.returnFilmsScreened();
    }

    // They use methods of ScreeningController.

    @Override
    public List<Integer> getScreeningList() {
        if (screeningC == null) {
            addRoomList();
            screeningC = new ScreeningControllerImpl();
        }
        return screeningC.getScreeningList();
    }

    @Override
    public void setSeatsPosition(final Map<Character, List<Boolean>> map, final int screening, final int room) {
        screeningC.setSeatsPosition(map, screening, room);
    }

    @Override
    public List<Map<Character, List<Boolean>>> getScreeningListForRoom(final int pos) {
        return screeningC.getScreeningListForRoom(pos);
    }

    // They use methods of SetUserImpl.

    @Override
    public boolean checkOwnerPassword() {
        return ownerC.checkPassword();
    }

    @Override
    public boolean checkStaffPassword() {
        return staffC.checkPassword();
    }

    @Override
    public void insertPasswordForOwner(final String password) throws PasswordException {
        ownerC.insertPassword(password);
    }

    @Override
    public void insertPasswordForStaff(final String password) throws PasswordException {
        staffC.insertPassword(password);
    }

    // It is a method used to reset files with informations.

    @Override
    public void reset() {
        ownerC.reset();
        staffC.reset();
        new SaveAndReadFilm().reset();
        new SaveAndReadBalance().reset();
        new SaveAndReadRoom().reset();
    }
}
