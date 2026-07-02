package it.unibo.the100dayswar.model.playeraction.impl;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.playeraction.api.PurchaseCommand;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An implementations of the command pattern that represents the purchase 
 * of a buyable unit.
 */
public class PurchaseUnitCommand implements PurchaseCommand {
    private static final long serialVersionUID = 1L;
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player player, final Unit unit) {
        player.addUnit(unit);
        if (player.getUnits().contains(unit)) {
            player.spendResources(unit.getBuyCost());
            unit.notifyObservers(unit.getPosition());
        } else {
            throw new IllegalStateException("The unit is not be added correctly to the player.");
        }
    }
}
