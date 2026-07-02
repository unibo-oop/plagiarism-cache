package it.dpg.minigames.moleMinigameTest.model;

import it.dpg.minigames.molegame.model.Mole;
import it.dpg.minigames.molegame.model.MoleImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoleTest {
    @Test
    public void moleTest() {
        Mole m = new MoleImpl();
        Assertions.assertSame(m.isOut(), false);
        m.setMoleOut();
        Assertions.assertSame(m.isOut(), true);
        m.setMoleIn();
        Assertions.assertSame(m.isOut(), false);
    }
}
