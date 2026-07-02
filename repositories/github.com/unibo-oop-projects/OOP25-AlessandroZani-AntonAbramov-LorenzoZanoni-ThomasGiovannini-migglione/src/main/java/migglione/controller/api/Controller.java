package migglione.controller.api;

import java.util.List;

import migglione.model.api.Player;
import migglione.model.impl.Card;

/**
 * Interface for the Controller of the application.
 * 
 * <p>
 * Accordingly to the MVC method, the role of the
 * Controller will be the one of making practical decisions
 * like to determine the winner or deciding who is playing
 */
public interface Controller {

    /**
     * Used to start the real game.
     * 
     * @param name is the name of the player,
     *             it will be used later if they
     *             won the game
     */
    void startSession(String name);

    /**
     * Used to check if its time to end the match.
     * 
     * <p>
     * This method decides if endSession should be
     * called, by asking directly the model
     */
    void checkSession();

    /**
     * Used to call the same method in the Model.
     * 
     * @return the list of players, its implementation
     *         can be fully seen in the class chosen as Model
     */
    List<Player> getPlayers();

    /**
     * Used to call the same method in the Model.
     * 
     * @param attr is the attribute chosen to play the card in
     * @param card the chosen card to be played
     */
    void playTurnLead(String attr, Card card);

    /**
     * Used to call the same method in the Model.
     * 
     * @param card the chosen card to be played
     */
    void playTurnTail(Card card);

    /**
     * Used to call the same method in the Model.
     * 
     * @return whether the game has finished or not.
     */
    boolean playTurnStored();

    /**
     * Used to call the same method in the Model.
     * 
     * @return the current attribute to which
     *         the cards will be compared
     */
    String getCurrAttr();

    /**
     * Used to call the same method in the Model.
     * 
     * @return current the player whose turn
     *         the current one is
     */
    Player getTurnLeader();

    /**
     * Used to call the same method in the Model.
     * 
     * @param player is the one we want the current score of
     * @return the current score of the player
     */
    int getScore(Player player);

    /**
     * Determines the end of the match.
     * 
     * <p>
     * This method decides who wins by comparing the points,
     * and if the player is the one who won then its name
     * will be put in the scores file, so that it can be
     * displayed in the Scores scene
     */
    void endSession();
}
