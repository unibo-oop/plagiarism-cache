package model.entities;

import model.property.Life;
import model.property.Property;
import utils.Counter;
import utils.CounterFloor;
import utils.Pair;

/**
 * This class provides a skeletal implementation of the {@link Character}
 * interface, to minimize the effort required to implement this interface.
 */
public abstract class AbstractCharacter extends AbstractEntity implements Character {

    private final Pair<Double, Double> characterShift;
    private final Property life;
    private boolean climbing;
    private final Counter scaledFloors;

    /**
     * Defines the character based on the game map.
     * 
     * @param initPos     The initial position of the character
     * @param shift       The displacement size of the character
     * @param environment The character's environment
     */
    protected AbstractCharacter(final Position initPos, final Pair<Double, Double> shift,
            final Environment environment) {
        super(environment);
        this.characterShift = shift;
        this.life = new Life();
        this.climbing = false;
        this.scaledFloors = new CounterFloor();
    }

    /**
     * Set if the character is climbing a floor.
     * 
     * @param climbing The status of the character
     */
    @Override
    public void setClimbing(final boolean climbing) {
        this.climbing = climbing;
    }

    /**
     * Allows to move forward.
     */
    @Override
    public void moveUp() {
        this.move(0, 0);
    }

    /**
     * Allows to move left.
     */
    @Override
    public void moveLeft() {
        this.move(-this.characterShift.getX(), 0);
    }

    /**
     * Allows to move right.
     */
    @Override
    public void moveRight() {
        this.move(this.characterShift.getX(), 0);
    }

    /**
     * Allows the character to move.
     * 
     * @param x Where to move horizontally
     * @param y Where to move vertically
     */
    private void move(final double x, final double y) {
        this.getEnvironment().move(x, y);
    }

    /**
     * @return The displacement values ​​of the character
     */
    protected Pair<Double, Double> getCharacterShift() {
        return this.characterShift;
    }

    /**
     * @return If the character is climbing a floor
     */
    @Override
    public boolean isClimbing() {
        return this.climbing;
    }

    /**
     * @return The character's life
     */
    public Property getLife() {
        return this.life;
    }

    /**
     * @return The floors scaled by the character
     */
    @Override
    public Counter getCounterFloors() {
        return this.scaledFloors;
    }

    @Override
    public abstract void moveDown();

    @Override
    public abstract double getHeight();

    @Override
    public abstract double getWidth();

}
