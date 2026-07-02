package it.unibo.biscia.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;

/**
 * testing core function.
 *
 */
public class TestCore {

    private EntityFactory makeEntityFactory() {
        return new EntityFactoryImpl(new LevelImpl(Level.MIN_COLS, Level.MIN_ROWS));
    }

    /**
     * test for Level.
     */
    @Test
    public void testLevelImpl() {
        final Level.LevelManaged cl = new LevelImpl(Level.MIN_COLS, Level.MIN_ROWS);
        final Level l = (Level) cl;
        final var analyzer = cl.getAnalyzer();
        assertEquals(Level.MIN_COLS, cl.getCols(), "number of cols");
        assertEquals(Level.MIN_ROWS, cl.getRows(), "number of rows");
        // work compare from type converted from private interface to public interface?
        assertEquals(l, cl, "Compare class and superclass");
        assertEquals(cl.getCols() * cl.getRows(), cl.getCells().size(), "cell count");
        assertEquals(cl.getCols() * cl.getRows(), analyzer.getFreeCells().size(), "full free cell count");
        assertEquals(0, analyzer.getOccupiedCells().size(), "empty occupied cell count");
        // test for cell relations
        assertEquals(l.getCell(1, 0), l.getSideCell(0, 0, Direction.RIGHT), "Right side");
        assertEquals(l.getCell(0, 1), l.getSideCell(0, 0, Direction.DOWN), "Down side");
        assertEquals(l.getCell(0, 0), l.getSideCell(1, 0, Direction.LEFT), "Left side");
        assertEquals(l.getCell(0, 0), l.getSideCell(0, 1, Direction.UP), "Up fail");
        assertEquals(l.getCell(l.getCols() - 1, 0), l.getSideCell(0, 0, Direction.LEFT), "Left to border side");
        assertEquals(l.getCell(0, l.getRows() - 1), l.getSideCell(0, 0, Direction.UP), "Up to border side");
        assertEquals(l.getCell(0, 0), l.getSideCell(l.getCols() - 1, 0, Direction.RIGHT), "Right to border side");
        assertEquals(l.getCell(0, 0), l.getSideCell(0, l.getRows() - 1, Direction.DOWN), "Down to border side");
        final Cell c = l.getCell(0, 0);
        Cell c1 = c;
        for (final var d : Direction.values()) {
            c1 = l.getSideCell(c1, d);
        }
        assertEquals(c, c1, "Turn around side");
        for (int i = 0; i < l.getCols(); i++) {
            c1 = l.getSideCell(c1, Direction.LEFT);
        }
        assertEquals(c, c1, "Side all columns");
        for (int i = 0; i < l.getRows(); i++) {
            c1 = l.getSideCell(c1, Direction.DOWN);
        }
        assertEquals(c, c1, "Side all rows");

    }

