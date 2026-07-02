package it.unibo.briscoola.view.api;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.view.api.popup.Popups;

/**
 * Interface View for the graphic setting.
 * 
 * @author Andrea Reggiani
 * @author Riem Boukhama
 */
public interface View {

    /**
     * Register the action to be performed when the user starts the game from the menu.
     * 
     * @param onStartGame receives the name and difficulty
     */
    void setOnGameStartListener(BiConsumer<String, Difficulty> onStartGame);
 
    /**
     * Register the action to be performed when the user clicks a card in his hand.
     * 
     * @param onCardPlayed receives the index of the played card
     */
    void setOnCardPlayedListener(Consumer<Integer> onCardPlayed);

    /**
     * Shows the inital screen.
     */
    void start();

    /**
     * Initialize the game table.
     */
    void initGame();

    /**
     * Updates the card in the player's hand after the draw.
     * 
     * @param playerID the ID of the player
     * @param handCards the list of the cards in the player hand
     */
    void updateHand(int playerID, List<Pair<String, String>> handCards);

    /**
     * Updates the card count in a player's deck.
     * 
     * @param cardsCount the number of the cards in the player Pile
     * @param player the player
     */
    void updatePile(int cardsCount, boolean player);

    /**
     * Shows amessage for the end of the game or the turn.
     *
     * @param type type of message
     * @param message error message
     */
    void displayMessage(Popups type, String message);

    /**
     * Returns an unmodifiable list of the player hand component views.
     * 
     * @return the hand component views
     */
    List<CardView> getPlayerHandCards();

    /**
     * Updates the graphical representation of the card.
     * 
     * @param seed the seed of the card
     * @param value the value of the card
     */
    void updateBriscola(String seed, String value);

    /**
     * Close the app.
     */
    void quit();

    /**
     * Update the table after the player chose the card.
     * 
     * @param playerSeed the seed of the card played by the player
     * @param playerValue the value of the card played by the player
     * @param cpuSeed the seed of the card played by the CPU
     * @param cpuValue the value of the card played by the CPU
     * @param deckSize the current number of cards in the deck
     */
    void updateTable(String playerSeed, String playerValue, String cpuSeed, String cpuValue, int deckSize);

}
