package tmw.controller.camera;

import java.util.Optional;
import javafx.scene.Camera;
import javafx.scene.Parent;
import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.model.entities.GameEntity;

/**
 * Simple controller for a camera. Camera is intended to be the javaFx Camera.
 * This controller can match camera to a gameObject/entity in the gameWorld.
 * 
 * @version 1.2
 *
 */
public class CameraControllerImpl implements CameraController {

    private final Camera camera;
    private final Optional<Parent> hud;
    private double posX;
    private double posY;
    private GameEntity entity;
    private Dim2D resolution;

    /**
     * Public constructor.
     * 
     * @param camera camera to control
     * @param hud    parent which represents an hud
     * @param res    game resolution
     */
    public CameraControllerImpl(final Camera camera, final Optional<Parent> hud, final Dim2D res) {
        this.camera = camera;
        this.hud = hud;
        this.resolution = res;
    }

    @Override
    public final void followEntiy() {
        this.camera.setTranslateX(entity.getCentralPosition().getX() - resolution.getWidth() / 2);
        this.camera.setTranslateY(entity.getCentralPosition().getY() - resolution.getHeight() / 2);

        this.posX = entity.getCentralPosition().getX();
        this.posY = entity.getCentralPosition().getY();
        this.moveHud();
    }

    @Override
    public final void moveHud() {
        if (this.hud.isPresent()) {
            this.hud.get().setTranslateX(this.getCamPosition().getX() - resolution.getWidth() / 2);
            this.hud.get().setTranslateY(this.getCamPosition().getY() - resolution.getHeight() / 2);
        }
    }

    @Override
    public final void zoomOnPlayer(final double scaleFactor) {

        if (this.hud.isPresent()) {

            this.hud.get().setScaleX(scaleFactor);
            this.hud.get().setScaleY(scaleFactor);
        }

        this.camera.setScaleX(scaleFactor);
        this.camera.setScaleY(scaleFactor);

        this.hud.get().setLayoutX(((1 - scaleFactor) * 0.5 * resolution.getWidth()));
        this.hud.get().setLayoutY(((1 - scaleFactor) * 0.5 * resolution.getHeight()));
        this.camera.setLayoutX(((1 - scaleFactor) * 0.5 * resolution.getWidth()));
        this.camera.setLayoutY(((1 - scaleFactor) * 0.5 * resolution.getHeight()));
    }

    @Override
    public final P2d getCamPosition() {
        return new P2d(this.posX, this.posY);
    }

    @Override
    public final void setResolution(final Dim2D res) {
        this.resolution = res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void associtateToEntity(final GameEntity entity) {
        this.entity = entity;
    }

}
