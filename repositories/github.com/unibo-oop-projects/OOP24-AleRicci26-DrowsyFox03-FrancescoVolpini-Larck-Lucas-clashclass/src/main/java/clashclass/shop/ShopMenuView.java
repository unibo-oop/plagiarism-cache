package clashclass.shop;

/**
 * Represents a shop menu view.
 */
public interface ShopMenuView {
    /**
     * Sets the shop menu controller reference.
     *
     * @param controller the shop menu controller
     */
    void setController(ShopMenuController controller);

    /**
     * Shows the menu.
     */
    void show();

    /**
     * Hides the menu.
     */
    void hide();

    /**
     * Clears the scene.
     */
    void clearScene();
}
