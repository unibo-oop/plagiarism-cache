package frogger.model.implementations;

import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;

/**
 * Class that extends AbstractRandomEntitySpawner to specify the behaviour spawning type MovingObject (Car or Trunk) entity.
 * @param <X> generic type that extends MovingObjectImpl (Trunk or Car)
 */
public class RandomObstaclesSpawner<X extends MovingObject> extends AbstractRandomEntitySpawner<X> {

    private final Random ran;
    private final Class<X> type;
    private final int y;
    private final float speed;
    private final Direction direction;
    private final MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
    private int width;

    /**
     * Since speed, y and direction don't depend on this class they need to be passed.
     * @param type the type of obstacle that will be created
     * @param y the y coordinate of the position
     * @param speed the speed of the obstacle
     * @param direction the direction of the obstacle
     * @param ran random injection to be able to test it
     */
    @SuppressFBWarnings(
        value = "EI2",
        justification = "Random is intentionally injected to allow deterministic behavior during testing"
    )
    public RandomObstaclesSpawner(final Class<X> type, final int y, final float speed, final Direction direction,
    final Random ran) {
        super(ran);
        this.ran = ran;
        this.type = type;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Position generatePosition() {
        this.width = getWidth();
        return new Position(randomX(), this.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValidPosition(final Position pos, final Set<Position> used) {
        return IntStream.range(0, this.width).noneMatch(i -> used.contains(new Position(pos.x() + i, this.y)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected X createEntity(final Position pos) {
       final Pair dim = new Pair(this.width, Constants.OBJECT_HEIGHT);
       return obstaclesFactory.createMovingObject(pos, dim, speed, direction, type);
    }

    /**
     * return the width of the obstacle based on the type, Car o Trunk.
     * @return the width
     */
    private int getWidth() {
        if (this.type.equals(Car.class)) {
            return ran.nextInt(Constants.MAX_CAR_WIDTH - Constants.MIN_CAR_WIDTH + 1) + Constants.MIN_CAR_WIDTH;
        } else {
            return ran.nextInt(Constants.MAX_TRUNK_WIDTH - Constants.MIN_TRUNK_WIDTH + 1) + Constants.MIN_TRUNK_WIDTH;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Modified the default behaviour to add more positions to the list of used positions instead of just one,
     * in particular add n = width positions.
     * </p>
     */
    @Override
    protected void addPos(final Position pos, final Set<Position> usedPositions) {
        IntStream.range(0, this.width).forEach(i -> usedPositions.add(new Position(pos.x() + i, this.y)));
    }

}
