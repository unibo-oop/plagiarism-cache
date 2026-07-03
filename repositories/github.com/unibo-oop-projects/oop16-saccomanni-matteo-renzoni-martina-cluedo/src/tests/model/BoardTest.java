package tests.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.board.Board;
import model.board.BoardFactory;
import model.board.Cell;
import model.board.DoorCell;
import model.board.TrapDoorCell;
import utilities.enumerations.BoardType;
import utilities.enumerations.CellType;
import utilities.enumerations.RoomCard;

/**
 * Tests the correctness of the board. This class has to achieve success in all
 * its tests.
 */
public class BoardTest {

    private Board board;

    /**
     * Creates a BoardTest instance.
     */
    public BoardTest() {
        try {
            this.board = new BoardFactory(BoardType.DEFAULT_BOARD).createBoard();
        } catch (IOException e) {
            fail("Board creation failed: " + e.getMessage());
        }
    }

    /**
     * Tests all rooms are present.
     */
    @Test
    public void testAllRoomsPresent() {
        assertTrue(this.board.getRooms().containsAll(RoomCard.getRoomCards()));
    }

    /**
     * Checks that there aren't duplicate cells (cells with the same coordinates
     * in different rooms).
     */
    @Test
    public void testDuplicateCells() {
        int count = 0;
        final Set<Cell> cells = new HashSet<>();
        for (final RoomCard r : this.board.getRooms()) {
            count += this.board.getRoomCells(r).size();
            cells.addAll(this.board.getRoomCells(r));
        }
        cells.addAll(this.board.getHallwayCells());
        count += this.board.getHallwayCells().size();
        assertEquals(count, cells.size());
    }

    /**
     * Tests that that every room has one door that leads to an adjacent cell in
     * the hallway.
     */
    @Test
    public void testDoors() {
        this.board.getRooms().forEach(r -> {
            final DoorCell door = this.board.getDoor(r);
            assertNotNull(door);
            assertEquals(door.getRoom().get(), r);
            assertEquals(door.getType(), CellType.DOOR);
            assertFalse(door.getDestination().getRoom().isPresent());
            assertTrue(checkContiguity(door, door.getDestination()));
        });
    }

    /**
     * Tests that every trap-door leads to another another room with a
     * trap-door.
     */
    @Test
    public void testTrapDoors() {
        this.board.getRooms().forEach(r -> {
            if (this.board.getTrapDoor(r).isPresent()) {
                final TrapDoorCell trap = this.board.getTrapDoor(r).get();
                assertEquals(trap.getRoom().get(), r);
                assertEquals(trap.getType(), CellType.TRAP_DOOR);
                assertFalse(trap.getDestinationRoom().equals(trap.getRoom()));
                assertTrue(this.board.getTrapDoor(trap.getDestinationRoom()).isPresent());
                assertTrue(this.board.getTrapDoor(trap.getDestinationRoom()).get().getDestinationRoom().equals(r));
            }
        });
    }

    /**
     * Tests that each room is made up of adjacent cells.
     */
    @Test
    public void testRoomsStructure() {
        this.board.getRooms().forEach(r -> {
            assertTrue(checkRoomStructure(this.board.getRoomCells(r)));
        });
        assertTrue(checkRoomStructure(this.board.getHallwayCells()));
    }

    /**
     * Checks that all cells respect the size of the board.
     */
    @Test
    public void testDimensions() {
        final int height = this.board.getHeight();
        final int width = this.board.getWidth();
        for (final RoomCard r : this.board.getRooms()) {
            this.board.getRoomCells(r).forEach(
                    cell -> assertTrue(cell.getPosition().getX() < height && cell.getPosition().getY() < width));
        }
        this.board.getHallwayCells()
                .forEach(cell -> assertTrue(cell.getPosition().getX() < height && cell.getPosition().getY() < width));
    }

    /**
     * Checks if 2 cells are adjacent.
     * 
     * @param first
     *            the first cell
     * @param second
     *            the second cell
     * @return true if adjacent, false otherwise
     */
    private boolean checkContiguity(final Cell first, final Cell second) {
        final int x1 = first.getPosition().getX();
        final int y1 = first.getPosition().getY();
        final int x2 = second.getPosition().getX();
        final int y2 = second.getPosition().getY();
        return ((x1 == x2 && (y2 == y1 + 1 || y2 == y1 - 1)) || (y1 == y2 && (x2 == x1 + 1 || x2 == x1 - 1)));
    }

    /**
     * Checks that all the cells of a room are adjacent.
     * 
     * @param cells
     *            the cells of the room
     */
    private boolean checkRoomStructure(final Set<Cell> cells) {
        final List<Cell> room = new ArrayList<>(cells);
        final List<Boolean> ok = new ArrayList<Boolean>(Arrays.asList(new Boolean[room.size()]));
        Collections.fill(ok, Boolean.FALSE);
        boolean modified;
        ok.set(0, true);
        do {
            modified = false;
            for (int i = 0; i < room.size(); i++) {
                if (ok.get(i)) {
                    for (int j = 0; j < room.size(); j++) {
                        if (!ok.get(j) && checkContiguity(room.get(i), room.get(j))) {
                            ok.set(j, true);
                            modified = true;
                        }
                    }
                }
            }
        } while (modified);
        return !ok.contains(false);
    }
}