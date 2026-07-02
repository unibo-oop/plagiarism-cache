package bubbleshooter.model.collision;

/**
 * Interface used to manage a generic {@link Collision}.
 * It's implemented by {@link GridCollisionHandler} and {@link BoundsCollisionHandler} through the Strategy pattern.
 */
public interface CollisionHandler {

    /**
     * The method which handle the {@link Collision}.
     */
    void handle();
}
