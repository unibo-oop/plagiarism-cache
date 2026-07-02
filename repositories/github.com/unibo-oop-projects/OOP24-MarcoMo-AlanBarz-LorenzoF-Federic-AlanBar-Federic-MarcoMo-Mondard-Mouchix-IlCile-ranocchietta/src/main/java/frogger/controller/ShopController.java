package frogger.controller;


import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import frogger.common.GameState;
import frogger.model.implementations.ShopImpl;
import frogger.model.interfaces.PurchasableObject;
import frogger.model.interfaces.Shop;
import frogger.view.GameScene;
import frogger.view.ShopPanel;

/**
 * Controller for the shop scene. Handles initialization, loading and saving of purchasable objects,
 * and manages the shop panel UI.
 */
public class ShopController extends AbstractController {

    private final Shop shop;
    /** Reference to the shop panel UI. */
    private ShopPanel shopPanel;
    /** Reference to the main game controller. */
    private final GameController gameController;

    /**
     * Constructs a new ShopController.
     * Deletes the existing shop save file if present.
     * The passed GameController reference is stored and may be modified externally.
     *
     * @param gc the main game controller
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The exposure of GameController is intentional: its lifecycle is managed externally to LevelPainter."
    )
    public ShopController(final GameController gc) {
        this.shop = new ShopImpl();
        this.gameController = gc;
        this.shopInit();
    }

    /**
     * Returns the internal GameController reference.
     * Modifying the returned object will affect this ShopController.
     * The exposure of GameController is intentional: its lifecycle is managed externally to ShopController.
     *
     * @return the game controller associated with this shop
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The exposure of GameController is intentional: its lifecycle is managed externally to ShopController."
    )
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * {@inheritDoc}
     * Initializes the shop scene, loads purchasable objects, and sets up the shop panel.
     */
    @Override
    public void init(final GameScene gameScene) {
        this.shopPanel = new ShopPanel();
        this.shopPanel.setController(this);
        this.shopPanel.updateButtons();
        gameScene.setPanel(shopPanel);
    }

    /**
     * Returns a copy of the list of purchasable objects available in the shop.
     *
     * @return a new list containing the purchasable objects
     */
    public List<PurchasableObject> getPurchasableObject() {
        return this.shop.getPurchasableObjects();
    }

    /**
     * Initializes the shop by loading purchasable objects from the save file if it exists,
     * or from the default resource file otherwise.
     * Populates the internal list of purchasable objects.
     */
    public final void shopInit() {
        this.shop.init();
    }

    /**
     * {@inheritDoc}
     * Repaints the shop panel during the controller's core loop.
     */
    @Override
    protected void core() {
        this.shopPanel.repaint();
    }

    /**
     * {@inheritDoc}
     * The loop continues as long as the game state is SHOP.
     */
    @Override
    protected boolean loopCondition() {
        return GameState.getState() == GameState.SHOP;
    }
}
