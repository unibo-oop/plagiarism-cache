package fargoal.model.map.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import fargoal.commons.api.Position;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.map.api.FloorGenerator;
import fargoal.model.map.api.FloorMap;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;

/**
 * class that creates a random floor.
 */
public class FloorGeneratorImpl implements FloorGenerator {

    private static final int MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS = 10;
    private static final int EXTRA_ROOMS_IN_THE_BEGINNIG = 5;
    private static final int EXTRA_ROOMS_BETWEEN_SIX_TO_TEN = 3;
    private static final int EXTRA_ROOMS_END = 2;
    private static final int LOW_LEVELS = 5;
    private static final int MEDIUM_LEVELS = 10;
    private static final int HIGH_LEVELS = 15;

    private final Random rnd;

    /**
     * A contructor to initialize the internal random.
     */
    public FloorGeneratorImpl() {
        this.rnd = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final FloorMap createFloor(final FloorManager manager) {
        if (manager.getFloorLevel() <= LOW_LEVELS) {
            return new FloorMapBuilder(manager.getRenderFactory())
                    .buildRooms(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS + EXTRA_ROOMS_IN_THE_BEGINNIG)
                    .buildCorridors(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS + rnd.nextInt(EXTRA_ROOMS_IN_THE_BEGINNIG))
                    .build();
        } else if (manager.getFloorLevel() <= MEDIUM_LEVELS) {
            return new FloorMapBuilder(manager.getRenderFactory())
                    .buildRooms(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS + EXTRA_ROOMS_BETWEEN_SIX_TO_TEN)
                    .buildCorridors(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS + rnd.nextInt(EXTRA_ROOMS_BETWEEN_SIX_TO_TEN))
                    .build();
        } else if (manager.getFloorLevel() <= HIGH_LEVELS) {
            return new FloorMapBuilder(manager.getRenderFactory())
                    .buildRooms(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS + EXTRA_ROOMS_END)
                    .buildCorridors(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS + rnd.nextInt(EXTRA_ROOMS_END))
                    .build();
        } else {
            return new FloorMapBuilder(manager.getRenderFactory())
                    .buildRooms(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS)
                    .buildCorridors(MINUMUM_NUMBER_OF_ROOMS_AND_CORRIDORS)
                    .build();
        }
    }

    private class FloorMapBuilder {

        private static final int OPPOSITE_DIRECTION = 3;
        private static final int MINIMUM_ROOM_SIZE = 3;
        private static final int VARIABLE_ROOM_SIZE = 5;
        private static final int MINIMUM_CORRIDOR_LENGTH = 5;
        private static final int VARIABLE_CORRIDOR_LENGTH = 8;
        private static final int FLOOR_LENGTH = 40;
        private static final int FLOOR_HEIGHT = 25;
        private static final int MAX_TURNS = 10;

        private Map<Position, Renderer> temporaryTiles;
        private final Map<Position, Renderer> temporaryWalls;
        private final List<Position> centers;
        private final RenderFactory rf;

        FloorMapBuilder(final RenderFactory renderFactory) {
            this.rf = renderFactory;
            this.temporaryTiles = new HashMap<>();
            this.temporaryWalls = new HashMap<>();
            this.centers = new ArrayList<>();
            for (int i = 0; i < FLOOR_LENGTH; i++) {
                for (int j = 0; j < FLOOR_HEIGHT; j++) {
                    this.temporaryWalls.put(new Position(i, j), rf.wallRenderer(new Position(i, j)));
                }
            }
        }

        private FloorMap build() {
            temporaryTiles = temporaryTiles.keySet().stream()
                    .filter(p -> p.x() >= 1 && p.y() >= 1)
                    .filter(p -> p.x() < FLOOR_LENGTH - 1 && p.y() < FLOOR_HEIGHT - 1)
                    .collect(Collectors.toMap(p -> p, rf::tileRenderer));
            return new FloorMapImpl(temporaryTiles, temporaryWalls, FLOOR_LENGTH, FLOOR_HEIGHT);
        }

        private void buildRoom(final Position pos, final int height, final int length) {
            for (int i = pos.x(); i < pos.x() + length; i++) {
                for (int j = pos.y(); j < pos.y() + height; j++) {
                    this.temporaryTiles.put(new Position(i, j), rf.tileRenderer(pos));
                    this.temporaryWalls.remove(pos);
                }
            }
        }

        private void buildCorridor(final Position pos) {
            enum FloorState {
                CONTINUE, HIT_PATH, WALL
            }
            final List<Position> directions = new ArrayList<>(List.of(
                        new Position(1, 0),
                        new Position(0, 1),
                        new Position(0, -1),
                        new Position(-1, 0)));
            FloorState state = FloorState.CONTINUE;
            int direction = rnd.nextInt(directions.size());
            Position currentPosition = pos;
            int turns = 0;

            while (!state.equals(FloorState.HIT_PATH) &&  turns < MAX_TURNS) {
                final int length = rnd.nextInt(VARIABLE_CORRIDOR_LENGTH) + MINIMUM_CORRIDOR_LENGTH;

                for (int j = 0; j < length; j++) {
                    currentPosition = currentPosition.add(directions.get(direction));
                    if (!this.temporaryTiles.containsKey(currentPosition)) {
                        state = FloorState.WALL;
                    }
                    if (currentPosition.x() < 1 || currentPosition.y() < 1 
                        || currentPosition.x() >= FLOOR_LENGTH - 1 || currentPosition.y() >= FLOOR_HEIGHT - 1) {
                        break;
                    }
                    if (state.equals(FloorState.WALL) && this.temporaryTiles.containsKey(currentPosition)) {
                        state = FloorState.HIT_PATH;
                        break;
                    }

                    this.temporaryTiles.put(currentPosition, rf.tileRenderer(currentPosition));
                    this.temporaryWalls.remove(currentPosition);
                }

                turns++;
                final int last = direction;
                do {
                    direction = rnd.nextInt(directions.size());
                } while (direction == OPPOSITE_DIRECTION - last);
            }
        }

        private FloorMapBuilder buildRooms(final int numberOfRooms) {
            for (int i = 0; i < numberOfRooms; i++) {
                final int length = rnd.nextInt(VARIABLE_ROOM_SIZE) + MINIMUM_ROOM_SIZE;
                final int height = rnd.nextInt(VARIABLE_ROOM_SIZE) + MINIMUM_ROOM_SIZE;
                final Position start = new Position(
                    rnd.nextInt(FLOOR_LENGTH - MINIMUM_ROOM_SIZE - length) + 1,
                    rnd.nextInt(FLOOR_HEIGHT - MINIMUM_ROOM_SIZE - height) + 1);
                centers.add(new Position(start.x() + length / 2, start.y() + height / 2));
                this.buildRoom(start, height, length);
            }
            return this;
        }

        private FloorMapBuilder buildCorridors(final int numberOfCorridors) {
            for (int i = 0; i < numberOfCorridors; i++) {
                this.buildCorridor(centers.get(i));
            }
            return this;
        }
    }
}
