package model.components;

/**
 * Give to the entity the possibility to jump.
 */
public interface Jump {

    /**
     * Do a jump.
     */
    void jump();

    /**
     * Do a jump of a requested height.
     * 
     * @param requestedHeight the jump height
     */
    void jumpFromExternalForce(float requestedHeight);

    /**
     * Do a jump selecting the grater height between the requested one and the
     * entity one.
     * 
     * @param requestedHeight the requested height to jump
     */
    void jumpMax(float requestedHeight);

}
