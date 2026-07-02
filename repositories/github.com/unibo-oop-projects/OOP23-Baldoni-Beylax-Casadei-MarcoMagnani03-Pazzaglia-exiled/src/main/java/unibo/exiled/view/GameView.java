package unibo.exiled.view;

import java.util.Locale;
import java.util.Optional;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.EndState;
import unibo.exiled.utilities.Position;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.MenuControllerImpl;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameKeyListener;

import javax.swing.JFrame;

import javax.swing.GroupLayout;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.BorderLayout;

/**
 * The main view of the game, everything starts here.
 */
public final class GameView {

    // Views
    private final CombatView combatView;
    private final InventoryView inventoryView;
    private final HudView hud;
    private final GameGridView grid;

    // MVC Components(MC)
    private final JFrame mainFrame;
    private final JPanel gameHudPanel;
    private final JPanel gamePanel;
    private final JPanel menuPanel;
    private final JPanel inventoryPanel;
    private final JPanel combatPanel;
    private final JPanel gameContainerPanel;

    /**
     * The game controller that manages interaction between the model and the view.
     */
    private final GameController gameController;

    /**
     * Constructor of the main view.
     *
     * @param gameController The game controller that manages interaction between
     *                       the model and the view.
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;

        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle(ConstantsAndResourceLoader.GAME_NAME);
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);

        gameContainerPanel = new JPanel();
        this.menuPanel = new JPanel();
        this.inventoryPanel = new JPanel();
        this.combatPanel = new JPanel(new BorderLayout());
        this.gamePanel = new JPanel(new BorderLayout());
        this.gameHudPanel = new JPanel(new BorderLayout());

        final GroupLayout gamePanelLayout = new GroupLayout(gameContainerPanel);
        gameContainerPanel.setLayout(gamePanelLayout);

        gamePanelLayout.setHorizontalGroup(gamePanelLayout.createSequentialGroup()
                .addComponent(gamePanel)
                .addComponent(combatPanel));
        gamePanelLayout.setVerticalGroup(gamePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(gamePanel)
                .addComponent(combatPanel));
        this.gameHudPanel.add(gameContainerPanel, BorderLayout.CENTER);

        this.inventoryView = new InventoryView(this.gameController, this);

        final String playerClass = this.gameController
                .getCharacterController().getPlayerClassName().toLowerCase(Locale.ROOT);
        final CharacterView playerView = new CharacterView(
                this.gameController.getCharacterController().getImagePathOfCharacter(
                        ConstantsAndResourceLoader.PLAYER_PATH + "/" + playerClass,
                        ConstantsAndResourceLoader.PLAYER_NAME + "_" + playerClass));
        this.combatView = new CombatView(this.gameController, this);
        final MenuView menuView = new MenuView(new MenuControllerImpl().getInGameMenuItems(), Optional.of(this));
        this.hud = new HudView(this, this.gameController);
        this.grid = new GameGridView(this.gameController, playerView);

        this.menuPanel.add(menuView);
        this.inventoryPanel.add(this.inventoryView);
        this.combatPanel.add(combatView, BorderLayout.CENTER);

        final Container contentPanel = this.mainFrame.getContentPane();

        final GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup()
                .addComponent(menuPanel)
                .addComponent(gameHudPanel)
                .addComponent(inventoryPanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(menuPanel)
                .addComponent(gameHudPanel)
                .addComponent(inventoryPanel));

        this.hideMenu();
        this.hideInventory();
        this.hideCombat();

        this.draw();
        this.createHUD();
        this.mainFrame.addKeyListener(new GameKeyListener(gameController, this, playerView));
    }

    /**
     * This method sets up buttons for inventory and menu, displays player health,
     * level, and class information.
     */
    public void createHUD() {
        this.gameHudPanel.removeAll();
        this.gameHudPanel.add(gameContainerPanel, BorderLayout.CENTER);
        this.gameHudPanel.add(this.hud.initialize(), BorderLayout.NORTH);
    }

    /**
     * Draws the grid game panel.
     */
    public void draw() {
        if (this.gameController.isOver()) {
            new EndGameView(EndState.GAME_OVER).display();
            this.mainFrame.dispose();
        } else if (this.gameController.isWon()) {
            new EndGameView(EndState.VICTORY).display();
            this.mainFrame.dispose();
        }

        this.gamePanel.removeAll();

        this.gamePanel.add(this.grid.initializeGrid());
        this.gamePanel.repaint();
        this.gamePanel.revalidate();
    }

    /**
     * Returns true if the game is currently in combat mode.
     *
     * @return true if the game is in combat mode, otherwise false.
     */
    public boolean isInInventory() {
        return this.inventoryPanel.isVisible();
    }

    /**
     * Returns true if the game is currently in combat mode.
     *
     * @return true if the game is in combat mode, otherwise false.
     */
    public boolean isInCombat() {
        return this.combatPanel.isVisible();
    }

    /**
     * Returns true if the game map is currently in-game mode.
     *
     * @return true if the game map is in-game mode, otherwise false.
     */
    public boolean isInGame() {
        return this.gameHudPanel.isVisible();
    }

    /**
     * Shows the combat view.
     */
    public void initializeCombat() {
        final Position combatPosition = this.gameController.getCharacterController().getPlayerPosition();
        this.gameController.getCombatController().initializeCombat(combatPosition);
        this.combatView.draw();
        this.showCombat();
    }

    /**
     * Shows the inventory view.
     */
    public void showInventory() {
        this.gameHudPanel.setVisible(false);
        this.inventoryPanel.setVisible(true);
    }

    /**
     * Hides the inventory view.
     */
    public void hideInventory() {
        this.gameHudPanel.setVisible(true);
        createHUD();
        this.inventoryPanel.setVisible(false);
    }

    /**
     * Updates the buttons of the inventory.
     */
    public void updateInventory() {
        this.inventoryView.updateInventoryButtons();
    }

    /**
     * Shows the menu view.
     */
    public void showMenu() {
        this.gameHudPanel.setVisible(false);
        this.menuPanel.setVisible(true);
    }

    /**
     * Hides the menu view.
     */
    public void hideMenu() {
        this.gameHudPanel.setVisible(true);
        createHUD();
        this.menuPanel.setVisible(false);
    }

    /**
     * Shows the combat view.
     */
    public void showCombat() {
        this.gamePanel.setVisible(false);
        this.combatPanel.setVisible(true);
    }

    /**
     * Hides the combat view.
     */
    public void hideCombat() {
        this.gamePanel.setVisible(true);
        this.combatPanel.setVisible(false);
    }

    /**
     * Makes the main frame visible on screen.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }

    /**
     * Hides the frame.
     */
    public void hide() {
        this.mainFrame.setVisible(false);
    }
}
