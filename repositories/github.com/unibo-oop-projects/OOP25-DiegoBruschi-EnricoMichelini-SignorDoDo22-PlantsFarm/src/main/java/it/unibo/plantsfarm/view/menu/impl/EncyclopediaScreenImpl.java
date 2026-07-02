package it.unibo.plantsfarm.view.menu.impl;

import it.unibo.plantsfarm.view.menu.api.EncyclopediaScreen;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.TextureUtils;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * Manages the view for the Encyclopedia feature.
 */
public final class EncyclopediaScreenImpl implements EncyclopediaScreen {

    private static final String TITLE = "Encyclopedia";
    private static final String FONT_NAME = "Arial";
    private static final int GRID_COLS = 5;

    private static final double FONT_TITLE_RATIO = 0.025;
    private static final double FONT_DESCRIPTION_RATIO = 0.015;
    private static final double GAP_RATIO = 0.01;
    private static final double PADDING_RATIO = 0.02;

    private static final Color BG_COMMON = new Color(144, 238, 144);
    private static final Color BG_RARE = new Color(221, 160, 221);
    private static final Color BG_EPIC = new Color(255, 117, 120);
    private static final Color BG_LEGENDARY = new Color(255, 252, 115);

    private static final Color DARK_COMMON = new Color(34, 139, 34);
    private static final Color DARK_RARE = new Color(138, 43, 226);
    private static final Color DARK_EPIC = new Color(220, 20, 60);
    private static final Color DARK_LEGENDARY = new Color(255, 140, 0);

    private static final Color BG = new Color(245, 245, 220);

    private final JDialog encyclpediaScreen;
    private final JPanel buttonPanel;
    private final JPanel detailsPanel;
    private final JLabel nameLabel;
    private final JLabel plantStageImageLabel;
    private final JTextArea plantDescriptionArea;
    private final JButton stageButton;

    /**
     * Initializes the screen.
     */
    public EncyclopediaScreenImpl() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;

        final int fontSizeTitle = (int) (screenHeight * FONT_TITLE_RATIO);
        final int fontSizeDesc = (int) (screenHeight * FONT_DESCRIPTION_RATIO);
        final int gap = (int) (screenHeight * GAP_RATIO);
        final int padding = (int) (screenHeight * PADDING_RATIO);

        this.encyclpediaScreen = WindowFactory.createMenuWindow(TITLE);
        this.encyclpediaScreen.setLayout(new GridLayout(1, 2));

        this.buttonPanel = new JPanel(new GridLayout(0, GRID_COLS, gap, gap));
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));

        this.detailsPanel = new JPanel(new BorderLayout(gap, gap));
        this.detailsPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        this.detailsPanel.setBackground(BG);

        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, gap, 0));
        topPanel.setOpaque(false);

        this.nameLabel = new JLabel("Select a Plant");
        this.nameLabel.setFont(new Font(FONT_NAME, Font.BOLD, fontSizeTitle));
        this.nameLabel.setOpaque(true);
        this.nameLabel.setBackground(BG);
        this.nameLabel.setForeground(Color.BLACK);
        this.nameLabel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        this.stageButton = ButtonFactory.createButton("Next Stage");
        this.stageButton.setVisible(false);

        topPanel.add(this.nameLabel);
        topPanel.add(this.stageButton);

        final JPanel centerWrapper = new JPanel(new BorderLayout(0, gap));
        centerWrapper.setOpaque(false);

        this.plantStageImageLabel = new JLabel();
        this.plantStageImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        centerWrapper.add(this.plantStageImageLabel, BorderLayout.CENTER);

        this.plantDescriptionArea = new JTextArea("Click on a plant icon.");
        this.plantDescriptionArea.setFont(new Font(FONT_NAME, Font.BOLD, fontSizeDesc));
        this.plantDescriptionArea.setEditable(false);
        this.plantDescriptionArea.setLineWrap(true);
        this.plantDescriptionArea.setOpaque(false);

        this.detailsPanel.add(topPanel, BorderLayout.NORTH);
        this.detailsPanel.add(centerWrapper, BorderLayout.CENTER);
        this.detailsPanel.add(this.plantDescriptionArea, BorderLayout.SOUTH);

        this.encyclpediaScreen.add(this.buttonPanel);
        this.encyclpediaScreen.add(this.detailsPanel);
    }

    /**
     * Updates rarity badge.
     *
     * @param rarity the name of the rarity.
     */
    @Override
    public void setRarity(final String rarity) {
        this.nameLabel.setForeground(Color.WHITE);
        switch (rarity) {
            case "COMMON":
                this.nameLabel.setBackground(DARK_COMMON);
                this.detailsPanel.setBackground(BG_COMMON);
                break;
            case "RARE":
                this.nameLabel.setBackground(DARK_RARE);
                this.detailsPanel.setBackground(BG_RARE);
                break;
            case "EPIC":
                this.nameLabel.setBackground(DARK_EPIC);
                this.detailsPanel.setBackground(BG_EPIC);
                break;
            case "LEGENDARY":
                this.nameLabel.setBackground(DARK_LEGENDARY);
                this.detailsPanel.setBackground(BG_LEGENDARY);
                break;
            default:
                this.nameLabel.setBackground(BG);
                this.nameLabel.setForeground(Color.BLACK);
                this.detailsPanel.setBackground(BG);
                break;
        }
    }

    /**
     * Shows the screen.
     *
     * @param names names list.
     * @param unlocked unlock status list.
     * @param listener controller listener.
     * @param stageCommand the stage command.
     */
    @Override
    public void show(final List<String> names, final List<Boolean> unlocked,
        final ActionListener listener, final String stageCommand) {

        this.buttonPanel.removeAll();
        this.stageButton.setActionCommand(stageCommand);
        for (final ActionListener oldActionListener : this.stageButton.getActionListeners()) {
            this.stageButton.removeActionListener(oldActionListener);
        }
        this.stageButton.addActionListener(listener);

        for (int currentIndex = 0; currentIndex < names.size(); currentIndex++) {
            final String name = names.get(currentIndex);
            ImageIcon icon = Texture.getPlantIcon(name);

            if (!unlocked.get(currentIndex)) {
                icon = TextureUtils.createLockedIcon(icon);
            }

            final JButton button = ButtonFactory.createMenuButton(icon);

            if (unlocked.get(currentIndex)) {
                button.setActionCommand(name);
                button.addActionListener(listener);
            }

            this.buttonPanel.add(button);
        }
        this.encyclpediaScreen.setVisible(true);
    }

    /**
     * Updates image and returns true if successful.
     *
     * @param name plant name.
     * @param stage stage index.
     * @return true if icon was found, false otherwise.
     */
    @Override
    public boolean updateStageImage(final String name, final int stage) {
        final ImageIcon icon = Texture.getPlantStageIcon(name, stage);
        if (icon != null) {
            this.plantStageImageLabel.setIcon(icon);
            return true;
        }
        return false;
    }

    /**
     * Updates details.
     *
     * @param name plant name.
     * @param description plant description.
     */
    @Override
    public void updateDetails(final String name, final String description) {
        this.nameLabel.setText(name);
        this.plantDescriptionArea.setText(description);
        this.stageButton.setVisible(true);
    }
}
