package oop.lit.model.simplegame;

import java.util.Optional;

import oop.lit.model.PlayerModel;
import oop.lit.model.simplegame.actions.GroupActionFactory;
import oop.lit.model.simplegame.elements.BasicSBE;

/**
 * A group representing a player hand.
 */
public class SimplePlayerHand extends SimpleSelectableGroup<BasicSBE> {
    /**
     * 
     */
    private static final long serialVersionUID = -267412591965485312L;

    /**
     * @param player
     *      this player this hand belongs to.
     * @param actionFactory
     *      an actionFactory for BasicSBE.
     */
    public SimplePlayerHand(final PlayerModel player, final GroupActionFactory actionFactory) {
        super(Optional.of(player.getName() + "'s hand"), actionFactory);
    }
}
