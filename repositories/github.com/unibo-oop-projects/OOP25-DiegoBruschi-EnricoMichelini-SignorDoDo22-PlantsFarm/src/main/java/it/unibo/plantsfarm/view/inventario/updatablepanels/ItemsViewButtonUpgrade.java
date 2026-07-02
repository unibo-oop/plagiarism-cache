package it.unibo.plantsfarm.view.inventario.updatablepanels;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.inventario.UpdatablePanel;

/**
 * This class display the button for upgrade the items of inventory.
 *
 */
public final class ItemsViewButtonUpgrade extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 6L;

    private static final String UPGRADE_TEXT = " UPGRADE ";
    private transient ControllerInventario controllerInventario;
    private final Map<Tooltype, JButton> progressButtonUpgradeMap = new LinkedHashMap<>();

    /**
     * Creates the upgrade buttons panel.
     *
     * @param controllerInventario the inventory controller used to manage upgrades
     */
    public ItemsViewButtonUpgrade(final ControllerInventario controllerInventario) {
        setControllerInventory(controllerInventario);
        this.setLayout(new GridLayout(3, 1));
        createButtonItemsAction();
        update();
    }

    /**
     * Creates the upgrade action buttons for each tool.
     */
    private void createButtonItemsAction() {
        for (final Tooltype tool : Tooltype.values()) {
            final JButton upgrade = new JButton(UPGRADE_TEXT);
            upgrade.addActionListener(e -> {
                if (controllerInventario.isUpgredable(tool)) {
                    controllerInventario.pressUpgradeItem(tool);
                }
            });
            progressButtonUpgradeMap.put(tool, upgrade);
            this.add(upgrade);
        }
    }

    @Override
    public void update() {
        for (final Tooltype tool : Tooltype.values()) {
            final JButton button = progressButtonUpgradeMap.get(tool);
            button.setEnabled(controllerInventario.isUpgredable(tool));
        }
    }

    private void setControllerInventory(final ControllerInventario givenControllerInventario) {
        this.controllerInventario = givenControllerInventario;
    }
}
