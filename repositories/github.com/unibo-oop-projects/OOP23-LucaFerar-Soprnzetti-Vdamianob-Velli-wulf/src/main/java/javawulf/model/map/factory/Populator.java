package javawulf.model.map.factory;

import java.util.List;
import java.util.Random;

import javafx.util.Pair;
import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.enemy.EnemyFactory;
import javawulf.model.enemy.EnemyFactoryImpl;
import javawulf.model.item.AmuletPiece;
import javawulf.model.item.ItemFactory;
import javawulf.model.item.ItemFactoryImpl;
import javawulf.model.map.Biome;
import javawulf.model.map.BiomeQuadrant;
import javawulf.model.map.Map;
import javawulf.model.map.Space;
import javawulf.model.map.TilePosition;
import javawulf.model.map.TileType;
import javawulf.model.powerup.PowerUpFactory;
import javawulf.model.powerup.PowerUpFactoryImpl;

/**
 * Utility class used to populate a map with items, power-up and enemies.
 */
public final class Populator {
    private static final int HALFTILE = TileType.TILE_DIMENSION / 2;
    private static ItemFactory itemFactory = new ItemFactoryImpl();
    private static EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private static PowerUpFactory powerUpFactory = new PowerUpFactoryImpl();
    static final Random RANDOM_NUMBER = new Random();

    enum Collectables {
        CURE, CUREMAX, EXTRAHEART, GREATSWORD, SHIELD, POWERUPATTACK, POWERUPDOUBLEPOINTS, POWERUPSPEED,
        POWERUPINVINCIBILITY;

        public static Collectables getRandomic() {
            final Collectables[] colls = Collectables.values();
            return colls[RANDOM_NUMBER.nextInt(colls.length)];
        }
    }

    private Populator() {
        throw new UnsupportedOperationException("This class cannot be instantiated (Utility class)");
    }

    /**
     * 
     * @param map to populate with game elements (1 collectable in the center of
     *            each room, 1 entity per corridor).
     * @return map itself
     */
    public static Map populate(final Map map) {
        for (final var biomeQuadrant : BiomeQuadrant.values()) {
            final Biome biome = map.getBiomes().get(biomeQuadrant.getPos());
            final Pair<TilePosition, Space> randRoom = getRandomicSpace(biome.getRooms());
            final AmuletPiece biomeAmulet = new AmuletPiece(
                    getCentralPosition(randRoom.getKey(), randRoom.getValue(), biomeQuadrant));
            randRoom.getValue().addGameElement(biomeAmulet);

            for (final var room : biome.getRooms()) {
                if (!room.getValue().getElements().contains(biomeAmulet)) {
                    room.getValue().addGameElement(
                            getRandomCollectable(getCentralPosition(room.getKey(), room.getValue(), biomeQuadrant)));
                }

            }

            for (final var corridor : biome.getCorridors()) {
                corridor.getValue().addGameElement(
                        enemyFactory
                                .createPawn(getCentralPosition(corridor.getKey(), corridor.getValue(), biomeQuadrant)));
            }
        }
        return map;
    }

    /**
     * Utility method used for obtain central coordinate of a space (useful for
     * rooms).
     * 
     * @param spacePos of the space
     * @param space    where found its central coordinate position.
     * @param quadrant specify in which quadrant the space is in.
     * @return central position.
     */
    private static Coordinate getCentralPosition(final TilePosition spacePos, final Space space,
            final BiomeQuadrant quadrant) {
        final int halfWidth = space.getWidth() / 2;
        final int halfHeight = space.getHeight() / 2;
        return new CoordinateImpl(
                (spacePos.getX() + quadrant.getOffset().getX() + halfWidth) * TileType.TILE_DIMENSION + HALFTILE,
                (spacePos.getY() + quadrant.getOffset().getY() + halfHeight) * TileType.TILE_DIMENSION + HALFTILE);
    }

    /**
     * Utility method used for obtain a lateral coordinate of a space, next to the
     * center (useful for put
     * guards).
     * 
     * @param spacePos  of the space
     * @param space     where found its lateral coordinate position.
     * @param quadrant  specify in which quadrant the space is in.
     * @param leftRight is a flag. See @return
     * @return lateral position (lateral left if 'leftRight' is True or lateral
     *         right if 'leftRight' is False).
     */
    @SuppressWarnings("unused")
    private static Coordinate getlateralPosition(final TilePosition spacePos, final Space space,
            final BiomeQuadrant quadrant,
            final boolean leftRight) {
        final int offSetPos;
        if (leftRight) {
            offSetPos = TileType.TILE_DIMENSION;
        } else {
            offSetPos = -TileType.TILE_DIMENSION;
        }
        final int halfWidth = space.getWidth() / 2;
        final int halfHeight = space.getHeight() / 2;
        return new CoordinateImpl(
                (spacePos.getX() + quadrant.getOffset().getX() + halfWidth) * TileType.TILE_DIMENSION + HALFTILE
                        + offSetPos,
                (spacePos.getY() + quadrant.getOffset().getY() + halfHeight) * TileType.TILE_DIMENSION + HALFTILE);
    }

    private static Collectable getRandomCollectable(final Coordinate coordColl) {
        switch (Collectables.getRandomic()) {
            case CURE:
                return itemFactory.createCure(coordColl);
            case CUREMAX:
                return itemFactory.createCureMax(coordColl);
            case EXTRAHEART:
                return itemFactory.createExtraHeart(coordColl);
            case GREATSWORD:
                return itemFactory.createGreatSword(coordColl);
            case SHIELD:
                return itemFactory.createShield(coordColl);
            case POWERUPATTACK:
                return powerUpFactory.createPowerUpAttack(coordColl);
            case POWERUPDOUBLEPOINTS:
                return powerUpFactory.createPowerUpDoublePoints(coordColl);
            case POWERUPSPEED:
                return powerUpFactory.createPowerUpSpeed(coordColl);
            case POWERUPINVINCIBILITY:
                return powerUpFactory.createPowerUpInvincibility(coordColl);
            default:
                return itemFactory.createCure(coordColl);
        }
    }

    /**
     * 
     * @param spaces arrayList of spaces
     * @return a randomic space from arraylist
     */
    private static Pair<TilePosition, Space> getRandomicSpace(final List<Pair<TilePosition, Space>> spaces) {
        return spaces.get(RANDOM_NUMBER.nextInt(spaces.size()));
    }

}
