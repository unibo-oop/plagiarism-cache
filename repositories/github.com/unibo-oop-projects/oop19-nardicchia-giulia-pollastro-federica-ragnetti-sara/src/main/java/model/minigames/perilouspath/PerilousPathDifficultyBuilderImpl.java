package model.minigames.perilouspath;

import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;

import model.DifficultyLevel;
import utility.Pair;

/**
 * Implementation of {@link PerilousPathDifficultyBuilder}.
 */
public class PerilousPathDifficultyBuilderImpl implements PerilousPathDifficultyBuilder {

    /**
     * The size's lower bound.
     */
    private static final int MINIMUM_SIZE = 4;

    /**
     * The initial size.
     */
    private static final int SIZE = 5;

    /**
     * The initial amount of mines.
     */
    private static final int NUM_MINES = 10;

    private EnumMap<DifficultyLevel, Pair<Integer, Integer>> difficulty;
    private Optional<DifficultyLevel> optCurrentDifficultyLevel;
    private Optional<Integer> optSize;
    private Optional<Integer> optNumMines;
    private boolean built;

    /**
     * Constructor of {@link PerilousPathDifficultyBuilder}.
     */
    public PerilousPathDifficultyBuilderImpl() {
        this.difficulty = this.initDefaultDifficulty();
        this.optCurrentDifficultyLevel = Optional.empty();
        this.optSize = Optional.empty();
        this.optNumMines = Optional.empty();
        this.built = false;
    }

    private int increment(final DifficultyLevel difficultyLevel) {
        Objects.requireNonNull(difficultyLevel);
        return difficultyLevel.ordinal();
    }

    private EnumMap<DifficultyLevel, Pair<Integer, Integer>> initDefaultDifficulty() {
        this.difficulty = new EnumMap<>(DifficultyLevel.class);
        for (final DifficultyLevel d : DifficultyLevel.values()) {
            this.difficulty.put(d, new Pair<>(SIZE + this.increment(d), NUM_MINES * this.increment(d) + NUM_MINES));
        }
        return this.difficulty;
    }

    private static void check(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }

    private static void validity(final boolean b) {
        if (b) {
           throw new IllegalArgumentException();
        }
    }

    private static <X> Optional<X> reassignOptional(final Optional<X> opt, final X x) {
        check(!opt.isPresent());
        return Optional.of(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PerilousPathDifficultyBuilder setDifficultyLevel(final DifficultyLevel currentDifficultyLevel) {
        check(!this.built);
        this.optCurrentDifficultyLevel = reassignOptional(this.optCurrentDifficultyLevel, currentDifficultyLevel);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PerilousPathDifficultyBuilder setSize(final int size) {
        check(!this.built);
        this.optSize = reassignOptional(this.optSize, size);
        validity(size < MINIMUM_SIZE);
        final int numMines = this.difficulty.get(this.optCurrentDifficultyLevel.get()).getY();
        this.difficulty.replace(this.optCurrentDifficultyLevel.get(), new Pair<>(size, numMines));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PerilousPathDifficultyBuilder setNumMines(final int numMines) {
        check(!this.built);
        this.optNumMines = reassignOptional(this.optNumMines, numMines);
        final int size = this.difficulty.get(this.optCurrentDifficultyLevel.get()).getX();
        validity(numMines >= size * size);
        this.difficulty.replace(this.optCurrentDifficultyLevel.get(), new Pair<>(size, numMines));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PerilousPathDifficulty build() {
        check(!this.built);
        check(this.optCurrentDifficultyLevel.isPresent());
        this.built = true;

        return new PerilousPathDifficulty() {

            private Pair<Integer, Integer> getValue(final DifficultyLevel difficultyLevel) {
                return difficulty.get(difficultyLevel);
            }

            @Override
            public int getSize() {
                return this.getValue(optCurrentDifficultyLevel.get()).getX();
            }

            @Override
            public int getNumMines() {
                return this.getValue(optCurrentDifficultyLevel.get()).getY();
            }

        };
    }
}
