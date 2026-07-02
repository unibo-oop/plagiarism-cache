package model.input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete model implementation of Input for a Tank
 * <p>
 * This class bind {@link TankInput} and {@link CannonInput}. Both describes the
 * input for Tank.
 * 
 * @see TankImpl
 * @see Pair
 */
public class InputImpl implements Input {

    private Map<Direction, Boolean> movement;
    private Pair<Double, Double> target;
    /**
     * Constructor.
     */
    public InputImpl() {
        this.movement = new HashMap<>();
        Arrays.asList(Direction.values()).forEach(d -> this.movement.put(d, false));
        this.target = new Pair<>(0.0, 0.0);
    }
    /**
     * Constructor.
     * @param movement next movement to do.
     * @param target next target that cannon have to aim
     */
    public InputImpl(final Map<Direction, Boolean> movement, final Pair<Double, Double> target) {
        this.movement = movement;
        this.target = target;
    }

    @Override
    public final Pair<Double, Double> getTargetPosition() {
        return this.target;
    }

    @Override
    public final void setTarget(final Pair<Double, Double> target) {
        this.target = target;
    }

    @Override
    public final void setMovement(final Map<Direction, Boolean> movement) {
        this.movement = movement;
    }

    @Override
    public final Map<Direction, Boolean> getMovement() {
        return this.movement;
    }
}