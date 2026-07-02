package booster;

import java.util.HashSet;
import java.util.Set;

import gamelogic.Board;
import pair.Pair;
import playcontroller.PlayController;

/**
 * Booster2: Cancels the top line.
 *
 */
public class Booster2Impl implements Booster {
    private boolean boosterStatus;
    private int lastPoints;

    private static final int ACTIVATE_BOOSTER_RANGE = 30;

    /**
     * @param boosterStatus : true if the booster will be usable at the start of the
     *                      game, false otherwise
     */
    public Booster2Impl(final boolean boosterStatus) {
        this.boosterStatus = boosterStatus;
        this.lastPoints = 0;
    }

    @Override
    public final void useBooster(final PlayController playController) {
        this.controlStatus(playController.getGame().getScore().getScore());
        if (this.boosterStatus) {
            this.lastPoints = playController.getGame().getScore().getScore();
            playController.getGame().getBoard();
            // By default, it tries to cancel the higher row
            int rowNumber = Board.COLLENGTH - 1;
            // For each block, if it's in a row lower than rowNumber, the rowNumber gets
            // updated
            for (final Pair<Integer, Integer> coordinate : playController.getGame().getBoard().getBoard().keySet()) {
                if (coordinate.getX() < rowNumber) {
                    rowNumber = coordinate.getX();
                }
            }
            final Set<Integer> rowToDelete = new HashSet<>();
            rowToDelete.add(rowNumber);
            playController.getGame().getBoard().cancelLines(rowToDelete);
            playController.getGameManagement().eliminationRow();
            this.boosterStatus = false;
        }
    }

    /**
     * @param currentPoints : total points at the activation of the booster
     */
    private void controlStatus(final int currentPoints) {
        if (!this.boosterStatus && currentPoints - this.lastPoints >= ACTIVATE_BOOSTER_RANGE) {
            this.boosterStatus = true;
        }
    }
}
