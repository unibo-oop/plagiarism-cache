package it.unibo.model.shop.api;

/**
 * Interface that manages the active power-ups and skins.
 */
public interface ActiveUpgrades {

    /**
     * Updates the multipliers checking the inventory and calculate the current max
     * bonuses for each ShopItemType.
     */
    void updateValues();

    /**
     * @return the current speed multiplier.
     */
    double getSpeedMultiplier();

    /**
     * @return the current jump multiplier.
     */
    double getJumpMultiplier();

    /**
     * @return the current coin multiplier.
     */
    int getCoinMultiplier();
}
