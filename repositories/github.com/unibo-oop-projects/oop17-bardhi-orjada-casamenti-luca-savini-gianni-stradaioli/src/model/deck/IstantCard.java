package model.deck;

import java.util.List;

import model.player.Player;

/**
 *
 */
public class IstantCard extends AbstractCard {
/**
 * 
 * @param name is name of the card.
 * @param price is how much is this card.
 * @param effects are all effect of this card.
 */
    public IstantCard(final String name, final int price, final List<Effect> effects) {
        super(name, price, effects, true);
    }

    @Override
    public void activeCard(final Player player, final List<Player> other) {
        effects.forEach(e -> e.active(player, other));
    }

    @Override
    public void destroyCard() {
        // TODO Auto-generated method stub
    }

}
