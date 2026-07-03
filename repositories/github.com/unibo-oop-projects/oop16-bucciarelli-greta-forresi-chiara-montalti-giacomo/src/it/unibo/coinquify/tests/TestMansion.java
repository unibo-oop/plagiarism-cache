package it.unibo.coinquify.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.mansionsmng.controller.MansionManagerControllerImpl;
import it.unibo.coinquify.mansionsmng.controller.MansionMangerController;
import it.unibo.coinquify.mansionsmng.model.Mansion;
import it.unibo.coinquify.mansionsmng.model.MansionImpl;
import it.unibo.coinquify.mansionsmng.model.MansionType;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.roommates.model.RoomMateImpl;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * Test RoomMate mansion.
 */
public class TestMansion {

    /**
     * test a single mansion.
     */
    @Test
    public void testSingleMansion() {
        final Mansion spesa = new MansionImpl(MansionType.SHOPPING, new TimeImpl(9, 0), new TimeImpl(10, 2),
                DayOfWeek.FRIDAY);
        assertTrue(spesa.getDayOfWeek().equals(DayOfWeek.FRIDAY));
        assertFalse(spesa.getName().equals(MansionType.BILLS));
        assertEquals(spesa.getEndTime(), new TimeImpl(10, 2));
        assertNotEquals(spesa.getStartTime(), new TimeImpl(10, 3));
    }

    /**
     * test room mate mansions.
     */
    @Test
    public void testRoomMateMansion() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);

        RoomMate marioRossi;
        try {
            marioRossi = new RoomMateImpl("Mario", "Rossi", "MRARSS00A01H501B", Room.ROOM_A, "3332343359",
                    dateFormat.parse("01/01/1990"));
            assertTrue(marioRossi.getMansions().isEmpty());
            final Set<RoomMate> roomMateSet = new HashSet<>();
            roomMateSet.add(marioRossi);
            final Mansion bollette = new MansionImpl(MansionType.BILLS, new TimeImpl(10, 0), new TimeImpl(10, 10),
                    DayOfWeek.MONDAY);
            final MansionMangerController controller = new MansionManagerControllerImpl(roomMateSet);
            try {
                controller.addMansion(bollette, marioRossi);
            } catch (Exception e) {
                Assert.fail(TestRoomMate.NOEXCEPTION);
            }

            /* Try to insert mansion incorrect, illegal argument exception */
            try {
                controller.addMansion(new MansionImpl(MansionType.SHOPPING, new TimeImpl(10, 0), new TimeImpl(1, 0),
                        DayOfWeek.FRIDAY), marioRossi);
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            /*
             * Try to insert mansion in the same time, illegal argument
             * exception
             */
            try {
                controller.addMansion(new MansionImpl(MansionType.SHOPPING, new TimeImpl(10, 0), new TimeImpl(10, 10),
                        DayOfWeek.MONDAY), marioRossi);
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(OperationNotSupportedException.class));
            }

            assertEquals(marioRossi.getMansions().size(), 1);
            assertFalse(marioRossi.getMansions().contains(new MansionImpl(MansionType.BATHROOM_CLEAN,
                    new TimeImpl(10, 0), new TimeImpl(10, 10), DayOfWeek.SATURDAY)));
            controller.deleteMansion(bollette, marioRossi);
            assertNotEquals(marioRossi.getMansions().size(), 1);
        } catch (ParseException e) {
            Assert.fail(TestRoomMate.NOEXCEPTION);
        }

    }
}
