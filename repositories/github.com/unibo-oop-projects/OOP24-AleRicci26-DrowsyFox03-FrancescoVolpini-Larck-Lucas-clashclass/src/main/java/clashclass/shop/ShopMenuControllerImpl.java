package clashclass.shop;

import clashclass.gamestate.GameStateManager;
import clashclass.village.manager.PlayerVillageController;


/**
 * Represents a {@link ShopMenuController} implementation.
 */
public class ShopMenuControllerImpl implements ShopMenuController {
    private final ShopMenuModel model;
    private final ShopMenuView view;

    /**
     * Constructs the stop menu controller.
     *
     * @param model the shop menu model
     * @param view the shop menu view
     */
        public ShopMenuControllerImpl(final ShopMenuModel model, final ShopMenuView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopManager getShopManager() {
        return this.model.getShopManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryToBuyItem(final ShopItem item) {
        if (this.model.tryToBuyItem(item)) {
            this.goBackToVillage();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.view.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        this.view.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.view.clearScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.model.setGameStateManager(gameStateManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goBackToVillage() {
       this.model.getGameStateManager().setStatePlayerVillage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerVillageController(final PlayerVillageController playerVillageController) {
        this.model.setPlayerVillageController(playerVillageController);
    }
}
