package view.hud;

import controller.collisionDetection.Collision;
import model.hud.HUDLife;
import model.hud.HUDPoints;
import model.hud.HUDPowerUp;

/**
 * 
 */
public interface HUDInterface {

    /**
     * @return gameStatus.
     */
    boolean checkGameStatus();

    /**
     * @return collision detector.
     */
    Collision getCollision();

    /**
     * @return points.
     */
    int checkPoints();

    /**
     * @return lifePoints.
     */
    int checkLives();

    /**
     * @return HUDPowerUp reference.
     */
    HUDPowerUp getPowerUpImpl();

    /**
     * @return HUDLife reference.
     */
    HUDLife getLifeImpl();

    /**
     * @return HUDPoints reference.
     */
    HUDPoints getPointsImpl();
}
