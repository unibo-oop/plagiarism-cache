package model.minigames.perilouspath;

/**
 * Implementation of {@link MineFactory}.
 */
public class MineFactoryImpl implements MineFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Mine createSimpleMine(final int size) {
        return new SimpleMineImpl(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FragmentationMine createFragmentationMine(final int size) {
        return new FragmentationMineImpl(size);
    }
}
