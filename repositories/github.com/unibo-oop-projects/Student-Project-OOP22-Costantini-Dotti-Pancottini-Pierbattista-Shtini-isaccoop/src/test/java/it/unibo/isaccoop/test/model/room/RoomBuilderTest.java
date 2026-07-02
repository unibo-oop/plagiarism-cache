package it.unibo.isaccoop.test.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.RoomType;
import it.unibo.isaccoop.model.room.RoomBuilder.Builder;
import it.unibo.isaccoop.model.room.RoomBuilderUtils;

/**
 * RoomBuilder test.
 */
class RoomBuilderTest {

    private static final int ROOM_WIDTH = 300;
    private static final int ROOM_HEIGHT = 200;
    private static final int MAX_COORD_NUMBER = 20;

    @Test
    void testPutCoord() {
        final Builder emptyBuilder = createBuilder();
        final Builder ris = emptyBuilder.putCoord(generateCoord());
        assertEquals(ris, emptyBuilder);
    }

    @Test
    void testRoomType() {
        for (final var rType: RoomType.values()) {
            final Builder emptyBuilder = createBuilder();
            final Builder ris = emptyBuilder.roomType(rType);
            assertEquals(ris, emptyBuilder);
        }
    }

    @Test
    void testPutItems() {
        for (final var rType: RoomType.values()) {
            final RoomBuilderUtils utils = new RoomBuilderUtils(rType);
            final Builder localBuilder = new Builder(ROOM_WIDTH, ROOM_HEIGHT)
                    .putCoord(generateCoord())
                    .roomType(rType);
            if (utils.canRoomHaveItems()) {
                assertEquals(localBuilder.putItems(), localBuilder);
            } else {
                assertThrows(IllegalStateException.class, () -> localBuilder.putItems());
            }
        }
    }

    @Test
    void testPutEnemies() {
        for (final var rType: RoomType.values()) {
            final RoomBuilderUtils utils = new RoomBuilderUtils(rType);
            final Builder localBuilder = new Builder(ROOM_WIDTH, ROOM_HEIGHT)
                    .putCoord(generateCoord())
                    .roomType(rType);
            if (utils.canRoomHaveEnemies()) {
                assertEquals(localBuilder.putEnemies(), localBuilder);
            } else {
                assertThrows(IllegalStateException.class, () -> localBuilder.putEnemies());
            }
        }
    }

    @Test
    void testPutPowerUps() {
        for (final var rType: RoomType.values()) {
            final RoomBuilderUtils utils = new RoomBuilderUtils(rType);
            final Builder localBuilder = new Builder(ROOM_WIDTH, ROOM_HEIGHT)
                    .putCoord(generateCoord())
                    .roomType(rType);
            if (utils.canRoomHavePowerUps()) {
                assertEquals(localBuilder.putPowerUps(), localBuilder);
            } else {
                assertThrows(IllegalStateException.class, () -> localBuilder.putPowerUps());
            }
        }
    }

    @Test
    void testBuild() {
        final Builder localBuilder = new Builder(ROOM_WIDTH, ROOM_HEIGHT)
                .putCoord(generateCoord());

        // build() at this point will throw an exception because each room
        // must have a RoomType
        assertThrows(IllegalStateException.class, () -> localBuilder.build());

        for (final var rType: RoomType.values()) {
            final Builder otherBuilder = new Builder(ROOM_WIDTH, ROOM_HEIGHT)
                    .roomType(rType);

            // build() at this point will throw an exception because each room
            // must have a coordinate
            assertThrows(IllegalStateException.class, () -> otherBuilder.build());
        }

        // previous tests check if a room is created correctly, using the correct methods
        // so such tests are not repeated here
    }

    /**
     * Utility method to create a new builder.
     * @return a new builder
     */
    private Builder createBuilder() {
        return new Builder(ThreadLocalRandom.current().nextInt(ROOM_WIDTH),
                ThreadLocalRandom.current().nextInt(ROOM_HEIGHT));
    }

    /**
     * Utility method to generate a new random {@link Point2D}.
     * @return a new Point2D
     */
    private Point2D generateCoord() {
        return new Point2D(ThreadLocalRandom.current().nextInt(MAX_COORD_NUMBER),
                ThreadLocalRandom.current().nextInt(MAX_COORD_NUMBER));
    }
}
