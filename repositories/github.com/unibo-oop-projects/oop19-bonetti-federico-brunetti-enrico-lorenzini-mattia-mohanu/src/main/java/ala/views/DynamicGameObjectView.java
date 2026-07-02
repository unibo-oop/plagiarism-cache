package ala.views;

import ala.models.DynamicGameObjectModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * DynamiGameObjectVIew class.
 * 
 */
public abstract class DynamicGameObjectView extends GameObjectView {
    //Attributes:
    private DynamicGameObjectModel dynamicGameObjectModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param image
     * @param dynamicGameObjectModel
     * 
     */
    public DynamicGameObjectView(final Pane layer, final Image image, final DynamicGameObjectModel dynamicGameObjectModel) {
        super(layer, image, dynamicGameObjectModel);
        this.dynamicGameObjectModel = dynamicGameObjectModel;
    }

    //Getters&Setters:
    public final DynamicGameObjectModel getDynamicGameObjectModel() {
        return dynamicGameObjectModel;
    }

    public final void setDynamicGameObjectModel(final DynamicGameObjectModel dynamicGameObjectModel) {
        this.dynamicGameObjectModel = dynamicGameObjectModel;
    }

    //Methods:
    /**
     * Relocate UI.
     * 
     */
    public final void updateUI() {
        this.getImageView().relocate(this.getGameObjectModel().getX(), this.getGameObjectModel().getY());
        this.getImageView().setRotate(this.getGameObjectModel().getR());
    }
}
