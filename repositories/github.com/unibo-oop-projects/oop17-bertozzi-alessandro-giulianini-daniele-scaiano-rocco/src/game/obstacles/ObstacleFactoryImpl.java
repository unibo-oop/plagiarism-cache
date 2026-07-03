package game.obstacles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import game.GameImpl;
import utilities.Pair;

/**
 * An implementation of {@link ObstacleFactory}.
 */
public class ObstacleFactoryImpl implements ObstacleFactory {
    private final Random rd = new Random(100);
    private static final int MIN_SIDE_LENGTH = 40;
    private static final int MAX_SIDE_LENGTH = 80;

    private static final int MIN_VELOCITY = 0; //positive cause they spawns at the top 
    private static final int MAX_VELOCITY = 8;

    private final Iterator<Integer> sideLength = rd.ints(MIN_SIDE_LENGTH, MAX_SIDE_LENGTH).iterator();
    private final Iterator<Integer> velocities = rd.ints(MIN_VELOCITY, MAX_VELOCITY).iterator();

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createSimpleObstacle() {
        return new AbstractObstacle.SimpleObstacle(this.getRandomGameSpaceLocation(), 
                                                                                    this.getRandomVelocity(),
                                                                                    this.getRandomVelocity(),
                                                                                    this.getRandomSideLength());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createBouncingObstacle() {
        return new ObstacleDecorator.BouncingObstacle(
                this.createSimpleObstacle());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createEnlargingObstacle() {
        return new ObstacleDecorator.EnlargingObstacle(
                this.createSimpleObstacle());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createTimeLimitedObstacle() {
        return new ObstacleDecorator.TimeLimitedObstacle(
                this.createSimpleObstacle());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createEnlargingBouncingObstacle() {
        return new ObstacleDecorator.EnlargingObstacle(
                this.createBouncingObstacle());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createTimeLimitedEnlargingObstacle() {
        return new ObstacleDecorator.TimeLimitedObstacle(
                this.createEnlargingBouncingObstacle());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createTimeLimitedBouncingObstacle() {
        return new ObstacleDecorator.TimeLimitedObstacle(
                this.createBouncingObstacle());
    }

    /** {@inheritDoc}*/
    @Override
    public AbstractObstacle createTimeLimitedEnlargingBouncingObstacle() {
        return new ObstacleDecorator.TimeLimitedObstacle(
                this.createEnlargingBouncingObstacle());
    }

    /** {@inheritDoc} */
    public List<AbstractObstacle> createObstaclesShower() {
        final int distanceCoefficient = 5;
        final List<AbstractObstacle> list = new ArrayList<>();
        final int vx = this.getRandomVelocity();
        final int vy = this.getRandomVelocity();
        final int sideLength = this.getRandomSideLength();
        final Pair<Integer, Integer> position = this.getRandomGameSpaceLocation();
        final int distance = distanceCoefficient * sideLength;
        list.add(new ObstacleDecorator.BouncingObstacle(new AbstractObstacle.SimpleObstacle(new Pair<Integer, Integer>(position.getX(), position.getY()), vx, vy, sideLength)));      //centro
        list.add(new ObstacleDecorator.BouncingObstacle(new AbstractObstacle.SimpleObstacle(new Pair<Integer, Integer>(position.getX(), position.getY() + distance), vx, vy, sideLength)));     //sopra
        list.add(new ObstacleDecorator.BouncingObstacle(new AbstractObstacle.SimpleObstacle(new Pair<Integer, Integer>(position.getX() - distance, position.getY()), vx, vy, sideLength)));     //sx
        list.add(new ObstacleDecorator.BouncingObstacle(new AbstractObstacle.SimpleObstacle(new Pair<Integer, Integer>(position.getX() + distance, position.getY()), vx, vy, sideLength)));     //dx
        list.add(new ObstacleDecorator.BouncingObstacle(new AbstractObstacle.SimpleObstacle(new Pair<Integer, Integer>(position.getX(), position.getY() - distance), vx, vy, sideLength)));     //sotto
        return list;
    }

    private Integer getRandomVelocity() {
        return this.velocities.next();
    }

    private int getRandomSideLength() {
        return this.sideLength.next();
    }

    private Pair<Integer, Integer> getRandomGameSpaceLocation() {
        return new Pair<Integer, Integer>(this.rd.nextInt(GameImpl.GAMEAREA_WIDTH - 2 * MAX_SIDE_LENGTH) + MAX_SIDE_LENGTH, MAX_SIDE_LENGTH);

    }
}
