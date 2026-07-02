package model.entity.factory;

import java.awt.geom.Point2D;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import model.entity.Coin;
import model.entity.DynamicEntity;
import model.entity.EntityType;
import model.entity.Obstacle;
import model.entity.Platform;
import model.entity.SpawnLevel;
import model.entity.powerup.ExtraLife;
import model.entity.powerup.Mushroom;
import model.entity.powerup.PowerUpType;
import model.entity.powerup.Shield;
import model.entity.powerup.Spraybomb;
import model.entity.powerup.Superjump;
import view.entity.EntityImages;

/**
 * 
 * Factory Method for {@link DynamicEntity}.
 *
 */
public final class EntityFactoryImpl implements EntityFactory {

    private static final double LAND_HEIGHT = 40.0f;
    private static final int POWERUPS = 5; 

    private final Dimension2D worldDimensions;
    private final SecureRandom rand;

    /**
     * 
     * @param worldDimensions the world dimensions 
     */
    public EntityFactoryImpl(final Dimension2D worldDimensions) {

        this.worldDimensions = new Dimension2D(worldDimensions.getWidth(), worldDimensions.getHeight() - LAND_HEIGHT);
        this.rand = new SecureRandom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicEntity createObstacle(final SpawnLevel level) {

        final Image image = Math.random() > 0.5 ? EntityImages.OBSTACLE_ONE.getImage() : EntityImages.OBSTACLE_TWO.getImage();
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.OBSTACLE.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.OBSTACLE.getDistanceFactor();

        return new Obstacle(coordinates, image, level, EntityType.OBSTACLE, distance);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicEntity createPlatform(final SpawnLevel level) {

        final Image image = EntityImages.PLATFORM.getImage();
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.PLATFORM.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth();

        return new Platform(coordinates, image, level, EntityType.PLATFORM, distance);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicEntity createCoin(final SpawnLevel level) {

        final Image image = EntityImages.COIN.getImage();
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.COIN.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.COIN.getDistanceFactor();

        return new Coin(coordinates, image, level, EntityType.COIN, distance);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DynamicEntity> combinePlatformObstacle(final SpawnLevel platformLevel, final SpawnLevel obstacleLevel) {

        final Stream.Builder<DynamicEntity> builder = Stream.builder();
        return builder.add(this.createObstacle(obstacleLevel))
                      .add(this.createPlatform(platformLevel))
                      .build()
                      .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DynamicEntity> combinePlatformCoin(final SpawnLevel platformLevel, final SpawnLevel coinLevel) {

        final Stream.Builder<DynamicEntity> builder = Stream.builder();
        return builder.add(this.createCoin(coinLevel))
                      .add(this.createPlatform(platformLevel))
                      .build()
                      .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DynamicEntity> combineObstacleCoin(final SpawnLevel obstacleLevel, final SpawnLevel coinLevel) {

        final Stream.Builder<DynamicEntity> builder = Stream.builder();
        return builder.add(this.createCoin(coinLevel))
                      .add(this.createObstacle(obstacleLevel))
                      .build()
                      .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DynamicEntity> combineAll(final SpawnLevel platformLevel, final SpawnLevel obstacleLevel, final SpawnLevel coinLevel) {

        final Stream.Builder<DynamicEntity> builder = Stream.builder();
        return builder.add(this.createCoin(coinLevel))
                      .add(this.createObstacle(obstacleLevel))
                      .add(this.createPlatform(platformLevel))
                      .build()
                      .collect(Collectors.toList());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicEntity createPowerup(final SpawnLevel level) {

        final int random = rand.nextInt(POWERUPS); 
        final DynamicEntity powerup; 
        switch (PowerUpType.values()[random]) {
            case EXTRALIFE: 
                powerup = createExtralife(level); 
                break; 
            case MUSHROOM: 
                powerup = createMushroom(level); 
                break; 
            case SHIELD: 
                powerup = createShield(level); 
                break; 
            case SPRAYBOMB: 
                powerup = createSpraybomb(level); 
                break; 
            case SUPERJUMP: 
                powerup = createSuperjump(level); 
                break; 
            default: 
                powerup = null; 
                break; 
        }

        return powerup; 
    }

    /**
     * Creates the Extralife powerup.
     * @param level the level where the powerup spawns.
     * @return the powerup.
     */
    private DynamicEntity createExtralife(final SpawnLevel level) {

        final Image image = EntityImages.EXTRALIFE.getImage(); 
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.POWERUP.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        return new ExtraLife(coordinates, image, level, EntityType.POWERUP, distance);
    }

    /**
     * Creates the Mushroom powerup.
     * @param level the level where the powerup spawns.
     * @return the powerup.
     */
    private DynamicEntity createMushroom(final SpawnLevel level) {

        final Image image = EntityImages.MUSHROOM.getImage(); 
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.POWERUP.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        return new Mushroom(coordinates, image, level, EntityType.POWERUP, distance);
    }

    /**
     * Creates the Shield powerup.
     * @param level the level where the powerup spawns.
     * @return the powerup.
     */
    private DynamicEntity createShield(final SpawnLevel level) {

        final Image image = EntityImages.SHIELD.getImage(); 
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.POWERUP.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        return new Shield(coordinates, image, level, EntityType.POWERUP, distance);
    }

    /**
     * Creates the Spraybomb powerup.
     * @param level the level where the powerup spawns.
     * @return the powerup.
     */
    private DynamicEntity createSpraybomb(final SpawnLevel level) {

        final Image image = EntityImages.SPRAYBOMB.getImage(); 
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.POWERUP.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        return new Spraybomb(coordinates, image, level, EntityType.POWERUP, distance);
    }

    /**
     * Creates the Superjump powerup.
     * @param level the level where the powerup spawns.
     * @return the powerup.
     */
    private DynamicEntity createSuperjump(final SpawnLevel level) {

        final Image image = EntityImages.SUPERJUMP.getImage(); 
        final Point2D.Double coordinates = this.generatePoint(level, image, EntityType.POWERUP.getDistanceFactor());
        final double distance = worldDimensions.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        return new Superjump(coordinates, image, level, EntityType.POWERUP, distance);
    }

    /**
     * Generate the coordinates of the entity, according to given properties. 
     * @param level the level on which entity should spawn.
     * @param image the image identifying the entity.
     * @param distanceFactor property of the entity that describe the multiplicative factor.
     * @return a new {@link Double} identifying the coordinates of the entity. 
     */
    private Point2D.Double generatePoint(final SpawnLevel level, final Image image, final double distanceFactor) {
        final double x = worldDimensions.getWidth() + image.getWidth() * distanceFactor;
        final double y = worldDimensions.getHeight() * level.getSpawnY() - image.getHeight();
        return new Point2D.Double(x, y);
    }

}
