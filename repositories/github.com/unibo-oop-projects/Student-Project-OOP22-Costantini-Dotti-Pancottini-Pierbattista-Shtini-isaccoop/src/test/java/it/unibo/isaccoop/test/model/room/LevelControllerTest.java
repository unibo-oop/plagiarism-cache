package it.unibo.isaccoop.test.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.core.GameEngineImpl;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.room.LevelController;
import it.unibo.isaccoop.model.room.LevelControllerImpl;
import it.unibo.isaccoop.model.room.Room;

/**
 * {@link LevelController} test.
 * This test is meant to check if the {@link LevelController} methods work properly.
 */
class LevelControllerTest {

    private static final int NUMBER_OF_LEVELS = 10;
    private static final int MAX_NUMBER_OF_ROOMS = 30;
    private static final int MIN_NUMBER_OF_ROOMS = 6;
    private final LevelController lvlController = new LevelControllerImpl(NUMBER_OF_LEVELS, new GameEngineImpl());

    @Test 
    void testGetCurrentLevelIndex() {
        // just after the creation of the levelController, the current level must be the first
        assertEquals(this.lvlController.getCurrentLevelIndex(), 0);
    }

    @Test 
    void testGetNumberOfLevels() {
        // check that the level controller has the correct number of levels
        assertEquals(this.lvlController.getNumberOfLevels(), NUMBER_OF_LEVELS);
    }

    @Test
    void testGetNumberOfRoomsOfCurrentLevel() {
        // the level has a random number of rooms N, with 6 <= N < 30
        assertTrue(this.lvlController.getCurrentLevel().getRooms().size() < MAX_NUMBER_OF_ROOMS 
                && this.lvlController.getCurrentLevel().getRooms().size() >= MIN_NUMBER_OF_ROOMS);
    }

    @Test
    void testGetCurrentRoomCoord() {
        // at the beginning, the player is in room (0.0, 0.0)
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(0.0, 0.0));
    }

    @Test
    void testGetCurrentRoom() {
        // the current room is the room where the player is, i.e., the room where getPlayer() 
        // returns a NON empty optional
        final Room playerRoom = this.lvlController.getCurrentLevel().getRooms().stream()
                .filter(r -> r.getPlayer().isPresent())
                .findFirst().get();

        // so playerRoom must be equal to the room returned by getCurrentRoom()
        assertEquals(playerRoom, this.lvlController.getCurrentRoom());
    }

    @Test
    void testIsRoomComplete() {
        // at the beginning, STANDARD and BOSS rooms must be NOT complete
        // (there are enemies to defeat)
        final List<Room> roomsThatMustBeNOTComplete = this.lvlController.getRoomsOfCurrentLevel().stream()
                .filter(r -> r.getRoomType() == RoomType.STANDARD && r.getRoomType() == RoomType.BOSS)
                .collect(Collectors.toUnmodifiableList());
        assertTrue(roomsThatMustBeNOTComplete.stream().allMatch(r -> this.lvlController.isRoomComplete(r)));

        // while other rooms must be complete (there are no enemies to defeat)
        final List<Room> roomsThatMustBeComplete = this.lvlController.getRoomsOfCurrentLevel().stream()
                .filter(r -> r.getRoomType() != RoomType.STANDARD && r.getRoomType() != RoomType.BOSS)
                .collect(Collectors.toUnmodifiableList());
        assertTrue(roomsThatMustBeComplete.stream().allMatch(r -> this.lvlController.isRoomComplete(r)));
    }

    @Test
    void testIsCurrentLevelComplete() {
        // at the beginning, a level cannot be complete
        assertFalse(this.lvlController.isCurrentLevelComplete());
    }

    @Test
    void testAreAllLevelsComplete() {
        // at the beginning, all levels cannot be complete
        assertFalse(this.lvlController.areAllLevelsComplete());
    }

    @Test 
    void testMoveToPreviousRoom() {
        // the player is in room (0.0, 0.0), so there is not a previous room
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(0.0, 0.0));
        // the player does not move, because there is not a previous room
        this.lvlController.getCurrentLevel().moveToPreviousRoom(); 
        // so the player is still in room (0.0, 0.0)
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(0.0, 0.0));
    }

    @Test 
    void testMoveToNextRoom() {
        // the player is in room (0.0, 0.0), so the next room is room with coord (1.0, 0.0)
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(0.0, 0.0));
        // the player moves to next room
        this.lvlController.getCurrentLevel().moveToNextRoom();
        // so the player is now in room (1.0, 0.0)
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(1.0, 0.0));
    }

    @Test
    void testMoveUsingGetPreviousAndGetNextRoom() {
        // execute again this test to check that the player is actually in room (0.0, 0.0)
        testMoveToPreviousRoom();

        // if player moves to room (1.0, 0.0)
        this.lvlController.getCurrentLevel().moveToNextRoom();
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(1.0, 0.0));

        // and then to room (2.0, 0.0)
        this.lvlController.getCurrentLevel().moveToNextRoom();
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(2.0, 0.0));

        // now the player can move back to room (1.0, 0.0)
        this.lvlController.getCurrentLevel().moveToPreviousRoom();
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(1.0, 0.0));

        // and then to room (0.0, 0.0)
        this.lvlController.getCurrentLevel().moveToPreviousRoom();
        assertEquals(this.lvlController.getCurrentRoom().getCoords(), new Point2D(0.0, 0.0));
    }
}
