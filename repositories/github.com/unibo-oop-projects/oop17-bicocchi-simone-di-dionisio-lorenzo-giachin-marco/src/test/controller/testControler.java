//CHECKSTYLE:OFF
package test.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import controller.Controller;
import controller.TamagotchiController;
import controller.timer.TamagotchiTimer;
import model.Model;
import model.ModelImpl;
import model.ranking.AbstractCharacter;

public class testControler {
    Model model;

    @Test
    public void testCharacterName_ok() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.loadStartInformation();
        controller.setCharacterName("Pippo");
        assertEquals(controller.getCharacterName(), "Pippo");
    }

    @Test
    public void testCharacterName_fail() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.loadStartInformation();
        assertFalse(controller.setCharacterName("01234567890"));
        // il nome ha max 10 caratteri
    }

    @Test
    public void testCharacterNameYetInRanking() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.loadStartInformation();
        controller.setCharacterName("Pippo");
        controller.addRanking();
        controller.loadStartInformation();
        assertFalse(controller.setCharacterName("Pippo"));
        // il nome è gia in classifica
    }

    @Test
    public void testRanking() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.loadStartInformation();
        controller.setCharacterName("Paperino");
        AbstractCharacter pet = new AbstractCharacter("Paperino", model.getAge());
        assertFalse(controller.modAllStats(-1000));
        controller.addRanking();
        assertTrue(controller.getRanking().contains(pet));
        // il salvataggio in classifica è esatto
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testCheckInventory() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.buy("Ball");
        controller.modStat("Happines");
        controller.checkInventory();
        assertFalse(controller.getInventory().containsValue("Ball"));
    }

    @Test
    public void testCheckAndSetMainItem() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.loadStartInformation();
        controller.buy("Ball");
        controller.checkAndSetMainItem();
        assertTrue(controller.getMainItem("HAPPINESS").equals("file: ../../imgs/ball.png"));
    }
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testLifeTimer() {
        int initialStat = 1000;
        Model model = new ModelImpl();
        TamagotchiController controller = new TamagotchiController(model);
        TamagotchiTimer tamaTimer = new TamagotchiTimer(controller, 1000, 1);
        controller.loadStartInformation();
        tamaTimer.start();
        assertFalse(controller.getStats().contains(initialStat));
    }

}