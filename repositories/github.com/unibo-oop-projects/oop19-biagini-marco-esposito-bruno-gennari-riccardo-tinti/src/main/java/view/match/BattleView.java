package view.match;

import java.util.List;

import model.enums.PlayerNumber;
import model.util.Pair;

/**
 * 
 * View of match battle started.
 * 
 */
public interface BattleView {

    /**
     * Show a dialog where is explained which cells have been overlapped. 
     * 
     * @param cells - list of cells already used
     */
    void showCellAlreadyUsedAlert(List<Pair<Integer, Integer>> cells);

    /**
     * Show a dialog where is explained which cell has already been shot. 
     * 
     * @param cell - cell already shot
     */
    void showCellAlreadyShottedAlert(Pair<Integer, Integer> cell);

    /**
     * Draw hit in the determined cell of grid of this player number.
     * 
     * @param cell - cell in grid where draw it (X is row number, Y is col number)
     * @param playerNumber - player number representing the player who received hit
     */
    void drawHit(Pair<Integer, Integer> cell, PlayerNumber playerNumber);

    /**
     * Draw in passed cells to show that there a ship is sunk. The drawing will be do in playground
     * of player passed. 

     * @param cells - Cells where draw
     * @param playerNumber - Player owning the grid to draw on
     */
    void drawSunkShip(List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    /**
     * Draw ship in passed cells of playground represented by passed player number. 
     * 
     * @param cells - Cells where draw 
     * @param playerNumber - Player owning the grid to draw on
     */
    void drawShip(List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    /**
     * Draw the missed shot in the determined cell of grid of this player number.
     * 
     * @param cell - cell in grid where draw it (X is row number, Y is col number)
     * @param playerNumber - player number representing the player who received hit
     */
    void drawMissed(Pair<Integer, Integer> cell, PlayerNumber playerNumber);

    /**
     * Change current player.
     */
    void changePlayer();

    /**
     * Set the new score of current player.
     * 
     * @param point - new score of current player
     */
    void setPoints(int point);

    /**
     * Set the new number of shot number for current villain.
     * 
     * @param shotAvailable - new shot available
     */
    void setShotAvailable(int shotAvailable);

    /**
     * Show win dialog. After clicked it, you will linked to fist scene of application.
     */
    void showWinDialog();

    /**
     * Hide ships of this player in these determined cells. Cells hit will be not hided. 
     * 
     * @param cells - cells representing ships to hide
     * @param playerNumber - player number representing the player of who hide ships
     */
    void hideShip(List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    void showChangePlayerDialog();

}
