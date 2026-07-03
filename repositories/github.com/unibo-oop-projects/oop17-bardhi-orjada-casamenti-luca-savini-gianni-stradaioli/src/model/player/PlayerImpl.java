package model.player;

import java.util.ArrayList;
import java.util.List;

import model.bonus.Bonus;
import model.bonus.BonusImpl;
import model.deck.Card;
import model.dice.Dice;
/**
 * 
 *
 */
public class PlayerImpl implements Player {
    private final String name;
    private int life;
    private int money;
    private int level;
    private boolean king; // flag to know if the player is the King
    private final List<Card> playerCards; // player's cards
    private final int maxLife;
    private final Bonus bonus;

    private int hash;
    /**
     * complete costructor, with all fields.
     * @param name Player's name.
     * @param life Player start life points.
     * @param money Player start money.
     */
    public PlayerImpl(final String name, final int life, final int money) {
        this.name = name;
        this.life = life;
        this.money = money;
        this.level = 0;
        this.playerCards = new ArrayList<>();
        maxLife = life;
        bonus = new BonusImpl();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public int getPoints() {
        return this.level;
    }

    @Override
    public List<Card> getPlayerCards() {
        return this.playerCards;
    }

    @Override
    public Bonus getBonus() {
        return this.bonus;
    }

    @Override
    public int getMoneyAndDiscount() {
        return this.money + this.bonus.getCashSale();
    }

    @Override
    public void pickCard(final Card card, final List<Player> players) {
        if (this.money >= card.getPrice()) {
            this.loseMoney(card.getPrice());
            if (!card.isSingleUse()) {
                this.playerCards.add(card);
            }
            card.activeCard(this, players);
        }
    }

    @Override
    public void useCard(final int indice) {
        this.playerCards.get(indice).activeCard(null, null);
    }

    @Override
    public void loseMoney(final int money) {
        this.money = decrease(this.money, money - bonus.getCashSale());
    }

    @Override
    public void earnMoney(final int money) {
        loseMoney(-money - bonus.getBonusCash());
    }

    @Override
    public void takeDamages(final int damage) {
        this.life = decrease(this.life, damage - bonus.getArmor());
    }

    @Override
    public void rechargeLife(final int life) {
        // controllo se supero il valore massimo di vita, in quel caso la vita =
        // MAX
        this.life = this.life + life > maxLife + bonus.getBonusLife() ? maxLife + bonus.getBonusLife() : this.life + life;
    }

    @Override
    public void decreasePoints(final int points) {
        this.level = decrease(this.level, points);
    }

    @Override
    public void increasePoints(final int point) {
        decreasePoints(-point - bonus.getBonusPoints());
    }

    @Override
    public List<Dice> rollDices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Dice> rerollDices(final List<Dice> dices) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void changeRule() {
        this.king ^= true;
    }

    @Override
    public boolean isAlive() {
        return this.life != 0;
    }

    @Override
    public boolean isKing() {
        return this.king;
    }

    @Override
    public int hashCode() {
        if (hash == 0) {
            hash = name.hashCode() ^ life ^ money ^ level ^ playerCards.hashCode() ^ maxLife;
        }
        return hash;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof PlayerImpl) {
            final PlayerImpl p = (PlayerImpl) o;
            return this.name.equals(p.getName())
                    && this.life == p.getLife()
                    && this.money == p.getMoney()
                    && this.level == p.getPoints()
                    && this.playerCards.equals(p.getPlayerCards());
        }
        return false;
    }

    private int decrease(final int value, final int loss) {
        return value - loss < 0 ? 0 : value - loss;
    }
}
