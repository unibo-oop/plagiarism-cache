package model.enemy;

import java.awt.Toolkit;

import model.entities.AbstractEntity;
import model.entities.Environment;
import model.entities.Position;
import utils.Pair;

/**
 * Abstract Enemy class.
 */
public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private boolean spawned = false;
    private final Pair<Double, Double> size;
    private static final double WINDOWS_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;

    /**
     * @param size        The size of Enemy.
     * @param environment The enemy's environment
     */
    public AbstractEnemy(final Pair<Double, Double> size, final Environment environment) {
        super(environment);
        this.size = size;
    }

    /**
     * Allows the stuntman to move.
     * 
     * @param x Where to move horizontally
     * @param y Where to move vertically
     */
    protected void move(final double x, final double y) {
        if (this.checkInGame()) {
            this.getEnvironment().move(x, y);
        }
    }

    /**
     * Let the enemy come down when the stuntman rises.
     */
    @Override
    public void shiftDown() {
        this.move(0, WINDOWS_HEIGHT);
    }

    /**
     * Check if the enemy is in game, otherwise set spawned to false.
     * 
     * @return If the enemy is in game
     */
    public abstract boolean checkInGame();

    /**
     * Set the new enemy position.
     * 
     * @param position The new Enemy Position.
     */
    public void setPosition(final Position position) {
        this.getEnvironment().setPosition(position);
    }

    /**
     * Set True if the Enemy is in game, otherwise false.
     * 
     * @param state The new State of Enemy.
     */
    public void setInGame(final boolean state) {
        this.spawned = state;
    }

    /**
     * @return True if the Enemy is in game, otherwise False.
     */
    public boolean isInGame() {
        return this.spawned;
    }

    /**
     * @return The enemy's height
     */
    @Override
    public double getHeight() {
        return this.size.getY();
    }

    /**
     * @return The enemy's width
     */
    @Override
    public double getWidth() {
        return this.size.getX();
    }

}
