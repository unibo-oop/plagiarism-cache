package ala.views;

import ala.models.DynamicGameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * MicheleVisualEntity class.
 * 
 */
public class MicheleVisualEntity extends GameObjectAliveView {
    private static Image imgExplosion = new Image(MicheleView.class.getResource("/bigExplosion.gif").toExternalForm());
    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param dynamicGameObjectModel
     * 
     */
    public MicheleVisualEntity(final Pane layer, final Image image, final DynamicGameObjectModel dynamicGameObjectModel) {
        super(layer, image, dynamicGameObjectModel);
    }

    public final void explode() {
        this.getImageView().setImage(imgExplosion);
    }
}
