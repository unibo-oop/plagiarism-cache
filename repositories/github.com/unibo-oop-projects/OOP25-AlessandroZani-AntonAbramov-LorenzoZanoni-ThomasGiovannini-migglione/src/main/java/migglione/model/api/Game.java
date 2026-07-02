package migglione.model.api;

import java.util.List;
import java.util.Optional;

import migglione.model.impl.Card;

/**
 * Interface of the model of the application.
 * 
 * <p>
 * Since its implementation extends another class,
 * the Javadocs of this methods can be also seen
 * in the classes themselves
 */
public interface Game {

    /**
     * Starts a turn of the game. In order:
     * <ol>
     * <li> Awaits the attribute selection;
     * <li> Asks for the players to submit cards;
     * <li> Compares the cards based on the chosen attribute;
     * <li> sends the cards to the winner's winnings pile;
     * <li> updates the score.
     * </ol>
     * 
     * @param plrStat the value of the attribute of the player
     * @param cpuStat the value of the attribute of the CPU
     * @return if this was the last turn of the match.
     */
    boolean playTurn(int plrStat, int cpuStat);

    /**
     * Plays the turn, utilizing stored values of the players' choices.
     * Requires the class to save both values to apply.
     * 
     * @return if this was the last turn of the match.
     */
    boolean playTurnStored();

    /**
     * Plays the lead player's choice card and attribute.
     * 
     * @param attr the chosen attribute
     * @param card the chosen card
     */
    void playTurnLead(String attr, Card card);

    /**
     * Plays the "tail" (last to go) player's card choice.
     * Attribute is not required as it is already chosen by the lead player.
     * 
     * @param card the chosen card
     */
    void playTurnTail(Card card);

    /**
     * Method to obtain the attribute this turn is being played on.
     * 
     * @return the current active attribute.
     */
    String getCurrAttr();

    /**
     * Method to return the name of the winner.
     * If match hasn't finished, returns null.
     * 
     * @return the String of the winner's name, or null if match is not finished.
     */
    Optional<String> getWinner();

    /**
     * Method used to get the score of the player.
     * 
     * @return the optional of the score
     */
    Optional<Integer> getPlayerScore();

    /**
     * Method used to get the score of the mosquito.
     * 
     * @return the optional of the score
     */
    Optional<Integer> getCPUScore();

    /**
     * Method to get the current score of one player.
     * 
     * @param player the player whose score is requested.
     * @return the score of said player.
     */
    int getScore(Player player);

    /**
     * Method to get who started the current turn.
     * 
     * @return the player that starts the current turn.
     */
    Player getTurnLeader();

    /**
     * Method to obtain the players involved in the match.
     * 
     * @return a list containing the players in the match.
     */
    List<Player> getPlayers();

    /** 
     * Checks if the match has ended.
     * 
     * @return a boolean indicating if this match has ended.
     */
    boolean matchEnded();
}
