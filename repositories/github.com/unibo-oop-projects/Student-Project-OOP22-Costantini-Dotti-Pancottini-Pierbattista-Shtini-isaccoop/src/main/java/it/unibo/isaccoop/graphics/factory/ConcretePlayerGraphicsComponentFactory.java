package it.unibo.isaccoop.graphics.factory;

import it.unibo.isaccoop.graphics.GraphicsComponent;

/**
 * Graphics component factory implementation for player.
 * */
public final class ConcretePlayerGraphicsComponentFactory extends AbstractGraphicsComponentFactory
        implements PlayerGraphicsComponentFactory {

    @Override
    public GraphicsComponent getPlayerGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("player.gif");
    }

    @Override
    public GraphicsComponent getPlayerBaseWeaponShotGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("playerShot.png");
    }
}
