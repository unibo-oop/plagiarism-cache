package it.unibo.plantsfarm.view.map.impl;

import it.unibo.plantsfarm.view.map.api.SeedView;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.TextureUtils;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.BorderLayout;

/**
 * Represents the view for selecting seeds to plant.
 * It adapts its size based on the screen resolution.
 */
public final class SeedViewImpl implements SeedView {

    private static final String TITLE_EDIBLE = "Edible Plants";
    private static final String TITLE_ORNAMENTAL = "Ornamental Plants";
    private static final Color BG_COLOR = new Color(245, 245, 220);
    private static final Color ACTIVE_BUTTON = new Color(200, 230, 200);

    private static final int GRID_COLS = 3;
    private static final int VISIBLE_ROWS = 3;

    private static final double ICON_SCALE = 0.10;
    private static final double GAP_SCALE = 0.005;
    private static final double PADDING_SCALE = 0.01;

    private final JDialog dialog;
    private final JPanel gridPanel;
    private final int iconSize;
    private final JButton edibleButton;
    private final JButton ornamentalButton;

    /**
     * Creates a new SeedView.
     *
     * @param isEdible True to show edible plants, false for ornamental ones.
     */
    public SeedViewImpl(final boolean isEdible) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;

        this.iconSize = (int) (screenHeight * ICON_SCALE);
        final int gap = (int) (screenHeight * GAP_SCALE);
        final int padding = (int) (screenHeight * PADDING_SCALE);

        this.dialog = new JDialog();
        this.dialog.setTitle(isEdible ? TITLE_EDIBLE : TITLE_ORNAMENTAL);
        this.dialog.setModal(true);
        this.dialog.setResizable(false);
        this.dialog.setLayout(new BorderLayout());

        this.dialog.getRootPane().registerKeyboardAction(e -> close(),
            KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),
            JPanel.WHEN_IN_FOCUSED_WINDOW
        );

        final JPanel headerPanel = new JPanel(new GridLayout(1, 2));

        this.edibleButton = new JButton("EDIBLE");
        this.ornamentalButton = new JButton("ORNAMENTAL");

        final Font btnFont = new Font("Arial", Font.BOLD, (int) (this.iconSize * 0.2));
        this.edibleButton.setFont(btnFont);
        this.ornamentalButton.setFont(btnFont);
        this.edibleButton.setFocusable(false);
        this.ornamentalButton.setFocusable(false);

        if (isEdible) {
            this.edibleButton.setBackground(ACTIVE_BUTTON);
            this.edibleButton.setEnabled(false);
            this.ornamentalButton.setBackground(Color.WHITE);
        } else {
            this.ornamentalButton.setBackground(ACTIVE_BUTTON);
            this.ornamentalButton.setEnabled(false);
            this.edibleButton.setBackground(Color.WHITE);
        }

        headerPanel.add(this.edibleButton);
        headerPanel.add(this.ornamentalButton);

        this.dialog.add(headerPanel, BorderLayout.NORTH);

        this.gridPanel = new JPanel();
        this.gridPanel.setLayout(new GridLayout(0, GRID_COLS, gap, gap));
        this.gridPanel.setBackground(BG_COLOR);
        this.gridPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        final JScrollPane scrollPane = new JScrollPane(this.gridPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        if (isEdible) {
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        } else {
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        }

        final int contentSize = (this.iconSize * VISIBLE_ROWS) + (gap * (VISIBLE_ROWS - 1)) + (padding * 2);

        int scrollBarWidth = 0;
        if (isEdible) {
            scrollBarWidth = scrollPane.getVerticalScrollBar().getPreferredSize().width;
        }

        scrollPane.setPreferredSize(new Dimension(contentSize + scrollBarWidth, contentSize));

        this.dialog.add(scrollPane, BorderLayout.CENTER);
        this.dialog.pack();
        this.dialog.setLocationRelativeTo(null);
    }

    /**
     * Adds a plant button to the grid.
     *
     * @param plantName     The name of the plant.
     * @param isDiscovered  If false, the icon will be darkened.
     * @param listener      The action to perform on click.
     */
    @Override
    public void addPlantButton(final String plantName, final boolean isDiscovered, final ActionListener listener) {
        ImageIcon icon = Texture.getPlantIcon(plantName);

        if (icon.getIconWidth() != this.iconSize) {
            final Image img = icon.getImage().getScaledInstance(this.iconSize, this.iconSize, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }

        if (!isDiscovered) {
            icon = TextureUtils.createLockedIcon(icon);
        }
        final JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(this.iconSize, this.iconSize));
        button.addActionListener(listener);
        this.gridPanel.add(button);
    }

    /**
     * Attaches a listener to the switch buttons.
     *
     * @param listener The action to perform.
     */
    @Override
    public void addSwitchModeListener(final ActionListener listener) {

        if (this.edibleButton.isEnabled()) {
            this.edibleButton.addActionListener(e -> {
                close();
                listener.actionPerformed(e);
            });
        }
        if (this.ornamentalButton.isEnabled()) {
            this.ornamentalButton.addActionListener(e -> {
                close();
                listener.actionPerformed(e);
            });
        }
    }

    /**
     * Shows the dialog.
     */
    @Override
    public void show() {
        this.dialog.setVisible(true);
    }

    /**
     * Closes the dialog.
     */
    @Override
    public void close() {
        this.dialog.dispose();
    }
}
