package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.model.plant.PlantRegistry;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Manages the animation for opening a Mystery Box.
 */
public final class MysteryBox {

    private static final int CLOCK = 100;
    private static final int FRAMES = 20;

    private static final String FONT_NAME = "Arial";

    private static final double TITLE_RATIO = 0.04;
    private static final double NAME_RATIO = 0.03;
    private static final double WINNER_RATIO = 0.045;
    private static final double BUTTON_RATIO = 0.03;
    private static final double PADDING_RATIO = 0.04;
    private static final double GAP_RATIO = 0.02;

    private static final Color BG_COMMON = new Color(144, 238, 144);
    private static final Color BG_RARE = new Color(221, 160, 221);
    private static final Color BG_EPIC = new Color(255, 117, 120);
    private static final Color BG_LEGENDARY = new Color(255, 252, 115);

    private static final Color DARK_COMMON = new Color(34, 139, 34);
    private static final Color DARK_RARE = new Color(138, 43, 226);
    private static final Color DARK_EPIC = new Color(220, 20, 60);
    private static final Color DARK_LEGENDARY = new Color(255, 140, 0);

    private final JDialog dialog;
    private final JLabel titleLabel;
    private final JLabel iconLabel;
    private final JLabel nameLabel;
    private final JButton getWinnerButton;
    private final Random random;

    private final PlantType unlockedPlant;
    private final List<PlantType> frames;
    private final Timer animationTimer;
    private int currentIndex;
    private final Font winnerFont;

    /**
     * Creates a new Mystery Box opening.
     *
     * @param unlockedPlant The winner plant that the user has won.
     */
    public MysteryBox(final PlantType unlockedPlant) {
        this.unlockedPlant = unlockedPlant;
        this.random = new Random();
        this.frames = generateFramesList();

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;

        final int padding = (int) (screenHeight * PADDING_RATIO);
        final int gap = (int) (screenHeight * GAP_RATIO);
        final int titleSize = (int) (screenHeight * TITLE_RATIO);
        final int nameSize = (int) (screenHeight * NAME_RATIO);
        final int winnerSize = (int) (screenHeight * WINNER_RATIO);
        final int buttonSize = (int) (screenHeight * BUTTON_RATIO);

        final Font titleFont = new Font(FONT_NAME, Font.BOLD, titleSize);
        final Font nameFont = new Font(FONT_NAME, Font.BOLD, nameSize);
        final Font buttonFont = new Font(FONT_NAME, Font.BOLD, buttonSize);
        this.winnerFont = new Font(FONT_NAME, Font.BOLD, winnerSize);

        this.dialog = new JDialog((JFrame) null, "Mystery Box", true);
        this.dialog.setUndecorated(true);
        this.dialog.setResizable(false);
        this.dialog.setSize(screenSize);
        this.dialog.setLocationRelativeTo(null);

        final JPanel mainPanel = new JPanel(new BorderLayout());
        Color tempDark = Color.BLACK;

        switch (unlockedPlant.getRarity()) {
            case COMMON -> {
                mainPanel.setBackground(BG_COMMON);
                tempDark = DARK_COMMON;
            }
            case RARE -> {
                mainPanel.setBackground(BG_RARE);
                tempDark = DARK_RARE;
            }
            case EPIC -> {
                mainPanel.setBackground(BG_EPIC);
                tempDark = DARK_EPIC;
            }
            case LEGENDARY -> {
                mainPanel.setBackground(BG_LEGENDARY);
                tempDark = DARK_LEGENDARY;
            }
        }
        mainPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        final Color darkColor = tempDark;

        this.titleLabel = new JLabel("OPENING MYSTERY BOX...", SwingConstants.CENTER);
        this.titleLabel.setFont(titleFont);
        this.titleLabel.setForeground(darkColor);

        mainPanel.add(this.titleLabel, BorderLayout.NORTH);

        this.iconLabel = new JLabel("", SwingConstants.CENTER);
        this.iconLabel.setIcon(Texture.getMysteryPlantIcon(frames.get(0).getName()));

        mainPanel.add(this.iconLabel, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1, gap, gap));
        bottomPanel.setOpaque(false);

        this.nameLabel = new JLabel("???", SwingConstants.CENTER);
        this.nameLabel.setFont(nameFont);
        this.nameLabel.setForeground(darkColor);

        final JPanel buttonWrapper = new JPanel();
        buttonWrapper.setOpaque(false);

        this.getWinnerButton = ButtonFactory.createButton("GET!");
        this.getWinnerButton.setFont(buttonFont);
        this.getWinnerButton.setForeground(darkColor);
        this.getWinnerButton.setVisible(false);
        this.getWinnerButton.addActionListener(e -> {
            this.dialog.dispose();
        });

        buttonWrapper.add(this.getWinnerButton);

        bottomPanel.add(this.nameLabel);
        bottomPanel.add(buttonWrapper);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.dialog.add(mainPanel);

        this.animationTimer = new Timer(CLOCK, e -> updateFrame());
    }

    private List<PlantType> generateFramesList() {
        final List<PlantType> sequence = new ArrayList<>();
        final List<PlantType> allPlants = PlantRegistry.getAll();

        for (int i = 0; i < FRAMES; i++) {
            sequence.add(allPlants.get(this.random.nextInt(allPlants.size())));
        }
        sequence.add(this.unlockedPlant);
        return sequence;
    }

    private void updateFrame() {
        if (currentIndex < frames.size() - 1) {
            final PlantType current = frames.get(currentIndex);
            this.iconLabel.setIcon(Texture.getMysteryPlantIcon(current.getName()));
            currentIndex++;
        } else {
            this.animationTimer.stop();
            showWinner();
        }
    }

    private void showWinner() {
        final String realName = this.unlockedPlant.getName();
        this.iconLabel.setIcon(Texture.getMysteryPlantIcon(realName));

        this.titleLabel.setText("NEW PLANT UNLOCKED!");

        this.nameLabel.setText(realName);
        this.nameLabel.setFont(this.winnerFont);

        this.getWinnerButton.setVisible(true);
        this.getWinnerButton.requestFocusInWindow();
    }

    /**
     * Starts the animation and shows the dialog.
     */
    public void start() {
        this.animationTimer.start();
        this.dialog.setVisible(true);
    }
}
