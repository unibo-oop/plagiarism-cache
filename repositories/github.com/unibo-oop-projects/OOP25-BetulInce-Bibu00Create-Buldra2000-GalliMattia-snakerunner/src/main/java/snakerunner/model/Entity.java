package snakerunner.model;

/**
 * The Entity class represents a generic entity in the Snake Runner game.
 */
public class Entity {
    /*
    * X-coordinate of the entity.
    */
    private int x;

    /*
    * Y-coordinate of the entity
    */
    private int y;

    /**
     * Default constructor fon Entity.
     * Initializes a new instance for default values.
     */
    public Entity() {
    //Default constructor
    }

    /**
     * Constructs an Entity with coordinates.
     *
     * @param x set X-coordinate.
     * @param y set Y-coordinate.
     */
    protected Entity(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * generate the entity.
     */
    public void generate() {

    }

    /**
     * To read the coordinates.
     * 
     * @return X-coordinate.
     */
    public int getX() {
       return x;
    }

    /**
     * To read the coordinates.
     * 
     * @return Y-coordinate.
     */
    public int getY() {
       return y;
    }

    /**
     * Setting the coordinates.
     * 
     * @param x set X-coordinate.
     */
    public final void setX(final int x) {
        this.x = x;
    }

    /**
     * Setting the coordinates.
     * 
     * @param y set Y-coordinate.
     */
    public final void setY(final int y) {
        this.y = y;
    }
}
