package it.unibo.isaccoop.graphics.factory;

import it.unibo.isaccoop.graphics.GraphicsComponent;

/**
 * Graphics component factory implementation for enemies.
 * */
public final class ConcreteEnemyGraphicsComponentFactory extends AbstractGraphicsComponentFactory
        implements EnemyGraphicsComponentFactory {

    @Override
    public GraphicsComponent getNonShootingGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("nonShootingEnemy1.gif");
    }

    @Override
    public GraphicsComponent getShootingGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("shootingEnemy.gif");
    }

    @Override
    public GraphicsComponent getBossGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("EnemyBoss.gif");
    }

    @Override
    public GraphicsComponent getEnemyBaseWeaponShotGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("enemyShoot.gif");
    }

    @Override
    public GraphicsComponent getBossBaseWeaponShotGraphicsComponent() {
        return super.getElementAsImageGraphicsComponent("shotBoss.gif");
    }
}
