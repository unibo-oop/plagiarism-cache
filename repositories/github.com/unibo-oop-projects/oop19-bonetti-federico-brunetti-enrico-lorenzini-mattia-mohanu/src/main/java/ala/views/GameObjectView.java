package ala.views;

import ala.models.GameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
/**
 * GameObjectView class.
 * 
 */
public abstract class GameObjectView {
    //Attributes:
    private ImageView imageView;
    private Pane layer;
    private GameObjectModel gameObjectModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param gameObjectModel
     * 
     */
    public GameObjectView(final Pane layer, final Image image, final GameObjectModel gameObjectModel) {
        this.imageView = new ImageView(image);
        this.imageView.setRotate(gameObjectModel.getR());

        this.layer = layer;

        addToLayer();
        this.setTo(gameObjectModel.getX(), gameObjectModel.getY());

        this.gameObjectModel = gameObjectModel;
    }

    //Getters&Setters:
    /**
     * 
     * @return Pane
     */
    public Pane getLayer() {
        return layer;
    }

    /**
     * 
     * @param layer
     */
    public void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public final ImageView getImageView() {
        return imageView;
    }

    public final void setImageView(final ImageView imageView) {
        this.imageView = imageView;
    }

    //Methods:
    /**
     * add image to layer.
     * 
     */
    public final void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }
    /**
     * remove image from layer.
     * 
     */
    public final void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }
    /**
     * relocate image.
     * 
     * @param x
     *        x position of the object
     * @param y
     *        y position of the object
     */
    public final void setTo(final double x, final double y) {
        this.imageView.relocate(x, y);
    }

    public final GameObjectModel getGameObjectModel() {
        return gameObjectModel;
    }

    public final void setGameObjectModel(final GameObjectModel gameObjectModel) {
        this.gameObjectModel = gameObjectModel;
    }
}
