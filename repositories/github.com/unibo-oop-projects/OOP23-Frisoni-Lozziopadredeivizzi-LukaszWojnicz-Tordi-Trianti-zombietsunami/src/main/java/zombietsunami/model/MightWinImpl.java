package zombietsunami.model;

import zombietsunami.Pair;
import zombietsunami.api.MightWin;

/**
 * This class implements the MightWin interface
 * {@link zombietsunami.api.MightWin}.
 */
public final class MightWinImpl implements MightWin {

    private static final int WIN_AXIS = 80;

    private Pair<Integer, Integer> endPos;
    private boolean mightWin;

    /**
     * Initializes the class filds.
     */
    public MightWinImpl() {
        this.endPos = new Pair<>(0, 0);
        this.mightWin = false;
    }

    @Override
    public void setEndPos(final int endX) {
        this.mightWin = true;
        this.endPos = new Pair<>(endX, null);
    }

    @Override
    public boolean isWin() {
        return this.endPos.getX() < WIN_AXIS && this.mightWin;
    }
}
