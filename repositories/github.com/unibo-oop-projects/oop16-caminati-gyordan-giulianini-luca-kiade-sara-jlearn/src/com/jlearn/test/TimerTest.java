package com.jlearn.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.jlearn.controller.timer.OutOfTimeException;
import com.jlearn.controller.timer.SimpleTimer;
import com.jlearn.controller.timer.SimpleTimerImpl;

/**
 * {@link ParserTest} tester.
 */
public class TimerTest {

    /**
     * Test the simple Timer..
     */
    // CHECKSTYLE:OFF
    @Test
    public void testSimpleTimer() {
        final SimpleTimer tim = new SimpleTimerImpl(5, null);

        org.junit.Assert.assertEquals(tim.remainingTime(), 5);

        try {
            tim.dec();
            tim.dec();
            tim.dec();
            tim.dec();
            tim.dec();
            tim.dec();
            fail("Need trow OutOfTimeException");

        } catch (final OutOfTimeException e) {
            tim.reset();
            org.junit.Assert.assertEquals(tim.remainingTime(), 5);
        }

    }
}
