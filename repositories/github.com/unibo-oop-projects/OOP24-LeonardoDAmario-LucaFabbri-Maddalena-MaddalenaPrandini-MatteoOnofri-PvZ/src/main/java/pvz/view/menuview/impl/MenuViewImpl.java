package pvz.view.menuview.impl;

import pvz.controller.menucontroller.api.MenuController;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;
import pvz.view.menuview.api.MenuView;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Implementation of the MenuView interface.
 * This class is responsible for displaying the main menu and handling menu interactions.
 */
public final class MenuViewImpl extends JPanel implements MenuView {

    private static final long serialVersionUID = 1L;
    private static final String FONT_NAME = "Arial";
    private static final int TITLE_FONT_SIZE = 40;
    private static final int DIFFICULTY_FONT_SIZE = 28;
    private static final int RES_LABEL_FONT_SIZE = 28;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int EMPTY_BORDER_TOP = 50;
    private static final int EMPTY_BORDER_LEFT = 150;
    private static final int EMPTY_BORDER_BOTTOM = 50;
    private static final int EMPTY_BORDER_RIGHT = 150;
    private static final int TITLE_BORDER_BOTTOM = 40;
    private static final int DIFFICULTY_BORDER_TOP = 35;
    private static final int SETTINGS_PANEL_GRID_ROWS = 2;
    private static final int SETTINGS_PANEL_GRID_COLS = 1;
    private static final int SETTINGS_PANEL_GRID_HGAP = 5;
    private static final int SETTINGS_PANEL_GRID_VGAP = 5;
    private static final int BUTTON_PANEL_GRID_ROWS = 3;
    private static final int BUTTON_PANEL_GRID_COLS = 1;
    private static final int BUTTON_PANEL_GRID_HGAP = 10;
    private static final int BUTTON_PANEL_GRID_VGAP = 10;
    private static final Color BACKGROUND_COLOR = new Color(34, 139, 34);

    private final JButton playButton;
    private final JButton difficultyButton;
    private final JButton tutorialButton;
    private final JButton exitButton;
    private final JLabel difficultyLabel;
    private final JComboBox<Resolution> resolutionCombo;
    private Difficulty currentDifficulty = Difficulty.NORMAL;
    private Resolution currentResolution = Resolution.R_800X600;
    private final transient MenuController parentController;
    private final JFrame frame = new JFrame();

    /**
     * Constructs a new MenuViewImpl with the given controller.
     *
     * @param controller the MenuController instance
     */
    public MenuViewImpl(final MenuController controller) {
        this.parentController = controller;
        this.setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel("Piante contro Zombie", SwingConstants.CENTER);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, TITLE_FONT_SIZE));
        this.add(titleLabel, BorderLayout.NORTH);

        final JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(SETTINGS_PANEL_GRID_ROWS, SETTINGS_PANEL_GRID_COLS,
                SETTINGS_PANEL_GRID_HGAP, SETTINGS_PANEL_GRID_VGAP));
        settingsPanel.setBackground(BACKGROUND_COLOR);

        difficultyLabel = new JLabel("Difficoltà: Normale", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font(FONT_NAME, Font.BOLD, DIFFICULTY_FONT_SIZE));
        settingsPanel.add(difficultyLabel);

        resolutionCombo = new JComboBox<>(Resolution.values());
        resolutionCombo.setSelectedItem(Resolution.R_800X600);
        resolutionCombo.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        final JPanel resPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resPanel.setBackground(BACKGROUND_COLOR);
        final JLabel resLabel = new JLabel("Risoluzione: ", SwingConstants.CENTER);
        resLabel.setFont(new Font(FONT_NAME, Font.BOLD, RES_LABEL_FONT_SIZE));
        resPanel.add(resLabel);
        resPanel.add(resolutionCombo);
        settingsPanel.add(resPanel);
        this.add(settingsPanel, BorderLayout.SOUTH);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(BUTTON_PANEL_GRID_ROWS, BUTTON_PANEL_GRID_COLS,
                BUTTON_PANEL_GRID_HGAP, BUTTON_PANEL_GRID_VGAP));
        playButton = new JButton("Gioca");
        difficultyButton = new JButton("Seleziona Difficoltà");
        tutorialButton = new JButton("Tutorial");
        exitButton = new JButton("Esci");

        buttonPanel.add(playButton);
        buttonPanel.add(difficultyButton);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(exitButton);

        this.add(buttonPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(
                EMPTY_BORDER_TOP, EMPTY_BORDER_LEFT, EMPTY_BORDER_BOTTOM, EMPTY_BORDER_RIGHT));

        this.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBackground(BACKGROUND_COLOR);

        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, TITLE_BORDER_BOTTOM, 0));
        difficultyLabel.setBorder(BorderFactory.createEmptyBorder(DIFFICULTY_BORDER_TOP, 0, 0, 0));

        playButton.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        difficultyButton.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        tutorialButton.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));
        exitButton.setFont(new Font(FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE));

        initListeners();
        configureFrame();
    }

    /**
     * Configures the main application window for the menu.
     */
    private void configureFrame() {
        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(this.currentResolution.getWidth(), this.currentResolution.getHeight());
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Initializes button and combo listeners.
     */
    private void initListeners() {
        this.playButton.addActionListener(e -> parentController.startGame(currentDifficulty, currentResolution));

        this.difficultyButton.addActionListener(e -> {
            currentDifficulty = switch (currentDifficulty) {
                case NORMAL -> Difficulty.HARD;
                case HARD -> Difficulty.EASY;
                case EASY -> Difficulty.NORMAL;
            };
            updateDifficultyLabel(currentDifficulty);
        });

        this.tutorialButton.addActionListener(e -> parentController.showTutorialView(currentResolution));

        this.exitButton.addActionListener(e -> parentController.quit());

        this.resolutionCombo.addActionListener(e -> {
            final Resolution sel = (Resolution) resolutionCombo.getSelectedItem();
            setResolution(sel);
        });
    }

    /**
     * Disposes the menu frame.
     */
    public void dispose() {
        frame.dispose();
    }

    /**
     * Changes the window resolution and asks the user to confirm.
     *
     * @param resolution the new Resolution to use
     */
    public void setResolution(final Resolution resolution) {
        final Resolution previousResolution = this.currentResolution;

        frame.setSize(resolution.getWidth(), resolution.getHeight());

        final int choice = JOptionPane.showConfirmDialog(
                this,
                "Vuoi mantenere la nuova risoluzione?",
                "Conferma risoluzione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.NO_OPTION) {
            frame.setSize(previousResolution.getWidth(), previousResolution.getHeight());
            this.setSelectedResolution(previousResolution);
        } else {
            this.currentResolution = resolution;
        }
    }

    /**
     * Updates the difficulty label.
     *
     * @param difficulty the new Difficulty to display
     */
    public void updateDifficultyLabel(final Difficulty difficulty) {
        this.difficultyLabel.setText("Difficoltà: " + difficulty.toString());
    }

    /**
     * Updates the selected resolution in the combo box.
     *
     * @param resolution the new Resolution to select
     */
    public void setSelectedResolution(final Resolution resolution) {
        this.resolutionCombo.setSelectedItem(resolution);
    }

    /**
     * Closes the menu view and disposes of the associated window.
     */
    @Override
    public void close() {
        this.frame.dispose();
    }
}
