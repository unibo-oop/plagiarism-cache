package view.keyboardhandler;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import view.Sound;
import view.menu.MainMenuUIController;
import controller.Binder;
import controller.stagehandler.playerhandler.PlayerHandler;

/**
 * Implements KeyEvent.
 */
public class KeyEventImpl implements KeyEvent {

    private PlayerHandler handler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyCode key) {
        switch (key) {
        case UP:
        case W:
            if (Binder.getController().isGameRunning()) {
                handler.activate(PlayerHandler.PlayerAction.ACCELERATE);
            }
            break;
        case DOWN:
        case S:
            if (Binder.getController().isGameRunning()) {
                handler.activate(PlayerHandler.PlayerAction.DECELERATE);
            }
            break;
        case LEFT:
        case A:
            if (Binder.getController().isGameRunning()) {
                handler.activate(PlayerHandler.PlayerAction.ROTATE_ANTICLOCKWISE);
            }
            break;
        case RIGHT:
        case D:
            if (Binder.getController().isGameRunning()) {
                handler.activate(PlayerHandler.PlayerAction.ROTATE_CLOCKWISE);
            }
            break;
        case SPACE:
            if (Binder.getController().isGameRunning()) {
                handler.activate(PlayerHandler.PlayerAction.SHOOT);
            }
            break;
        case ESCAPE:
            try {
                ((MainMenuUIController) view.image.Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Binder.getController().isGameRunning()) {
                Binder.getController().endGame();
            }
            break;
        case END:
            System.exit(0);
            break;
        case P:
            if (Binder.getController().isGameRunning()) {
                if (Binder.getController().isPaused()) {
                    Platform.runLater(() -> Binder.getView().getArenaUIController().drawPauseMenu(false));
                    Binder.getController().resumeGame();
                } else {
                    Platform.runLater(() -> Binder.getView().getArenaUIController().drawPauseMenu(true));
                    Binder.getController().pauseGame();
                }
            }
            break;
        case CONTROL:
            if (Binder.getController().isGameRunning()) {
                Binder.getView().toggleEasterEggs();
            }
            break;
        case COMMA:
            Sound.decreaseVolume();
            break;
        case PERIOD:
            Sound.increaseVolume();
            break;
        default:
            break;
        }
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyCode key) {
        switch (key) {
        case UP:
        case W:
            if (Binder.getController().isGameRunning()) {
                handler.deactivate(PlayerHandler.PlayerAction.ACCELERATE);
            }
            break;
        case DOWN:
        case S:
            if (Binder.getController().isGameRunning()) {
                handler.deactivate(PlayerHandler.PlayerAction.DECELERATE);
            }
            break;
        case LEFT:
        case A:
            if (Binder.getController().isGameRunning()) {
                handler.deactivate(PlayerHandler.PlayerAction.ROTATE_ANTICLOCKWISE);
            }
            break;
        case RIGHT:
        case D:
            if (Binder.getController().isGameRunning()) {
                handler.deactivate(PlayerHandler.PlayerAction.ROTATE_CLOCKWISE);
            }
            break;
        case SPACE:
            if (Binder.getController().isGameRunning()) {
                handler.deactivate(PlayerHandler.PlayerAction.SHOOT);
            }
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerHandler(final PlayerHandler handler) {
        this.handler = handler;
    }

}
