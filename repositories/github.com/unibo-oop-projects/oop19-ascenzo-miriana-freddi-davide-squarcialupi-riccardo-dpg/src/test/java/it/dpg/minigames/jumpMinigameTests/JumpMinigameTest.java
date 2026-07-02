package it.dpg.minigames.jumpMinigameTests;

import it.dpg.maingame.model.character.Difficulty;
import it.dpg.minigames.punchygame.PunchyMinigame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JumpMinigameTest {
    @Test
    void minigameTest() {
        PunchyMinigame pm = new PunchyMinigame();

        Assertions.assertTrue(pm.getMaxScore() > 0);

        for(Difficulty d : Difficulty.values()) {
            Assertions.assertTrue(pm.randomizeScore(d) > 0);
        }
    }
}
