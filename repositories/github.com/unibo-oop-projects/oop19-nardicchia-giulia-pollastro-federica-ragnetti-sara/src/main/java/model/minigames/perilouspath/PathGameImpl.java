package model.minigames.perilouspath;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

import utility.Pair;

/**
 * Implementations of {@link PathGame}.
 */
public final class PathGameImpl implements PathGame {

    private int size;
    private int numMines;
    private Pair<Integer, Integer> start;
    private Pair<Integer, Integer> finish;
    private LinkedList<Pair<Integer, Integer>> path;  //NOPMD

    /**
     * Private constructor of {@link PathGame}.
     */
    private PathGameImpl() { }

    private static Pair<Integer, Integer> getRandomPosition(final int size) {
        final Random random = new Random();
        return new Pair<>(random.nextInt(size), random.nextInt(size));
    }

    private static boolean adjacent(final Pair<Integer, Integer> position1, final Pair<Integer, Integer> position2) {
        Objects.requireNonNull(position1);
        Objects.requireNonNull(position2);
        return Math.abs(position1.getX() - position2.getX()) <= 1 && Math.abs(position1.getY() - position2.getY()) <= 1;
    }

    private static Pair<Integer, Integer> initStart(final int size) {
        return getRandomPosition(size);
    }

    private static Pair<Integer, Integer> initFinish(final int size, final Pair<Integer, Integer> start) {
        final Pair<Integer, Integer> finish = getRandomPosition(size);
        if (finish.equals(start) || adjacent(finish, start)) {
            return initFinish(size, start);
        } else {
            return finish;
        }
    }

    private static void check(final int value) {
        final Predicate<Integer> predicate = (i) -> i == 0;
        if (predicate.test(value)) {
            throw new IllegalStateException();
        }
    }

    private Direction getRandomDirection() {
        final int direction = new Random().nextInt(Direction.values().length);
        return Direction.values()[direction];
    }

    private boolean checkReachLimit(final Pair<Integer, Integer> lastPosition) {
        Objects.requireNonNull(lastPosition);
        return lastPosition.getX() < 0 || lastPosition.getY() < 0 || lastPosition.getX() >= this.size || lastPosition.getY() >= this.size;
    }

    private void setPath() {
        final Direction direction = this.getRandomDirection();
        final Pair<Integer, Integer> lastPosition;
        if (direction == Direction.RIGHT || direction == Direction.LEFT) {
            lastPosition = new Pair<>(this.path.getLast().getX(), this.path.getLast().getY() + direction.horizontal());
        } else {
            lastPosition = new Pair<>(this.path.getLast().getX() + direction.vertical(), this.path.getLast().getY());
        }
        if (!this.path.contains(lastPosition) && !this.checkReachLimit(lastPosition)) {
            this.path.add(lastPosition);
        } else {
            if (this.path.size() >= 2) {
                this.path.remove(this.path.getLast()); 
            }
            this.setPath();
        }
    }

    private void initPath() {
        this.path.clear();
        this.path.add(this.start);
        while (!this.path.getLast().equals(this.finish)) {
            if (this.path.size() < (this.size * this.size) - this.numMines) {
                this.setPath();
            } else {
                this.initPath();
            }
        }
    }

    private static PathGame createGenericRandomDirectionPath(final int size, final int numMines,
            final Pair<Integer, Integer> start, final Pair<Integer, Integer> finish) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(finish);
        final PathGameImpl pathGame = new PathGameImpl();
        pathGame.size = size;
        pathGame.numMines = numMines;
        pathGame.start = start;
        pathGame.finish = finish;
        pathGame.path = new LinkedList<>();
        pathGame.initPath();
        return pathGame;
    }

    /**
     * Create a path based on the random direction.
     * 
     * @param size
     *          the size of the current difficulty level
     * @param numMines
     *          the amount of mines of the current difficulty level
     *
     * @return a {@link PathGame} which is the game's path
     */
    public static PathGame createRandomDirectionPath(final int size, final int numMines) {
        check(size);
        check(numMines);
        final Pair<Integer, Integer> start = initStart(size);
        final Pair<Integer, Integer> finish = initFinish(size, start);
        return PathGameImpl.createGenericRandomDirectionPath(size, numMines, start, finish);
    }

    /**
     * Create a path based on the random direction with default initial step.
     * 
     * @param size
     *          the size of the current difficulty level
     * @param numMines
     *          the amount of mines of the current difficulty level
     *
     * @return a {@link PathGame} which is the game's path
     */
    public static PathGame createRandomDirectionPathDefaultStart(final int size, final int numMines) {
        check(size);
        check(numMines);
        final Pair<Integer, Integer> start = new Pair<>(0, 0);
        final Pair<Integer, Integer> finish = initFinish(size, start);
        return PathGameImpl.createGenericRandomDirectionPath(size, numMines, start, finish);
    }

    /**
     * Create a path based on the random direction with default end step.
     * 
     * @param size
     *          the size of the current difficulty level
     * @param numMines
     *          the amount of mines of the current difficulty level
     *
     * @return a {@link PathGame} which is the game's path
     */
    public static PathGame createRandomDirectionPathDefaultFinish(final int size, final int numMines) {
        check(size);
        check(numMines);
        final Pair<Integer, Integer> start = new Pair<>(size - 1, size - 1);
        final Pair<Integer, Integer> finish = initFinish(size, start);
        return PathGameImpl.createGenericRandomDirectionPath(size, numMines, finish, start);
    }

    /**
     * Create a path based on the random direction with default initial and end steps.
     * 
     * @param size
     *          the size of the current difficulty level
     * @param numMines
     *          the amount of mines of the current difficulty level
     *
     * @return a {@link PathGame} which is the game's path
     */
    public static PathGame createRandomDirectionPathDefaultStartFinish(final int size, final int numMines) {
        check(size);
        check(numMines);
        final Pair<Integer, Integer> start = new Pair<>(0, 0);
        final Pair<Integer, Integer> finish = new Pair<>(size - 1, size - 1);
        return PathGameImpl.createGenericRandomDirectionPath(size, numMines, start, finish);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<Pair<Integer, Integer>> getPathList() {
        return this.path;
    }
}
