package model.minigames.oneway;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import utility.Pair;

/**
 * Implementation of {@link PathDrawer}. This class generates the path from
 * an initial random position to a computed final position.
 *
 */
public class OneWayPathDrawerImpl implements PathDrawer {

    private final int size;
    private final int arrows;
    private final List<Direction> steps;

    private Pair<Integer, Integer> currentPosition;
    private Pair<Integer, Integer> initialPosition;
    private Pair<Integer, Integer> finalPosition;
    private Pair<Integer, Integer> prevFinishPosition;

    private final Predicate<Pair<Integer, Integer>> isClosedPath = currentPosition -> 
                                    currentPosition.getX() == this.initialPosition.getX()
                                    && currentPosition.getY() == this.initialPosition.getY();

    /**
     * Constructor of {@link PathDrawer}.
     * @param size
     *          the size of the playground main grid
     * @param arrows 
     *          the number of steps to compute
     */
    public OneWayPathDrawerImpl(final int size, final int arrows) {
        this.size = size;
        this.arrows = arrows;
        this.steps = new ArrayList<>();
    }

    private void setInitialPosition() {
        final Random random = new Random();
        this.currentPosition = new Pair<>(random.nextInt(this.size), random.nextInt(this.size));
        this.initialPosition = this.currentPosition;
    }

    private void ensureValidStart() {
        do {
            this.setInitialPosition();
        } while (this.initialPosition == this.prevFinishPosition);
    }

    private boolean checkX(final int x) {
        return x + this.currentPosition.getX() < 0 || x + this.currentPosition.getX() >= size;
    }

    private boolean checkY(final int y) {
        return y + this.currentPosition.getY() < 0 || y + this.currentPosition.getY() >= size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawPath() {
        this.ensureValidStart();
        this.steps.clear();
        IntStream.range(0, this.arrows).forEach(i -> {
            Direction dir = Direction.generateStep();
            Pair<Integer, Integer> increment = Direction.delta(dir);
            while (checkX(increment.getX()) || checkY(increment.getY())) {
                dir = Direction.generateStep();
                increment = Direction.delta(dir);
            }
            final int x = increment.getX() + this.currentPosition.getX();
            final int y = increment.getY() + this.currentPosition.getY();
            this.currentPosition = new Pair<>(x, y);
            this.steps.add(dir);
        });

        if (this.isClosedPath.test(currentPosition)) {
            this.drawPath();
        } else {
            this.finalPosition = this.currentPosition;
            this.prevFinishPosition = this.currentPosition;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getInitialPosition() {
        return this.initialPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getFinalPosition() {
        return this.finalPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Direction> getSteps() {
        return this.steps;
    }

}
