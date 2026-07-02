package model.enemy;

import java.awt.Toolkit;

import model.entities.Environment;
import utils.Pair;

/**
 * Implements the Vase.
 */
public final class Vase extends AbstractEnemy implements Enemy {

    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double SIGHT_FLOORS = 11;
    private static final double RATIO = 15;
    private static final double SPEED = (SCREEN_HEIGHT / SIGHT_FLOORS) / RATIO;

    /**
     * Vase builder.
     * 
     * @param dimension   The dimension of vase.
     * @param environment The vase's environment
     */
    public Vase(final Pair<Double, Double> dimension, final Environment environment) {
        super(dimension, environment);
    }

    @Override
    public void moveUp() {
        throw new UnsupportedOperationException("Invalid operation for movemnt of Vase");
    }

    @Override
    public void moveDown() {
        this.move(0, SPEED);
    }

    @Override
    public void moveLeft() {
        throw new UnsupportedOperationException("Invalid operation for movemnt of Vase");
    }

    @Override
    public void moveRight() {
        throw new UnsupportedOperationException("Invalid operation for movemnt of Vase");
    }

    @Override
    public boolean checkInGame() {
        if (this.getEnvironment().getShape().getVertices()[0].y >= SCREEN_HEIGHT) {
            this.setInGame(false);
            return false;
        }
        return true;
    }
}
