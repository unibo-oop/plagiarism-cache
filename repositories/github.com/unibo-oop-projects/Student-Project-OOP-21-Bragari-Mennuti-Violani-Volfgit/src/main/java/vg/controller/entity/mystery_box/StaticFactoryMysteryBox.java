package vg.controller.entity.mystery_box;

import vg.model.mystery_box.EAbility;
import vg.model.mystery_box.factory.AbstractFactoryMysteryBox;
import vg.model.mystery_box.factory.ConcreteFactoryEasyMysteryBox;
import vg.view.entity.StaticFactoryEntityBlock;

/**
 * Factory class for creating a mystery box controller.
 */
public final class StaticFactoryMysteryBox {
    private StaticFactoryMysteryBox() {
    }
    private static MysteryBoxController createRandom(final EAbility eAbility) {
        switch (eAbility) {
            case FREEZE_TIME:
                return createFreezeTime();
            case KILL_ALL_MOSQUITOES:
                return createKillMosquitoes();
            case SCORE:
                return createScore();
            case SPEED_UP:
                return createSpeed();
            default:
                throw new IllegalArgumentException("Unknown ability: " + eAbility);
        }
    }
    /**
     * Creates random  mystery box controller.
     * @return a new mystery box controller.
     */
    public static MysteryBoxController createRandomMysteryBoxDefault() {
        return createRandom(EAbility.randomAll());
    }
    /**
     * Creates freeze time mystery box controller.
     * @return a new mystery box controller.
     */
    public static MysteryBoxController createFreezeTime() {
        final AbstractFactoryMysteryBox factory = new ConcreteFactoryEasyMysteryBox();
        final var model = factory.createFreezeTime();
        final var view = StaticFactoryEntityBlock.createMysteryBox(model.getPosition(), model.getDimension());

        return new MysteryBoxControllerImpl(model, view);
    }
    /**
     * Creates kill mosquitoes mystery box controller.
     * @return a new mystery box controller.
     */
    public static MysteryBoxController createKillMosquitoes() {
        final AbstractFactoryMysteryBox factory = new ConcreteFactoryEasyMysteryBox();
        final var model = factory.createKillMosquitoes();
        final var view = StaticFactoryEntityBlock.createMysteryBox(model.getPosition(), model.getDimension());

        return new MysteryBoxControllerImpl(model, view);
    }
    /**
     * Creates score mystery box controller.
     * @return a new mystery box controller.
     */
    public static MysteryBoxController createScore() {
        final AbstractFactoryMysteryBox factory = new ConcreteFactoryEasyMysteryBox();
        final var model = factory.createScore();
        final var view = StaticFactoryEntityBlock.createMysteryBox(model.getPosition(), model.getDimension());

        return new MysteryBoxControllerImpl(model, view);
    }
    /**
     * Creates speed mystery box controller.
     * @return a new mystery box controller.
     */
    public static MysteryBoxController createSpeed() {
        final AbstractFactoryMysteryBox factory = new ConcreteFactoryEasyMysteryBox();
        final var model = factory.createSpeedUp();
        final var view = StaticFactoryEntityBlock.createMysteryBox(model.getPosition(), model.getDimension());

        return new MysteryBoxControllerImpl(model, view);
    }
}
