package powpaw.core.view.impl;

import javafx.scene.Scene;
import javafx.stage.Stage;
import powpaw.core.GameLoop;
import powpaw.core.controller.api.GameStateController;
import powpaw.core.controller.impl.GameStateControllerImpl;
import powpaw.core.view.api.GameStateView;
import powpaw.menu.GameOver;
import powpaw.menu.StartMenu;
import powpaw.menu.StatsSettingMenu;
import powpaw.world.controller.ScreenController;
import powpaw.world.view.impl.WorldRenderImpl;

/**
 * GameStateView implementation.
 * 
 * @author Simone Collorà
 */
public final class GameStateViewImpl implements GameStateView {

    private final Stage stage = new Stage();
    private final GameStateController gameStateController = new GameStateControllerImpl();
    private final GameLoop loop = new GameLoop();
    private WorldRenderImpl worldRender;

    /**
     * GameStateView costructore set stage title and resizable.
     */
    public GameStateViewImpl() {
        stage.setTitle("PowPaw");
        stage.setResizable(false);
    }

    @Override
    public void showStartMenu() {
        stage.show();
        gameStateController.start();
        stage.setScene(new Scene(new StartMenu(), ScreenController.SIZE_HD_W, ScreenController.SIZE_HD_H));
        stage.show();
    }

    @Override
    public void showCharacterCreation() {
        gameStateController.characterCreation();
        stage.setScene(new Scene(new StatsSettingMenu(), ScreenController.SIZE_HD_W, ScreenController.SIZE_HD_H));
    }

    @Override
    public void showGame() {
        worldRender = new WorldRenderImpl();
        gameStateController.game();
        stage.setScene(worldRender.render());
        worldRender.playersCommands();
        loop.setPlayerController(worldRender.getPlayerController());
        loop.setWeaponController(worldRender.getWeaponController());
        loop.setPowerUpController(worldRender.getPowerUpController());
        loop.setDamageMeterController(worldRender.getDamageMeterController());
        loop.start();
    }

    @Override
    public void showGameOver() {
        loop.stop();
        gameStateController.gameOver();
        stage.setScene(new Scene(new GameOver(worldRender.getPlayerController()), ScreenController.SIZE_HD_W,
                ScreenController.SIZE_HD_H));
    }

}
