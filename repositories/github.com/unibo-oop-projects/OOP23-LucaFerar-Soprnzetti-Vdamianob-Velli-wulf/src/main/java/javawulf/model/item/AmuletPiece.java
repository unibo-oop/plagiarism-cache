package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * The AmuletPiece class represents a piece of the amulet that the player must
 * collect to win the game. He should collect all 4 pieces.
 */
public final class AmuletPiece extends AbstractCollectable {

    private static final int POINTS = 500;

    /**
     * Creates a new AmuletPiece.
     * @param position the position of the piece
     */
    public AmuletPiece(final Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(final Player p) {
        p.collectAmuletPiece(this);
    }

    /**
     * Checks if the player is aligned with the piece.
     * @param p the player
     * @return true if the player is aligned with the piece, false otherwise
     */
    public boolean isPlayerAligned(final Player p) {
        return p.getPosition().getX().equals(this.getPosition().getX())
                || p.getPosition().getY().equals(this.getPosition().getY());
    }

}
