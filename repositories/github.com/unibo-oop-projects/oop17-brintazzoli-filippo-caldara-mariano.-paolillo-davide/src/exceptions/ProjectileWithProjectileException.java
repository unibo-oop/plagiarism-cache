package exceptions;
/**
 * Exception threw when two {@link Projectile} collide each other.
 */
public class ProjectileWithProjectileException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = 8127253338298911574L;

    /**
     * Constructor.
     */
    public ProjectileWithProjectileException() {
        super();
    }

    /**
     * Constructor.
     * @param msg
     *          {@inheritDoc IllegalStateException#IllegalStateException(String)}
     */
    public ProjectileWithProjectileException(final String msg) {
        super(msg);
    }

}
