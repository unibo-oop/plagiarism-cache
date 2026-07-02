package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javawulf.model.BoundingBox;
import javawulf.model.BoundingBoxImpl;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.enemy.Pawn;
import javawulf.model.map.Room;
import javawulf.model.map.Space;
import javawulf.model.map.Map;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;
import javawulf.model.map.Biome;
import javawulf.model.map.MapImpl;
import javawulf.model.map.BiomeImpl;
import javawulf.model.map.BiomeQuadrant;
import javawulf.model.map.Corridor;
import javawulf.model.map.factory.MapFactoryImpl;
import javawulf.model.player.PlayerImpl;

/**
 * Some tests for try rooms, corridors, biomes, and getTileTypes.
 */
// CHECKSTYLE: MagicNumber OFF
// During map tests, they are often used arbitrary positions and dimensions for
// rooms and corridors.
// For this reason it was decided to suppress the warning checkstyles.
final class MapTest {
        private Map gameMapExample;
        private Biome firstBiome, secondBiome, thirdBiome, fourthBiome;

        @Test
        void testRooms() {
                assertEquals(TileType.ROOM, Room.DEFAULT_TYPE);

                // width and height have same dimension
                final Space roomA = new Room(10, 10);
                assertEquals(10, roomA.getWidth());
                assertEquals(10, roomA.getHeight());

                // rectangular room
                final Space roomB = new Room(6, 9);
                assertEquals(6, roomB.getWidth());
                assertEquals(9, roomB.getHeight());
        }

        @Test
        void testCorridors() {
                // default tile type of Corridors
                assertEquals(TileType.CORRIDOR, Corridor.DEFAULT_TYPE);

                // corridors with differents dimensions
                final Space corridor1 = new Corridor(10, 5);
                assertEquals(10, corridor1.getWidth());
                assertEquals(5, corridor1.getHeight());

                final Space corridor2 = new Corridor(6, 3);
                assertEquals(6, corridor2.getWidth());
                assertEquals(3, corridor2.getHeight());
        }

        @Test
        void testBiomes() {
                final Biome biome = new BiomeImpl();
                biome.addRoom(new TilePosition(5, 5), new Room(10, 10));
                biome.addRoom(new TilePosition(10, 5), new Room(7, 6));

                // biome have 2 rooms and 0 corridors
                assertEquals(2, biome.getRooms().size());
                assertEquals(0, biome.getCorridors().size());

                // Out-of-biome rooms cannot allowed (exception)
                assertThrows(IllegalArgumentException.class,
                                () -> biome.addRoom(new TilePosition(Biome.SIZE, Biome.SIZE), new Room(5, 4)));
        }

        /**
         * Setups four biomes and the Map.
         */
        @BeforeEach
        void setUp() {
                firstBiome = new BiomeImpl();
                firstBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

                secondBiome = new BiomeImpl();
                secondBiome.addRoom(new TilePosition(1, 1), new Room(7, 7));

                thirdBiome = new BiomeImpl();
                thirdBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

                fourthBiome = new BiomeImpl();
                fourthBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

                this.gameMapExample = new MapImpl(null, firstBiome, secondBiome, thirdBiome, fourthBiome);
        }

        /**
         * Setups four biomes and the Map, with entity 'variant'.
         */
        @BeforeEach
        void setUpWithEntities() {
                firstBiome = new BiomeImpl();
                final Room roomA = new Room(10, 10);
                roomA.addGameElement(new Pawn(new CoordinateImpl(0, 0)));
                firstBiome.addRoom(new TilePosition(1, 1), roomA);

                secondBiome = new BiomeImpl();
                secondBiome.addRoom(new TilePosition(1, 1), new Room(7, 7));

                thirdBiome = new BiomeImpl();
                thirdBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

                fourthBiome = new BiomeImpl();
                fourthBiome.addRoom(new TilePosition(1, 1), new Room(10, 10));

                this.gameMapExample = new MapImpl(null, firstBiome, secondBiome, thirdBiome, fourthBiome);
        }

