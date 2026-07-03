package model.deck;

import java.util.List;

import model.player.Player;
/**
 * 
 */
public interface Card {

    /**
     * active the effect of the Card.
     * 
     * @param player
     *            current player
     * @param players
     *            other players
     */
    void activeCard(Player player, List<Player> players);

    /**
     * Destroy this "single-use" card.
     */
    void destroyCard();

    /**
     * @return the price of Card.
     */
    int getPrice();

    /**
     * @return if card is istant or equipment.
     */
    boolean isSingleUse();

    /**
     * @return the name of card.
     */
    String getName();

    /**
     * 
     * @return A string which describes card effects.
     */
    String getDescription();

}
