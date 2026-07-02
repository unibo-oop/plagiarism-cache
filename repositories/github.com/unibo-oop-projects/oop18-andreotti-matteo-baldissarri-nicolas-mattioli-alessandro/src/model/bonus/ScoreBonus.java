package model.bonus;

import model.entities.Character;
import model.entities.Environment;
import utils.Pair;

/**
 * 
 * Implementation of ScoreBonus Class.
 *
 */
public final class ScoreBonus extends AbstractBonus {

    /**
     * ScoreBonus constructor.
     * 
     * @param size        The Size of ScoreBonus.
     * @param environment The environment of ScoreBonus
     */
    public ScoreBonus(final Pair<Double, Double> size, final Environment environment) {
        super(size, environment);
    }

    @Override
    public void applyEffect(final Character stuntman) {
        int score = stuntman.getCounterFloors().getValue() / 10;
        for (int i = 0; i < score; i++) {
            stuntman.getCounterFloors().increment();
        }
    }

    @Override
    public void moveUp() {
        throw new UnsupportedOperationException("Invalid operation for movement of ScoreBonus.");

    }

    /**
     * Move the ScoreBonus down.
     */
    public void moveDown() {
        throw new UnsupportedOperationException("Invalid operation for movement of ScoreBonus.");
    }

    @Override
    public void moveLeft() {
        throw new UnsupportedOperationException("Invalid operation for movement of ScoreBonus.");

    }

    @Override
    public void moveRight() {
        throw new UnsupportedOperationException("Invalid operation for movement of ScoreBonus.");

    }

}