        @Test
        @SuppressFBWarnings(value = {
                        "L", "D", "UwF"
        }, justification = "Preferred to re-initialize biomes for each test that uses them.")
        void testMap() {
                this.setUp();

                // coordinates that are in the first Tile at the top left of the Map, which must
                // be of type WALL
                assertEquals(TileType.WALL, gameMapExample.getTileType(new CoordinateImpl(0, 0)).get());
                assertEquals(TileType.WALL, gameMapExample.getTileType(new CoordinateImpl(23, 23)).get());

                // coordinates falling within the Tiles of the one room contained in the first
                // biome
                assertEquals(TileType.ROOM, gameMapExample.getTileType(new CoordinateImpl(24, 24)).get());
                assertEquals(TileType.ROOM, gameMapExample.getTileType(new CoordinateImpl(100, 100)).get());

                // coordinates that are out of the room positioned in the first biome (so the
                // tile of coordinates need to be WALL type)
                assertEquals(TileType.WALL, gameMapExample.getTileType(new CoordinateImpl(1000, 1000)).get());

                // Coordinate falling in the central biome (+) where there are no corridors
                // (so it must be a WALL)
                assertEquals(TileType.WALL,
                                gameMapExample.getTileType(new CoordinateImpl(TileType.TILE_DIMENSION * Biome.SIZE, 0))
                                                .get());

                // Coordinates are within the Tiles of the one room contained in the second
                // biome
                assertEquals(TileType.ROOM,
                                gameMapExample.getTileType(
                                                new CoordinateImpl(TileType.TILE_DIMENSION
                                                                * (Biome.SIZE + Map.WIDTH_CENTRAL_BIOME) + 24, 24))
                                                .get());
                assertEquals(TileType.ROOM,
                                gameMapExample.getTileType(
                                                new CoordinateImpl(TileType.TILE_DIMENSION
                                                                * (Biome.SIZE + Map.WIDTH_CENTRAL_BIOME
                                                                                + secondBiome.getRooms().get(0)
                                                                                                .getValue().getWidth())
                                                                - 1, 24))
                                                .get());

                // Coordinates that are outside the map (must therefore return an
                // Optional.empty)
                assertEquals(Optional.empty(),
                                gameMapExample.getTileType(
                                                new CoordinateImpl(Map.MAP_SIZE * TileType.TILE_DIMENSION, 0)));
        }

        @Test
        @SuppressFBWarnings(value = {
                        "L", "D", "UwF"
        }, justification = "Preferred to re-initialize biomes for each test that uses them.")
        void testMapEntityInteraction() {
                this.setUp();

                // EntityBox perfectly matches the position tile (0, 0) which is a
                // wall
                assertEquals(Set.of(TileType.WALL),
                                gameMapExample.getTileTypes(
                                                new BoundingBoxImpl(12, 12, 24, 24, BoundingBox.CollisionType.PLAYER)));

                // EntityBox intersects 4 different tiles, 3 of type WALL and 1 of type ROOM
                assertEquals(Set.of(TileType.WALL, TileType.ROOM),
                                gameMapExample.getTileTypes(
                                                new BoundingBoxImpl(13, 13, 24, 24, BoundingBox.CollisionType.PLAYER)));

                // EntityBox is inside a room
                assertEquals(Set.of(TileType.ROOM),
                                gameMapExample.getTileTypes(new BoundingBoxImpl(200, 200, 24, 24,
                                                BoundingBox.CollisionType.PLAYER)));

                // EntityBox passed is outside the map: in that case, it should return an
                // Empty set
                assertEquals(Set.of(),
                                gameMapExample.getTileTypes(new BoundingBoxImpl(
                                                Map.MAP_SIZE * TileType.TILE_DIMENSION + 24, 0, 24, 24,
                                                BoundingBox.CollisionType.PLAYER)));
        }

        @Test
        @SuppressFBWarnings(value = {
                        "L", "D", "UwF"
        }, justification = "Preferred to re-initialize biomes for each test that uses them.")
        void testEntitiesInMap() {

                // The map initialised on setUp() has no elements (0)
                this.setUp();
                final int numberOfElements = 0;
                assertEquals(numberOfElements, gameMapExample.getAllElements().size());

                // Added a Pawn (enemy) element in the first biome room: We
                // expect the corresponding element.
                gameMapExample.getBiomes().get(BiomeQuadrant.FIRST.getPos()).getRooms().get(0).getValue()
                                .addGameElement(new Pawn(new CoordinateImpl(0, 0)));
                assertEquals(Pawn.class, gameMapExample.getAllElements().get(0).getClass());

                final Coordinate playerStartingPos = new CoordinateImpl(TileType.TILE_DIMENSION * 3,
                                TileType.TILE_DIMENSION * 3);
                final int health = 2;
                final int startingPoints = 1;
                // Note that in the default map #1, there are a total of 15
                // rooms,
                // distributed over the 4 biomes.
                final Map gameMapExample2 = new MapFactoryImpl().getDefaultMap1(
                                new PlayerImpl(playerStartingPos.getX(), playerStartingPos.getY(), health,
                                                startingPoints));

                // Default Map #1 has, by default, 52 gameobjects (1 per room and 1 per
                // corridor).
                int expectedGameElem = 52;
                assertEquals(expectedGameElem, gameMapExample2.getAllElements().size());

                // An additional element is then added for each of the 15 rooms (the
                // elements
                // expected should now be 52 + 15 = 67)
                expectedGameElem += 15;
                gameMapExample2.getBiomes().forEach(biome -> biome.getRooms()
                                .forEach(roomPair -> roomPair.getValue()
                                                .addGameElement(new Pawn(new CoordinateImpl(0, 0)))));
                assertEquals(expectedGameElem, gameMapExample2.getAllElements().size());
        }
}
// CHECKSTYLE: MagicNumber ON
