package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.implementations.Car;
import frogger.model.implementations.RandomObstaclesSpawner;
import frogger.model.interfaces.EntitySpawner;

/**
 * Test class for RandomObstalesSpawner, the type tested is always Car but it works the same way with Trunk.
 */
final class RandomObstalesSpawnerTest {

    private float speed;
    private Direction dir;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        speed = Constants.MAX_SPEED;
        dir = Direction.RIGHT;
        mockRandom = mock(Random.class);
    }

    @Test
    void numberTest() {
        final int bound = Constants.MAX_OBSTACLES_NUMBER - Constants.MIN_OBSTACLES_NUMBER + 1;
        List<Car> entity;

        //just checking if the number is right, so the position is always valid
        final EntitySpawner<Car> spawner = new RandomObstaclesSpawner<>(Car.class, 0, speed, dir, mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(bound)).thenReturn(0);
        entity = spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER);
        assertEquals(entity.size(), Constants.MIN_OBSTACLES_NUMBER);

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(bound)).thenReturn(bound - 1);
        entity = spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER);
        assertEquals(entity.size(), Constants.MAX_OBSTACLES_NUMBER);
    }

    @Test
    void generatePositionTest() {
        final int bound = Constants.MAX_X - Constants.MIN_X + 1;

        //Just checking if the position generated is correct, so is always valid
        final EntitySpawner<Car> spawner = new RandomObstaclesSpawner<>(Car.class, 0, speed, dir, mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check the x coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(bound)).thenReturn(0);
        spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().x(), Constants.MIN_X));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(bound)).thenReturn(bound - 1);
        spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().x(), Constants.MAX_X));

        //Check the y coordinate, should be 0 (from the constructor)
        spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().y(), 0));
    }

    @Test
    void validPositionTest() {
        final int bound = Constants.MAX_CAR_WIDTH - Constants.MIN_CAR_WIDTH + 1;
        final List<Car> entity;

        //Check with the max width to have the worst case scenario
        when(mockRandom.nextInt(bound)).thenReturn(bound - 1);

        //Checking if it works with a scenario where there is no overlap
        final EntitySpawner<Car> spawner = new RandomObstaclesSpawner<>(Car.class, 0, speed, dir, mockRandom) {
            private int i;
            @Override
            protected Position generatePosition() {
                super.generatePosition();
                final int x = Constants.MIN_X + i * (Constants.MAX_CAR_WIDTH + 1);
                i++;
                return new Position(x, 0);
            }
        };

        entity = spawner.spawn(Constants.MAX_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER);

        final Set<Position> occupied = new HashSet<>();
        entity.forEach(e -> IntStream.range(0, e.getDimension().width())
        .forEach(i -> occupied.add(new Position(e.getPos().x() + i, e.getPos().y()))));

        assertEquals(occupied.size(), Constants.MAX_CAR_WIDTH * entity.size());

        //Checking if it works with a scenario where there is an overlap
        final EntitySpawner<Car> spawner1 = new RandomObstaclesSpawner<>(Car.class, 0, speed, dir, mockRandom) {
            @Override
            protected Position generatePosition() {
                super.generatePosition();
                return new Position(0, 0);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        assertEquals(List.of(), spawner1.spawn(Constants.MAX_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER));
    }
}
