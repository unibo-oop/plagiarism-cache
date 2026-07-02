package test;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import javafx.util.Pair;
import model.construction.Building;
import model.construction.ConstructionType;
import model.game.Game;
import model.game.GameImpl;

/*
 * ONLY in case test, we assume that:
 * ConstructionType.POMPIERI    is always in position   1, 1
 * ConstructionType.GRATTACIELO is always in position   2, 2
 * ConstructionType.POLIZIA     is always in position   4, 4
 * ConstructionType.OSPEDALE    is always in position   5, 5
 */

class TestAchievements {
    private final Pair<Integer, Integer> fireFightersPosition = new Pair<>(1, 1);
    private final Pair<Integer, Integer> policePosition = new Pair<>(4, 4);
    private final Pair<Integer, Integer> hospitalPosition = new Pair<>(5, 5);

    @Test
    public void unlockServiziPerTutti() {
        final Game game = GameImpl.getGameImpl();
        if (game.getMap().isBuilding(fireFightersPosition)) {
            game.getMap().addBuilding(new Building(ConstructionType.POMPIERI, fireFightersPosition));
            game.getBuildingsCounter().addBuilding(ConstructionType.POMPIERI);
        }
        if (game.getMap().isBuilding(policePosition)) {
            game.getMap().addBuilding(new Building(ConstructionType.POLIZIA, policePosition));
            game.getBuildingsCounter().addBuilding(ConstructionType.POLIZIA);
        }
        if (game.getMap().isBuilding(hospitalPosition)) {
            game.getMap().addBuilding(new Building(ConstructionType.OSPEDALE, hospitalPosition));
            game.getBuildingsCounter().addBuilding(ConstructionType.OSPEDALE);
        }
        game.getAchievements().get(1).update(game);
        assertTrue(game.getAchievements().get(1).isUnlock());
    }

    @Test
    public void unlockSanoComeUnPesce() {
        final Game game = GameImpl.getGameImpl();
        if (game.getMap().isBuilding(hospitalPosition)) {
            game.getMap().addBuilding(new Building(ConstructionType.OSPEDALE, hospitalPosition));
            game.getBuildingsCounter().addBuilding(ConstructionType.OSPEDALE);
        }
        game.getAchievements().get(2).update(game);
        assertTrue(game.getAchievements().get(2).isUnlock());
    }
}
