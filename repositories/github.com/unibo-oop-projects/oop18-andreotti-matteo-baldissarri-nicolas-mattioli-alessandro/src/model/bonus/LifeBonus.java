package model.bonus;

import model.entities.Character;
import model.entities.Environment;
import utils.Pair;

/**
 * 
 * Implementation of LifeBomus Class.
 * 
 */
public final class LifeBonus extends AbstractBonus {

    /**
     * LifeBonus constructor.
     * 
     * @param size        The Size of LifeBonus.
     * @param environment The environment of LifeBonus
     */
    public LifeBonus(final Pair<Double, Double> size, final Environment environment) {
        super(size, environment);
    }

    @Override
    public void applyEffect(final Character stuntman) {
        stuntman.getLife().increment();
    }

    @Override
    public void moveUp() {
        throw new UnsupportedOperationException("Invalid operation for movement of LifeBonus.");

    }

    /**
     * Move the LifeBonus down.
     */
    public void moveDown() {
        throw new UnsupportedOperationException("Invalid operation for movement of LifeBonus.");
    }

    @Override
    public void moveLeft() {
        throw new UnsupportedOperationException("Invalid operation for movement of LifeBonus.");

    }

    @Override
    public void moveRight() {
        throw new UnsupportedOperationException("Invalid operation for movement of LifeBonus.");

    }
}
