package it.unibo.plantsfarm.controller.inventario;

import java.util.Map;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.inventario.UpgradeItemsView;

/**
 * Implementation of Inventory model.
 */
public final class ImplControllerInventario implements ControllerInventario {

    private Player abstractPlayer;
    private UpgradeItemsView viewItemsInventory;

    /**
     * Creates a new inventory controller for the given player.
     *
     * @param player the player whose inventory will be managed
     */
    public ImplControllerInventario(final Player player) {
        setPlayer(player);
    }

    @Override
    public boolean isUpgredable(final Tooltype tool) {
        return abstractPlayer.getInventory().isUpgredableItem(tool);
    }

    @Override
    public void pressUpgradeItem(final Tooltype tool) {
        abstractPlayer.upgradeItemRarityFromPlayer(tool);
        viewItemsInventory.updateAllItemsPanel();
    }

    @Override
    public void addView(final ImplViewGamePanel gamePanel) {
        this.viewItemsInventory = new UpgradeItemsView(gamePanel, this);
    }

    @Override
    public void openViewInv() {
         if (viewItemsInventory != null) {
            this.viewItemsInventory.setVisible(true);
        }
    }

    @Override
    public void updateInventory() {
        if (viewItemsInventory != null) {
            this.viewItemsInventory.updateAllItemsPanel();
        }
    }

    @Override
    public Map<Tooltype, ItemsFarm> getInventoryClone() {
        return this.abstractPlayer.getInventory().getInventorySnapshot();
    }

    private void setPlayer(final Player entryPlayer) {
        this.abstractPlayer = entryPlayer;
    }
}
