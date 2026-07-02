package javawulf.model;

/**
 * GameElement represents an element of the game.
 */
public interface GameElement {

    /**
     * The size of an element.
     */
    int OBJECT_SIZE = 24;

    /**
     * @return The BoundingBox of the element
     */
    BoundingBox getBounds();

    /**
     * @return The current Coordinate of the element
     */
    Coordinate getPosition();

    /**
     * @param p The Coordinate the entity must now have
     */
    void setPosition(Coordinate p);
}
