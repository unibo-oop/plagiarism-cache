package it.unibo.plantsfarm.view.inventario.updatablepanels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.inventario.UpdatablePanel;

/**
 * This Panel show all  items image and their current level.
 *
 */
public final class ItemsViewImageItem extends JPanel implements UpdatablePanel {

    private static final long serialVersionUID = 7L;

    private static final int BUTTON_SIZE = 60;
    private static final int ICON_SIZE = 48;
    private transient ControllerInventario controllerInventario;
    private final Map<Tooltype, JButton> itemsDisplay = new EnumMap<>(Tooltype.class);

    /**
     * Creates and initializes the view with all item image components.
     *
     * @param controllerInventario the inventory controller used to retrieve item data
     */
    public ItemsViewImageItem(final ControllerInventario controllerInventario) {
        setControllerInventory(controllerInventario);
        this.setLayout(new GridLayout(3, 1));
        createItemButtons();
        update();
    }

    @Override
    public void update() {
        final var inv = controllerInventario.getInventoryClone();
        for (final Tooltype tool : Tooltype.values()) {
            final JButton jb = itemsDisplay.get(tool);
            jb.setText(" Level " + inv.get(tool).getLevel());
        }
    }

    /**
     * Create and inizialize the Immage button with the respective immages for each items.
     *
     */
    private void createItemButtons() {
         for (final Tooltype tool : Tooltype.values()) {
            final JButton button = new JButton();
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
             if (tool == Tooltype.WATERCAN) {
                button.setIcon(loadScaledIcon("/plantStatus/WaterCan.png"));
                button.setText(Integer.toString(controllerInventario.getInventoryClone().get(tool).getLevel()));
            } else if (tool == Tooltype.AXE) {
                button.setIcon(loadScaledIcon("/plantStatus/Axe.png"));
                button.setText(Integer.toString(controllerInventario.getInventoryClone().get(tool).getLevel()));
            } else {
                button.setIcon(loadScaledIcon("/plantStatus/Hoe.png"));
                button.setText(Integer.toString(controllerInventario.getInventoryClone().get(tool).getLevel()));
            }

            button.setEnabled(true);
            itemsDisplay.put(tool, button);
            this.add(button);
        }
    }

    /**
     * Utility method for load the Icon image for the items.
     *
     * @param path path icon
     * @return ImageIcon
     */
    private ImageIcon loadScaledIcon(final String path) {
        final var url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        }
        final ImageIcon icon = new ImageIcon(url);
        final Image scaled = icon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void setControllerInventory(final ControllerInventario entryControllerInventario) {
        this.controllerInventario = entryControllerInventario;
    }
}
