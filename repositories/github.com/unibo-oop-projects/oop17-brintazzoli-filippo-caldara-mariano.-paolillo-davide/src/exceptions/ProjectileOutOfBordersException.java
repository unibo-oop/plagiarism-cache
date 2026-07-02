package exceptions;

/**
 * Exception threw when a {@link Projectile} goes out from the {@link World}
 * borders.
 */
public class ProjectileOutOfBordersException extends IllegalStateException {

    /**
     * 
     */
    private static final long serialVersionUID = 2435568105377350466L;

    /**
     * Constructor.
     */
    public ProjectileOutOfBordersException() {
        super();
    }

    /**
     * Constructor.
     * @param msg
     *          {@inheritDoc IllegalStateException#IllegalStateException(String)}
     */
    public ProjectileOutOfBordersException(final String msg) {
        super(msg);
    }

}
