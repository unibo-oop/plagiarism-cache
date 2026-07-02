package it.unibo.isaccoop.model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import it.unibo.isaccoop.core.GameEngine;
import it.unibo.isaccoop.model.player.Player;

/**
 * Implementation of {@link LevelController}.
 */
public final class LevelControllerImpl implements LevelController {

    private final List<Level> lvl = new LinkedList<>();
    private int currentLevelID;

    /**
     * Create a game with the specified number of levels.
     * @param numberOfLevels the number of levels to create
     * @param engine the {@link GameEngine} to be attached to this level
     */
    public LevelControllerImpl(final int numberOfLevels, final GameEngine engine) {
        final LevelFactory lvlFactory = new LevelFactoryImpl(engine);
        this.currentLevelID = 0;
        Stream.iterate(0, i -> i + 1)
            .limit(numberOfLevels)
            .forEach(r -> this.lvl.add(lvlFactory.createLevel()));
    }

    @Override
    public Level getCurrentLevel() {
        return this.lvl.get(currentLevelID);
    }

    @Override
    public int getCurrentLevelIndex() {
        return this.currentLevelID;
    }

    @Override
    public int getNumberOfLevels() {
        return this.lvl.size();
    }

    @Override
    public List<Room> getRoomsOfCurrentLevel() {
        return List.copyOf(getCurrentLevel().getRooms());
    }

    @Override
    public int getNumberOfRoomsOfCurrentLevel() {
        return getRoomsOfCurrentLevel().size();
    }

    @Override
    public Room getCurrentRoom() {
        return getCurrentLevel().getRooms().stream()
                .filter(r -> r.getPlayer().isPresent())
                .findFirst().get();
    }

    @Override
    public Player getPlayer() {
        return getCurrentRoom().getPlayer().get();
    }

    @Override
    public boolean isRoomComplete(final Room room) {
        return room.isComplete();
    }

    @Override
    public boolean isCurrentLevelComplete() {
        if (getCurrentLevel().isLevelComplete()) {
            goToNextLevel();
            return true;
        }
        return false;
    }

    @Override
    public boolean areAllLevelsComplete() {
        return this.lvl.stream().allMatch(l -> l.isLevelComplete());
    }

    /**
     * Move the player to the next Level.
     */
    private void goToNextLevel() {
        this.currentLevelID++;
    }
}
