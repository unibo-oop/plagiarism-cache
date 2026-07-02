package it.tbt.controller.modelmanager.shop;

import java.util.List;

import it.tbt.controller.modelmanager.ModelState;

/**
 * Shop state.
 */
public interface ShopState extends ModelState {

    /**
     * Focus the next element.
     */
    void nextElement();

    /**
     * Focus the previous element.
     */
    void previousElement();

    /**
     * Get the focused item of the party.
     * @return item index
     */
    int getPartyFocus();

    /**
     * Get the focused item of the shop.
     * @return item index
     */
    int getShopFocus();

    /**
     * Check if is focused the party or the shop.
     * @return true if the party is focused
     */
    boolean isPartyListFocused();

    /**
     * Get the items of the party.
     * @return a list of ShopItem
     */
    List<ShopItem> getPartyItems();

    /**
     * Get the items of the hsop.
     * @return a list of ShopItem
     */
    List<ShopItem> getShopItems();

    /**
     * Get the party wallet.
     * @return party wallet
     */
    int getPartyWallet();

    /**
     * Get the shop wallet.
     * @return shop wallet
     */
    int getShopWallet();

    /**
     * Execute the transaction.
     * It uses the item focused to determine what to do
     */
    void execute();

    /**
     * Switch between the party inventory and the shop inventory.
     */
    void toggleList();

    /**
     * Go back to the exploration.
     */
    void goToExplore();
}
