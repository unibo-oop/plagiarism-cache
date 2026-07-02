package it.unibo.controller.inventory.impl;

import java.util.List;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.app.api.MainController;
import it.unibo.controller.inventory.api.InventoryController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.model.menu.impl.ShoppingState;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.view.inventory.api.InventoryView;

/**
 * Implementation of {@link InventoryController} interface.
 */
public class InventoryControllerImpl implements InventoryController {

    private final MainController mainController;
    private final Inventory inventory;
    private InventoryView view;
    private final ShopItemFactory factory;
    private final Menu menu;

    /**
     * Construct a InventoryControllerImpl with required model and factory.
     * 
     * @param mainController the main controller for managing view transitions and
     *                       saving progress
     * @param inventory      the model inventory
     * @param factory        the factory for items
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "The View must hold and interact with the actual Controller instance to dispatch user inputs correctly."
        + "So it is intentionally exposed."
    )
    public InventoryControllerImpl(final MainController mainController, final Inventory inventory,
            final ShopItemFactory factory) {
        this.mainController = mainController;
        this.inventory = inventory;
        this.factory = factory;
        this.menu = new MenuImpl(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final InventoryView view) {
        this.view = view;
        refreshView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectSkin(final int index) {
        final List<ShopItem> ownedSkin = this.getOwnedSkins();
        if (isValidIndex(index, ownedSkin)) {
            final String skinId = ownedSkin.get(index).getId();
            inventory.equipSkin(skinId);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void plusJump() {
        final int current = inventory.getSelectedJumpLevel();
        if (current < getMaxLevelOwned("pp_jump")) {
            inventory.setSelectedJumpLevel(current + 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void minusJump() {
        final int current = inventory.getSelectedJumpLevel();
        if (current > 0) {
            inventory.setSelectedJumpLevel(current - 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void plusVelocity() {
        final int current = inventory.getSelectedSpeedLevel();
        if (current < getMaxLevelOwned("pp_speed")) {
            inventory.setSelectedSpeedLevel(current + 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void minusVelocity() {
        final int current = inventory.getSelectedSpeedLevel();
        if (current > 0) {
            inventory.setSelectedSpeedLevel(current - 1);
            this.mainController.saveProgress();
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleTemporaryItem(final int index) {
        final List<String> consumablesId = inventory.getConsumablesStatus().keySet().stream().sorted().toList();
        if (isValidIndex(index, consumablesId)) {
            inventory.toggleConsumable(consumablesId.get(index), factory);
            refreshView();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEquippedSkin() {
        return inventory.getSelectedSkin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxJumpLevelOwned() {
        return getMaxLevelOwned("pp_jump");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxSpeedLevelOwned() {
        return getMaxLevelOwned("pp_speed");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getOwnedSkins() {
        return factory.getSkins().stream()
                .filter(i -> inventory.hasItem(i.getId()))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getOwnedTempItems() {
        return inventory.getConsumablesStatus().keySet().stream()
                .sorted()
                .map(id -> factory.getItemById(id).orElseThrow())
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedJumpLevel() {
        return inventory.getSelectedJumpLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedSpeedLevel() {
        return inventory.getSelectedSpeedLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> getTempItemsStatus() {
        final Set<String> active = inventory.getActiveConsumables();
        return inventory.getConsumablesStatus().keySet().stream()
                .sorted()
                .map(active::contains)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventory() {
        menu.setState(new InventoryState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShop() {
        menu.setState(new ShoppingState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        menu.setState(new MenuState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getTempItemsDuration() {
        final var consumablesMap = inventory.getConsumablesStatus();
        return consumablesMap.keySet().stream()
                .sorted()
                .map(consumablesMap::get)
                .toList();
    }

    /**
     * Refresh the inventory view with current data from the model.
     */
    private void refreshView() {
        if (view != null) {
            final List<ShopItem> ownedSkin = getOwnedSkins();
            final String equippedSkin = getEquippedSkin();
            final List<ShopItem> allPermItems = factory.getPowerUpsPermanent();
            final int selectedJump = getSelectedJumpLevel();
            final int maxJump = getMaxJumpLevelOwned();
            final int selectedSpeed = getSelectedSpeedLevel();
            final int maxSpeed = getMaxSpeedLevelOwned();
            final List<ShopItem> ownedTempItems = getOwnedTempItems();
            final List<Boolean> tempItemsStatus = getTempItemsStatus();
            final List<Integer> tempItemsDuration = getTempItemsDuration();
            this.view.updateInventory(ownedSkin, equippedSkin, allPermItems, selectedJump, maxJump, selectedSpeed,
                    maxSpeed, ownedTempItems, tempItemsStatus, tempItemsDuration);
            this.view.updateCoins(inventory.getTotalCoins());
        }
    }

    /**
     * Validate if the index is within the bounds of a list.
     * 
     * @param index the index to check
     * @param list  the list to check
     * @return true if index is valid, false otherwise
     */
    private boolean isValidIndex(final int index, final List<?> list) {
        return index >= 0 && index < list.size();
    }

    /**
     * Calculate highest level currently owned for specific power ups.
     * Takes the level number from the item Id.
     * 
     * @param prefix the Id prefix for a type
     * @return the maximum level find, or 0 if no items are owned
     */
    private int getMaxLevelOwned(final String prefix) {
        return inventory.getOwnedItems().stream()
                .filter(id -> id.startsWith(prefix))
                .mapToInt(id -> Integer.parseInt(id.substring(id.lastIndexOf('_') + 1)))
                .max()
                .orElse(0);
    }
}
