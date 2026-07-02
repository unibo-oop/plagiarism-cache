package ala.views;

import ala.models.DynamicGameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * MoonView class.
 * 
 */
public class MoonView extends DynamicGameObjectView {
    private static final Image IMG_MOON = new Image(MoonView.class.getResource("/moon.png").toExternalForm());
    /**
     * Constructor.
     * 
     * @param layer
     * @param dynamicGameObjectModel
     * 
     */
    public MoonView(final Pane layer, final DynamicGameObjectModel dynamicGameObjectModel) {
        super(layer, IMG_MOON, dynamicGameObjectModel);
    }
}
