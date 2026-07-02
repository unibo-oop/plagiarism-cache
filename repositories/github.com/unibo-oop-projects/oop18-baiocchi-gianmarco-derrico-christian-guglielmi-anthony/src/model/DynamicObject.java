package model;

/**
 *  Interface for moving objects.
 */
public interface DynamicObject extends AliveObject {

    /**
     * Process input entity.
     */
    void processInput();

    /**
     * Makes the entity move to an other {@link Position}.
     * @param elapsedTime : passed time between two frames.
     */
    void move(long elapsedTime);

}
