package unibo.exiled.view.items;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import unibo.exiled.controller.GameController;
import unibo.exiled.utilities.Direction;
import unibo.exiled.view.GameView;
import unibo.exiled.view.character.CharacterView;

/**
 * A KeyListener implementation that handles player movement in the game.
 */
public final class GameKeyListener implements KeyListener {
    private final GameController gameController;
    private final GameView gameView;
    private final CharacterView playerView;

    /**
     * Constructs a MovementKeyListener with the specified game controller and game
     * view.
     *
     * @param gameController The game controller responsible for managing game
     *                       logic.
     * @param gameView       The game view responsible for rendering the game.
     * @param playerView     The CharacterView of the player.
     */
    public GameKeyListener(final GameController gameController,
            final GameView gameView,
            final CharacterView playerView) {
        this.gameController = gameController;
        this.gameView = gameView;
        this.playerView = playerView;
    }

    /**
     * Determines the direction corresponding to the pressed key.
     *
     * @param e The KeyEvent corresponding to the pressed key.
     * @return The Direction corresponding to the pressed key.
     * @throws IllegalStateException If an illegal key is pressed.
     */
    private static Direction getDirection(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                return Direction.NORTH;
            }
            case KeyEvent.VK_A -> {
                return Direction.WEST;
            }
            case KeyEvent.VK_S -> {
                return Direction.SOUTH;
            }
            case KeyEvent.VK_D -> {
                return Direction.EAST;
            }
            default -> throw new IllegalStateException("Illegal pressed key.");
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (this.gameView.isInGame()) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.gameView.showMenu();
            } else if (isValidMovementKey(e) && !this.gameView.isInCombat()) {
                final Direction directionPressed = getDirection(e);
                this.gameController.getCharacterController().move(directionPressed);
                updatePlayerView(directionPressed);
                this.gameView.draw();
            } else if (e.getKeyCode() == KeyEvent.VK_E) {
                this.gameView.showInventory();
            }
        } else if (this.gameView.isInInventory()) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.gameView.hideInventory();
            }
        } else if (this.gameView.isInCombat()) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.gameView.hideInventory();
                this.gameView.hideMenu();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.gameView.hideMenu();
                this.gameView.hideInventory();
                this.gameView.hideCombat();
            }
        }
    }

    /**
     * Checks if the pressed key is a valid movement key.
     *
     * @param e The KeyEvent corresponding to the pressed key.
     * @return True if the pressed key is a valid movement key, false otherwise.
     */
    private boolean isValidMovementKey(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        return keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S
                || keyCode == KeyEvent.VK_D;
    }

    /**
     * Updates the player view based on the movement direction.
     *
     * @param direction The direction in which the player is moving.
     */
    private void updatePlayerView(final Direction direction) {
        if (this.gameController.getMapController()
                .isEnemyInCell(this.gameController.getCharacterController().getPlayerPosition())) {
            this.gameView.initializeCombat();
        } else {
            playerView.changeImage(direction,
                    this.gameController.getCharacterController().getIfCharacterInPositionIsMoving(
                            this.gameController.getCharacterController().getPlayerPosition()));
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
