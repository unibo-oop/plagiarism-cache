package it.dpg.minigames.jumpMinigameTests.controller;

import it.dpg.minigames.jumpgame.controller.input.MoveLeft;
import it.dpg.minigames.jumpgame.controller.input.MoveRight;
import it.dpg.minigames.jumpgame.model.World;
import it.dpg.minigames.jumpgame.model.WorldImpl;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputTest {

    @Test
    public void inputTest() {
        World w = new WorldImpl();

        Pair<Integer, Integer> pos = w.getPlayerPosition();
        new MoveLeft().execute(w);
        w.update();
        Assertions.assertTrue(pos.getLeft() > w.getPlayerPosition().getLeft());

        pos = w.getPlayerPosition();
        new MoveRight().execute(w);
        w.update();
        Assertions.assertTrue(pos.getLeft() < w.getPlayerPosition().getLeft());
    }
}
