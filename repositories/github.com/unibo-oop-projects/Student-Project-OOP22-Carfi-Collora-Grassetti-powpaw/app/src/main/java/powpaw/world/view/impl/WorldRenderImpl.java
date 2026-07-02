package powpaw.world.view.impl;

import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import powpaw.map.view.api.MapRender;
import powpaw.map.view.impl.MapRenderImpl;
import powpaw.player.controller.api.DamageMeterController;
import powpaw.player.controller.api.PlayerController;
import powpaw.player.controller.impl.DamageMeterControllerImpl;
import powpaw.player.controller.impl.PlayerControllerImpl;
import powpaw.powerup.controller.api.PowerUpController;
import powpaw.powerup.controller.impl.PowerUpControllerImpl;
import powpaw.weapon.controller.api.WeaponController;
import powpaw.weapon.controller.impl.WeaponControllerImpl;
import powpaw.world.controller.ScreenController;
import powpaw.world.view.api.WorldRender;

/**
 * Implementation of the {@code WorldRender} interface that provides the ability
 * to render the game world.
 * 
 * @author Alessia Carfì
 */
public final class WorldRenderImpl implements WorldRender {

    private final MapRender mapRender = new MapRenderImpl();
    private final PlayerController playerController = new PlayerControllerImpl();
    private final WeaponController weaponController = new WeaponControllerImpl(playerController);
    private final PowerUpController powerUpController = new PowerUpControllerImpl();
    private final DamageMeterController damageMeterController = new DamageMeterControllerImpl(playerController);

    private Scene worldScene;

    @Override
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    @Override
    public WeaponController getWeaponController() {
        return this.weaponController;
    }

    @Override
    public PowerUpController getPowerUpController() {
        return this.powerUpController;
    }

    @Override
    public DamageMeterController getDamageMeterController() {
        return this.damageMeterController;
    }

    @Override
    public MapRender getMapRender() {
        return this.mapRender;
    }

    @Override
    public Scene render() {
        final BackgroundSize size = new BackgroundSize(ScreenController.SIZE_HD_W, ScreenController.SIZE_HD_H, true,
                true, true, false);
        final Pane worldPane = mapRender.createPane();
        worldPane.setBackground(
                new Background(new BackgroundImage(new Image("/background_world.png"), BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        size)));
        worldPane.getChildren()
                .addAll(playerController.getRender().stream().map(r -> r.getSprite()).collect(Collectors.toList()));

        worldPane.getChildren()
                .addAll(playerController.getRender().stream().map(r -> r.getArmSprite()).collect(Collectors.toList()));

        mapRender.getTerrains().forEach(b -> worldPane.getChildren().add(b.getHitbox().getShape()));
        worldPane.getChildren().add(weaponController.getRender().getWeaponSprite());
        worldPane.getChildren().add(powerUpController.getRender().getSprite());
        worldPane.getChildren().add(damageMeterController.getRender().getDamage().get(0));
        worldPane.getChildren().add(damageMeterController.getRender().getDamage().get(1));
        weaponController.getRender().setTerrains(mapRender.getTerrains());
        this.worldScene = new Scene(worldPane, ScreenController.SIZE_HD_W, ScreenController.SIZE_HD_H);
        return worldScene;
    }

    @Override
    public void playersCommands() {

        this.worldScene.setOnKeyPressed(event -> playerController.getPlayerObservable().getKeyObservable()
                .notifyObserversPressed(event.getCode()));

        this.worldScene.setOnKeyReleased(event -> playerController.getPlayerObservable().getKeyObservable()
                .notifyObserversReleased(event.getCode()));
    }
}
