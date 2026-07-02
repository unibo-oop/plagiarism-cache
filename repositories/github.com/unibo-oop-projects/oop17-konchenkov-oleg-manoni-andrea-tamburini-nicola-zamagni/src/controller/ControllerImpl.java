package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.jsoninput.LocationJSON;
import controller.jsoninput.LocationJSONImpl;
import controller.jsoninput.ResolutionsJSON;
import controller.jsoninput.ResolutionsJSONImpl;
import controller.jsoninput.TankShapeJSON;
import controller.jsoninput.TankShapeJSONImpl;
import controller.matchmanager.BasicGameManagerCreator;
import controller.matchmanager.GameManager;
import controller.matchmanager.GameModeType;
import model.player.Player;
import model.player.PlayerImpl;
import view.View;

/**
 *
 * @author Oleg, Nicola Tamburini
 *
 */
public final class ControllerImpl implements Controller {

    private static final ControllerImpl CONTROLLER = null;
    private View view;
    private GameManager gameManager;
    private final LocationJSON locationJSON;
    private final ResolutionsJSON resolutionsJSON;
    private final TankShapeJSON tankShapeJSON;

    private ControllerImpl() {
        locationJSON = new LocationJSONImpl();
        resolutionsJSON = new ResolutionsJSONImpl();
        tankShapeJSON = new TankShapeJSONImpl();
    }

    /**
     *
     * @return instance of controller.
     */
    public static ControllerImpl getInstanceOf() {
        return ControllerImpl.CONTROLLER == null ? new ControllerImpl() : ControllerImpl.CONTROLLER;
    }

    @Override
    public void update(final GameStatus status) {
        switch (status) {

        case LOAD:
            try {
                tankShapeJSON.load();
                locationJSON.load();
                resolutionsJSON.load();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            break;

        case START_GAME:
            gameManager = new BasicGameManagerCreator().createGameManager(GameModeType.MATCH, this, view);
            setGameManager(gameManager);
            gameManager.tankDeploy();
            break;

        case START_DEMO:
            gameManager = new BasicGameManagerCreator().createGameManager(GameModeType.DEMO, this, view);
            setGameManager(gameManager);
            gameManager.tankDeploy();
            break;

        case SET_PROJECTILE_ELEVATION_ANGLE:
            gameManager.setTurrenAngle();
            break;

        case SET_COMBO_INVENTORY:
            view.getSceneMainController().setComboInventory(
                    gameManager.getModel().getPlayers().get(gameManager.getTurn()).getInventory(),
                    gameManager.getModel().getPlayers().get(gameManager.getTurn()).getLastShotProjectile());
            break;

        case SET_POWER_OF_SLIDER:
            view.getSceneMainController().setSliderPower(
                    gameManager.getModel().getPlayers().get(gameManager.getTurn()).getTank()
                            .getProjectileInitialSpeed());
            break;
        case SET_ANGLE_OF_SLIDER:
            view.getSceneMainController().setSliderAngle(
                    Math.toDegrees(gameManager.getModel().getPlayers().get(gameManager.getTurn()).getTank()
                            .getElevationAngle()));
            break;

        case SHOOT_PROJECTILE:
            gameManager.startNewTurn();
            break;

        case GAMEOVER:
            final List<Player> players = new ArrayList<>();
            gameManager.getModel().getPlayers().forEach(p -> {
                players.add(new PlayerImpl(p));
            });
            view.getSceneMainController().sceneVictoryScreen(java.util.Collections.unmodifiableList(players));
            break;

        case STOP:
            if (gameManager.getTurnManager().isTurnStarted()) {
                gameManager.stopTurnManager();
            }
            break;

        case EXIT:
            break;

        default:
            break;
        }
    }

    @Override
    public LocationJSON getLocationsJSON() {
        return locationJSON;
    }

    @Override
    public ResolutionsJSON getResolutinsJSON() {
        return resolutionsJSON;
    }

    @Override
    public TankShapeJSON getTankShapeJSON() {
        return tankShapeJSON;
    }

    private void setGameManager(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void setView(final View view) {
        this.view = view;
    }
}
