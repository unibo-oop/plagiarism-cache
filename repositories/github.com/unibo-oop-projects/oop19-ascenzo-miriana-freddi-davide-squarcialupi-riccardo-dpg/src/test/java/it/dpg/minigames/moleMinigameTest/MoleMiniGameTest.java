package it.dpg.minigames.moleMinigameTest;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.minigames.molegame.MoleMiniGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class MoleMiniGameTest {

    @Test
    void minigameTest() {
        MoleMiniGame mg = new MoleMiniGame();

        Assertions.assertTrue(mg.getMaxScore() > 0);

        for(Difficulty d : Difficulty.values()) {
            Assertions.assertTrue(mg.randomizeScore(d) > 0);
        }
    }
}
