package it.unibo.isaccoop.test.model.room;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.room.Room;
import it.unibo.isaccoop.model.room.RoomFactory;
import it.unibo.isaccoop.model.room.RoomFactoryImpl;

/**
 * {@link RoomFactory} test.
 * This test is meant for checking if the methods in {@link RoomFactory} build correctly 
 * the requested rooms. To achieve so, this test checks if all fields in a {@link Room} are set
 * properly, depending on its {@link RoomType}
 */
class RoomFactoryTest {

    // just for testing purposes
    private static final int MAX_COORD_VALUE = 30; 
    private static final int NUMBER_OF_ROOMS = 10;

    private final RoomFactory rFactory = new RoomFactoryImpl(NUMBER_OF_ROOMS);
    private Point2D coord;
    private final List<Room> list = new LinkedList<>();

    @BeforeEach
    void setUp() {
        this.coord = new Point2D(ThreadLocalRandom.current().nextInt(MAX_COORD_VALUE), 
                ThreadLocalRandom.current().nextInt(MAX_COORD_VALUE));

        // creates a list of rooms, that will be checked in the following tests
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            assertDoesNotThrow(() -> this.list.add(this.rFactory.buildRoomInProperOrder(this.coord)));
        }

        // TREASURE, SHOP AND STANDARD rooms can be created directly with their methods: 
        // they can be at any position inside a level
        // BOSS and START cannot be created directly with their methods: 
        // the START room must always be the first, the BOSS room must always be the last
    }

    @Test 
    void testBuildBossRoom() {
        final Room bossRoom = this.list.stream()
                .filter(r -> r.getRoomType() == RoomType.BOSS)
                .findFirst().get();

        // check if all fields are set properly
        assertEquals(bossRoom.getCoords(), this.coord);
        assertTrue(bossRoom.getEnemies().isPresent());
        assertTrue(bossRoom.getItems().isEmpty());
        assertTrue(bossRoom.getPlayer().isEmpty());
        assertTrue(bossRoom.getRoomAI().isPresent());
        assertTrue(bossRoom.getPowerUps().isEmpty());
        assertEquals(bossRoom.getRoomType(), RoomType.BOSS);
        assertFalse(bossRoom.isComplete());
    }

    @Test
    void testBuildStandardRoom() {
        final Room standardRoom = this.list.stream()
                .filter(r -> r.getRoomType() == RoomType.STANDARD)
                .findFirst().get();

        // check if all fields are set properly
        assertEquals(standardRoom.getCoords(), this.coord);
        assertTrue(standardRoom.getEnemies().isPresent());
        assertTrue(standardRoom.getItems().isPresent());
        assertTrue(standardRoom.getPlayer().isEmpty());
        assertTrue(standardRoom.getRoomAI().isPresent());
        assertTrue(standardRoom.getPowerUps().isEmpty());
        assertEquals(standardRoom.getRoomType(), RoomType.STANDARD);
        assertFalse(standardRoom.isComplete());
    }

    @Test
    void testBuildShopRoom() {
        final Room shopRoom = this.list.stream()
                .filter(r -> r.getRoomType() == RoomType.SHOP)
                .findFirst().get();

        // check if all fields are set properly
        assertEquals(shopRoom.getCoords(), this.coord);
        assertTrue(shopRoom.getEnemies().isEmpty());
        assertTrue(shopRoom.getItems().isEmpty());
        assertTrue(shopRoom.getPlayer().isEmpty());
        assertTrue(shopRoom.getRoomAI().isEmpty());
        assertTrue(shopRoom.getPowerUps().isPresent());
        assertEquals(shopRoom.getRoomType(), RoomType.SHOP);
        assertTrue(shopRoom.isComplete());
    }

    @Test
    void testBuildStartRoom() {
        final Room startRoom = this.list.stream()
                .filter(r -> r.getRoomType() == RoomType.START)
                .findFirst().get();

        // check if all fields are set properly
        assertEquals(startRoom.getCoords(), this.coord);
        assertTrue(startRoom.getEnemies().isEmpty());
        assertTrue(startRoom.getItems().isEmpty());
        assertTrue(startRoom.getPlayer().isEmpty());
        assertTrue(startRoom.getRoomAI().isEmpty());
        assertTrue(startRoom.getPowerUps().isEmpty());
        assertEquals(startRoom.getRoomType(), RoomType.START);
        assertTrue(startRoom.isComplete());
    }

    @Test
    void testBuildTreasureRoom() {
        final Room treasureRoom = this.list.stream()
                .filter(r -> r.getRoomType() == RoomType.TREASURE)
                .findFirst().get();

        // check if all fields are set properly
        assertEquals(treasureRoom.getCoords(), this.coord);
        assertTrue(treasureRoom.getEnemies().isEmpty());
        assertTrue(treasureRoom.getItems().isEmpty());
        assertTrue(treasureRoom.getPlayer().isEmpty());
        assertTrue(treasureRoom.getRoomAI().isEmpty());
        assertTrue(treasureRoom.getPowerUps().isPresent());
        assertEquals(treasureRoom.getRoomType(), RoomType.TREASURE);
        assertTrue(treasureRoom.isComplete());
    }
}
