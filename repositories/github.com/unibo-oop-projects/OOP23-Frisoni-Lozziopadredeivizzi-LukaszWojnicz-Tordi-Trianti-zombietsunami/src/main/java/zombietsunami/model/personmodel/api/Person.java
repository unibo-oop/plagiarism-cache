package zombietsunami.model.personmodel.api;

/**
 * Defines the Person object.
 */
public interface Person {

    /**
     * Gets the X coordinate of the Person.
     * @return the X coordinate of the Person.
     */
    int getX();

    /**
     * Gets the Y coordinate of the Person.
     * @return the Y coordinate of the Person.
     */
    int getY();

    /** 
     * Sets the X coordinate of the Person.
     * @param x X coordinate.
    */
    void setX(int x);

    /** 
     * Sets the Y coordinate of the Person.
     * @param y Y coordinate.
    */
    void setY(int y);
}
