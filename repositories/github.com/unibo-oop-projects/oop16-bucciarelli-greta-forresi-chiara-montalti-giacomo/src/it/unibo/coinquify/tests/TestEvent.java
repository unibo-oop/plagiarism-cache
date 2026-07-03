package it.unibo.coinquify.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.coinquify.calendar.controller.CalendarController;
import it.unibo.coinquify.calendar.controller.CalendarControllerImpl;
import it.unibo.coinquify.calendar.model.Event;
import it.unibo.coinquify.calendar.model.EventImpl;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * test event.
 */
public class TestEvent {
    private static final String D12_03_2017 = "12/03/2017";
    private static final String D10_03_2017 = "10/03/2017";
    private static final String MONTAGNA = "Gita in montagna";
    private static final int T14 = 14;
    private static final int T20 = 20;
    private static final int T30 = 30;

    /**
     * TEST single event.
     */
    @Test
    public void testSingleEvent() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);

        try {
            final Event gita = new EventImpl(dateFormat.parse(D10_03_2017), dateFormat.parse(D12_03_2017),
                    new TimeImpl(10, 20), new TimeImpl(14, 30), MONTAGNA, "", "");
            assertTrue(gita.getDescription().isEmpty());
            assertFalse(gita.getTitle().isEmpty());
            assertEquals(gita.getEndDate(), dateFormat.parse(D12_03_2017));
            assertNotEquals(gita.getStartDate(), dateFormat.parse(D12_03_2017));
            gita.setDescription("ciao");
            assertFalse(gita.getDescription().isEmpty());
            gita.setStartDate(dateFormat.parse(D12_03_2017));
            assertEquals(gita.getStartDate(), dateFormat.parse(D12_03_2017));
            assertEquals(gita.getEndTime(), new TimeImpl(T14, T30));
            assertNotEquals(gita.getStartTime(), new TimeImpl(T14, T30));
        } catch (ParseException e) {
            Assert.fail(TestRoomMate.NOEXCEPTION);
        }
    }

    /**
     * test calendar.
     */
    @Test
    public void testCalendar() {
        try {
            Messages.getMessages().setCurrentLocale(new Locale("it", "IT"));
            final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
            final CalendarController controller = new CalendarControllerImpl();
            final Event gita = new EventImpl(dateFormat.parse("10/03/2018"), dateFormat.parse("12/03/2018"),
                    new TimeImpl(10, 20), new TimeImpl(14, 30), MONTAGNA, "", "");
            try {
                controller.addEvent(gita);
            } catch (Exception e) {
                Assert.fail(TestRoomMate.NOEXCEPTION);
            }

            // try to insert invalid events
            try {
                controller.addEvent(new EventImpl(dateFormat.parse("10/03/2018"), dateFormat.parse("9/03/2018"),
                        new TimeImpl(10, T20), new TimeImpl(T14, T30), "Gita in collina", "", ""));
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            try {
                controller.addEvent(new EventImpl(dateFormat.parse(D10_03_2017), dateFormat.parse(D10_03_2017),
                        new TimeImpl(10, T20), new TimeImpl(3, T30), "Gita al mare", "", ""));
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            try {
                controller.addEvent(new EventImpl(dateFormat.parse("4/03/2017"), dateFormat.parse(D10_03_2017),
                        new TimeImpl(10, T20), new TimeImpl(3, T30), "Gita in barca", "", ""));
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            assertTrue(controller.getEvents(Optional.empty()).contains(gita));

            // try to update gita with anoter event, Illegal argumente exception
            try {
                controller.update(gita, new EventImpl(dateFormat.parse("10/03/2016"), dateFormat.parse("20/03/2018"),
                        new TimeImpl(10, T20), new TimeImpl(3, T30), MONTAGNA, "", ""));
                Assert.fail(TestRoomMate.EXCEPTION);
            } catch (Exception e) {
                assertTrue(e.getClass().equals(IllegalArgumentException.class));
            }

            final Event gita2 = new EventImpl(dateFormat.parse("10/03/2018"), dateFormat.parse("18/03/2018"),
                    new TimeImpl(10, 20), new TimeImpl(14, 30), MONTAGNA, "", "");
            // try to update gita with anoter event ok
            try {
                controller.update(gita, gita2);
            } catch (Exception e) {
                Assert.fail(TestRoomMate.NOEXCEPTION);
            }

            assertTrue(controller.getEvents(Optional.empty()).contains(gita2));

            assertFalse(controller.getEvents(Optional.empty()).contains(gita));

        } catch (ParseException e) {
            Assert.fail(TestRoomMate.NOEXCEPTION);
        }
    }
}
