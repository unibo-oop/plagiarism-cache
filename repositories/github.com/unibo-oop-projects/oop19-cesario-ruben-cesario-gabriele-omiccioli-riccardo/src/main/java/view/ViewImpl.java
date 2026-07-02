package view;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import controller.Binder;
import controller.ControllerEvent;
import controller.stagehandler.playerhandler.PlayerHandler;
import model.Stage;
import view.arena.ArenaUIController;
import view.image.Loader;
import view.keyboardhandler.KeyEvent;
import view.keyboardhandler.KeyEventImpl;
import view.menu.LeaderboardUIController;

/**
 * Implements View.
 */
public class ViewImpl implements View {

    private final Executor soundManager = Executors.newSingleThreadExecutor();
    private ArenaUIController arenaController;
    private final KeyEvent keyEvent;

    // Variable used to activate or deactivate easter eggs
    private Boolean easterEggs = false;

    /**
     * Temporary constructor.
     */
    public ViewImpl() {
        this.keyEvent = new KeyEventImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyEvent getKeyEvent() {
        return this.keyEvent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArenaUIController(final ArenaUIController controller) {
        arenaController = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArenaUIController getArenaUIController() {
        return arenaController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerHandler(final PlayerHandler playerHandler) {
        keyEvent.setPlayerHandler(playerHandler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshArena(final Stage stage) {
        arenaController.refresh(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveEvent(final ControllerEvent event) {
        switch (event) {
        case SHOOT:
            if (easterEggs) {
                soundManager.execute(() -> Sound.play("pew"));
            } else {
                soundManager.execute(() -> Sound.play("shoot"));
            }
            break;
        case PROJECTILE_EXPLOSION:
            soundManager.execute(() -> Sound.play("collision"));
            break;
        case EXPLOSION:
            soundManager.execute(() -> Sound.play("explosion"));
            break;
        case NEXT_LEVEL:
            if (easterEggs) {
                soundManager.execute(() -> Sound.play("yay"));
            } else {
                soundManager.execute(() -> Sound.play("alarm"));
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
    public void exitArenaWindow() {
        try {
            ((LeaderboardUIController) Loader.loadFXML("layouts/leaderboard.fxml").getController()).draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Binder.getController().endGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        easterEggs = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEasterEggs(final Boolean value) {
        easterEggs = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleEasterEggs() {
        easterEggs = !easterEggs;
        if (easterEggs) {
            Sound.changeMusic("elevatorMusic");
            Sound.play("yay");
        } else {
            Sound.changeMusic("arenaMusic2");
        }
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEasterEggs() {
        return easterEggs;
    }

}
