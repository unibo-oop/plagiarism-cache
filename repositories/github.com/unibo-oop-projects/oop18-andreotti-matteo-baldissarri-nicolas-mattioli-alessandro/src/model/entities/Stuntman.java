package model.entities;

import utils.Pair;

/**
 * The Climber.
 */
public final class Stuntman extends AbstractCharacter implements Character {

    private static final double RATIO = 0.63;

    /**
     * Defines the stuntman based on the game map.
     * 
     * @param initPos     The initial position of the stuntman
     * @param shift       The displacement size of the stuntman
     * @param environment The stuntman's environment
     */
    public Stuntman(final Position initPos, final Pair<Double, Double> shift, final Environment environment) {
        super(initPos, shift, environment);
    }

    /**
     * @throws UnsupportedOperationException {@link Stuntman} cannot move down
     */
    @Override
    public void moveDown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getHeight() {
        return this.getCharacterShift().getY() * 2;
    }

    @Override
    public double getWidth() {
        return this.getCharacterShift().getX() * RATIO;
    }

}
