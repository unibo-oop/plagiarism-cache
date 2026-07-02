package it.unibo.isaccoop.graphics.factory;

import it.unibo.isaccoop.graphics.GraphicsComponent;

/**
 * Graphics component factory implementation for power ups.
 * */
public final class PowerUpGraphicsComponentImpl extends AbstractGraphicsComponentFactory implements PowerUpGraphicsComponent {

    @Override
    public GraphicsComponent getDamageUpGraphicsComponent(final Boolean isSuper) {
        return isSuper ? super.getElementAsImageGraphicsComponent("damage_super_up.png")
                : super.getElementAsImageGraphicsComponent("damage_up.png");
    }

    @Override
    public GraphicsComponent getCoinUpGraphicsComponent(final Boolean isSuper) {
        return isSuper ? super.getElementAsImageGraphicsComponent("coin_super_up.png")
                : super.getElementAsImageGraphicsComponent("coin_up.png");
    }

    @Override
    public GraphicsComponent getHealthUpGraphicsComponent(final Boolean isSuper) {
        return isSuper ? super.getElementAsImageGraphicsComponent("health_super_up.gif")
                : super.getElementAsImageGraphicsComponent("health_up.png");
    }

    @Override
    public GraphicsComponent getSpeedUpGraphicsComponent(final Boolean isSuper) {
        return isSuper ? super.getElementAsImageGraphicsComponent("speed_super_up.png")
                : super.getElementAsImageGraphicsComponent("speed_up.png");
    }

    @Override
    public GraphicsComponent getTearsUpGraphicsComponent(final Boolean isSuper) {
        return isSuper ? super.getElementAsImageGraphicsComponent("tears_super_up.gif")
                : super.getElementAsImageGraphicsComponent("tears_up.png");
    }

}
