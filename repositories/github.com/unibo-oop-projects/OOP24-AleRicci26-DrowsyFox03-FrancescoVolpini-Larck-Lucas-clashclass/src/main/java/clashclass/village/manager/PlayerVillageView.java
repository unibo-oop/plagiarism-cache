package clashclass.village.manager;

import clashclass.shop.ShopMenuView;

/**
 * Represents a player village view.
 */
public interface PlayerVillageView {
    /**
     * Sets the controller reference.
     *
     * @param controller the controller
     */
    void setController(PlayerVillageController controller);

    /**
     * Updates the view.
     *
     * @param model the model reference
     */
    void update(PlayerVillageModel model);

    /**
     * Clears the scene.
     */
    void clearScene();

    /**
     * Creates the shop menu view.
     *
     * @return the shop menu view
     */
    ShopMenuView getShopMenuView();
}
