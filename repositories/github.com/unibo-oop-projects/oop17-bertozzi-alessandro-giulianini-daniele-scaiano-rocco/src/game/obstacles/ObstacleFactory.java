package game.obstacles;

import java.util.List;

/**
 * A class with methods to create all the implementations of {@link AbstractObstacle}.
 */
public interface ObstacleFactory {
    /**
     * 
     * @return a {@link AbstractObstacle.SimpleObstacle} instance.
     */
    AbstractObstacle createSimpleObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.BouncingObstacle} instance.
     */
    AbstractObstacle createBouncingObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.EnlargingObstacle} instance.
     */
    AbstractObstacle createEnlargingObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.TimeLimitedObstacle} instance.
     */
    AbstractObstacle createTimeLimitedObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.EnlargingObstacle}-{@link ObstacleDecorator.BouncingObstacle} instance.
     */
    AbstractObstacle createEnlargingBouncingObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.TimeLimitedObstacle}-{@link ObstacleDecorator.EnlargingObstacle} instance.
     */
    AbstractObstacle createTimeLimitedEnlargingObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.TimeLimitedObstacle}-{@link ObstacleDecorator.BouncingObstacle} instance.
     */
    AbstractObstacle createTimeLimitedBouncingObstacle();
    /**
     * 
     * @return a {@link ObstacleDecorator.TimeLimitedObstacle}-{@link ObstacleDecorator.EnlargingObstacle}-{@link ObstacleDecorator.BouncingObstacle} instance.
     */
    AbstractObstacle createTimeLimitedEnlargingBouncingObstacle();

    /**
     * @return a list filled with {@link AbstractObstacle} with same velocity, size and type.
     * The aim of them being gathered is to simulate what in astronomical literature is reported as a "Meteor Shower".
     */
    List<AbstractObstacle> createObstaclesShower();
}
