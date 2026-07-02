package ala.views;

import ala.models.ArrowModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * ArrowView class.
 * 
 */
public final class ArrowView extends DynamicGameObjectView {
    //Attributes:
    private static final Image IMG_ARROW = new Image(ArrowView.class.getResource("/Arrow.gif").toExternalForm());
    private ArrowModel arrowModel;

    /**
     * Constructor.
     * 
     * @param layer
     * @param arrowModel
     * 
     */
    public ArrowView(final Pane layer, final ArrowModel arrowModel) {
        super(layer, IMG_ARROW, arrowModel);
        this.arrowModel = arrowModel;
    }

    public ArrowModel getArrowModel() {
        return arrowModel;
    }

    public void setArrowModel(final ArrowModel arrowModel) {
        this.arrowModel = arrowModel;
    }

    public static Image getImgArrow() {
        return IMG_ARROW;
    }

}
