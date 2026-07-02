package it.dpg.minigames.punchyMinigameTest.model;

import it.dpg.minigames.punchygame.model.Timer;
import it.dpg.minigames.punchygame.model.TimerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimerTest {

    @Test
    public void timerTest() {
        Timer t = new TimerImpl();
        Assertions.assertEquals(t.getTimeLeft(), t.getMaxTime());

        float elapsed = 1.7f;
        t.timerDecrease(elapsed);
        float firstTimeLeft = t.getTimeLeft();
        Assertions.assertEquals(firstTimeLeft, t.getMaxTime() - elapsed);

        t.timerIncrease();
        Assertions.assertEquals(t.getTimeLeft(), firstTimeLeft+0.10f);
    }
}
