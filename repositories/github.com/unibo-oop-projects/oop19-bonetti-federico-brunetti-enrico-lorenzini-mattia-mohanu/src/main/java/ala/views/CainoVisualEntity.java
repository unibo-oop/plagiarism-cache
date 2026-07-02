package ala.views;

import ala.models.DynamicGameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * CainoVisualEntity class.
 * 
 */
public class CainoVisualEntity extends GameObjectAliveView {

    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param dynamicGameObjectModel
     * 
     */
    public CainoVisualEntity(final Pane layer, final Image image, final DynamicGameObjectModel dynamicGameObjectModel) {
        super(layer, image, dynamicGameObjectModel);
    }
}
