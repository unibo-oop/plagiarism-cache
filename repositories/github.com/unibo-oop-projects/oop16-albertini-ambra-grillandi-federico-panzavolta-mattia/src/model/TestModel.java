package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import utilities.Genre;

/**
 * This class test Model.
 */
public class TestModel {

    /**
     * Test of Model.
     */
    @Test
    public void testmodel() {

        final Model m = new ModelImpl(2, 20);
        //Test for Film
        final Film f1 = new FilmImpl("Avatar", 100, Genre.ACTION, false, false);
        final Film f2 = new FilmImpl("", 242424, Genre.ANIMATION, false, true);
        final Film f3 = new FilmImpl("1j2j", 0, Genre.MYSTERY, true, false);

        assertEquals(m.getName(f1), "Avatar");
        assertEquals(m.getLength(f1), 100);
        assertEquals(m.getGenre(f1), Genre.ACTION);
        assertFalse(m.isOver14(f1));
        assertFalse(m.is3D(f1));       //tested
 

        //Test for Room
        final Room r1 = new RoomImpl("A", 160);
        final Room r2 = new RoomImpl("A", 160);
//        final Room r3 = new RoomImpl("B", 2200);  //tested; seats>400
        final Room r4 = new RoomImpl("C", 364);
        final double filmCost = -1200;
        final double budgetExpexted = 7800;
        m.setFilm(f1, r1);
        m.setFilm(f2, r1);
        assertEquals(m.getFilmScreened(r1), f2);
        assertEquals(m.getRoomName(r1), "A");
        assertEquals(m.getScreening(r1), 1);
        assertEquals(m.getScreening(r2), 0);  //tested

        //Test for Owner
        m.setOwnerPassword("lol");
        m.setStaffPassword("rqrq");

        assertEquals(m.getOwnerPassword(), "lol");
        assertEquals(m.getStaffPassword(), "rqrq"); //tested

        //Test for Balance and Cinema

        m.buyFilm(f1);
        assertEquals(m.getExpense(), filmCost, 0.0); //tested
        m.buyFilm(f3);
        assertEquals(m.getBudget(), budgetExpexted, 0.0);  //tested

        final List<Film> filmList = new ArrayList<>();
        filmList.add(f1);
        filmList.add(f3);
        assertEquals(m.getFilmList(), filmList); //tested
        m.addRoom(r4);
        final List<Room> roomList = new ArrayList<>();
        roomList.add(r4);
        assertEquals(m.getRoomList(), roomList); //tested

        m.addRoom(r1);
//        m.addRoom(r2);  //tested; you can't add the 3 room, this room limit=2
        m.removeFilm(f1);
        filmList.remove(f1);
        assertEquals(m.getFilmList(), filmList); //tested
        m.removeFilm(f2); //tested


        //Test for Booking

        final Discount d1 = new DiscountImpl(4, 5);
        final Booking bo1 = new BookingImpl(true, 15);
        final int row = 15;
        final double priceExpected = 328.8;
        final int  characterConv = 65;
        final int nSeats = 10;
        bo1.setRoomForBooking(r1);
        assertEquals(m.computePrice(bo1, d1, m.getBalance()), priceExpected, 0.0);
        assertEquals(m.getBoxOffice(), priceExpected, 0.0);       //tested

        final Map<Character, List<Boolean>> mapTest = new HashMap<>();
        final List<Boolean> listOfSeatsForRow = new ArrayList<>();

        for (int i = 0; i <  row; i++) {
            listOfSeatsForRow.add(true);
        }
        for (int i = 0; i < nSeats; i++) {
            mapTest.put((char) (i + characterConv), listOfSeatsForRow);
        }
        r1.reset();
        assertEquals(r1.getLayoutRoom(), mapTest); //tested
    }
}
