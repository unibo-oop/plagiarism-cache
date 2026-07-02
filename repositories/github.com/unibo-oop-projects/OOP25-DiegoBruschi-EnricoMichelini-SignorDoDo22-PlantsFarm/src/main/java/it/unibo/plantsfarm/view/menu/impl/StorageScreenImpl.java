package it.unibo.plantsfarm.view.menu.impl;

import it.unibo.plantsfarm.view.menu.api.StorageScreen;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * Manages the view for the Storage feature.
 */
public final class StorageScreenImpl implements StorageScreen {

    private static final String TITLE = "Storage";
    private static final double GAP_RATIO = 0.005;
    private static final double PADDING_RATIO = 0.005;
    private static final int GRID_COLS = 3;

    private static final Color BG_COLOR = new Color(245, 245, 220);

    private final JDialog dialog;
    private final JPanel itemsPanel;
    private final Map<String, JButton> itemButtons;

    /**
     * Creates a new StorageScreen using WindowFactory.
     */
    public StorageScreenImpl() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int gap = (int) (screenSize.height * GAP_RATIO);
        final int padding = (int) (screenSize.height * PADDING_RATIO);

        this.dialog = WindowFactory.createMenuWindow(TITLE);
        this.dialog.setLayout(new BorderLayout());

        this.itemsPanel = new JPanel(new GridLayout(0, GRID_COLS, gap, gap));
        this.itemsPanel.setBackground(BG_COLOR);
        this.itemsPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        this.dialog.add(itemsPanel, BorderLayout.CENTER);

        this.itemButtons = new HashMap<>();
    }

    /**
     * Creates a visual slot for a plant using ButtonFactory.
     *
     * @param plantName The name of the plant.
     */
    @Override
    public void createStorageSlot(final String plantName) {
        if (itemButtons.containsKey(plantName)) {
            return;
        }

        final ImageIcon icon = Texture.getPlantIcon(plantName);
        final String buttonText = formatButtonText(plantName, 0);

        final JButton itemButton = ButtonFactory.createButton(buttonText);

        itemButton.setIcon(icon);
        itemButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        itemButton.setVerticalTextPosition(SwingConstants.CENTER);
        itemButton.setHorizontalAlignment(SwingConstants.LEFT);

        itemButton.setBackground(Color.WHITE);
        itemButton.setToolTipText("Inventory: " + plantName);
        itemButton.setEnabled(false);

        this.itemButtons.put(plantName, itemButton);
        this.itemsPanel.add(itemButton);
    }

    /**
     * Updates the quantity for a specific plant.
     *
     * @param plantName   The name of the plant.
     * @param newQuantity The new quantity to display.
     */
    @Override
    public void updateItemQuantity(final String plantName, final int newQuantity) {
        final JButton button = itemButtons.get(plantName);
        if (button != null) {
            button.setText(formatButtonText(plantName, newQuantity));
            button.setEnabled(newQuantity > 0);
        }
    }

    /**
     * Shows the storage screen.
     */
    @Override
    public void show() {
        dialog.setVisible(true);
    }

    /**
     * Formats the text.
     *
     * @param name     The plant name.
     * @param quantity The quantity.
     * @return The formatted string.
     */
    private String formatButtonText(final String name, final int quantity) {
        return "<html><font size='4'><b>" + name + "</b></font><br>"
             + "Quantity: " + quantity + "</html>";
    }
}
