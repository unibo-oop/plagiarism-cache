package model.match;

import org.junit.jupiter.api.Test;

import model.enums.Orientation;
import model.enums.ShipType;
import model.util.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

public class TestPlayGroundBattle {

    private static final int COLUMNS = 10;
    private static final int LINES = 10;
    private static final int FIRST = 0;
    private static final int CELLS_USED = 2;

    // private static final Ship SHIP_SIZE_ONE = new Ship(ShipType.BATTLESHIP);
    private static final Ship SHIP_SIZE_TWO = new Ship(ShipType.DESTROYER);
    private static final Ship SHIP_SIZE_THREE = new Ship(ShipType.CRUISER);
    private static final Ship SHIP_SIZE_FOUR = new Ship(ShipType.BATTLESHIP);
    private static final Ship SHIP_SIZE_FIVE = new Ship(ShipType.CARRIER);

    @Test
    public void testCreationPlayground() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final List<List<Boolean>> playground = playgroundBattle.getLogicGrid();

        playground.stream().forEach(x -> x.stream().forEach(y -> assertFalse(y)));
        System.out.println(playground);
        assertEquals(LINES, playground.size());
        assertEquals(COLUMNS, playground.get(FIRST).size());
    }

    @Test
    public void testInsertShip() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);

        final Pair<Integer, Integer> firstShipPosition = new Pair<>(0, 0);

        final List<Pair<Integer, Integer>> cells = Orientation.HORIZONTAL.cellsUsedList(new Pair<Integer, Integer>(0, 0),
                CELLS_USED);

        try {
            playgroundBattle.positionShip(SHIP_SIZE_THREE, firstShipPosition);
        } catch (CellsFilledException e) {
            fail(e.toString());
        }

        playgroundBattle.getLogicGrid().forEach(e -> System.out.println(e.toString()));

        System.out.println("");

        cells.forEach(cell -> assertTrue(playgroundBattle.isCellUsed(cell)));
        cells.forEach(cell -> assertTrue(playgroundBattle.isCellUsedByShip(cell)));
    }

    @Test
    public void testInsertMultipleShip() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);

        final Pair<Integer, Integer> firstShipPosition = new Pair<>(0, 0);

        final List<Pair<Integer, Integer>> cells = Orientation.HORIZONTAL.cellsUsedList(new Pair<Integer, Integer>(0, 0),
                CELLS_USED);

        try {
            playgroundBattle.positionShip(SHIP_SIZE_THREE, firstShipPosition);
        } catch (CellsFilledException e) {
            fail(e.toString());
        }

        try {
            playgroundBattle.positionShip(SHIP_SIZE_THREE, firstShipPosition);
            fail();
        } catch (CellsFilledException e) {
            for (int i = 0; i < cells.size(); i++) {
                assertEquals(cells.get(i), e.getCellsUsed().get(i));
            }
        }

    }

    @Test
    public void testRemoveShip() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final Pair<Integer, Integer> firstShipPosition = new Pair<>(0, 0);
        final List<Pair<Integer, Integer>> cells = Orientation.HORIZONTAL.cellsUsedList(firstShipPosition,
                SHIP_SIZE_THREE.getSize());

        for (int j = 0; j < cells.size(); j++) {

            try {
                playgroundBattle.positionShip(SHIP_SIZE_THREE, firstShipPosition);
            } catch (CellsFilledException e) {
                fail(e.toString());
            }

            playgroundBattle.removeShipWithCell(new Pair<>(0, j));
            assertFalse(playgroundBattle.getShips().containsKey(cells));
        }

        // playgroundBattle.getLogicGrid().forEach(i -> System.out.println(i));

        // System.out.println(playgroundBattle.getShips().toString());
        // System.out.println("");
    }

    @Test
    public void testShot() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final Pair<Integer, Integer> firstShipPosition = new Pair<>(0, 0);
        final Pair<Integer, Integer> notOccupiedPosition = new Pair<>(1, 1);
        final List<Pair<Integer, Integer>> cells = Orientation.HORIZONTAL.cellsUsedList(firstShipPosition,
                SHIP_SIZE_THREE.getSize());

        try {
            playgroundBattle.positionShip(SHIP_SIZE_THREE, firstShipPosition);
        } catch (CellsFilledException e) {
            fail(e.toString());
        }

        playgroundBattle.resetLogicGrid();

        for (int i = 0; i < cells.size(); i++) {
            final Pair<Integer, Integer> cellToShot = new Pair<>(0, i);
            try {
                playgroundBattle.shipHitted(cellToShot);
            } catch (CellAlreadyShotException e) {
                fail(e.toString());
            }

            try {
                playgroundBattle.shipHitted(cellToShot);
                fail();
            } catch (CellAlreadyShotException e) {
                assertTrue(e.getCellsUsed().contains(cellToShot));
            }
        }

        playgroundBattle.resetLogicGrid();

        try {
            Optional<Entry<List<Pair<Integer, Integer>>, Ship>> pairCellsShipHitted = playgroundBattle.shipHitted(firstShipPosition);
            assertEquals(pairCellsShipHitted.get().getKey(), cells);
            assertEquals(pairCellsShipHitted.get().getValue(), SHIP_SIZE_THREE);
        } catch (CellAlreadyShotException e) {
            fail(e.toString());
        }

        playgroundBattle.resetLogicGrid();

        try {
            Optional<Entry<List<Pair<Integer, Integer>>, Ship>> pairCellsShipHitted = playgroundBattle.shipHitted(notOccupiedPosition);
            assertEquals(pairCellsShipHitted, Optional.empty());
        } catch (CellAlreadyShotException e) {
            fail(e.toString());
        }

    }

    @Test
    public void testSunkAndAlive() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final Pair<Integer, Integer> firstShipPosition = new Pair<>(0, 0);
        final Pair<Integer, Integer> notOccupiedPosition = new Pair<>(1, 1);

        final List<Pair<Integer, Integer>> cells = Orientation.HORIZONTAL.cellsUsedList(firstShipPosition,
                SHIP_SIZE_THREE.getSize());

        final List<Pair<Integer, Integer>> cellsNotOccupied = Orientation.HORIZONTAL.cellsUsedList(notOccupiedPosition,
                SHIP_SIZE_THREE.getSize());

        try {
            playgroundBattle.positionShip(new Ship(ShipType.CRUISER), firstShipPosition);
        } catch (CellsFilledException e) {
            fail(e.toString());
        }

        playgroundBattle.resetLogicGrid();

        for (int i = 0; i < cells.size(); i++) {
            final Pair<Integer, Integer> cellToShot = new Pair<>(0, i);
            try {
                assertTrue(playgroundBattle.getDamage() == i);
                assertEquals(playgroundBattle.shipSunk(cells), Optional.of(false));
                assertEquals(playgroundBattle.shipSunk(cellsNotOccupied), Optional.empty());
                playgroundBattle.shipHitted(cellToShot);
            } catch (CellAlreadyShotException e) {
                e.printStackTrace();
            }

        }

        assertTrue(playgroundBattle.getNumberOfAliveShip() == 0);
    }

}