    /**
     * test on cells.
     */
    @Test
    public void testCellImpl() {
        final int cols = 1000;
        final Set<Cell> l = new HashSet<>(cols * cols);
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < cols; r++) {
                final Cell cell = new CellImpl(c, r);
                assertTrue("Duplicate cell", l.add(cell));
                assertEquals(c, cell.getCol(), "cell.getCol()");
                assertEquals(r, cell.getRow(), "cell.getRow()");
                assertEquals(new CellImpl(c, r), cell, "cell not equals");
                assertEquals(new CellImpl(c, r).hashCode(), cell.hashCode(), "hashcode not equals");
            }
        }
        assertEquals(cols * cols, l.size(), "size of set");
    }

    /**
     * test for entity factory.
     */
    @Test
    public void testEntityFactoryImpl() {
        final int cols = 30;
        final int rows = 20;
        final var level = new LevelImpl(cols, rows);
        final var analyzer = level.getAnalyzer();
        final EntityFactory entityFactory = new EntityFactoryImpl(level);

        int occupies = (level.getCols() + level.getRows() - 2) * 2;
        int entityCount = 1;
        level.addEntity(entityFactory.makeEdge());
        assertEquals(entityCount, level.getEntities().size(), "numbers of entity not correct after edge");
        assertEquals(level.getCells().size() - occupies, analyzer.getFreeCells().size(),
                "numbers of free cells correct after edge");
        level.addEntity(entityFactory.makeLinearWall(level.getCell(1, 1), 1, Direction.DOWN));
        occupies++;
        entityCount++;
        assertEquals(entityCount, level.getEntities().size(), "numbers of entity not correct after single wall");
        assertEquals(level.getCells().size() - occupies, analyzer.getFreeCells().size(),
                "numbers of free cells correct after single wall");
        final int maxFood = level.getCells().size() - occupies;
        for (int i = 0; i < maxFood; i++) {
            System.out.println("makeCasualFood " + i);
            level.addEntity(entityFactory.makeCasualFood(1));
            occupies++;
            entityCount++;
            assertEquals(entityCount, level.getEntities().size(), "numbers of entity not correct after food");
            assertEquals(level.getCells().size() - occupies, analyzer.getFreeCells().size(),
                    "numbers of free cells correct");
            assertEquals(occupies, analyzer.getOccupiedCells().size(), "numbers of occupied cells correct");
        }
        // now no space, to do error
        try {
            System.out.println("makeCasualFood over");
            level.addEntity(entityFactory.makeCasualFood(1));
            fail("expected error");
        } catch (IllegalArgumentException e) {
            // ok
            System.out.println(e);
        }

        while (level.getEntities().size() > 0) {
            System.out.println("remove entity " + level.getEntities().size());
            entityCount--;
            final int freeCells = analyzer.getFreeCells().size();
            level.removeEntity(level.getEntitiesManaged().get(0));
            assertEquals(entityCount, level.getEntities().size(), "numbers of entity not correct after remove");
            assertTrue("cells feee not increased", freeCells < analyzer.getFreeCells().size());
            assertTrue("occupied cell not decreased", occupies > analyzer.getOccupiedCells().size());
            occupies = analyzer.getOccupiedCells().size();
        }
        // full alf level
        for (int i = 0; i < level.getRows() / 2; i++) {
            System.out.println("add linear wall " + i);
            level.addEntity(entityFactory.makeLinearWall(level.getCell(0, i), level.getCols(), Direction.RIGHT));
        }
        // test for casual free empty location
        for (int i = 0; i < 100; i++) {
            System.out.println("Casual food " + i + " " + System.currentTimeMillis());
            final var e = entityFactory.makeCasualFood(1);
            assertTrue("food out of level", level.getCells().contains(e.getCells().get(0)));
            assertTrue("food not in free cell", analyzer.getFreeCells().contains(e.getCells().get(0)));
            assertFalse("food in occupied cell", analyzer.getOccupiedCells().contains(e.getCells().get(0)));
        }
    }

    /**
     * test on player.
     */
    @Test
    public void testPlayerImpl() {

        final String name = "prova";
        final Player.PlayerManaged pm = new PlayerImpl(name);
        assertEquals(name, pm.getName(), "name");

        pm.setDirectable(this.makeEntityFactory().makeBabySnake(false));
        assertFalse("Directable null", Objects.isNull(pm.getDirectable()));
        assertFalse("Entity null", Objects.isNull(pm.getDirectable()));
        try {
            pm.setDirectable(this.makeEntityFactory().makeBabySnake(false));
            fail("new diractabe on directable present");
        } catch (IllegalStateException e) {
            // ok
            System.out.println(e);
        }
        assertEquals(Player.INITIAL_LIVES, pm.getLives(), "initial lives");
        pm.addPoints(Player.POINTS_FOR_FOOD_ENERGY);
        assertEquals(Player.POINTS_FOR_FOOD_ENERGY, pm.getPoints(), "points added");
        final int points = pm.getPoints();
        pm.dead();
        assertTrue("Directable not null", Objects.isNull(pm.getDirectable()));
        assertTrue("Entity not null", Objects.isNull(pm.getDirectable()));
        assertEquals(Player.INITIAL_LIVES - 1, pm.getLives(), "lives after dead");
        assertEquals(Player.POINTS_FOR_DEAD + points, pm.getPoints(), "points after dead");
        try {
            pm.dead();
            fail("dead without directable");
        } catch (IllegalStateException e) {
            // ok
            System.out.println(e);
        }
        while (pm.getLives() > 0) {
            pm.setDirectable(this.makeEntityFactory().makeBabySnake(false));
            pm.dead();
        }
        try {
            pm.setDirectable(this.makeEntityFactory().makeBabySnake(false));
            fail("new directable without lives");
        } catch (IllegalStateException e) {
            // ok
            System.out.println(e);
        }

    }

    /**
     * test for movement.
     */
    @Test
    public void testMovementType() {
        // TODO
    }

    /**
     * test on interaction manager.
     */
    @Test
    public void testInteractionManagerImpl() {
        // TODO
    }

    /**
     * test for Direction.
     */
    @Test
    public void testDirection() {
        // TODO
    }

    /**
     * test for EntityType.
     */
    @Test
    public void testEntityType() {
        // TODO
    }

    /**
     * test for LevelAnalyzer.
     */
    @Test
    public void testLevelAnalyzerImpl() {
        // TODO
    }

    /**
     * test for .
     */
    @Test
    public void testLevelLoaderImpl() {
        // TODO
    }

    /**
     * test for .
     */
    @Test
    public void testControllerImpl() {
        // TODO
    }
}
