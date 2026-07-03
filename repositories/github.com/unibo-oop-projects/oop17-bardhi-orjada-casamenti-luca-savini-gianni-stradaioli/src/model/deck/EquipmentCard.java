package model.deck;

import java.util.List;

import model.bonus.PowerBonus;
import model.player.Player;

/**
 *
 */
public class EquipmentCard extends AbstractCard {

    private final List<PowerBonus> pb;;
    /**
     * 
     * @param name is name of the card.
     * @param price is how much is this card.
     * @param effects are all effect of this card.
     * @param pb 
     */
    public EquipmentCard(final String name, final int price, final List<Effect> effects, final List<PowerBonus> pb) {
        super(name, price, effects, false);
        this.pb = pb;
    }

    @Override
    public void activeCard(final Player player, final List<Player> players) {
        effects.forEach(e -> e.active(player, players));
        pb.forEach(p -> p.power(player));
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " " + this.pb.stream().map(PowerBonus::toString).reduce((x,  y) -> x + " " + y).orElseGet(String::new);
    }

    @Override
    public void destroyCard() {
        // TODO Auto-generated method stub
    }

}
