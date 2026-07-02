//CHECKSTYLE:OFF
package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import controller.Controller;
import controller.TamagotchiController;
import model.Model;
import model.ModelImpl;
import model.character.Stats;
import model.container.Box;
import model.container.Item;
import model.ranking.AbstractCharacter;

public class ModelTest {

    Stats happiness = new Stats("HAPPINESS", 1000);
    Stats hungry = new Stats("HUNGRY", 1000);
    Stats health = new Stats("HEALTH", 1000);
    Stats cleanness = new Stats("CLEANNESS", 1000);
    Item ball = new Item("Ball", 20, 5, "file: ../../imgs/ball.png");
    Box ballBox = new Box(ball);
    Item hamburger = new Item("Hamburger", 50, 5, "file: ../../imgs/hamburger.png");
    List<Stats> list = new LinkedList<>(Arrays.asList(happiness, hungry, health, cleanness));

    @Test
    public void testCharacter() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        controller.loadStartInformation();
        list.forEach(e -> assertTrue(controller.getStats().contains(e)));
        controller.getInventory().values().forEach(e -> assertTrue(e.isEmpty()));
        controller.buy(ball.getName());
        assertTrue(controller.getInventory().get(happiness.getName()).contains(ballBox));
        assertTrue(!controller.getInventory().isEmpty());
        assertEquals(controller.getBalance(), 95);
        controller.setMainItem(happiness.getName(), ball.getName());
        assertTrue(controller.getMainItem(happiness.getName()).equals("file: ../../imgs/ball.png"));
        controller.buy(hamburger.getName());
        controller.checkAndSetMainItem();
        assertTrue(controller.getMainItem(hungry.getName()).equals("file: ../../imgs/hamburger.png"));
    }

    @Test
    public void testShop() {
        Model model = new ModelImpl();
        model.initializeContainers(new HashMap<String, List<Box>>());
        Controller controller = new TamagotchiController(model);
        // System.out.println(controller.getShop());
        assertEquals(controller.getShop(), new HashMap<String, List<Box>>());
        controller.loadStartInformation();
        controller.buy(ball.getName());
        controller.buy(ball.getName());
        controller.buy(ball.getName());
        assertTrue(controller.getShop().get(happiness.getName()).contains(ballBox));
        assertEquals(controller.getShop().size(), 4);
        list.forEach(e -> assertEquals(controller.getShop().get(e.getName()).size(), 5));
    }

    @Test
    public void testRanking() {
        Model model = new ModelImpl();
        Controller controller = new TamagotchiController(model);
        System.out.println(controller.getRanking());
        assertTrue(controller.getRanking().isEmpty());
        controller.loadStartInformation();
        assertTrue(!controller.modAllStats(-1000));
        controller.addRanking();
        assertTrue(controller.getRanking().contains(new AbstractCharacter(null, 0)));
        assertEquals(controller.getRanking().size(), 1);

    }

}
