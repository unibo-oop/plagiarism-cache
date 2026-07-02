package booster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import gamelogic.Board;
import pair.Pair;
import playcontroller.PlayController;

/**
 * Booster1: Cancels all lines with 80% or more blocks in it.
 * 
 */
public class Booster1Impl implements Booster {
    private boolean boosterStatus;
    private int lastPoints;

    private static final int ACTIVATE_BOOSTER_RANGE = 80;

    /**
     * @param boosterStatus : true if the booster will be usable at the start of the
     *                      game, false otherwise
     */
    public Booster1Impl(final boolean boosterStatus) {
        this.boosterStatus = boosterStatus;
        this.lastPoints = 0;
    }

    @Override
    public final void useBooster(final PlayController playController) {
        this.controlStatus(playController.getGame().getScore().getScore());
        if (this.boosterStatus) {
            this.lastPoints = playController.getGame().getScore().getScore();
            // Value: rowNumber, Key: number of blocks in that row
            final Map<Integer, Integer> repetitionInRow = new HashMap<>();

            playController.getGame().getBoard();
            // Initialize the Map for each row
            for (int i = 0; i < Board.COLLENGTH; i++) {
                repetitionInRow.put(i, 0);
            }
            // Each block in put in the repetition map
            for (final Pair<Integer, Integer> coordinate : playController.getGame().getBoard().getBoard().keySet()) {
                repetitionInRow.put(coordinate.getX(), repetitionInRow.get(coordinate.getX()) + 1);
            }

            // This Set will contain all the rowNumber of the rows with 80% or more blocks
            final Set<Integer> rowsToDelete = new HashSet<>();
            for (final int rowNumber : repetitionInRow.keySet()) {
                playController.getGame().getBoard();
                if (repetitionInRow.get(rowNumber) >= ((Board.ROWLENGTH / 10) * 8)) {
                    rowsToDelete.add(rowNumber);
                }
            }
            playController.getGame().getBoard().cancelLines(rowsToDelete);
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
