package powpaw.core;

import java.time.Duration;
import java.time.Instant;
import javafx.animation.AnimationTimer;
import powpaw.player.controller.api.DamageMeterController;
import powpaw.player.controller.api.PlayerController;
import powpaw.powerup.controller.api.PowerUpController;
import powpaw.weapon.controller.api.WeaponController;

/**
 * This class is responsible for running the game loop for the game. It extends
 * the {@code AnimationTimer} class of JavaFx lib that allows it to update the
 * game at a fixed interval, and is triggered by the JavaFX Application Thread.
 */
public final class GameLoop extends AnimationTimer {

    private Instant lastFrameTime;
    private PlayerController playerController;
    private WeaponController weaponController;
    private PowerUpController powerUpController;
    private DamageMeterController damageMeterController;

    @Override
    public void start() {
        super.start();
        lastFrameTime = Instant.now();

    }

    @Override
    public void handle(final long now) {
        final Instant currentFrameTime = Instant.now();
        final Duration deltaTime = Duration.between(lastFrameTime, currentFrameTime);
        lastFrameTime = currentFrameTime;
        update(deltaTime);
    }

    /**
     * Calls the update method of the PlayerObservable class to update the game
     * state and the render method of the Render classes to render the game objects.
     * 
     * @param deltaTime the time since the last frame
     */
    private void update(final Duration deltaTime) {
        playerController.getPlayerObservable().update(deltaTime);
        playerController.getRender().forEach(player -> player.renderPlayer());
        powerUpController.pickPowerUp(playerController);
        powerUpController.getRender().render();
        weaponController.pickWeapon();
        weaponController.getRender().render();
        weaponController.getWeapon().update();
        damageMeterController.getRender().update(playerController.getPlayers());
    }

    /**
     * Sets the PlayerController for the game loop.
     * 
     * @param playerController the PlayerController to be set
     */
    public void setPlayerController(final PlayerController playerController) {
        this.playerController = playerController;
    }

    /**
     * Sets the WeaponController for the game loop.
     * 
     * @param weaponController the WeaponController to be set
     */
    public void setWeaponController(final WeaponController weaponController) {
        this.weaponController = weaponController;
    }

    /**
     * Sets the PowerUpController for the game loop.
     * 
     * @param powerUpController the PowerUpController to be set
     */
    public void setPowerUpController(final PowerUpController powerUpController) {
        this.powerUpController = powerUpController;
    }

    /**
     * Sets the DamageMeterController for the game loop.
     * 
     * @param damageMeterController the DamageMeterController to be set
     */
    public void setDamageMeterController(final DamageMeterController damageMeterController) {
        this.damageMeterController = damageMeterController;
    }
}
