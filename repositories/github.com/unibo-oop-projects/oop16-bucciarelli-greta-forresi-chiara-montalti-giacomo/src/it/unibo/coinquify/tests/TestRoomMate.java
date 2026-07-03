package it.unibo.coinquify.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.coinquify.home.Home;
import it.unibo.coinquify.home.HomeImpl;
import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.roommates.controller.RoomMatesController;
import it.unibo.coinquify.roommates.controller.RoomMatesControllerImpl;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.roommates.model.RoomMateImpl;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * Room mate test class.
 */
public class TestRoomMate {

    private static final String ROSSI = "Rossi";
    private static final String MARIO_NUMBER = "3332343359";
    private static final String DATE = "01/01/1990";
    private static final String MARIO = "Mario";
    /**
     * exception messsage.
     */
    protected static final String EXCEPTION = "Should have thrown an exception";

    /**
     * no exception messsage.
     */
    protected static final String NOEXCEPTION = "Shouldn't have thrown an exception";

    /**
     * test a single room mate options.
     * 
     * @TestMansion with jUnit
     */
    @Test
    public void testSingleRoomMate() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
        try {
            final RoomMate marioRossi = new RoomMateImpl(MARIO, ROSSI, "MRARSS00A01H501B", Room.ROOM_A, MARIO_NUMBER,
                    dateFormat.parse(DATE));
            assertTrue(marioRossi.getName().equals(MARIO));
            assertEquals(marioRossi.getBirthDay(), dateFormat.parse(DATE));
            assertFalse(marioRossi.getFiscalCode().isEmpty());
            assertTrue(marioRossi.getRoom().equals(Room.ROOM_A));
            assertEquals(marioRossi.getPhoneNumber(), MARIO_NUMBER);
            assertFalse(marioRossi.getSurname().equals(MARIO));

            marioRossi.setName("Marino");
            assertFalse(marioRossi.getName().equals(MARIO));
            marioRossi.setSurname("Rosso");
            assertEquals(marioRossi.getSurname(), "Rosso");
            marioRossi.setPhoneNumber("3332343353");
            assertNotEquals(marioRossi.getPhoneNumber().length(), 2);
        } catch (ParseException e) {
            Assert.fail(TestRoomMate.NOEXCEPTION);
        }
    }

    /**
     * @TestMansion home functions.
     */
    @Test
    public void testHome() {
        try {
            final Home home = new HomeImpl();
            final RoomMatesController controller = new RoomMatesControllerImpl(home, new ArrayList<>());
            final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
            final RoomMate marioRossi = new RoomMateImpl(MARIO, ROSSI, "MRARSS00A01H501B", Room.ROOM_A, MARIO_NUMBER,
                    dateFormat.parse(DATE));
            try {
                controller.add(marioRossi);
            } catch (Exception e) {
                Assert.fail(TestRoomMate.NOEXCEPTION);
            }
            assertTrue(home.getRoomMates().contains(marioRossi));

            // try to add mario rossi another time, illegal arguement exception
            try {
                controller.add(marioRossi);
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            // try to insert with inconsistent data, illegal arguement exception
            try {
                controller
                        .add(new RoomMateImpl(MARIO, "", "A01H501B", Room.ROOM_A, "32343359", dateFormat.parse(DATE)));
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            /*
             * try to add another room mate that have the mario rossi phone
             * number, PhoneNumberPresentException, but it's insered
             */
            final RoomMate giorgio = new RoomMateImpl("Giorgio", ROSSI, "GGARSS00A01H501B", Room.ROOM_B, MARIO_NUMBER,
                    dateFormat.parse(DATE));
            try {
                controller.add(giorgio);
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(PhoneNumberPresentException.class));
            }
            assertTrue(home.getRoomMates().contains(giorgio));

            final RoomMate marioRossi2 = new RoomMateImpl(MARIO, ROSSI, "MRARSS00A01H501B", Room.ROOM_B, "33323443359",
                    dateFormat.parse(DATE));

            /*
             * try to update mario rossi with mario rossi 2
             */
            try {
                controller.update(marioRossi, marioRossi2);
            } catch (IllegalArgumentException | PhoneNumberPresentException e) {
                Assert.fail(TestRoomMate.NOEXCEPTION);
            }
            home.deleteRoomMate(marioRossi);
            assertFalse(home.getRoomMates().contains(marioRossi));
        } catch (ClassNotFoundException | IOException | ParseException e) {
            Assert.fail(TestRoomMate.NOEXCEPTION);
        }
    }
}
