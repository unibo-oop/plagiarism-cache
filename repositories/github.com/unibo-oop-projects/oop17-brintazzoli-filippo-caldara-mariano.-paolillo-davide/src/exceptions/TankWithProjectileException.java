package exceptions;

/**
 * Exception threw when a @{@linkTank} and a {@link Projectile} collide each
 * other.
 */
public class TankWithProjectileException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = -5491542338022860387L;

    /**
     * Constructor.
     */
    public TankWithProjectileException() {
        super();
    }

    /**
     * Constructor.
     * @param msg
     *          {@inheritDoc IllegalStateException#IllegalStateException(String)}
     */
    public TankWithProjectileException(final String msg) {
        super(msg);
    }

}
