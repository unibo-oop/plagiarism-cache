package it.unibo.pacman.test;


import static it.unibo.pacman.model.utilities.EntityType.WALL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import it.unibo.pacman.model.mapeditor.FixedGridImpl;
import it.unibo.pacman.model.mapeditor.MapBuilder;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
import it.unibo.pacman.view.utilities.MapSize;

public class TestGrid {

    @Test
    public void testBorderedStandardGridCreation() {
        final FixedGridImpl standardGrid = new MapBuilder(MapSize.STANDARD.getHeight(), MapSize.STANDARD.getWidth()).addFixedBordersWithPortals()
                                                                                                                    .getGrid();
        final int centerY = (standardGrid.getRows() - 1) / 2;
        for (int y = 1; y < standardGrid.getRows() - 1; y++) {
            if (y != centerY) {
                assertEquals(WALL, standardGrid.getEntity(0, y));
                assertEquals(WALL, standardGrid.getEntity(standardGrid.getColumns() - 1, y));
            }
        }
        for (int x = 0; x < standardGrid.getColumns(); x++) {
            assertEquals(EntityType.WALL, standardGrid.getEntity(x, 0));
            assertEquals(EntityType.WALL, standardGrid.getEntity(x, standardGrid.getRows() - 1));
        }
    }

    @Test
    public void testBorderedSmallGridCreation() {
        final FixedGridImpl smallGrid = new MapBuilder(MapSize.SMALL.getHeight(), MapSize.SMALL.getWidth()).addFixedBordersWithPortals()
                                                                                                                    .getGrid();
        final int centerY = (smallGrid.getRows() - 1) / 2;
        for (int y = 1; y < smallGrid.getRows() - 1; y++) {
            if (y != centerY) {
                assertEquals(WALL, smallGrid.getEntity(0, y));
                assertEquals(WALL, smallGrid.getEntity(smallGrid.getColumns() - 1, y));
            }
        }
        for (int x = 0; x < smallGrid.getColumns(); x++) {
            assertEquals(EntityType.WALL, smallGrid.getEntity(x, 0));
            assertEquals(EntityType.WALL, smallGrid.getEntity(x, smallGrid.getRows() - 1));
        }
    }

    @Test
    public void testBorderedLargeGridCreation() {
        final FixedGridImpl largeGrid = new MapBuilder(MapSize.LARGE.getHeight(), MapSize.LARGE.getWidth()).addFixedBordersWithPortals()
                                                                                                                    .getGrid();
        final int centerY = (largeGrid.getRows() - 1) / 2;
        for (int y = 1; y < largeGrid.getRows() - 1; y++) {
            if (y != centerY) {
                assertEquals(WALL, largeGrid.getEntity(0, y));
                assertEquals(WALL, largeGrid.getEntity(largeGrid.getColumns() - 1, y));
            }
        }
        for (int x = 0; x < largeGrid.getColumns(); x++) {
            assertEquals(EntityType.WALL, largeGrid.getEntity(x, 0));
            assertEquals(EntityType.WALL, largeGrid.getEntity(x, largeGrid.getRows() - 1));
        }
    }

