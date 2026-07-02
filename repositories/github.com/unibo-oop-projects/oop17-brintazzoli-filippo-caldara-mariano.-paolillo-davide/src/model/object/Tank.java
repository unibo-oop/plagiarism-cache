package model.object;

import model.input.Input;
import model.utility.Pair;

/**
 * Interface that represents a Tank.
 */
public interface Tank {
    /** 
     * Dimension of Tank.
     */
    Pair<Double, Double> DIMENSION = new Pair<>(25.0, 25.0);
    /**
     * Get if the tank is alive.
     * 
     * @return true if it's alive
     */
    boolean isAlive();

    /**
     * Getter for nr. of lifes.
     * 
     * @return number of lifes
     */
    int getLifes();

    /**
     * Getter for position of Tank.
     * 
     * @return {@linkplain Pair} with coordinates of Tank
     */
    Pair<Double, Double> getPosition();

    /**
     * Set the position of tank.
     * 
     * @param position
     *            {@linkplain Pair} with coordinates of Tank
     */
    void setPosition(Pair<Double, Double> position);

    /**
     * Inflict damage to itself.
     * 
     * @param damage
     *            nr of lifes to subtract
     */
    void damage(int damage);

    /**
     * Shot a projectile with the direction of cannon.
     * 
     * @return {@linkplain Projectile}
     */
    Projectile shot();
    /**
     * Update status of tank with new input.
     * 
     * @param i
     *            {@linkplain Input}
     */
    void update(Input i);

    /**
     * Getter for dimension of tank.
     * 
     * @return {@link Pair} with height and widht of tank
     */
    static Pair<Double, Double> getDimension() {
        return DIMENSION;
    } 
    /**
     * Getter for position of cannon's tank.
     * 
     * @return {@linkplain Pair} with coordinates of cannon's tank
     */
    Pair<Double, Double> getCannonPosition();

    /**
     * Getter for dimension of cannon's tank.
     * 
     * @return {@linkplain Pair}
     */
    Pair<Double, Double> getCannonDimension();

    /**
     * Getter for angle of cannon's tank.
     * 
     * @return angle of cannon's tank.
     */
    double getAngle();
}
