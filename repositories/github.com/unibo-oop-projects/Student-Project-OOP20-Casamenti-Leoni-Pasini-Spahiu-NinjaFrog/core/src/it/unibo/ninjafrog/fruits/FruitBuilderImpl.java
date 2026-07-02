package it.unibo.ninjafrog.fruits;

import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * Definition of {@link it.unibo.ninjafrog.fruits.FruitBuilder FruitBuilder}
 * implementation.
 */
public final class FruitBuilderImpl implements FruitBuilder {
    private PlayScreen screen;
    private float x;
    private float y;
    private FruitType type;

    /**
     * private constructor of a FruitBuilderImpl.
     * 
     */
    private FruitBuilderImpl() {
    }

    /**
     * public static newBuilder that use FruitBuilderImpl.
     * 
     * @return FruitBuilderImpl new FruitBuilder object.
     */
    public static FruitBuilderImpl newBuilder() {
        return new FruitBuilderImpl();
    }

    @Override
    public FruitBuilder selectScreen(final PlayScreen screen) {
        this.screen = screen;
        return this;
    }

    @Override
    public FruitBuilder chooseXPosition(final float x) {
        this.x = x;
        return this;
    }

    @Override
    public FruitBuilder chooseYPosition(final float y) {
        this.y = y;
        return this;
    }

    @Override
    public FruitBuilder selectFruitType(final FruitType type) {
        this.type = type;
        return this;
    }

    @Override
    public FruitPowerUp build() {
        if (this.screen == null) {
            throw new IllegalStateException("Screen can't be null.");
        }
        if (this.type == null) {
            throw new IllegalStateException("Type can't be null.");
        }
        return new FruitPowerUpImpl(this.screen, this.x, this.y, this.type);
    }

}
