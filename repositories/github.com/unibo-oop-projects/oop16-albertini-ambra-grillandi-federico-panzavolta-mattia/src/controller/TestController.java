package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import exceptions.NewFilmException;
import exceptions.PasswordException;
import model.Film;
import model.Model;
import model.Room;
import utilities.CreateCinema;
import utilities.Genre;

/**
 * It is the class used to test the controller.
 *
 */

public class TestController {

    private static final int LENGTH = 120;
    private static final int MAX_TICKETS = 20;
    private static final Double EXPECTED_PRICE = 6.0;
    private static final Double EXPECTED_PRICE_ONLINE = 7.2;
    private static final Double E_P_1_STUDENT = 5.4;
    private static final Double E_P_1_STUDENT_ONLINE = 6.48;
    private static final Double E_P_U14 = 4.8;
    private static final Double E_P_U14_ONLINE = 5.76;
    private static final String TITLE_TEST_1 = "Name";
    private static final String TITLE_TEST_2 = "Film";
    private static Model testCinema = CreateCinema.getCinema();

    private final Controller cont = new ControllerImpl();

    /**
     * Test of FilmController and RoomController.
     */
    @Test
    public void testFilmController() {
        List<Film> filmList;
        List<Room> roomList;
        List<String> titleList;
        final String errorStr = "Error: it does not have to throw an exception";
        String title = "Title";
        int length = LENGTH;
        Genre genre = Genre.ACTION;
        int room = 0;
        int filmListSize = 0;
        CreateCinema.setCinema();
        // Testing addFilmList() and addRoomList().
        filmList = cont.addFilmList();
        assertEquals(filmList, testCinema.getFilmList());
        assertTrue(filmList.isEmpty());
        roomList = cont.addRoomList();
        assertEquals(roomList, testCinema.getRoomList());
        assertEquals(roomList.size(), testCinema.getRoomList().size());
        // -- TEST 1 --
        // Testing buyNewFilm() and setFilmInRoom().
        try {
            cont.buyNewFilm(room, title, length, genre, false, false);
            assertEquals(testCinema.getFilmList().size(), 1);
            assertEquals(testCinema.getFilmList().get(room), filmList.get(room));
            assertEquals(testCinema.getFilmScreened(testCinema.getRoomList().get(room)).getName(), title);
            assertEquals(testCinema.getFilmScreened(testCinema.getRoomList().get(room)).getLength(), length);
            assertEquals(testCinema.getFilmScreened(testCinema.getRoomList().get(room)).getGenre(), genre);
            assertFalse(testCinema.getFilmScreened(testCinema.getRoomList().get(room)).isOver14());
            assertFalse(testCinema.getFilmScreened(testCinema.getRoomList().get(room)).is3D());

        } catch (final NewFilmException e) {
            fail(errorStr);
        }
        // Testing exception.
        try {
            cont.buyNewFilm(2, title, length, genre, false, false);
            fail("This film already exists in the film list");
        } catch (final NewFilmException e) {
            assertEquals(testCinema.getFilmList().size(), 1);
        }
        // Testing addFilmList() and addRoomList().
        filmList = cont.addFilmList();
        assertEquals(filmList, testCinema.getFilmList());
        roomList = cont.addRoomList();
        assertEquals(roomList, testCinema.getRoomList());
        // Testing getTitlesList().
        filmListSize = testCinema.getFilmList().size();
        titleList = cont.getTitlesList();
        assertEquals(titleList.size(), filmListSize);
        assertEquals(titleList.get(filmListSize - 1), title);

        // -- TEST 2 --
        title = TITLE_TEST_1;
        length -= 10;
        genre = Genre.FANTASY;
        room = 2;
        // Testing exception for the room with buyNewFilm().
        try {
            cont.buyNewFilm(0, "XXX", length, genre, false, true);
            fail("This film can't be setted in this room.");
        } catch (final Exception e) {
            assertEquals(testCinema.getFilmList().size(), 2);
            assertEquals(testCinema.getFilmList(), filmList);
            assertFalse(testCinema.getName(testCinema.getFilmScreened(testCinema.getRoomList().get(0))).equals(title));
        }

        // Testing getTitlesList().
        filmListSize = testCinema.getFilmList().size();
        titleList = cont.getTitlesList();
        assertEquals(titleList.size(), filmListSize);
        assertEquals(titleList.get(filmListSize - 1), "XXX");

        // Testing exception for the room with setFilmInRoom().
        try {
            cont.buyNewFilm(0, "XX", length, genre, true, true);
            fail("This film can't be setted in this room.");
        } catch (final Exception e) {
            assertFalse(testCinema.getName(testCinema.getFilmScreened(testCinema.getRoomList().get(0))).equals(title));
        }

        // Testing buyNewFilm() and setFilmInRoom() of FilmController.
        try {
            cont.buyNewFilm(room, title, length, genre, false, true);
            assertEquals(testCinema.getFilmList().size(), 4);
            assertEquals(testCinema.getFilmList(), filmList);
            assertEquals(testCinema.getName(testCinema.getFilmScreened(testCinema.getRoomList().get(room))), title);
        } catch (final NewFilmException e) {
            fail(errorStr);
        }
        // -- TEST 3 --
        title = TITLE_TEST_2;
        length = LENGTH;
        genre = Genre.HORROR;
        room = 1;
        // Testing buyNewFilm() and setFilmInRoom() of FilmController.
        try {
            cont.buyNewFilm(room, title, length, genre, true, false);
            assertEquals(testCinema.getFilmList().size(), 4 + 1);
            assertEquals(testCinema.getFilmList(), filmList);
            assertEquals(testCinema.getName(testCinema.getFilmScreened(testCinema.getRoomList().get(room))), title);
        } catch (final NewFilmException e) {
            fail(errorStr);
        }
        // Testing getTitlesList().
        filmListSize = testCinema.getFilmList().size();
        titleList = cont.getTitlesList();
        assertEquals(titleList.size(), filmListSize);
        assertEquals(titleList.get(filmListSize - 1), title);

        title = testCinema.getFilmList().get(1).getName();
        length = testCinema.getFilmList().get(1).getLength();
        genre = testCinema.getFilmList().get(1).getGenre();

        // Testing exception.
        try {
            cont.buyNewFilm(3, title, length, genre, testCinema.getFilmList().get(1).isOver14(),
                    testCinema.getFilmList().get(1).is3D());
            fail("This film already exists in the film list");
        } catch (final NewFilmException e) {
            assertEquals(testCinema.getFilmList().size(), 4 + 1);
        }
    }

