package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bullet.Bullet;
import model.ship.Ship;
import model.status.Status;
import utilities.InputCommands;

/**
 * Used to control and modify PlayerShip class
 */
public interface PlayerShipController  {
    /**
     * Initialises PlayerShip, keeps the original PlayerGun that levels up with the Player
     * @param initialPos position where the PlayerShip spawns
     * @param sprite image of the ship
     */
    void initialisePlayerShip(Vec2 initialPos, Image sprite);

    /**
     * Displays sprite based on PlayerShip position and rotation, updates timers for FireRate and PowerUps
     * @param deltaTime time elapsed
     * @return returns the Player sprite
     */
    ImageView update(long deltaTime);

    /**
     * updates the sprite based on PlayerShip
     * @return ImageView of the sprite
     */
    ImageView display();

    /**
     * destroys PlayerShip
     */
    void destroy();

    /**
     * getter of PlayerShip
     * @return PlayerShip
     */
    Ship getPlayerShip();

    /**
     * acceleration based on the user input
     * @param input direction of the thrust
     */
    void thrust(InputCommands input);

    /**
     * rotation based on the user input
     * @param input direction of the rotation
     */
    void rotate(InputCommands input);

    /**
     * shoots a bullet then enters cooldown until it can fire again
     * @return PlayerBullet that has been just shot
     */
    Bullet shot();

    Status getStatus();

    void setStatus(Status status);

    void activatePowerUp();

    /**
     * checks if the player has let go of the thrust inputs (UP and DOWN)
     * consequently the speed will decay
     */
    void thrustReleased();

    void acquirePowerUp();

    int getExp();

    void setExp(int expPoints);

    void addExp(int value);

    boolean isInPowerUp();
}
