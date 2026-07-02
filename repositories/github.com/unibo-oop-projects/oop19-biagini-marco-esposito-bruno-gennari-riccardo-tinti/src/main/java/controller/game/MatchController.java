package controller.game;

import model.enums.ShipType;
import model.match.PlaygroundBattle;
import model.util.Pair;
import view.match.BattleView;

/**
 * 
 * Interface of match's controller.
 * 
 */
public interface MatchController {

    /**
     * Method to position a new Ship inside battle playground.
     * 
     * @param shipType  - ShipType of ship to position
     * @param firstCell - Upper and further to left cell used by ship.
     */
    void positionShip(ShipType shipType, Pair<Integer, Integer> firstCell);

    /**
     * Method to remove ship that occupied one of cells passed.
     * 
     * @param cell - One of cell occupied by the ship to remove.
     */
    void removeShip(Pair<Integer, Integer> cell);

    /**
     * Method for shot to enemy playground.
     * 
     * @param line - vertical position of shot.
     * @param col - horizontal position of shot.
     */
    void shot(int line, int col);

    /**
     * Set the game view linked to this controller.
     * 
     * @param battleView - Game view
     */
    void setView(BattleView battleView);

    /**
     * Set the passed playground for current player who has to position ship.
     * 
     * @param playgroundBattle - Playground to set
     */
    void setPlayground(PlaygroundBattle playgroundBattle);

    /**
     * Call new scene based on who is the next player who has to position ship. Start battle if 
     * both player positioned ships.
     */
    void nextToPosition();

    /**
     * Call view to show ship of current player.
     */
    void showShip();

}
