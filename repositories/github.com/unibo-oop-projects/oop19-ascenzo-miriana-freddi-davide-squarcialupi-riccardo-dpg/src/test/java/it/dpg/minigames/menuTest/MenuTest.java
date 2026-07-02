package it.dpg.minigames.menuTest;

import it.dpg.maingame.controller.menu.MenuController;
import it.dpg.maingame.controller.menu.MenuControllerImpl;
import it.dpg.maingame.model.character.Difficulty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MenuTest {

    @Test
    void menutest(){
        MenuController m = new MenuControllerImpl();


        Assertions.assertTrue(m.getOptionsAI().toString().equals("{AI1=EASY}"));

        Assertions.assertTrue(m.getOptionsPlayer().toString().equals("{1=Giocatore1}"));

        m.setAIDifficulty(0, Difficulty.HARD);

        Assertions.assertTrue(m.getOptionsAI().toString().equals("{AI1=HARD}"));

        m.setOptionsAI(4);
        Assertions.assertTrue(m.getOptionsAI().toString().equals("{AI1=EASY, AI2=EASY, AI3=EASY, AI4=EASY}"));

        m.setAIDifficulty(0, Difficulty.NORMAL);
        m.setAIDifficulty(2, Difficulty.HARD);

        Assertions.assertTrue(m.getOptionsAI().toString().equals("{AI1=NORMAL, AI2=EASY, AI3=HARD, AI4=EASY}"));
        m.setOptionsPlayer(3);
        Assertions.assertTrue(m.getOptionsPlayer().toString().equals("{1=Giocatore1, 2=Giocatore2, 3=Giocatore3}"));
    }
}
