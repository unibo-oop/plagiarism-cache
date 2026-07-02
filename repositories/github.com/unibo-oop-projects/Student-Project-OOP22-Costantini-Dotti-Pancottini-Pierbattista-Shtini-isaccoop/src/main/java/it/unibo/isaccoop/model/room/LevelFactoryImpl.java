package it.unibo.isaccoop.model.room;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import it.unibo.isaccoop.core.GameEngine;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * Implementation of {@link LevelFactory}.
 */
public final class LevelFactoryImpl implements LevelFactory {

    private static final int MAX_NUMBER_OF_ROOMS = 10;
    private static final int MIN_NUMBER_OF_ROOMS = 6;
    private final LevelFactoryUtils lvlFactoryUtils = new LevelFactoryUtils();
    private final GameEngine engine;

    /**
     * Constructor.
     * @param engine the {@link GameEngine} for this game
     */
    public LevelFactoryImpl(final GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public Level createLevel() {
        final int numberOfRooms = ThreadLocalRandom.current().nextInt(
                MAX_NUMBER_OF_ROOMS - MIN_NUMBER_OF_ROOMS) + MIN_NUMBER_OF_ROOMS;
        final List<Point2D> roomCoords = lvlFactoryUtils.generateRoomCoordinates(numberOfRooms);
        final List<Room> rooms = createRooms(roomCoords);
        final Level lvl = new LevelImpl(this.engine);
        lvl.putRooms(rooms);
        return lvl;
    }

    /**
     * @return the {@link GameEngine}
     */
    GameEngine getGameEngine() {
        return this.engine;
    }

    /**
     * Method to associate rooms to their coordinates.
     * @param coordsList
     * @return the list of created rooms.
     */
    private List<Room> createRooms(final List<Point2D> coordsList) {
        final RoomFactory rFactory = new RoomFactoryImpl(coordsList.size());
        final List<Room> rooms = new LinkedList<>();

        for (final Point2D coord: coordsList) {
            rooms.add(rFactory.buildRoomInProperOrder(coord));
        }
        return rooms;
    }
}
