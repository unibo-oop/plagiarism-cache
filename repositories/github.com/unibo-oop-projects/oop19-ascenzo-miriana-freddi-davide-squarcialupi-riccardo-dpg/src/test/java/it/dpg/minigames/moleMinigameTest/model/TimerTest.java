package it.dpg.minigames.moleMinigameTest.model;

import it.dpg.minigames.molegame.model.Timer;
import it.dpg.minigames.molegame.model.TimerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimerTest {
    @Test
    public synchronized void timerTest() {
        Timer t = new TimerImpl();
        t.timeStart();
        Assertions.assertSame(t.getRemainTime(), (long)20);
        Assertions.assertSame(t.checkTimeIsUp(), false);
        try {
            wait(20000, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertSame(t.getRemainTime(), (long)0);
        Assertions.assertSame(t.checkTimeIsUp(), true);

    }
}