    /**
     * Test of BookingController and DiscountController.
     */
    @Test
    public void testBookingDiscountController() {
        int pos;
        Double price = 0.0;
        Double expectedP;
        final String errorStr = "It can't find the room with that film";
        // Testing checkDiscount().
        assertFalse(cont.checkDiscount(MAX_TICKETS + 1, 2, 1));
        assertFalse(cont.checkDiscount(3, 4, 0));
        assertFalse(cont.checkDiscount(3, 0, 4));
        assertFalse(cont.checkDiscount(3, 2, 3));
        assertFalse(cont.checkDiscount(1, 1, 1));
        assertTrue(cont.checkDiscount(1, 0, 0));

        try {
            pos = cont.searchRoom("Title", 1, 0);
            assertEquals(pos, 0);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        // Testing the exception.
        try {
            pos = cont.searchRoom("AAA", 1, 0);
            fail("It had to throw an IllegalArgumentException because this film does not exist");
        } catch (final IllegalArgumentException e) {
            testCinema.getFilmList().forEach(film -> {
                assertFalse(film.getName().equals("AAA"));
            });
        }
        // Testing getBookedRoom() of BookingController.
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(0));
        // Testing booking().
        price = cont.booking(true);
        assertEquals(price, EXPECTED_PRICE_ONLINE);
        price = cont.booking(false);
        assertEquals(price, EXPECTED_PRICE);
        // Testing checkDiscount().
        assertTrue(cont.checkDiscount(1, 0, 1));
        try {
            pos = cont.searchRoom(TITLE_TEST_1, 2, 0);
            assertEquals(pos, 2);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(2));
        // Testing booking().
        price = cont.booking(true);
        assertEquals(price, E_P_1_STUDENT_ONLINE);
        price = cont.booking(false);
        assertEquals(price, E_P_1_STUDENT);
        // Testing checkDiscount().
        assertTrue(cont.checkDiscount(1, 1, 0));
        try {
            pos = cont.searchRoom(TITLE_TEST_1, 2, 1);
            assertEquals(pos, 2);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(2));
        // Testing booking().
        price = cont.booking(true);
        assertEquals(price, E_P_U14_ONLINE);
        price = cont.booking(false);
        assertEquals(price, E_P_U14);
        // Testing checkDiscount().
        assertTrue(cont.checkDiscount(2, 1, 1));
        try {
            pos = cont.searchRoom(TITLE_TEST_1, 2, 1);
            assertEquals(pos, 2);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(2));
        // Testing booking().
        price = cont.booking(true);
        expectedP = E_P_1_STUDENT_ONLINE + E_P_U14_ONLINE;
        assertEquals(price, expectedP);
        price = cont.booking(false);
        expectedP = E_P_1_STUDENT + E_P_U14;
        assertEquals(price, expectedP);
        // Testing checkDiscount().
        assertTrue(cont.checkDiscount(3, 0, 2));
        try {
            pos = cont.searchRoom(TITLE_TEST_2, 3, 0);
            assertEquals(pos, 1);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(1));
        // Testing booking().
        price = cont.booking(true);
        expectedP = EXPECTED_PRICE_ONLINE + 2 * E_P_1_STUDENT_ONLINE;
        assertEquals(price, expectedP);
        price = cont.booking(false);
        expectedP = EXPECTED_PRICE + 2 * E_P_1_STUDENT;
        assertEquals(price, expectedP);
        // Testing checkDiscount().
        assertTrue(cont.checkDiscount(3, 0, 2));
        try {
            pos = cont.searchRoom(TITLE_TEST_2, 3, 0);
            assertEquals(pos, 1);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(1));
        // Testing booking().
        price = cont.booking(true);
        expectedP = EXPECTED_PRICE_ONLINE + 2 * E_P_1_STUDENT_ONLINE;
        assertEquals(price, expectedP);
        price = cont.booking(false);
        expectedP = EXPECTED_PRICE + 2 * E_P_1_STUDENT;
        assertEquals(price, expectedP);
        // Testing checkDiscount().
        assertTrue(cont.checkDiscount(4, 2, 2));
        try {
            pos = cont.searchRoom(TITLE_TEST_2, 3, 2);
            assertEquals(pos, -1 - 1);
        } catch (final IllegalArgumentException e) {
            fail(errorStr);
        }
        assertEquals(cont.getBookingC().getBookedRoom(), testCinema.getRoomList().get(1));
        // Testing booking().
        price = cont.booking(true);
        expectedP = 2 * E_P_1_STUDENT_ONLINE + 2 * E_P_U14_ONLINE;
        assertEquals(price, expectedP);
        price = cont.booking(false);
        expectedP = 2 * E_P_1_STUDENT + 2 * E_P_U14;
        assertEquals(price, expectedP);
    }

