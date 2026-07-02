package model.minigames.perilouspath;

import java.util.Random;

import utility.Pair;

/**
 * This abstract class implements the common aspect of {@link Mine}.
 */
public abstract class AbstractMine implements Mine {

    private final Pair<Integer, Integer> mine;
    private MineState mineState;

    /**
     * Constructor of {@link Mine}.
     * 
     * @param size
     *          the upper bound's size
     */
    public AbstractMine(final int size) {
        final Random random = new Random();
        this.mine = new Pair<>(random.nextInt(size), random.nextInt(size));
        this.mineState = MineState.UNDETONATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getMinePosition() {
        return this.mine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExploded() {
        this.mineState = MineState.EXPLODED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExploded() {
        return this.mineState.isExploded();
    }
}
