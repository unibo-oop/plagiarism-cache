package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.implementations.PowerUpImpl;
import frogger.model.implementations.RandomPickableSpawner;
import frogger.model.interfaces.EntitySpawner;
import frogger.model.interfaces.PickableObject;

/**
 * Test class for RandomPickableSpawner, the type tested is often just PowerUp,
 * the Coin test would be the same.
 */
final class RandomPickableSpawnerTest {

    private Random mockRandom;
    private int boundPowerUp;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        boundPowerUp = Constants.MAX_POWER_UP_NUMBER - Constants.MIN_POWER_UP_NUMBER + 1;
    }

    @Test
    void numberTest() {
        List<PickableObject> entity;

        //Power up test
        //Just checking if the number is correct so the position is always valid
        final EntitySpawner<PickableObject> spawner = new RandomPickableSpawner(mockRandom, PowerUpImpl.class, Set.of()) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundPowerUp)).thenReturn(0);
        entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        assertEquals(entity.size(), Constants.MIN_POWER_UP_NUMBER);

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundPowerUp)).thenReturn(boundPowerUp - 1);
        entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        assertEquals(entity.size(), Constants.MAX_POWER_UP_NUMBER);

        /*
        //Coin test
        //Just checking if the number is correct so the position is always valid
        final EntitySpawner<PickableObject> spawner1 = new RandomPickableSpawner(mockRandom, Coin.class, Set.of()) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundCoin)).thenReturn(0);
        entity = spawner1.spawn(Constants.MIN_COIN_NUMBER, Constants.MAX_COIN_NUMBER);
        assertEquals(entity.size(), Constants.MIN_COIN_NUMBER);

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundCoin)).thenReturn(boundCoin - 1);
        entity = spawner1.spawn(Constants.MIN_COIN_NUMBER, Constants.MAX_COIN_NUMBER);
        assertEquals(entity.size(), Constants.MAX_COIN_NUMBER);
        */
    }

    @Test
    void generatePositionTest() {
        final int boundX = Constants.MAX_X - Constants.MIN_X + 1;
        final int boundY = Constants.MAX_Y - Constants.MIN_Y + 1;

        //Power up test
        //Just checking if the position generated is correct, so is always valid
        final EntitySpawner<PickableObject> spawner = new RandomPickableSpawner(mockRandom, PowerUpImpl.class, Set.of()) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check the x coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundX)).thenReturn(0);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertEquals(e.getPos().x(), Constants.MIN_X));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundX)).thenReturn(boundX - 1);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertEquals(e.getPos().x(), Constants.MAX_X));

        //Check the y coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundY)).thenReturn(0);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertEquals(e.getPos().y(), Constants.MIN_Y));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundY)).thenReturn(boundY - 1);
        spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER)
        .forEach(e -> assertEquals(e.getPos().y(), Constants.MAX_Y));

        /*
        //Coin test
        //Just checking if the position generated is correct, so is always valid
        final EntitySpawner<PickableObject> spawner1 = new RandomPickableSpawner(mockRandom, Coin.class, Set.of()) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        //Check the x coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundX)).thenReturn(0);
        spawner1.spawn(Constants.MIN_COIN_NUMBER, Constants.MAX_COIN_NUMBER)
        .forEach(e -> assertTrue(e.getPos().x() == Constants.MIN_X));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundX)).thenReturn(boundX - 1);
        spawner1.spawn(Constants.MIN_COIN_NUMBER, Constants.MAX_COIN_NUMBER)
        .forEach(e -> assertTrue(e.getPos().x() == Constants.MAX_X));

        //Check the y coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundY)).thenReturn(0);
        spawner1.spawn(Constants.MIN_COIN_NUMBER, Constants.MAX_COIN_NUMBER)
        .forEach(e -> assertTrue(e.getPos().y() == Constants.MIN_Y));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundY)).thenReturn(boundY - 1);
        spawner1.spawn(Constants.MIN_COIN_NUMBER, Constants.MAX_COIN_NUMBER)
        .forEach(e -> assertTrue(e.getPos().y() == Constants.MAX_Y));
        */
    }

    @Test
    void validPositionTest() {
        final List<PickableObject> entity;

        //Power up test
        //Checking if it works with a scenario where there is no overlap
        final EntitySpawner<PickableObject> spawner = new RandomPickableSpawner(mockRandom, PowerUpImpl.class, Set.of()) {
            private static final int I = 0;
            @Override
            protected Position generatePosition() {
                return new Position(I, 0);
            }
        };

        entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);

        final Set<Position> occupied = new HashSet<>();
        entity.forEach(e -> occupied.add(e.getPos()));

        assertEquals(occupied.size(), entity.size());

        //forcing the max number of power up, if it was just one there wouldn't be an overlap
        when(mockRandom.nextInt(boundPowerUp)).thenReturn(Constants.MAX_POWER_UP_NUMBER);

        //Checking if it works with a scenario where there is an overlap
        final EntitySpawner<PickableObject> spawner1 = new RandomPickableSpawner(mockRandom, PowerUpImpl.class, Set.of()) {
            @Override
            protected Position generatePosition() {
                return new Position(0, 0);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        assertEquals(List.of(), spawner1.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER));
    }

    @Test
    void overlapWithOtherPickableTest() {
        //Forcing the max number of both to work with the worst case scenario
        when(mockRandom.nextInt(boundPowerUp)).thenReturn(Constants.MAX_POWER_UP_NUMBER);

        //Simulate a position occupied by a coin
        final Position occupiedPos = new Position(0, 0);
        final Set<Position> alreadyPresent = Set.of(occupiedPos);

        //This spawner will try to generate a position where is already occupied
        final EntitySpawner<PickableObject> spawner = new RandomPickableSpawner(mockRandom, PowerUpImpl.class, alreadyPresent) {
            @Override
            protected Position generatePosition() {
                return occupiedPos;
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        final List<PickableObject> entity = spawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER);
        assertEquals(List.of(), entity);
    }
}
