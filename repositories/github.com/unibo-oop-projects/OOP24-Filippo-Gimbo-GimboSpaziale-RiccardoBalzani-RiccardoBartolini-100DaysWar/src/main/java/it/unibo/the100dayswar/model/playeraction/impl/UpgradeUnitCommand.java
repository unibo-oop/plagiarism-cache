package it.unibo.the100dayswar.model.playeraction.impl;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.playeraction.api.UpgradeCommand;
import it.unibo.the100dayswar.model.unit.api.Buyable;

/**
 * An implementations of the command pattern that represents 
 * the upgrade of a buyable.
 */
public class UpgradeUnitCommand implements UpgradeCommand {
    private static final long serialVersionUID = 1L;
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player player, final Buyable unit) {
        if (!player.getUnits().contains(unit)) {
            throw new IllegalArgumentException("The unit is not owned by the player.");
        }
        player.spendResources(unit.getUpgradeCost());
        unit.upgrade();
    }
}
