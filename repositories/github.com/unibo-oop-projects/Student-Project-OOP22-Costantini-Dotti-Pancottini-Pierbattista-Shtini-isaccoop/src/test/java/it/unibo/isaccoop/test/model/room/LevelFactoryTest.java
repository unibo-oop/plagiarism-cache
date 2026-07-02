package it.unibo.isaccoop.test.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.core.GameEngineImpl;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.room.Level;
import it.unibo.isaccoop.model.room.LevelFactory;
import it.unibo.isaccoop.model.room.LevelFactoryImpl;
import it.unibo.isaccoop.model.room.Room;

/**
 * {@link LevelFactory} test.
 * This test is meant to check if a level is built correctly using {@link LevelFactory}, that is, 
 * if {@link Player}, {@link Item}, {@link Enemy} and {@link PowerUp} are placed correctly 
 * along the {@link Level}
 */
class LevelFactoryTest {

    private static final int MAX_NUMBER_OF_ROOMS = 30;
    private static final int MIN_NUMBER_OF_ROOMS = 6;
    private static final int SINGLE_ROOM_COUNT = 1;
    private static final int NON_STANDARD_ROOM_COUNT = 4;
    private static final int POWERUP_COUNT_IN_SHOP = 3;
    private static final int POWERUP_COUNT_IN_TREASURE_ROOM = 1;
    private static final int BOSS_COUNT = 1;

    private final LevelFactory lvlFactory = new LevelFactoryImpl(new GameEngineImpl());
    private Level lvl;
    private List<Room> rooms;

    @BeforeEach
    void setUp() {
        this.lvl = this.lvlFactory.createLevel();
        this.rooms = lvl.getRooms();
    }

    @Test 
    void testRoomCreation() {
        // check if a just created level has the correct number of rooms
        assertTrue(this.rooms.size() < MAX_NUMBER_OF_ROOMS && this.rooms.size() >= MIN_NUMBER_OF_ROOMS);
        // and if it is not complete
        assertFalse(this.lvl.isLevelComplete());
    }

    @Test
    void testRoomTypeCount() {
        // in a level, there must be ONLY ONE START, ONE SHOP, ONE TREASURE and ONE BOSS room
        assertEquals(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.START)
                .count(), SINGLE_ROOM_COUNT);
        assertEquals(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.SHOP)
                .count(), SINGLE_ROOM_COUNT);
        assertEquals(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.TREASURE)
                .count(), SINGLE_ROOM_COUNT);
        assertEquals(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.BOSS)
                .count(), SINGLE_ROOM_COUNT);

        // in a level, the remaining rooms must be STANDARD
        assertEquals(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.STANDARD)
                .count(), this.rooms.size() - NON_STANDARD_ROOM_COUNT);
    }

    @Test 
    void testPlayerLocationInLevel() {
        // when a level is created, the player must be in the START room ONLY

        // considering only the START rooms (there will be only one), and in that one
        // where will be the player
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.START)
                .allMatch(r -> r.getPlayer().isPresent()));

        // considering the other rooms, the player will not be in any of them
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() != RoomType.START)
                .allMatch(r -> r.getPlayer().isEmpty()));
    }

    @Test
    void testPowerUpInTreasureRoom() {
        // when a level is created, there must be powerups only in TREASURE and SHOP rooms
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.TREASURE)
                .allMatch(r -> r.getPowerUps().isPresent()));

        // in the treasure room there will be exactly one powerup
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.TREASURE)
                .allMatch(r -> r.getPowerUps().get().size() == POWERUP_COUNT_IN_TREASURE_ROOM));
    }

    @Test
    void testPowerUpInShopRoom() {
        // when a level is created, there must be powerups only in TREASURE and SHOP rooms
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.SHOP)
                .allMatch(r -> r.getPowerUps().isPresent()));

        // in the shop room there will be exactly 3 powerups
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.SHOP)
                .allMatch(r -> r.getPowerUps().get().size() == POWERUP_COUNT_IN_SHOP));
    }

    @Test
    void testPowerUpInOtherRooms() {
        // when a level is created, there must be powerups only in TREASURE and SHOP rooms
        // so, in the other rooms there must be NO items
        assertTrue(this.rooms.stream()
                .filter(r -> !checkConditionForPowerUp(r))
                .allMatch(r -> r.getPowerUps().isEmpty()));
    }

    @Test
    void testItemsInRooms() {
        // when a level is created, there must be at least one item ONLY in STANDARD rooms
        // so, only STANDARD rooms must have an item list 
        assertTrue(this.rooms.stream()
                .filter(r -> r.getItems().isPresent())
                .allMatch(r -> r.getRoomType() == RoomType.STANDARD));

        // all other room types must NOT have an item list
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomAI().isEmpty())
                .allMatch(r -> r.getRoomType() != RoomType.STANDARD));
    }

    @Test
    void testRoomAiInRooms() {
        // when a level is created, there must be an AIenemy only in BOSS and STANDARD rooms
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomAI().isPresent())
                .allMatch(r -> checkConditionForAiRoom(r)));

        // all other room types must NOT have an AIenemy
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomAI().isEmpty())
                .allMatch(r -> !checkConditionForAiRoom(r)));
    }

    @Test
    void testRoomStatus() {
        // when a level is created, BOSS and STANDARD must be NOT complete
        // (the player must defeat all enemies to complete a room)
        assertTrue(this.rooms.stream()
                .filter(r -> checkConditionForAiRoom(r))
                .allMatch(r -> !r.isComplete()));

        // the other rooms are already complete, because there are no enemies
        assertTrue(this.rooms.stream()
                .filter(r -> !checkConditionForAiRoom(r))
                .allMatch(r -> r.isComplete()));
    }

    @Test
    void testEnemiesInBossRoom() {
        // in BOSS room, there must be only ONE enemy (the boss itself)
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.BOSS)
                .allMatch(r -> r.getEnemies().get().size() == BOSS_COUNT));
    }

    @Test
    void testEnemiesInStandardRoom() {
        // in STANDARD room, there must be at least one enemy
        assertTrue(this.rooms.stream()
                .filter(r -> r.getRoomType() == RoomType.STANDARD)
                .allMatch(r -> r.getEnemies().get().size() > 0));
    }

    @Test
    void testEnemiesInOtherRooms() {
        // in the other rooms, there must be NO enemies
        assertTrue(this.rooms.stream()
                .filter(r -> !checkConditionForAiRoom(r))
                .allMatch(r -> r.getEnemies().isEmpty()));
    }

    /**
     * Check if the current room needs the AiEnemy object.
     * @param room the considered room
     * @return true if the room needs the AiEnemy object, false otherwise
     */
    private boolean checkConditionForAiRoom(final Room room) {
        return room.getRoomType() == RoomType.STANDARD || room.getRoomType() == RoomType.BOSS;
    }

    /**
     * Check if the current room needs powerups.
     * @param room the considered room
     * @return true if the room needs at least one powerup object, false otherwise
     */
    private boolean checkConditionForPowerUp(final Room room) {
        return room.getRoomType() == RoomType.SHOP || room.getRoomType() == RoomType.TREASURE;
    }
}
