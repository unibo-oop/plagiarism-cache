package breakout.model.physics;

/**
 * Handle the bounce of something hitting an object.
 * 
 * @param <X>
 *            a game object
 */
public interface BounceHandler<X extends GameObject> {

    /**
     * Makes an object bounce in a specified way.
     * 
     * @param who
     *            the bouncing object
     * @param which
     *            the hit object
     */
    void computeBounce(final X who, final GameObject which);

}
