package it.unibo.model.shop.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;

/**
 * Implementation of {@link ActiveUpgrades}.
 */
@SuppressFBWarnings(
    value = "EI_EXPOSE_REP",
    justification = "This component is designed to directly modify the shared game state (Inventory)."
    + "A defensive copy would render the game logic ineffective."
)
public class ActiveUpgradesImpl implements ActiveUpgrades {

    private static final String PREFIX_SPEED = "pp_speed_";
    private static final String PREFIX_JUMP = "pp_jump_";
    private final Inventory inventory;
    private final ShopItemFactory factory;
    private double currentSpeedMultiplier;
    private double currentJumpMultiplier;
    private int currentCoinMultiplier;

    /**
     * Constructor for ActiveUpgradesImpl with required inventory.
     * 
     * @param inventory the player's inventory
     */
    public ActiveUpgradesImpl(final Inventory inventory) {
        this.inventory = inventory;
        this.factory = inventory.getFactory();
        this.updateValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateValues() {
        this.currentJumpMultiplier = 1.0;
        this.currentSpeedMultiplier = 1.0;
        this.currentCoinMultiplier = 1;

        for (final String itemId : inventory.getActiveConsumables()) {
            applyItemStats(itemId);
        }

        applyItemStats(inventory.getSelectedSkin());

        final int speedLevel = inventory.getSelectedSpeedLevel();
        if (speedLevel > 0) {
            final String id = PREFIX_SPEED + speedLevel;
            applyItemStats(id);
        }

        final int jumpLevel = inventory.getSelectedJumpLevel();
        if (jumpLevel > 0) {
            final String id = PREFIX_JUMP + jumpLevel;
            applyItemStats(id);
        }
    }

    /**
     * Extract stats from an itemId and apply max value for type logic.
     * 
     * @param itemId the itemId to extract
     */
    private void applyItemStats(final String itemId) {
        if (itemId == null || itemId.isEmpty()) {
            return;
        }

        factory.getItemById(itemId).ifPresent(item -> {
            item.getStats().forEach((stat, value) -> {
                switch (stat) {
                    case SPEED:
                        this.currentSpeedMultiplier = Math.max(this.currentSpeedMultiplier, value);
                        break;
                    case JUMP_HEIGHT:
                        this.currentJumpMultiplier = Math.max(this.currentJumpMultiplier, value);
                        break;
                    case COIN_MULTIPLIER:
                        this.currentCoinMultiplier = Math.max(this.currentCoinMultiplier, value.intValue());
                        break;
                }
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeedMultiplier() {
        return this.currentSpeedMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getJumpMultiplier() {
        return this.currentJumpMultiplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoinMultiplier() {
        return this.currentCoinMultiplier;
    }

}
