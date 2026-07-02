package tmw.controller.camera;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.entities.GameEntity;

/**
 * Interface to manage camera. Camera can also control an hud.
 * Basically allows to reach a position and zoom there; it should be
 * used to follow player.
 *
 */
public interface CameraController {

    /**
     * This method should be called each time the position of a entity changes. In
     * this case moves automatically also an hud if present.
     */
    void followEntiy();
    /**
     * Allows to associate camera to a specific entity.
     * 
     * @param entity entity to associate with
     */
    void associtateToEntity(GameEntity entity);

    /**
     * This method allows to move hud. Should be used in addiction to followEntity
     * to move both camera and hud
     */
    void moveHud();

    /**
     * Allows to modify resolution.
     * 
     * @param res game resolution
     */
    void setResolution(Dim2D res);

    /**
     * Allows to zoom on the player position.
     * 
     * @param scaleFactor zoom value
     */
    void zoomOnPlayer(double scaleFactor);

    /**
     * Getter for camera position.
     * 
     * @return position
     */
    P2d getCamPosition();
}
