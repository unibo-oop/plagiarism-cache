package unibo.exiled.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import unibo.exiled.controller.GameController;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.WrapLayout;

/**
 * The Hud class represents the Heads-up Display (HUD) for the game.
 */
public final class HudView {
    private final GameView gameView;
    private final GameController gameController;

    /**
     * Constructs a new Hud instance.
     *
     * @param gameView       The GameView associated with the HUD.
     * @param gameController The GameController associated with the HUD.
     */
    public HudView(final GameView gameView,
            final GameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
    }

    /**
     * Initializes the HUD by setting up buttons, panels, and labels.
     * 
     * @return The panel of the game HUD.
     */
    public JPanel initialize() {
        final JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        final JPanel gameHudPanel = new JPanel(new BorderLayout());
        gameHudPanel.add(flowButtonPanelNorth, BorderLayout.NORTH);
        gameHudPanel.add(createStatusPanel());

        final GameButton inventoryButton = new GameButton("Inventory");
        inventoryButton.addActionListener(e -> gameView.showInventory());

        final GameButton menuButton = new GameButton("Menu");
        menuButton.addActionListener(e -> gameView.showMenu());

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        return gameHudPanel;
    }

    /**
     * Refreshes the status panel with updated information about the player's
     * health,
     * level, class, and experience.
     * 
     * @return The panel of the status of the player.
     */
    private JPanel createStatusPanel() {
        final JPanel gameStatusPanel = new JPanel(
                new WrapLayout(FlowLayout.CENTER, ConstantsAndResourceLoader.STATUS_PANEL_H_GAP,
                        ConstantsAndResourceLoader.STATUS_PANEL_V_GAP));
        gameStatusPanel.removeAll();
        final GameLabel healthBar = getHealthBar();
        final GameLabel levelLabel = new GameLabel(
                "Level: " + gameController.getCharacterController().getPlayerLevel());
        final GameLabel classLabel = new GameLabel(
                "Class: " + gameController.getCharacterController().getPlayerClassName());
        final int currentExperience = gameController.getCharacterController().getPlayerCurrentExperience();
        final int experienceCap = gameController.getCharacterController().getPlayerExperienceCap();
        final double attack = gameController.getCharacterController().getPlayerAttack();
        final double defense = gameController.getCharacterController().getPlayerDefense();
        final GameLabel experienceLabel = new GameLabel("Experience: " + currentExperience + " / " + experienceCap);
        final GameLabel attackLabel = new GameLabel("Attack: " + String.format("%.2f", attack));
        final GameLabel defenseLabel = new GameLabel("Defense: " + String.format("%.2f", defense));
        gameStatusPanel.setBorder(BorderFactory.createEtchedBorder());
        gameStatusPanel.add(healthBar);
        gameStatusPanel.add(levelLabel);
        gameStatusPanel.add(classLabel);
        gameStatusPanel.add(experienceLabel);
        gameStatusPanel.add(attackLabel);
        gameStatusPanel.add(defenseLabel);
        gameStatusPanel.revalidate();
        gameStatusPanel.repaint();
        return gameStatusPanel;
    }

    /**
     * Returns the health bar label based on the player's current health,
     * health cap.
     *
     * @return The health bar label.
     */
    private GameLabel getHealthBar() {
        final double playerHealth = gameController.getCharacterController().getPlayerHealth();
        final double playerHealthCap = gameController.getCharacterController().getPlayerHealthCap();
        final GameLabel healthBar = new GameLabel(
                "Health: " + String.format("%.2f", playerHealth < 0 ? 0 : playerHealth) + " / "
                        + playerHealthCap);
        if (playerHealth <= (playerHealthCap / 100) * ConstantsAndResourceLoader.HEALTH_CRITICAL_PERCENTAGE) {
            healthBar.setForeground(Color.RED);
        } else {
            healthBar.setForeground(Color.GREEN);
        }
        return healthBar;
    }
}
