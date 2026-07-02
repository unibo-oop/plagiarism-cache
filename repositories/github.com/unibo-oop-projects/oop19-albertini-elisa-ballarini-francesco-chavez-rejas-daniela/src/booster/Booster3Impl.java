package booster;

import java.awt.Color;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import pair.Pair;
import piece.Piece;
import piece.PieceImpl;
import piece.Type;
import playcontroller.PlayController;

/**
 * Class that implements the third booster: cancel the lower line in the grid of
 * the game.
 */
public class Booster3Impl implements Booster {

    private static final int ACTIVATE_BOOSTER_RANGE = 50;
    private int lastPoints;
    private boolean status;

    /**
     * @param status : initial status of the booster.
     */
    public Booster3Impl(final boolean status) {
        this.status = status;
    }

    @Override
    public final void useBooster(final PlayController playController) {
        this.controllStatus(playController.getGame().getScore().getScore());
        if (this.status) {
            this.lastPoints = playController.getGame().getScore().getScore();
            final Set<Pair<Integer, Integer>> tempSet = new HashSet<>();
            tempSet.add(new Pair<>(0, 0));
            final Piece tempBlock = new PieceImpl(Type.CUSTOM, Optional.of(tempSet), Optional.of(Color.BLACK),
                    Optional.of(new Pair<>(0, 0)));
            tempBlock.resetPiece();
            playController.getGame().setCurrent(tempBlock);
            playController.getView().drawPiece(playController.getGame().getCurrent().getPosition(),
                    playController.getGame().getCurrent().getColor());
            playController.getView().drawProjection(playController.getProjection().newProjection(),
                    playController.getProjection().getColor());
            this.status = false;
        }
    }

    private void controllStatus(final int currentPoints) {
            if (!this.status && currentPoints - this.lastPoints >= ACTIVATE_BOOSTER_RANGE) {
                this.status = true;
        }
    }

}
