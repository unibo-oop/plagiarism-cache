package unibo.exiled.view;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.controller.GameController;
import unibo.exiled.model.combat.CombatStatus;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.WrapLayout;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.io.Serial;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.Optional;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int MOVE_SET_PANEL_GAP = 10;

    private final transient GameController gameController;
    private final transient GameView gameView;

    /**
     * The constructor of the combat view.
     *
     * @param gameController The controller that manages the whole game.
     * @param gameView       The game view where the map is displayed.
     */
    public CombatView(final GameController gameController, final GameView gameView) {
        this.gameController = gameController;
        this.gameView = gameView;

        this.setLayout(new BorderLayout());
    }

    /**
     * Draws the view.
     */
    public void draw() {
        if (this.gameController.getCombatController().getCombatStatus().equals(CombatStatus.DEFEATED)) {
            this.gameView.createHUD();
            this.gameView.updateInventory();
            this.gameView.hideCombat();
            this.gameView.draw();

            if (this.gameController.getCombatController().needsPlayerToChangeMove()) {
                final GameMoveChangeView gameMoveChangeView = new GameMoveChangeView(
                        this.gameController,
                        this.gameView
                );
                gameMoveChangeView.display();
            }
        } else {
            this.removeAll();

            final JPanel battlePanel = new JPanel(new GridLayout(2, 1, 50, 50));
            battlePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 0, 100));
            final JPanel moveSetPanel = new JPanel(
                    new FlowLayout(FlowLayout.CENTER, MOVE_SET_PANEL_GAP, MOVE_SET_PANEL_GAP));
            this.add(battlePanel, BorderLayout.CENTER);
            this.add(moveSetPanel, BorderLayout.SOUTH);

            // Adding buttons
            for (final String moveName : this.gameController.getCharacterController().getPlayerMoveSet()) {
                final double moveDamage = this.gameController.getCharacterController()
                        .getMagicMoveDamage(moveName);
                final ElementalType moveType = this.gameController.getCharacterController()
                        .getMagicMoveElementalType(moveName);
                final JButton moveButton = new GameButton(moveName + " (" + moveDamage + ")");
                moveButton.setBackground(moveType.getElementalColor());
                moveButton.setForeground(Color.BLACK);
                moveButton
                        .setEnabled(
                                this.gameController.getCombatController()
                                        .getCombatStatus()
                                        .equals(CombatStatus.IDLE)
                                        && !this.gameController
                                                .getCombatController()
                                                .getCombatStatus()
                                                .equals(CombatStatus.DEFEATING));
                moveSetPanel.add(moveButton);
                moveButton.addActionListener(e -> {
                    gameController.getCombatController().attack(true, Optional.of(moveName),
                            gameController, this);
                    gameView.draw();
                });
            }

            final JPanel consoleArea = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            consoleArea.add(new GameLabel(this.gameController.getCombatController().getLastMoveLabel()),
                    gbc);
            gbc.gridy++;
            consoleArea.add(new GameLabel(
                    this.gameController.getCombatController().getAttackerModifierLabel()), gbc);
            gbc.gridy++;
            consoleArea.add(new GameLabel(
                    this.gameController.getCombatController().getDefenderModifierLabel()), gbc);
            gbc.gridy++;
            consoleArea.add(new GameLabel("-------------"), gbc);
            gbc.gridy++;
            consoleArea.add(new GameLabel(this.gameController.getCombatController().getMoveDescription()),
                    gbc);
            gbc.gridy++;

            // Adding console area and player and enemy view
            final String playerClass = this.gameController.getCharacterController()
                    .getPlayerClassName().toLowerCase(Locale.ROOT);
            final JLabel playerImage = new CharacterView(
                    this.gameController.getCharacterController().getImagePathOfCharacter(
                            ConstantsAndResourceLoader.PLAYER_PATH + "/" + playerClass,
                            ConstantsAndResourceLoader.PLAYER_NAME + "_" + playerClass));

            final List<String> enemyImagePath = this.gameController
                    .getCharacterController()
                    .getImagePathOfCharacter(ConstantsAndResourceLoader.ENEMY_PATH,
                            this.gameController.getCombatController().getEnemyName() + "/"
                                    + this.gameController.getCombatController()
                                            .getEnemyName());
            final JLabel enemyImage = new CharacterView(enemyImagePath);

            final JPanel gameStatusPanel = new JPanel(
                    new WrapLayout(FlowLayout.CENTER, ConstantsAndResourceLoader.STATUS_PANEL_H_GAP,
                            ConstantsAndResourceLoader.STATUS_PANEL_V_GAP));
            final double enemyHealth = gameController.getCombatController().getEnemyHealth();
            final double enemyHealthCap = gameController.getCombatController().getEnemyHealthCap();
            final GameLabel enemyHealthBar = new GameLabel(
                    "Health: " + String.format("%.2f", enemyHealth < 0 ? 0 : enemyHealth) + " / "
                            + enemyHealthCap);
            if (enemyHealth <= (enemyHealthCap / 100)
                    * ConstantsAndResourceLoader.HEALTH_CRITICAL_PERCENTAGE) {
                enemyHealthBar.setForeground(Color.RED);
            } else {
                enemyHealthBar.setForeground(Color.GREEN);
            }
            final GameLabel classLabel = new GameLabel(
                    "Class: " + gameController.getCombatController().getEnemyClassName());
            gameStatusPanel.setBorder(BorderFactory.createEtchedBorder());
            gameStatusPanel.add(enemyHealthBar);
            gameStatusPanel.add(classLabel);

            final JPanel enemyPanel = new JPanel(new BorderLayout());
            enemyPanel.add(enemyImage, BorderLayout.CENTER);
            enemyPanel.add(gameStatusPanel, BorderLayout.SOUTH);

            final JPanel charactersPanel = new JPanel(new GridLayout(1, 2));
            charactersPanel.add(playerImage);
            charactersPanel.add(enemyPanel);

            battlePanel.add(charactersPanel);
            battlePanel.add(consoleArea);

            this.revalidate();
            this.repaint();

            this.gameView.createHUD();
        }
    }
}