    @Test
    public void testCentralizedStandardGridCreation() {
        final FixedGridImpl standardGrid = new MapBuilder(MapSize.STANDARD.getHeight(), MapSize.STANDARD.getWidth()).addFixedBordersWithPortals()
                                                                                                                    .addFixedCenter()
                                                                                                                    .getGrid();
        final int centerX = (standardGrid.getColumns() - 1) / 2;
        final int centerY = (standardGrid.getRows() - 1) / 2;
        final Position pacmanInitialPos = new Position(centerX, centerY + 2);
        final Position pinkyInitialPos = new Position(centerX, centerY - 1);
        final Position blinkyInitialPos = new Position(centerX, centerY);
        final Position inkyInitialPos = new Position(centerX - 1, centerY);
        final Position clydeInitialPos = new Position(centerX + 1, centerY);
        assertEquals(EntityType.PACMAN, standardGrid.getEntity(pacmanInitialPos.getX(), pacmanInitialPos.getY()));
        assertEquals(EntityType.BLINKY, standardGrid.getEntity(blinkyInitialPos.getX(), blinkyInitialPos.getY()));
        assertEquals(EntityType.PINKY, standardGrid.getEntity(pinkyInitialPos.getX(), pinkyInitialPos.getY()));
        assertEquals(EntityType.INKY, standardGrid.getEntity(inkyInitialPos.getX(), inkyInitialPos.getY()));
        assertEquals(EntityType.CLYDE, standardGrid.getEntity(clydeInitialPos.getX(), clydeInitialPos.getY()));
        assertNotEquals(EntityType.WALL, standardGrid.getEntity(pacmanInitialPos.getX() - 1, pacmanInitialPos.getY()));
        assertNotEquals(EntityType.WALL, standardGrid.getEntity(pacmanInitialPos.getX() + 1, pacmanInitialPos.getY()));
        assertNotEquals(EntityType.WALL, standardGrid.getEntity(blinkyInitialPos.getX(), blinkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, standardGrid.getEntity(pinkyInitialPos.getX(), pinkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, standardGrid.getEntity(inkyInitialPos.getX(), inkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, standardGrid.getEntity(clydeInitialPos.getX(), clydeInitialPos.getY() - 1));
    }

    @Test
    public void testCentralizedSmallGridCreation() {
        final FixedGridImpl smallGrid = new MapBuilder(MapSize.SMALL.getHeight(), MapSize.SMALL.getWidth()).addFixedBordersWithPortals()
                                                                                                                    .addFixedCenter()
                                                                                                                    .getGrid();
        final int centerX = (smallGrid.getColumns() - 1) / 2;
        final int centerY = (smallGrid.getRows() - 1) / 2;
        final Position pacmanInitialPos = new Position(centerX, centerY + 2);
        final Position pinkyInitialPos = new Position(centerX, centerY - 1);
        final Position blinkyInitialPos = new Position(centerX, centerY);
        final Position inkyInitialPos = new Position(centerX - 1, centerY);
        final Position clydeInitialPos = new Position(centerX + 1, centerY);
        assertEquals(EntityType.PACMAN, smallGrid.getEntity(pacmanInitialPos.getX(), pacmanInitialPos.getY()));
        assertEquals(EntityType.BLINKY, smallGrid.getEntity(blinkyInitialPos.getX(), blinkyInitialPos.getY()));
        assertEquals(EntityType.PINKY, smallGrid.getEntity(pinkyInitialPos.getX(), pinkyInitialPos.getY()));
        assertEquals(EntityType.INKY, smallGrid.getEntity(inkyInitialPos.getX(), inkyInitialPos.getY()));
        assertEquals(EntityType.CLYDE, smallGrid.getEntity(clydeInitialPos.getX(), clydeInitialPos.getY()));
        assertNotEquals(EntityType.WALL, smallGrid.getEntity(pacmanInitialPos.getX() - 1, pacmanInitialPos.getY()));
        assertNotEquals(EntityType.WALL, smallGrid.getEntity(pacmanInitialPos.getX() + 1, pacmanInitialPos.getY()));
        assertNotEquals(EntityType.WALL, smallGrid.getEntity(blinkyInitialPos.getX(), blinkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, smallGrid.getEntity(pinkyInitialPos.getX(), pinkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, smallGrid.getEntity(inkyInitialPos.getX(), inkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, smallGrid.getEntity(clydeInitialPos.getX(), clydeInitialPos.getY() - 1));
    }

    @Test
    public void testCentralizedLargeGridCreation() {
        final FixedGridImpl largeGrid = new MapBuilder(MapSize.LARGE.getHeight(), MapSize.LARGE.getWidth()).addFixedBordersWithPortals()
                                                                                                                    .addFixedCenter()
                                                                                                                    .getGrid();
        final int centerX = (largeGrid.getColumns() - 1) / 2;
        final int centerY = (largeGrid.getRows() - 1) / 2;
        final Position pacmanInitialPos = new Position(centerX, centerY + 2);
        final Position pinkyInitialPos = new Position(centerX, centerY - 1);
        final Position blinkyInitialPos = new Position(centerX, centerY);
        final Position inkyInitialPos = new Position(centerX - 1, centerY);
        final Position clydeInitialPos = new Position(centerX + 1, centerY);
        assertEquals(EntityType.PACMAN, largeGrid.getEntity(pacmanInitialPos.getX(), pacmanInitialPos.getY()));
        assertEquals(EntityType.BLINKY, largeGrid.getEntity(blinkyInitialPos.getX(), blinkyInitialPos.getY()));
        assertEquals(EntityType.PINKY, largeGrid.getEntity(pinkyInitialPos.getX(), pinkyInitialPos.getY()));
        assertEquals(EntityType.INKY, largeGrid.getEntity(inkyInitialPos.getX(), inkyInitialPos.getY()));
        assertEquals(EntityType.CLYDE, largeGrid.getEntity(clydeInitialPos.getX(), clydeInitialPos.getY()));
        assertNotEquals(EntityType.WALL, largeGrid.getEntity(pacmanInitialPos.getX() - 1, pacmanInitialPos.getY()));
        assertNotEquals(EntityType.WALL, largeGrid.getEntity(pacmanInitialPos.getX() + 1, pacmanInitialPos.getY()));
        assertNotEquals(EntityType.WALL, largeGrid.getEntity(blinkyInitialPos.getX(), blinkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, largeGrid.getEntity(pinkyInitialPos.getX(), pinkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, largeGrid.getEntity(inkyInitialPos.getX(), inkyInitialPos.getY() - 1));
        assertNotEquals(EntityType.WALL, largeGrid.getEntity(clydeInitialPos.getX(), clydeInitialPos.getY() - 1));
    }

}
