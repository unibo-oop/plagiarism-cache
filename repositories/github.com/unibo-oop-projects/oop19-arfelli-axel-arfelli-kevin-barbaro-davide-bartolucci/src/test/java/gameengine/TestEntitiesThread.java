package gameengine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestEntitiesThread {

    private static EntitiesUpdater et;
    private static final int DEFAULTTICK = 2;
    private static final int LONGSLEEP = 5000;
    private int tick = DEFAULTTICK;

 
    @BeforeAll
    static void before() throws Exception {
        et = new EntitiesUpdater(DEFAULTTICK);
    }

    private void sleep() {
        sleep(1000 / tick);
    }

    private void sleep(final long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void startAndStop() {
        final Thread t = new Thread(et);
        t.start();

        sleep();
        assertTrue(et.isStarted());
        assertFalse(et.isPaused());
        et.pause();
        assertTrue(et.isPaused());
        assertTrue(et.isStarted());
        et.stop();
        assertFalse(et.isPaused());
        assertFalse(et.isStarted());
    }

    @Test
    void startAndStopFaster() {
        tick = 100;
        final EntitiesUpdater et = new EntitiesUpdater(100);
        final Thread t = new Thread(et);
        t.start();

        sleep();
        assertTrue(et.isStarted());
        assertFalse(et.isPaused());
        et.pause();
        sleep();
        assertTrue(et.isPaused());
        assertTrue(et.isStarted());
        et.stop();
        sleep();
        assertFalse(et.isPaused());
        assertFalse(et.isStarted());
    }

    @Test
    void longRun() {
        final EntitiesUpdater et = new EntitiesUpdater(10);
        final Thread t = new Thread(et);
        t.start();

        sleep(LONGSLEEP);
        et.stop();
    }
}
