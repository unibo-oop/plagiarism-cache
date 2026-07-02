package controller.eventController;

import java.io.IOException;

import controller.collisionDetection.Collision;
import controller.gameSwitcher.SceneController;
import model.hud.HUDPowerUp;
import view.GameMap;
import view.hud.HUDImpl;

/**
 * Implements event logic for the game.
 */
public class EventControllerImpl implements EventController {

    private HUDImpl hudBuilder;

    public EventControllerImpl(final GameMap gameMap) {
        this.hudBuilder = new HUDImpl(gameMap);
    }

    @Override
    public Collision getCollision() {
        return this.hudBuilder.getCollision();
    }

    public HUDImpl getHudBuilder() {
        return this.hudBuilder;
    }

    @Override
    public int checkPoints() {
        return this.hudBuilder.checkPoints();
    }

    @Override
    public int checkLifePoints() {
        return this.hudBuilder.checkLives();
    }

    @Override
    public void endGame(final SceneController sceneController) throws IOException {
        try {
            sceneController.getInputController().resetState();
            sceneController.switchToEndMenu(this.checkPoints());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkGameStatus() {
        return this.hudBuilder.checkGameStatus();
    }

    @Override
    public HUDPowerUp getPowerUp() {
        return this.hudBuilder.getPowerUpImpl();
    }

}
