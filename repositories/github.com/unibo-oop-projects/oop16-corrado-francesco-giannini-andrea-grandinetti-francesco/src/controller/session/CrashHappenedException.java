package controller.session;

import model.exception.CrashException;

/**
 * An exception for crashes that happens during a session.
 */
public class CrashHappenedException extends CrashException {

    private static final long serialVersionUID = -8110696361891266764L;
    private final boolean lapEnd;

    /**
     * Constructor.
     * @param e the CrashException occured
     * @param lapEnd if the player has ended the lap or not
     */
    public CrashHappenedException(final CrashException e, final boolean lapEnd) {
        super(e.getCrashMap(), e.getPos());
        this.lapEnd = lapEnd;
    }

    /**
     * Getter.
     * @return if the player has ended the lap or not
     */
    public boolean isLapEnd() {
        return this.lapEnd;
    }

}
