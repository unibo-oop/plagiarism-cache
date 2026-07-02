package ala.views;

import ala.models.DynamicGameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * GameObjectAliveView class.
 * 
 */
public abstract class GameObjectAliveView extends DynamicGameObjectView {

    private static final Image IMG_EXPLOSION = new Image(LuciferBasicView.class.getResource("/explosion.gif").toExternalForm());
    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param dynamicGameObjectModel
     * 
     */
    public GameObjectAliveView(final Pane layer, final Image image, final DynamicGameObjectModel dynamicGameObjectModel) {
        super(layer, image, dynamicGameObjectModel);
    }

    /**
     * do an explosion animation.
     */
    public void explode() {
        this.getImageView().setImage(IMG_EXPLOSION);
    }
}
