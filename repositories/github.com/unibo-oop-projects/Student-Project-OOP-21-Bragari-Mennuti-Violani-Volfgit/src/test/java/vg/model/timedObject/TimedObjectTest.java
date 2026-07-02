package vg.model.timedObject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimedObjectTest {

    @Test
    void updateTimer() {
        //After 500 unit of time timer must be expired.
        TimedObject timedObject = new TimedObjectImpl(500);

        timedObject.updateTimer(250);
        assertEquals(250, timedObject.getRemainingTime());
        timedObject.updateTimer(250);
        assertEquals(0, timedObject.getRemainingTime());

    }

    @Test
    void isTimeOver() {
        //After 500 unit of time timer must be expired.
        TimedObject timedObject = new TimedObjectImpl(500);

        assertFalse(timedObject.isTimeOver());
        timedObject.updateTimer(200);
        assertFalse(timedObject.isTimeOver());

        timedObject.updateTimer(300);
        assertEquals(timedObject.getRemainingTime(), 0);
        //durationMillis = 0 --> timerIsOver = true
        assertTrue(timedObject.isTimeOver());
    }

}
