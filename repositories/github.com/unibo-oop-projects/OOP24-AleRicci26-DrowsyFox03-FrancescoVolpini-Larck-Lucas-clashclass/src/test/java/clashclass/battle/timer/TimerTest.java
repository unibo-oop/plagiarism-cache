package clashclass.battle.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

    private TimerImpl timer;

    @BeforeEach
    void setUp() {
        timer = new TimerImpl();
    }

    @Test
    void testStartTimer() throws Exception {
        timer.start();
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertTrue((boolean) isRunningField.get(timer), "Timer should be running after start");
        timer.stop();
    }

    @Test
    void testStopTimer() throws Exception {
        timer.start();
        timer.stop();
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertFalse((boolean) isRunningField.get(timer), "Timer should not be running after stop");
    }

    @Test
    void testElapsedTimeIncreases() throws Exception {
        timer.start();
        Thread.sleep(1100); // Wait for just over 1 second
        long elapsed = timer.getElapsedTime();
        assertTrue(elapsed >= 1, "Elapsed time should be at least 1 second");
        timer.stop();
    }

    @Test
    void testOnFinished() throws Exception {
        timer.start();
        timer.onFinished();
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertFalse((boolean) isRunningField.get(timer), "Timer should stop when onFinished is called");
    }

    @Test
    void testTimeLimitStop() throws Exception {
        timer.start();
        // Artificially set startTime to TIME_LIMIT seconds ago
        var startTimeField = TimerImpl.class.getDeclaredField("startTime");
        startTimeField.setAccessible(true);
        var timeLimitField = TimerImpl.class.getDeclaredField("TIME_LIMIT");
        timeLimitField.setAccessible(true);
        int timeLimit = (int) timeLimitField.get(null);
        startTimeField.set(timer, System.currentTimeMillis() - (timeLimit * 1000));

        // Give a moment for runTimer to pick it up
        Thread.sleep(1100);

        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertFalse((boolean) isRunningField.get(timer), "Timer should stop at time limit");
    }
}