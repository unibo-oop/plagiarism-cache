package model;

import java.util.List;

import model.bonus.BonusCard;
import model.player.Player;

/**
 * Interface for {@link BonusCardManager} class. It gives the possibility to get the best combo 
 * and to communicate the combo cards list chosen from the player.
 */
public interface BonusCardManagerInterface {

    /**
     * 
     * This method calculates the number of tanks related to the combo traded,
     * add this number of tanks to the amount of tank available to the player,
     * removes those cards of which the combo is made up from the player's
     * BonusCard list, add those card to the bonusCardDeck.
     * 
     * @param player
     *            player who required the trade;
     * 
     * @param combo
     *            combo traded for tanks;
     * 
     */
    void tradeCombo(Player player, List<BonusCard> combo);

    /**
     * 
     * This method tries to establish which is the best combo of BonusCard
     * available to the player.
     * 
     * @param player
     *            player who owns the combo;
     * 
     * @return the best combo of the player if the player has at least one
     *         combo, a void list otherwise.
     * 
     */
    List<BonusCard> getBestCombo(Player player);

}