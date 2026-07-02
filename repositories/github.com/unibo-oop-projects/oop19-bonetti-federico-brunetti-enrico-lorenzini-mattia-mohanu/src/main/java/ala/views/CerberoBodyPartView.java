package ala.views;

import ala.models.DynamicGameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * CerberoBodyPartView class.
 * 
 */
public class CerberoBodyPartView extends GameObjectAliveView {
    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param dynamicGameObjectModel
     * 
     */
    public CerberoBodyPartView(final Pane layer, final Image image, final DynamicGameObjectModel dynamicGameObjectModel) {
        super(layer, image, dynamicGameObjectModel);
    }
}
