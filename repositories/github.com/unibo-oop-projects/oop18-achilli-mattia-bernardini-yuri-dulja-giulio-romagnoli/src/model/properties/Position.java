package model.properties;

/**
 * Class that represent the entity's position in the game.
 */
public interface Position {

    /**
     * @param v
     *        The velocity of the entity.
     * @return new Position
     */
    Position sum(Velocity v);

    /**
     * @param p
     *          The position of the entity
     * @return new Velocity
     */
    Velocity sub(Position p);

    /**
     * Getter position axis x.
     *
     * @return the position axis x.
     */
    double getX();

    /**
     * Getter position axis y.
     *
     * @return the position axis y.
     */
    double getY();
}
