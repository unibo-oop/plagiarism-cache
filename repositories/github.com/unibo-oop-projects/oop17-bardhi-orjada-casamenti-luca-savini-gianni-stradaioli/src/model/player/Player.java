package model.player;

import java.util.List;

import model.bonus.Bonus;
import model.deck.Card;
import model.dice.Dice;

/**
 * 
 * This interface represents the contract of Player class, or rather all action which Player can does.
 *
 */
public interface Player {

    /**
     * Returns the player's name.
     * @return Player's name.
     */
    String getName();

    /**
     * Return player's life points.
     * @return Player's life.
     */
    int getLife();

    /**
     * Return player's  money.
     * @return Player's moneys.
     */
    int getMoney();

    /**
     * Return player's  points.
     * @return Player's points.
     */
    int getPoints();

    /**
     * Return player's equipment cards.
     * @return All player's cards. 
     */
    List<Card> getPlayerCards();

    /**
     * That allows a player to active the effect of the card.
     * @param indice is number of card to active.
     */
    void useCard(int indice);

    /**
     * Decreases the player's money.
     * @param money is how many money player loses.
     */
    void loseMoney(int money);

    /**
     * Increases the player's money.
     * @param money is how many money player earns.
     */
    void earnMoney(int money);

    /**
     * Decrease the player's life points.
     * @param damage is how many damage player takes.
     */
    void takeDamages(int damage);

    /**
     * Increases the player's life points.
     * @param life is how many life player reufull's himself.
     */
    void rechargeLife(int life);

    /**
     * Decreases the player's points.
     * @param point is how many points player loses.
     */
    void decreasePoints(int point);

    /**
     * Increases the player's points.
     * @param point is how many points player earn.
     */
    void increasePoints(int point);

    /**
     * Roll all the dices; it returns the  result.
     * @return list of dice.
     */
    List<Dice> rollDices();
    /**
     * Re-rolls the selected dices; it returns the  result.
     * @param dices is the dice that player wants reroll.
     * @return list of relaunched dice.
     */
    List<Dice> rerollDices(List<Dice> dices);

    /**
     * Changes the  state from normal player to king, or from king to normal player.
     */
    void changeRule();

    /**
     * This method permits a player to buy a card on the filed if the player has enough money. Otherwise it throws  the "NotEnoughMoneyException" exception.
     * @param card is the card that the player has bought.
     * @param players the list of player that effects could hit.
     */
    void pickCard(Card card, List<Player> players);
    /**
     * This method return if player is alive or dead.
     * @return true if player is alive (life > 0), false otherwise.
     */
    boolean isAlive();
    /**
     * 
     * @return true if the player is king, false otherwise.
     */
    boolean isKing();

    /**
     * 
     * @return Current player's bonus.
     */
    Bonus getBonus();
    /**
     * If a player would buy a card, it is necessary to know, 
     * besides the monetary availability, also the discount.
     * 
     * @return Current money, and discount for shopping.
     */
    int getMoneyAndDiscount();

}
