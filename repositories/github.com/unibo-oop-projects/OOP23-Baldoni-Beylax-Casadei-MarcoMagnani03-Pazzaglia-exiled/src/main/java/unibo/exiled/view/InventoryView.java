package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.model.item.utilities.ItemNames;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.TitleGameLabel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.Serial;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The view panel of the player's inventory.
 */
public final class InventoryView extends JPanel {
    @Serial
    private static final long serialVersionUID = 2L;

    private static final Color HEALING_ITEM_COLOR = new Color(102, 204, 102);
    private static final Color POWER_UP_ITEM_COLOR = new Color(255, 102, 102);
    private static final Color RESOURCE_ITEM_COLOR = new Color(128, 128, 128);
    private static final int TOP_BOTTOM_MARGIN = 15;
    private static final int INVENTORY_BUTTON_MARGIN_LEFT_RIGHT = 20;
    private static final int INVENTORY_BUTTON_MARGIN_UP_BOTTOM = 45;
    private static final double ICON_SIZE_PERCENTAGE = 0.04;
    private static final int ITEM_BUTTON_FONT_SIZE = 15;
    private final transient GameController gameController;
    private final JLabel emptyInventoryLabel;
    private final JPanel inventoryButtonsPanel;

    /**
     * The constructor of the inventory view.
     *
     * @param gameController The controller of the Game.
     * @param game           The GameView associated with the game.
     */
    public InventoryView(final GameController gameController, final GameView game) {
        this.gameController = gameController;
        setLayout(new BorderLayout());

        inventoryButtonsPanel = new JPanel();
        inventoryButtonsPanel.setLayout(new GridLayout(0, 2,
                INVENTORY_BUTTON_MARGIN_LEFT_RIGHT, INVENTORY_BUTTON_MARGIN_UP_BOTTOM));

        emptyInventoryLabel = new GameLabel("The inventory is empty");
        emptyInventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        final JScrollPane scrollPane = new JScrollPane(inventoryButtonsPanel);
        scrollPane.setBackground(Color.white);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        final JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(scrollPane, BorderLayout.CENTER);
        centralPanel.add(emptyInventoryLabel, BorderLayout.SOUTH);
        centralPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BOTTOM_MARGIN, 0, TOP_BOTTOM_MARGIN, 0));
        add(centralPanel, BorderLayout.CENTER);

        final JPanel northPanel = getNorthPanel(game);
        add(northPanel, BorderLayout.NORTH);

        updateInventoryButtons();
    }

    private static JPanel getNorthPanel(final GameView game) {
        final JLabel titleLabel = new TitleGameLabel("Inventory");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        final GameButton exitButton = new GameButton("Exit");
        exitButton.addActionListener(e -> game.hideInventory());

        final JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(exitButton, BorderLayout.WEST);
        northPanel.add(titleLabel, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BOTTOM_MARGIN, 0, TOP_BOTTOM_MARGIN, 0));

        return northPanel;
    }

    /**
     * Updates the viewed buttons inside the inventory and repaints it.
     */
    public void updateInventoryButtons() {
        inventoryButtonsPanel.removeAll();

        final Map<String, Integer> itemsList = gameController.getItemsController().getItems();

        if (itemsList.isEmpty()) {
            emptyInventoryLabel.setVisible(true);
        } else {
            emptyInventoryLabel.setVisible(false);
            final List<Map.Entry<String, Integer>> sortedItems = itemsList.entrySet().stream()
                .sorted(Comparator.comparing(entry -> gameController.getItemsController().getItemType(entry.getKey())))
                .collect(Collectors.toList());
            for (final Map.Entry<String, Integer> entry : sortedItems) {
                final String itemName = entry.getKey();
                final int quantity = entry.getValue();
                final GameButton itemButton = createItemButton(itemName, quantity);
                inventoryButtonsPanel.add(itemButton);
            }
        }

        revalidate();
        repaint();
    }

    private GameButton createItemButton(final String itemName, final int quantity) {
        final GameButton itemButton = new GameButton("");
        itemButton.setFontSize(ITEM_BUTTON_FONT_SIZE);
        final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int calculatedIconSize = (int) (screenWidth * ICON_SIZE_PERCENTAGE);
        switch (gameController.getItemsController().getItemType(itemName)) {
            case HEALTH:
                itemButton.setBackground(HEALING_ITEM_COLOR);
                itemButton.setText("<html>" + itemName + "<br>Quantity: " + quantity + "<br>Description: "
                        + gameController.getItemsController().getItemDescription(itemName)
                        + "<br>Heal: " + gameController.getItemsController().getItemValor(itemName) + "</html>");
                break;
            case POWERUP:
                itemButton.setBackground(POWER_UP_ITEM_COLOR);
                itemButton.setText("<html>" + itemName + "<br>Quantity: " + quantity
                        + "<br>Description: " + gameController.getItemsController().getItemDescription(itemName)
                        + "<br>PowerUp: " + gameController.getItemsController().getItemValor(itemName)
                        + "<br>Attribute: " + gameController.getItemsController().getItemBoostedAttributeName(itemName)
                        + "</html>");
                break;
            case RESOURCE:
                itemButton.setBackground(RESOURCE_ITEM_COLOR);
                itemButton.setText("<html>" + itemName + "<br>Quantity: " + quantity
                        + "<br>Description: " + gameController.getItemsController().getItemDescription(itemName) + "</html>");
                itemButton.setEnabled(true);
                break;
            default:
                break;
        }
        final Optional<ImageIcon> itemImage = ItemNames.getItemImage(itemName);
        itemImage.ifPresent(imageIcon -> itemButton.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(
                calculatedIconSize, calculatedIconSize, java.awt.Image.SCALE_SMOOTH))));
        itemButton.addActionListener(e -> handleItemButtonClick(itemName));
        return itemButton;
    }

    private void handleItemButtonClick(final String itemName) {
        if (gameController.getItemsController().isItemUsable(itemName)) {
            final int confirmation = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to use " + itemName + "?",
                    "Confirm Use", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                gameController.getItemsController().useItem(itemName);
                updateInventoryButtons();
            }
        }
    }
}
