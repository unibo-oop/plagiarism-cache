package it.dpg.minigames.punchyMinigameTest.controller;

import it.dpg.minigames.punchygame.controller.input.Input;
import it.dpg.minigames.punchygame.controller.input.PunchLeft;
import it.dpg.minigames.punchygame.controller.input.PunchRight;
import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.World;
import it.dpg.minigames.punchygame.model.WorldImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputTest {

    @Test
    public void inputTest() {
        World w = new WorldImpl();
        Input punch;

        Direction d = w.getSacks().get(0);
        if(d == Direction.LEFT) {
            punch = new PunchLeft();
        } else {
            punch = new PunchRight();
        }
        Assertions.assertTrue(punch.execute(w));

        d = w.getSacks().get(0);
        if(d == Direction.LEFT) {
            punch = new PunchRight();
        } else {
            punch = new PunchLeft();
        }
        Assertions.assertFalse(punch.execute(w));
    }
}
