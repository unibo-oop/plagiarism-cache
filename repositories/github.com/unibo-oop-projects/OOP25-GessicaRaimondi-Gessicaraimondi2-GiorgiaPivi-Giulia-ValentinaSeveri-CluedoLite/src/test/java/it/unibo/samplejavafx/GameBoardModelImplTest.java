package it.unibo.samplejavafx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import it.unibo.cluedolite.model.gameboard.api.Room;
import it.unibo.cluedolite.model.gameboard.impl.GameBoardModelImpl;
import it.unibo.cluedolite.model.gameboard.impl.RoomImpl;
import it.unibo.cluedolite.model.player.impl.PlayerImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link GameBoardModelImpl}.
 */
final class GameBoardModelImplTest {

    private static final int EXPECTED_SIZE = 9;
    private static final String FAKE_ROOM = "FakeRoom";
    private GameBoardModelImpl board;
    private PlayerImpl player;

    @BeforeEach
    void setUp() {
        board = new GameBoardModelImpl();
        player = new PlayerImpl("Scarlett");
    }

    @Test
    void testGetRoomsReturnsAllNineRooms() {
        assertEquals(EXPECTED_SIZE, board.getRooms().size());
    }

    @Test
    void testGetRoomsReturnsUnmodifiableCopy() {
        assertThrows(UnsupportedOperationException.class,
                () -> board.getRooms().add(new RoomImpl(FAKE_ROOM)));
    }

    @Test
    void testGetRoomByName() {
        final Room kitchen = board.getRoomByName("Kitchen");
        assertNotNull(kitchen);
        assertEquals("Kitchen", kitchen.getName());
    }

    @Test
    void testGetRoomByNameCaseInsensitive() {
        final Room kitchen = board.getRoomByName("kitchen");
        assertNotNull(kitchen);
    }

    @Test
    void testGetRoomByNameNotFound() {
        assertNull(board.getRoomByName(FAKE_ROOM));
    }

    @Test
    void testSetAndGetPlayerPosition() {
        final Room kitchen = board.getRooms().get(0);
        board.setPlayerPosition(player, kitchen);
        assertEquals(kitchen, board.getPlayerPosition(player));
    }

    @Test
    void testGetPlayerPositionNullAtStart() {
        assertNull(board.getPlayerPosition(player));
    }

    @Test
    void testCircularAdjacencyFirstAndLast() {
        final Room first = board.getRooms().get(0);
        final Room last = board.getRooms().get(EXPECTED_SIZE - 1);
        assertTrue(first.getAdjacent().contains(last));
        assertTrue(last.getAdjacent().contains(first));
    }

    @Test
    void testAdjacencyBetweenConsecutiveRooms() {
        final Room r1 = board.getRooms().get(0);
        final Room r2 = board.getRooms().get(1);
        assertTrue(r1.getAdjacent().contains(r2));
        assertTrue(r2.getAdjacent().contains(r1));
    }

    @Test
    void testNoAdjacencyBetweenNonConsecutiveRooms() {
        final Room r1 = board.getRooms().get(0);
        final Room r3 = board.getRooms().get(3);
        assertFalse(r1.getAdjacent().contains(r3));
    }

    @Test
    void testAdjacentListIsUnmodifiable() {
        final Room kitchen = board.getRooms().get(0);
        assertThrows(UnsupportedOperationException.class,
                () -> kitchen.getAdjacent().add(new RoomImpl(FAKE_ROOM)));
    }
}
