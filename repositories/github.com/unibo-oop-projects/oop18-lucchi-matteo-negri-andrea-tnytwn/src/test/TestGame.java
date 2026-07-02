package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import javafx.util.Pair;
import model.construction.Building;
import model.construction.ConstructionType;
import model.game.GameImpl;

/**
 * This class is used for testing the Game model.
 */
public class TestGame {

    /**
     * Test the creation of a new Gme with the right parameters.
     */
    @Test public void testGameIncomes() {
        try {
            GameImpl.getGameImpl().getDailyIncomes().forEach(x -> assertEquals(x.intValue(), 0));
            GameImpl.getGameImpl().getDailyCosts().forEach(x -> assertEquals(x.intValue(), 0));
            GameImpl.getGameImpl();
            assertEquals(GameImpl.getGameImpl().getResources().get(0).getValue().intValue(), GameImpl.START_MONEY);
            assertEquals(GameImpl.getGameImpl().getResources().get(1).getValue().intValue(), GameImpl.START_WATER);
            assertEquals(GameImpl.getGameImpl().getResources().get(2).getValue().intValue(), GameImpl.START_ENERGY);
            GameImpl.getGameImpl().getResources().forEach(x -> x.add(1000));
            assertEquals(GameImpl.getGameImpl().getResources().get(0).getValue().intValue(), (GameImpl.START_MONEY + 1000));
            assertEquals(GameImpl.getGameImpl().getResources().get(1).getValue().intValue(), (GameImpl.START_WATER + 1000));
            assertEquals(GameImpl.getGameImpl().getResources().get(2).getValue().intValue(), (GameImpl.START_ENERGY + 1000));
            GameImpl.getGameImpl().createBuilding(new Building(ConstructionType.APPARTAMENTO, new Pair<Integer, Integer>(1, 1)), new Pair<Integer, Integer>(1, 1));
            assertEquals(GameImpl.getGameImpl().getResources().get(0).getValue().intValue(), ((GameImpl.START_MONEY + 1000) - ConstructionType.APPARTAMENTO.getCost()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
