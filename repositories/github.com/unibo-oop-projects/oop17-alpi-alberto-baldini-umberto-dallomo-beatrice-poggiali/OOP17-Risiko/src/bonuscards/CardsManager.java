package bonuscards;

import java.util.List;
import java.util.Map;

/** 
 * 
 * this class manages the bonus cards distribution and the 
 * exchange for bonus armies.
 * it will be taken for granted that the player will do
 * the exchange at first occasion, so it will be managed
 * by the object without asking. this implies that the players
 * cannot have more than 5 cards for a turn and that they will
 * be exchanged in the next's beginning.
 */
public interface CardsManager {

    /**
     * 
     * @param player defines which list of cards to work on.
     * this method sets the boolean "drawed" and visualize the right
     * list on the monitor.
     * the update is done acceding the view in a static way.
     * 
     * @return the number of bonus armies.
     */
    int newTurn(int player);

    /**
     * 
     * @return the boolean describing if the player has drawed yet.
     */
    boolean getDrawed();

    /**
     * 
     * @param condition to set drawed boolean,
     */
    void setDrawed(boolean condition);

    /**
     * 
     * methods called to simulate the act of drawing a bonus card.
     */
    void draw();

    /**
     * 
     * @return the map of players' lists of cards.
     */
    Map<Integer, List<String>> getPlayersCards();

}
