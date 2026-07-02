package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import model.construction.Building;
import model.construction.Construction;
import model.construction.ConstructionType;
import model.gamemap.GameMap;
import model.gamemap.GameMapImpl;

class TestMap {

    @Test
    public void testCreationMap() {
        final GameMap gameMap = new GameMapImpl();
        assertTrue(gameMap.getMap().isEmpty());
    }

    @Test
    public void testAddingBuilding() {
        final GameMap gameMap = new GameMapImpl();
        final Construction residential = new Building(ConstructionType.APPARTAMENTO, new Pair<>(1, 1));
        gameMap.addBuilding(residential);
        assertFalse(gameMap.getMap().isEmpty());
        assertFalse(gameMap.isBuilding(residential.getPosition()));
    }

    @Test
    public void testDeletingBuilding() {
        final GameMap gameMap = new GameMapImpl();
        final Construction residential = new Building(ConstructionType.APPARTAMENTO, new Pair<>(1, 1));
        final Construction policeStation = new Building(ConstructionType.POLIZIA, new Pair<>(2, 2));
        gameMap.addBuilding(residential);
        assertFalse(gameMap.getMap().isEmpty());
        gameMap.addBuilding(policeStation);
        gameMap.deleteBuilding(residential);
        assertFalse(gameMap.getMap().isEmpty());
        assertTrue(gameMap.isBuilding(residential.getPosition()));
    }
}
