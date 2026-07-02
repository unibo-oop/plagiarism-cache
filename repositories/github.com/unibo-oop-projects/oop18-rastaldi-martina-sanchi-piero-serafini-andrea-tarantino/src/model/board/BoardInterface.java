/**
 *
 */
package model.board;

import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import model.elements.Element;
import model.elements.Hero;
import model.elements.Minotaurus;
import model.elements.Wall;
import model.players.User;
import utilities.Colors;
import utilities.Status;

/**
 * Interface for the game board.
 * Andrea Serafini.
 *
 */
public interface BoardInterface {

    /**
     *
     * @param player
     *            the player owning the heroes
     * @return the number of heroes arrived
     */
    int arrivedHero(User player);

    /**
     *
     * @param pair
     *            the coordinates of a cell
     * @return an optional indicating the status of a cell
     */
    Optional<Pair<Status, Colors>> cellStatus(Pair<Integer, Integer> pair);

    /**
     *
     * @param position
     *            the coordinates of the requested element
     * @return the requested element
     */
    Element getElementAtPosition(Pair<Integer, Integer> position);

    /**
     *
     * @return the map containing the heroes
     */
    Map<Pair<Integer, Integer>, Hero> getHeroMap();

    /**
     *
     * @return the minotaurus
     */
    Minotaurus getMinotaurus();

    /**
     *
     * @return the occupation list
     */
    Map<Pair<Integer, Integer>, Pair<Status, Colors>> getOccupationList();

    /**
     *
     * @param selected
     *            the coordinates of the selected element
     * @return the selected element
     */
    Element getSelected(Pair<Integer, Integer> selected);

    /**
     *
     * @return the wall map
     */
    Map<Pair<Integer, Integer>, Wall> getWallMap();

    /**
     * Reset all hero to a non arrived state.
     */
    void resetArrivedHero();

}
