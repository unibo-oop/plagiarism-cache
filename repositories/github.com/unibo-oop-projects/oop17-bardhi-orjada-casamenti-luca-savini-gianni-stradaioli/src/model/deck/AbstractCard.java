package model.deck;

import java.util.List;

import model.player.Player;
/**
 *
 */
public abstract class AbstractCard implements Card {
    protected final String name;
    protected final int price;
    protected final boolean use;
    protected final List<Effect> effects;
/**
 * 
 * @param name is name of the card.
 * @param price is how much is this card.
 * @param effects are all effect of this card.
 * @param use is true if card is instant, false otherwise.
 */
    public AbstractCard(final String name, final int price, final List<Effect> effects, final boolean use) {
        this.name = name;
        this.price = price;
        this.effects = effects;
        this.use = use;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean isSingleUse() {
        return use;
    }

    @Override
    public String getDescription() {
        return this.effects.stream().map(Effect::toString).reduce((x,  y) -> x + " " + y).orElseGet(String::new);
    }

    public abstract void activeCard(Player player, List<Player> players);

    public abstract void destroyCard();

}
