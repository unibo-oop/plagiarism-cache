package it.unibo.view.shop.impl;

import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import it.unibo.controller.shop.api.ShopController;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.view.gamelaunchedview.renderers.skinregistry.api.SkinRegistry;
import it.unibo.view.gamelaunchedview.renderers.skinregistry.api.SkinSet;
import it.unibo.view.gamelaunchedview.renderers.skinregistry.impl.SkinRegistryImpl;
import it.unibo.view.shop.api.ShopView;
import it.unibo.view.utilities.SpriteEnum;
import it.unibo.view.utilities.SpriteManager;
import it.unibo.view.utilities.ViewConstants;

/**
 * Implementation of {@link ShopView} interface.
 */
public final class ShopViewImpl extends JPanel implements ShopView {

    private static final long serialVersionUID = 1L;
    private final transient ShopController controller;
    private final transient SpriteManager spriteManager;
    private final transient SkinRegistry skinRegistry;
    private JLabel coinsLabel;
    private JPanel itemsPanel;
    private JPanel skinsPanel;
    private JPanel permPanel;
    private JPanel tempPanel;

    /**
     * Construct a ShopViewImpl with specified controller.
     * 
     * @param controller the controller for managing user interactions and updating
     *                   the view
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The View must hold a reference to the actual Controller instance to dispatch user inputs."
        + "A defensive copy cannot be used here as it would break the MVC communication flow."
    )
    public ShopViewImpl(final ShopController controller) {
        super(new BorderLayout());
        this.controller = controller;
        this.spriteManager = new SpriteManager();
        this.skinRegistry = new SkinRegistryImpl();
        initialize();
    }

    /**
     * Initialize the view components.
     */
    private void initialize() {
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        final JLabel titleLabel = new JLabel("SHOP");
        titleLabel.setFont(new Font(ViewConstants.FONT_ARIAL, Font.BOLD, ViewConstants.FONT_TITLE_SIZE));
        titleLabel.setForeground(Color.BLUE);
        topPanel.add(titleLabel, BorderLayout.WEST);

        final JPanel coinsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        this.coinsLabel = new JLabel("Coins: 0");
        this.coinsLabel.setFont(new Font(ViewConstants.FONT_ARIAL, Font.BOLD, ViewConstants.FONT_COIN_LABEL_SIZE));

        final JLabel coinIconLabel = new JLabel();
        final BufferedImage coinImg = spriteManager.get(SpriteEnum.COIN);
        if (coinImg != null) {
            final Image scaledCoin = coinImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            coinIconLabel.setIcon(new ImageIcon(scaledCoin));
        }
        coinsPanel.add(coinsLabel);
        coinsPanel.add(coinIconLabel);

        final JButton inventoryButton = new JButton("INVENTORY");
        inventoryButton.addActionListener(e -> controller.openInventory());

        final JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(e -> controller.exit());

        final JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, ViewConstants.SPACING_20, 0));
        rightHeader.add(coinsPanel);
        rightHeader.add(inventoryButton);
        rightHeader.add(exitButton);

        topPanel.add(rightHeader, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        this.itemsPanel = new JPanel(new GridLayout(1, 3, 10, 0));

        this.skinsPanel = createCategoryPanel("SKINS");
        this.permPanel = createCategoryPanel("PERMANENT POWER-UPS");
        this.tempPanel = createCategoryPanel("TEMPORARY POWER-UPS");

        final JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Helper method to create a category panel with a title and add it to the items
     * panel.
     * 
     * @param title the title of the category panel
     * @return the created category panel
     */
    private JPanel createCategoryPanel(final String title) {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        final JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        panel.add(content, BorderLayout.CENTER);
        this.itemsPanel.add(panel);
        return content;
    }

    /**
     * Helper method to create a widget for a skin item.
     * 
     * @param item  the skin item to create the widget for
     * @param index the index of the item in the skins list
     * @return a JPanel representing the skin widget
     */
    private JPanel createSkinWidget(final ShopItem item, final int index) {
        final JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        card.setMaximumSize(new Dimension(ViewConstants.SKIN_CARD_WIDTH_SHOP, ViewConstants.SKIN_CARD_HEIGHT));
        card.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel imgLabel;
        final SkinSet skinSet = skinRegistry.getSkinSet(item.getId());
        final BufferedImage img = spriteManager.get(skinSet.right());
        if (img != null) {
            final Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(scaledImg));
        } else {
            imgLabel = new JLabel("Image not found");
            imgLabel.setPreferredSize(new Dimension(100, 100));
        }
        imgLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel descLabel = new JLabel(
                "<html><div style='text-align: center; width: 180px;'>" + item.getDescription() + "</div></html>");
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descLabel.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel priceLabel = new JLabel(item.getPrice() + ViewConstants.DOLLAR_SIMBOL);
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JButton buyButton = new JButton();
        if (controller.isOwned(item)) {
            buyButton.setText("OWNED");
            buyButton.setEnabled(false);
        } else {
            buyButton.setText("BUY");
            buyButton.addActionListener(e -> controller.buySkin(index));
        }
        buyButton.setAlignmentX(CENTER_ALIGNMENT);

        card.add(imgLabel);
        card.add(Box.createVerticalStrut(ViewConstants.SPACING_15));
        card.add(nameLabel);
        card.add(descLabel);
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(buyButton);

        return card;
    }

    /**
     * Helper method to create a widget for a permanent power up category.
     * 
     * @param title  the title of the widget
     * @param prefix the prefix for filtering items
     * @param items  the list of shop items
     * @return the created permanent power-up widget
     */
    private JPanel createPermanentWidget(final String title, final String prefix, final List<ShopItem> items) {
        final JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        container.setMaximumSize(
                new Dimension(ViewConstants.PERMANENT_CONTAINER_WIDTH, ViewConstants.PERMANENT_CONTAINER_HEIGHT));
        container.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(ViewConstants.FONT_ARIAL, Font.BOLD, 16));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        final int currentLevel = controller.getCurrentLevel(prefix);

        final List<ShopItem> categoryItems = items.stream()
                .filter(i -> i.getId().startsWith(prefix))
                .sorted(Comparator.comparing(ShopItem::getId))
                .toList();
        final int maxLevel = categoryItems.size();

        final JProgressBar progressBar = new JProgressBar(0, maxLevel);
        progressBar.setValue(currentLevel);
        progressBar.setStringPainted(true);
        progressBar.setString("Level " + currentLevel + "/" + maxLevel);
        progressBar.setMaximumSize(new Dimension(ViewConstants.PROGRESS_BAR_WIDTH, ViewConstants.PROGRESS_BAR_HEIGHT));
        progressBar.setAlignmentX(CENTER_ALIGNMENT);

        container.add(titleLabel);
        container.add(Box.createVerticalStrut(ViewConstants.SPACING_20));
        container.add(progressBar);
        container.add(Box.createVerticalStrut(ViewConstants.SPACING_20));

        final ShopItem nextItem = categoryItems.stream()
                .filter(i -> !controller.isOwned(i))
                .findFirst()
                .orElse(null);

        if (nextItem != null) {
            final JLabel nextname = new JLabel("Next: " + nextItem.getName());
            nextname.setAlignmentX(CENTER_ALIGNMENT);
            final JLabel nextDesc = new JLabel(nextItem.getDescription());
            nextDesc.setAlignmentX(CENTER_ALIGNMENT);
            final JLabel price = new JLabel("Cost: " + nextItem.getPrice() + ViewConstants.DOLLAR_SIMBOL);
            price.setAlignmentX(CENTER_ALIGNMENT);
            final JButton upgradeButton = new JButton("UPGRADE");
            upgradeButton.addActionListener(e -> {
                if (prefix.contains("jump")) {
                    controller.upgradeJump();
                } else {
                    controller.upgradeSpeed();
                }
            });
            upgradeButton.setAlignmentX(CENTER_ALIGNMENT);

            container.add(nextname);
            container.add(Box.createVerticalStrut(10));
            container.add(nextDesc);
            container.add(Box.createVerticalStrut(10));
            container.add(price);
            container.add(Box.createVerticalGlue());
            container.add(upgradeButton);
        } else {
            final JLabel maxReachedLabel = new JLabel("MAX LEVEL REACHED");
            maxReachedLabel.setAlignmentX(CENTER_ALIGNMENT);
            container.add(maxReachedLabel);
        }
        return container;
    }

    /**
     * Helper method to create a widget for a temporary power up item.
     * 
     * @param item  the shop item to create the widget for
     * @param index the index of the item in the temporary items list
     * @return the created temporary power-up widget
     */
    private JPanel createTemporaryWidget(final ShopItem item, final int index) {
        final JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        card.setMaximumSize(new Dimension(ViewConstants.TEMPORARY_CARD_WIDTH, ViewConstants.TEMPORARY_CARD_HEIGHT));
        card.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel descLabel = new JLabel(item.getDescription());
        descLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel priceLabel = new JLabel(item.getPrice() + ViewConstants.DOLLAR_SIMBOL);
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JButton buyButton = new JButton();
        if (controller.isOwned(item)) {
            buyButton.setText("OWNED");
            buyButton.setEnabled(false);
        } else {
            buyButton.setText("BUY");
            buyButton.addActionListener(e -> controller.buyTemporaryItem(index));
        }
        buyButton.setAlignmentX(CENTER_ALIGNMENT);

        card.add(nameLabel);
        card.add(Box.createVerticalStrut(ViewConstants.SPACING_5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(ViewConstants.SPACING_5));
        card.add(priceLabel);
        card.add(Box.createVerticalGlue());
        card.add(buyButton);

        return card;
    }

    /**
     * Helper method to create a subheader label for category sections.
     * 
     * @param text the text for the subheader
     * @return the created subheader label
     */
    private JLabel createSubHeader(final String text) {
        final JLabel label = new JLabel(text);
        label.setFont(new Font(ViewConstants.FONT_ARIAL, Font.BOLD, 16));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(10, 0, ViewConstants.SPACING_5, 0));
        return label;
    }

    /**
     * Helper method to create a temporary power-up category section.
     * 
     * @param title  the title of the category
     * @param prefix the prefix for filtering items
     * @param items  the list of items in the category
     */
    private void addTempCategory(final String title, final String prefix, final List<ShopItem> items) {
        final JPanel categoryContainer = new JPanel();
        categoryContainer.setLayout(new BoxLayout(categoryContainer, BoxLayout.Y_AXIS));
        categoryContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, ViewConstants.SPACING_20, 10, ViewConstants.SPACING_20)));
        categoryContainer.setAlignmentX(CENTER_ALIGNMENT);
        categoryContainer.add(createSubHeader(title));
        categoryContainer.add(Box.createVerticalStrut(10));
        for (int i = 0; i < items.size(); i++) {
            final ShopItem item = items.get(i);
            if (item.getId().startsWith(prefix)) {
                categoryContainer.add(createTemporaryWidget(item, i));
                categoryContainer.add(Box.createVerticalStrut(10));
            }
        }

        final Dimension maxSize = categoryContainer.getPreferredSize();
        categoryContainer.setMaximumSize(new Dimension(ViewConstants.TEMPCATEGORY_CONTAINER_WIDTH, maxSize.height));
        tempPanel.add(categoryContainer);
        tempPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_20));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display() {
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCoins(final int coins) {
        this.coinsLabel.setText("Coins: " + coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItems(final List<ShopItem> skins, final List<ShopItem> permUpgrades,
            final List<ShopItem> tempUpgrades) {
        skinsPanel.removeAll();
        permPanel.removeAll();
        tempPanel.removeAll();

        for (int i = 0; i < skins.size(); i++) {
            skinsPanel.add(Box.createVerticalStrut(10));
            skinsPanel.add(createSkinWidget(skins.get(i), i));
        }

        permPanel.add(Box.createVerticalStrut(10));
        permPanel.add(createPermanentWidget("JUMP HEIGHT", "pp_jump", permUpgrades));
        permPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_30));
        permPanel.add(createPermanentWidget("SPEED BOOST", "pp_speed", permUpgrades));

        addTempCategory("JUMP HEIGHT", "pt_jump", tempUpgrades);
        addTempCategory("SPEED BOOST", "pt_speed", tempUpgrades);
        addTempCategory("COIN MULTIPLIER", "pt_coin", tempUpgrades);

        skinsPanel.revalidate();
        skinsPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.setVisible(false);
    }

}
