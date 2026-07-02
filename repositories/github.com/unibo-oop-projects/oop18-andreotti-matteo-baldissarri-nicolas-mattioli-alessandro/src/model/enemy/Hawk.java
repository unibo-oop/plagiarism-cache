package model.enemy;

import java.awt.Toolkit;

import model.entities.Environment;
import utils.Pair;

/**
 * Implements the Hawk.
 */
public final class Hawk extends AbstractEnemy implements Enemy {

    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double RATIO = 30;
    private static final double SPEED = (SCREEN_WIDTH / 12.5) / RATIO;

    /**
     * Hawk builder.
     * 
     * @param dimension   The dimension of Hawk.
     * @param environment The hawk's environment
     */
    public Hawk(final Pair<Double, Double> dimension, final Environment environment) {
        super(dimension, environment);
    }

    @Override
    public void moveUp() {
        throw new UnsupportedOperationException("Invalid operation for movemnt of Hawk");
    }

    /**
     * Move hawk down when stuntman move up.
     */
    public void moveDown() {
        this.move(0, this.getHeight() / 10);
    }

    @Override
    public void moveLeft() {
        throw new UnsupportedOperationException("Invalid operation for movemnt of Hawk");
    }

    @Override
    public void moveRight() {
        this.move(SPEED, 0);
    }

    @Override
    public boolean checkInGame() {
        if (this.getEnvironment().getShape().getVertices()[0].x >= SCREEN_WIDTH) {
            this.setInGame(false);
            return false;
        }
        return true;
    }

}
