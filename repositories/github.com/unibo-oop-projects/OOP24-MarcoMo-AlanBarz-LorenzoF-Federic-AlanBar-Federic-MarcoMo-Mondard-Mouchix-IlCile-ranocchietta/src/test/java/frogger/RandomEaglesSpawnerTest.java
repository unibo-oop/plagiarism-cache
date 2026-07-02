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
import frogger.model.implementations.Eagle;
import frogger.model.implementations.RandomEaglesSpawner;
import frogger.model.interfaces.EntitySpawner;

/**
 * Test class for RanodomEaglesSpawner.
 */
final class RandomEaglesSpawnerTest {

    private Random mockRandom;
    private int bound;
    private int boundY;

    @BeforeEach
    void setUp() {
        mockRandom = mock(Random.class);
        bound = Constants.MAX_EAGLES_NUMBER - Constants.MIN_EAGLES_NUMBER + 1;
        boundY = Constants.MAX_Y - Constants.MIN_Y + 1;
    }

    @Test
    void numberTest() {
        List<Eagle> entity;

        final EntitySpawner<Eagle> spawner = new RandomEaglesSpawner(mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        when(mockRandom.nextInt(boundY)).thenReturn(1);

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(bound)).thenReturn(0);
        entity = spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER);
        assertEquals(entity.size(), Constants.MIN_EAGLES_NUMBER);

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(bound)).thenReturn(bound - 1);
        entity = spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER);
        assertEquals(entity.size(), Constants.MAX_EAGLES_NUMBER);
    }

    @Test
    void generatePositionTest() {
        final int boundX = Constants.MAX_X - Constants.MIN_X + 1;

        //Just checking if the position generated is correct, so is always valid
        final EntitySpawner<Eagle> spawner = new RandomEaglesSpawner(mockRandom) {
            @Override
            protected boolean isValidPosition(final Position pos, final Set<Position> used) {
                return true;
            }
        };

        when(mockRandom.nextInt(boundY)).thenReturn(1);

        //Check the x coordinate:

        //Check with 0, should return the minimum value
        when(mockRandom.nextInt(boundX)).thenReturn(0);
        spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().x(), Constants.MIN_X));

        //Check with bound - 1, should return the maximum value
        when(mockRandom.nextInt(boundX)).thenReturn(boundX - 1);
        spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().x(), Constants.MAX_X));

        //Check the y coordinate:

        //Check with true, should return Constants.MIN_Y - 1
        when(mockRandom.nextBoolean()).thenReturn(true);
        spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().y(), Constants.MIN_Y - 1));

        //Check with false, should return Constants.MAX_Y + 1
        when(mockRandom.nextBoolean()).thenReturn(false);
        spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER)
        .forEach(e -> assertEquals(e.getPos().y(), Constants.MAX_Y + 1));
    }

    @Test
    void validPositionTest() {
        final List<Eagle> entity;

        when(mockRandom.nextInt(boundY)).thenReturn(1);

        //Checking if it works with a scenario where there is no overlap
        final EntitySpawner<Eagle> spawner = new RandomEaglesSpawner(mockRandom) {
            private static final int I = 0;
            @Override
            protected Position generatePosition() {
                return new Position(I, 0);
            }
        };

        entity = spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER);

        final Set<Position> occupied = new HashSet<>();
        entity.forEach(e -> occupied.add(e.getPos()));

        assertEquals(occupied.size(), entity.size());

        //Checking if it works with a scenario where there is an overlap
        final EntitySpawner<Eagle> spawner1 = new RandomEaglesSpawner(mockRandom) {
            @Override
            protected Position generatePosition() {
                return new Position(0, 0);
            }
        };

        //In case of an overlap the method should cycle until the overlap it's fixed,
        //but since the position is fixed it exceed the max number of iterations,
        //returning an empty list.
        assertEquals(List.of(), spawner1.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER));
    }

    @Test
    void triggerRowTest() {
        final int boundY = Constants.MAX_Y - Constants.MIN_Y + 1;
        final int errorValue = Constants.MIN_Y - 1;

        final EntitySpawner<Eagle> spawner = new RandomEaglesSpawner(mockRandom);

        //Check with Constants.MIN_Y, should return the errorValue
        when(mockRandom.nextInt(boundY)).thenReturn(0);
        spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER)
        .forEach(e -> assertEquals(e.getTrigger(), errorValue));

        //Check with Constants.MAX_Y, should return the errorValue
        when(mockRandom.nextInt(boundY)).thenReturn(boundY - 1);
        spawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER)
        .forEach(e -> assertEquals(e.getTrigger(), errorValue));
    }
}
