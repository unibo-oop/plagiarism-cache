package model.entities.properties;

/**
 * Class that represent the entity's velocity in the game.
 */
public interface Velocity {

    /**
     * @param v
     *          the velocity of the entity
     * @return new velocity
     */
    Velocity sum(Velocity v);

    /**
     * @return the velocity of the entity
     */
    double module();

    /**
     * @param fact
     *          use to compute a new velocity
     * @return new velocity
     */
    Velocity mul(double fact);

    /**
     * Getter velocity axis x.
     *
     * @return the velocity axis x.
     */
    double getX();

    /**
     * Getter velocity axis y.
     *
     * @return the velocity axis y.
     */
    double getY();

}
