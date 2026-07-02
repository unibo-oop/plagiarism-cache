package it.unibo.view.menu.impl;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.controller.menu.api.MenuController;
import it.unibo.view.menu.api.MenuView;
import it.unibo.view.utilities.ViewConstants;

/**
 * Implementation of {@link MenuView} interface.
 */
public final class MenuViewImpl extends JPanel implements MenuView {

    private static final Logger LOGGER = Logger.getLogger(MenuViewImpl.class.getName());
    private static final long serialVersionUID = 1L;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private static final int TITLE_SIZE = 70;
    private static final int HIGHSCORE_LABEL_SIZE = 22;
    private final transient MenuController controller;
    private transient BufferedImage backgroundImage;
    private JButton highScoreLabel;

    /**
     * Construct a MenuViewImpl with specified controller.
     * 
     * @param controller the controller for managing user interactions and updating
     *                   the view
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The View must hold a reference to the actual Controller instance to dispatch user inputs."
        + "A defensive copy cannot be used here as it would break the MVC communication flow."
    )
    public MenuViewImpl(final MenuController controller) {
        super(new BorderLayout());
        this.controller = controller;
        final File bgFile = new File("src/main/resources/background.jpeg");
        if (bgFile.exists()) {
            try {
                this.backgroundImage = ImageIO.read(bgFile);
            } catch (final IOException e) {
                LOGGER.log(Level.SEVERE, "Errore I/O", e);
            }
        }
        initialize();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    /**
     * Initialize the view components.
     */
    private void initialize() {
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        final JLabel titleLabel = new JLabel("JAVA CLIMBER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, TITLE_SIZE));
        titleLabel.setForeground(Color.RED);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        final JButton btnStart = createMenuButton("START GAME");
        btnStart.setBackground(Color.WHITE);
        final JButton btnShop = createMenuButton("SHOP");
        btnShop.setBackground(Color.WHITE);
        final JButton btnInventory = createMenuButton("INVENTORY");
        btnInventory.setBackground(Color.WHITE);
        final JButton btnExit = createMenuButton("EXIT");
        btnExit.setBackground(Color.WHITE);

        this.highScoreLabel = new JButton("HIGH SCORE: " + controller.getHighScore());
        this.highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.highScoreLabel.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.highScoreLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.highScoreLabel.setFont(new Font("Arial", Font.PLAIN, HIGHSCORE_LABEL_SIZE));
        this.highScoreLabel.setBackground(Color.WHITE);
        this.highScoreLabel.setFocusable(false);
        this.highScoreLabel.setRolloverEnabled(false);
        this.highScoreLabel.setRequestFocusEnabled(false);
        this.highScoreLabel.setFocusPainted(false);

        btnStart.addActionListener(e -> start());
        btnShop.addActionListener(e -> shop());
        btnInventory.addActionListener(e -> inventory());
        btnExit.addActionListener(e -> exit());

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(titleLabel);
        buttonPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_30));

        buttonPanel.add(btnStart);
        buttonPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_20));
        buttonPanel.add(this.highScoreLabel);
        buttonPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_20));
        buttonPanel.add(btnShop);
        buttonPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_20));
        buttonPanel.add(btnInventory);
        buttonPanel.add(Box.createVerticalStrut(ViewConstants.SPACING_20));
        buttonPanel.add(btnExit);
        buttonPanel.add(Box.createVerticalGlue());

        final JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Helper method to create a styled menu button.
     * 
     * @param text the text to display on the button
     * @return the created button
     */
    private JButton createMenuButton(final String text) {
        final JButton button = new JButton(text);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setFont(new Font("Arial", Font.PLAIN, ViewConstants.FONT_TITLE_SIZE));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Display the menu view.
     */
    public void display() {
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHighScore(final int score) {
        if (this.highScoreLabel != null) {
            this.highScoreLabel.setText("HIGH SCORE: " + score);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.controller.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shop() {
        this.controller.shop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inventory() {
        this.controller.inventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        this.controller.exit();
    }

}
