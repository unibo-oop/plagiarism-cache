package powpaw.powerup.view.api;

import javafx.scene.shape.Circle;
import powpaw.powerup.model.api.PowerUp;

/**
 * Set the render of the powerUp like position,color and radius.
 * 
 * @author Simone Collorà
 */
public interface PowerUpRender {

    /**
     * set powerUp type.
     * 
     * @param powerup
     * @param type    powerUp type
     */
    void setPowerUp(PowerUp powerup, int type);

    /**
     * Return sprite.
     * 
     * @return sprite
     */
    Circle getSprite();

    /**
     * renderize powerUp.
     */
    void render();

}
