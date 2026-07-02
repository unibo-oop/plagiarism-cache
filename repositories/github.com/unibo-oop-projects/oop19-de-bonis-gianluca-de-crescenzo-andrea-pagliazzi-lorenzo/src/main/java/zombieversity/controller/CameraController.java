package zombieversity.controller;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Entity;
import zombieversity.model.world.Camera;
import zombieversity.model.world.CameraImpl;

/**
 * Game cameras controller.
 */
public class CameraController {
    private final Camera mapCam;
    private final Camera minimapCam;

    public CameraController(final double sX, final double sY, final double mapW, final double mapH,
            final double cameraW, final double cameraH) {
        this.mapCam = new CameraImpl(sX, sY, mapW, mapH, cameraW, cameraH);
        this.minimapCam = new CameraImpl(sX, sY, mapW / 10, mapH / 10, cameraW / 10, cameraH / 10);
    }

    /**
     * @return
     *          Center of map.
     */
    public final Point2D getCenter() {
        return this.mapCam.getCenter();
    }

    /**
     * Camera offset given.
     * 
     * @return - Point2D
     */
    public final Point2D getOffset() {
        return this.mapCam.getOffset();
    }

    /**
     * Center camera based on entity Position.
     * 
     * @param e
     */
    public final void centerOnEntity(final Entity e) {
        this.mapCam.centerOnEntity(e);
        this.minimapCam.centerOn(e.getPosition().getX() / 10, e.getPosition().getY() / 10);
    }

    /**
     * Return camera model.
     * 
     * @return - Camera
     */
    public final Camera getCamera() {
        return this.mapCam;
    }

    /**
     * @return
     *          Minimap's camera.
     */
    public final Camera getMiniCamera() {
        return this.minimapCam;
    }

}
