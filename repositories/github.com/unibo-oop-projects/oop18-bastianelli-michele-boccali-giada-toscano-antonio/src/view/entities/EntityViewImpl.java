package view.entities;

import org.jbox2d.common.Vec2;

import controller.GameController;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.EntityModel;
import utils.Box2DUtils;
import view.Camera;

public class EntityViewImpl implements EntityView {

    private final EntityModel entityModel;
    private final ImageView image;
    private final Dimension2D dimension;
    private static final String IMAGE_PATH = "images/";
    private Point2D position;

    public EntityViewImpl(final EntityModel entityModel) {
        this.entityModel = entityModel;
        this.dimension = Box2DUtils.vecToDim(entityModel.getDimension());
        this.image = new ImageView(new Image(IMAGE_PATH + entityModel.getSpecificType().getImageName()));
        initView();
    }

    @Override
    public final ImageView getImage() {
        return this.image;
    }

    private void initView() {
        position = Box2DUtils.vecToPoint(entityModel.getPhysicPosition());
        image.setCache(true);
        image.setCacheHint(CacheHint.SPEED);
        image.setX(position.getX());
        image.setY(position.getY());
        GameController.getInstance().getGameView().getViewNodes().add(image);
    }

    @Override
    public final void updateView() {
        position = Camera.viewPointToCamera(Box2DUtils.vecToPoint(entityModel.getPhysicPosition()));
        image.setX(position.getX());
        image.setY(position.getY());
    }

    @Override
    public final void remove() {
        GameController.getInstance().getGameView().getViewNodes().remove(image);
    }

    @Override
    public final void show() {
        GameController.getInstance().getGameView().getViewNodes().add(image);
    }

    @Override
    public final Vec2 getViewPosition() {
        return new Vec2((float) image.getX(), (float) image.getY());
    }

    @Override
    public final void fitViewToDimension(final boolean preserveRatio) {
        image.setPreserveRatio(preserveRatio);
        image.setFitWidth(dimension.getWidth());
        image.setFitHeight(dimension.getHeight());
    }

}
