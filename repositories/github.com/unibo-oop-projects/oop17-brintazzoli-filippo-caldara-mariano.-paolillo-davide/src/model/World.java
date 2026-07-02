package model;

import model.input.Input;
import model.input.InputImpl;
import model.object.Tank;
import model.utility.Pair;
import model.factory.FactoryTank;
/**
 * Implementation of Model interface.
 */
public class World implements Model {
    private static final Pair<Double, Double> DIMENSION = new Pair<>(600.0, 400.0);
    private Tank player;
    private Tank enemy;
    private Input playerInput;
    private Input enemyInput;

    @Override
    public final void configPlayerTank(final Pair<Double, Double> position, final int lifes) {
        this.player = FactoryTank.createPlayer(position, lifes);
        this.playerInput = new InputImpl();
    }

    @Override
    public final void configEnemyTank(final Pair<Double, Double> position, final int lifes, final double speed,
            final double projectileSpeed) {
        this.enemy = FactoryTank.createEnemy(position, lifes, speed, projectileSpeed);
        this.enemyInput = new InputImpl();
    }

    @Override
    public final Tank getPlayer() {
        return this.player;
    }

    @Override
    public final Tank getEnemy() {
        return this.enemy;
    }

    @Override
    public final Pair<Double, Double> getBounds() {
        return DIMENSION;
    }

    @Override
    public final Input getPlayerInput() {
        return this.playerInput;
    }

    @Override
    public final Input getEnemyInput() {
        return this.enemyInput;
    }

}
