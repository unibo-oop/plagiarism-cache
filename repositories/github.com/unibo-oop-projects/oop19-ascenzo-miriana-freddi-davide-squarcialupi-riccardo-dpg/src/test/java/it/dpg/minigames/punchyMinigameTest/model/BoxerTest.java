package it.dpg.minigames.punchyMinigameTest.model;

import it.dpg.minigames.punchygame.model.Boxer;
import it.dpg.minigames.punchygame.model.BoxerImpl;
import it.dpg.minigames.punchygame.model.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BoxerTest {

    @Test
    public void boxerTest() {
        Boxer b = new BoxerImpl();
        Assertions.assertSame(b.getDirection(), Direction.RIGHT);

        b.setDirection(Direction.LEFT);
        Assertions.assertSame(b.getDirection(), Direction.LEFT);
    }
}
