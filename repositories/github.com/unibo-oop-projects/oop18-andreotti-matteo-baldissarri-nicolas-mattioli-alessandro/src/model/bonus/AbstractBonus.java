package model.bonus;

import java.awt.Toolkit;

import model.entities.AbstractEntity;
import model.entities.Character;
import model.entities.Environment;
import model.entities.Position;
import utils.Pair;

/**
 * 
 * Abstract Bonus class.
 *
 */
public abstract class AbstractBonus extends AbstractEntity implements Bonus {

    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SIGHT_FLOORS = 11;

    /**
     * 
     * The dimension of bonus image.
     * 
     */
    private final Pair<Double, Double> size;

    private boolean inGame = false;

    /**
     * 
     * @param size        The size of Bonus.
     * @param environment The bonus's environment
     * 
     */
    public AbstractBonus(final Pair<Double, Double> size, final Environment environment) {
        super(environment);
        this.size = size;
    }

    /**
     * 
     * @return The Position of Bonus.
     * 
     */
    public Position getPosition() {
        return this.getEnvironment().getPosition();
    }

    /**
     * Set the new bonus position.
     * 
     * @param position The new bonus Position.
     */
    public void setPosition(final Position position) {
        this.getEnvironment().setPosition(position);
    }

    /**
     * @return The bonus's height
     */
    @Override
    public double getHeight() {
        return this.size.getY();
    }

    /**
     * @return The bonus's width
     */
    @Override
    public double getWidth() {
        return this.size.getX();
    }

    /**
     *
     * @param status The status of bonus, if is in game or not.
     */
    public void setInGame(final boolean status) {
        this.inGame = status;
    }

    /**
     * 
     * @return The status of Bonus
     */
    public boolean isInGame() {
        return this.inGame;
    }

    /**
     * Allows the Bonus to move.
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
     * Let the bonus come down when the stuntman rises.
     */
    @Override
    public void shiftDown() {
        if (this.checkInGame()) {
            this.move(0, SCREEN_HEIGHT / SIGHT_FLOORS);
        }
    }

    /**
     * Check if the bonus is in game, otherwise set spawned to false.
     * 
     * @return If the bonus is in game
     */
    private boolean checkInGame() {
        if (this.getEnvironment().getShape().getVertices()[3].y >= SCREEN_HEIGHT) {
            this.setInGame(false);
            return false;
        }
        return true;
    }

    /**
     * Applies the bonus effect.
     * 
     * @param stuntman The stuntman to whom the bonus effect applies
     */
    public abstract void applyEffect(Character stuntman);

}