    /**
     * Test of CinemaBalance.
     */
    @Test
    public void testCinemaBalance() {
        final Double balance = cont.totBalance();
        Double bal = testCinema.getBudget();
        assertEquals(balance, bal);
        final Double boxOffice = cont.cinemaBoxOffice();
        bal = testCinema.getBoxOffice();
        assertEquals(boxOffice, bal);
        final Double expense = cont.cinemaExpense();
        bal = testCinema.getExpense();
        assertEquals(expense, bal);
    }

    /**
     * Test of ScreeningController.
     */
    @Test
    public void testScreeningController() {
        assertEquals(cont.getScreeningList().size(), testCinema.getRoomList().size());
        for (int i = 0; i < testCinema.getRoomList().size(); i++) {
            final Integer screening = testCinema.getScreening(testCinema.getRoomList().get(i));
            final Integer sc = cont.getScreeningList().get(i);
            assertEquals(screening, sc);
        }
        final List<Map<Character, List<Boolean>>> room = cont.getScreeningListForRoom(0);
        assertFalse(room.isEmpty());
        cont.setSeatsPosition(room.get(1), 1, 0);
        assertFalse(room.get(1).isEmpty());
    }

    /**
     * Test of SetUser.
     */
    @Test
    public void testSetUser() {
        String ownerPassword = "Password";
        String staffPassword = "Staff";
        // Correct passwords
        assertFalse(cont.checkOwnerPassword());
        assertFalse(cont.checkStaffPassword());
        try {
            cont.insertPasswordForOwner(ownerPassword);
            assertEquals(testCinema.getOwnerPassword(), ownerPassword);
        } catch (final PasswordException e) {
            fail("It throws a PasswordException but owner's password is correct");
        }

        try {
            cont.insertPasswordForStaff(staffPassword);
            assertEquals(testCinema.getStaffPassword(), staffPassword);
        } catch (final PasswordException e) {
            fail("It throws a PasswordException but staff's password is correct");
        }

        ownerPassword = "123";
        staffPassword = "123";
        assertTrue(cont.checkOwnerPassword());
        assertTrue(cont.checkStaffPassword());

        // Wrong password
        try {
            cont.insertPasswordForOwner(ownerPassword);
            fail("The owner's password is not correct but the program thinks it is");
        } catch (final PasswordException e) {
            assertFalse(testCinema.getOwnerPassword().equals(ownerPassword));
        }

        try {
            cont.insertPasswordForStaff(staffPassword);
            fail("The staff's password is not correct but the program thinks it is");
        } catch (final PasswordException e) {
            assertFalse(testCinema.getStaffPassword().equals(staffPassword));
        }

        // Testing resetPasswords().
        cont.reset();
        assertTrue(testCinema.getFilmList().isEmpty());
        assertFalse(cont.checkOwnerPassword());
        assertFalse(cont.checkStaffPassword());
        try {
            cont.insertPasswordForOwner(ownerPassword);
            assertEquals(testCinema.getOwnerPassword(), ownerPassword);
        } catch (final PasswordException e) {
            fail("It throws a PasswordException but owner's password is correct");
        }

        try {
            cont.insertPasswordForStaff(staffPassword);
            assertEquals(testCinema.getStaffPassword(), staffPassword);
        } catch (final PasswordException e) {
            fail("It throws a PasswordException but staff's password is correct");
        }
        cont.reset();
    }
}
