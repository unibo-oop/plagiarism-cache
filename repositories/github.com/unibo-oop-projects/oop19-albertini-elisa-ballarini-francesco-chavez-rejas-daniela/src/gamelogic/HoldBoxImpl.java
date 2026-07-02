package gamelogic;

import piece.Piece;

/**
 * This class implements the interface {@link HoldBox}.
 *
 */
public final class HoldBoxImpl implements HoldBox {

    private boolean isHolding;
    private boolean canHold = true;
    private Piece hold;
    private final GameLogic game;

    /**
     * @param game : the current instance of GameLogic
     */
    public HoldBoxImpl(final GameLogic game) {
        this.game = game;
    }

    @Override
    public Piece hold() {
        final Piece tmp = this.hold.duplicate();
        this.hold = this.game.getCurrent().duplicate();
        this.hold.resetPiece();
        return tmp;
    }

    @Override
    public void firstHold() {
        this.hold = this.game.getCurrent().duplicate();
        this.hold.resetPiece();
        this.isHolding = true;
    }

    @Override
    public boolean isHolding() {
        return this.isHolding;
    }

    @Override
    public boolean canHold() {
        return this.canHold;
    }

    @Override
    public void setCanHold(final boolean bool) {
        this.canHold = bool;
    }

}
