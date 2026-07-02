package it.unibo.controller;

import it.unibo.GameEvent;
import it.unibo.GameListener;
import it.unibo.controller.interfaces.MenuManagerInterface;
import it.unibo.model.Scale;
import it.unibo.view.Menu;
import it.unibo.view.MenuRules;

import javax.swing.*;
import java.awt.*;

/**
 * This class manages the logic of the menu system of the game. It allows the user to select levels, view rules, and start a new game.
 * It handles interactions with the menu and the level selection process, including the display of a popup for the selected level.
 */
public class MenuManager extends JInternalFrame implements MenuManagerInterface {

    private final Menu menuView;
    private final MenuRules rulesView;
    private LevelManager levelManager;
    private final GameListener gameListener;

    /**
     * Constructor for the MenuManager class.
     * Initializes the menu view, rules view, and the level manager.
     * Sets up the listeners for menu actions.
     * 
     * @param gameListener the listener to handle game events
     * @param scale the scale to adjust the UI components
     */
    public MenuManager(GameListener gameListener, Scale scale) {
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        this.gameListener = gameListener;
        String[] levels = { "1", "2", "3" };
        this.menuView = new Menu(levels, scale);
        this.rulesView = new MenuRules(scale);

        this.levelManager = new LevelManager();
        setupMenuListeners();
        setupRulesListeners();
        this.start();
    }

    /**
     * Starts the game with a specific level configuration.
     * 
     * @param config the level configuration to start the game with
     */
    private void startGameWithConfig(LevelManager.LevelConfig config) {
        GameEvent event = new GameEvent(this, config);
        gameListener.startGame(event);
    }

    /**
     * Sets up listeners for actions in the main menu.
     * Includes starting the game with the selected level and navigating to the rules view.
     */
    private void setupMenuListeners() {
        /**
         * Action when a level is selected and the "Start" button is clicked.
         */
        menuView.getStartButton().addActionListener(e -> {
            String selectedLevel = menuView.getSelectedLevel();
            LevelManager.LevelConfig config = levelManager.getLevelConfig(Integer.parseInt(selectedLevel));
            showLevelPopup(selectedLevel, config); 
        });

        /**
         * Action when the "Controls" button is clicked and switch to the rules view.
         */
        menuView.getControlsButton().addActionListener(e -> {
            switchToRulesView(); 
        });
    }

    /**
     * Sets up listeners for actions in the rules view.
     * Includes navigating back to the main menu when the button is clicked.
     */
    private void setupRulesListeners() {
        rulesView.addBackButtonListener(e -> {
            switchToMenuView(); 
        });
    }

    /**
     * Starts the menu view.
     * Adds the menu view to the internal frame and sets it visible.
     */
    @Override
    public void start() {
        this.add(menuView);
        this.setVisible(true);
    }

    /**
     * Switches the current view to the main menu.
     */
    @Override
    public void switchToMenuView() {
        this.getContentPane().removeAll();
        this.getContentPane().add(menuView);
        this.revalidate();
        this.repaint();
    }

    /**
     * Switches the current view to the rules view.
     */
    @Override
    public void switchToRulesView() {
        this.getContentPane().removeAll();
        this.getContentPane().add(rulesView);
        this.revalidate();
        this.repaint();
    }

    /**
     * Displays a popup dialog when a level is selected.
     * This dialog confirms the selected level and starts the game once the "OK" button is clicked.
     * 
     * @param level the level that the user selected
     * @param config the configuration of the selected level
     */
    @Override
    public void showLevelPopup(String level, LevelManager.LevelConfig config) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog levelDialog = new JDialog();
        levelDialog.setLayout(new BorderLayout());
        levelDialog.setSize(450, 250);
        levelDialog.setLocationRelativeTo(topFrame);
        levelDialog.setUndecorated(true);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(51, 73, 112));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        JLabel levelMessage = new JLabel(
                "<html><div style='text-align: center;'>You have selected level:<br><span style='color: #FFFFFF; font-size: 24px; font-weight: bold;'>"
                        + level + "</span></div></html>",
                JLabel.CENTER);
        levelMessage.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        levelMessage.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        okButton.setBackground(new Color(25, 25, 112));
        okButton.setForeground(Color.BLACK);
        okButton.setPreferredSize(new Dimension(200, 60));
        okButton.setFocusPainted(false);
        okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            private final Color normalColor = new Color(25, 25, 112);
            private final Color hoverColor = normalColor.darker();

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                okButton.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                okButton.setBackground(normalColor);
            }
        });

        /**
         * Start the game with the selected level configuration.
         */
        okButton.addActionListener(e -> {
            levelDialog.dispose();
            startGameWithConfig(config); 
        });

        buttonPanel.add(okButton);

        panel.add(levelMessage, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        levelDialog.setContentPane(panel);
        levelDialog.setVisible(true);
    }
}
