package ala.views;

import ala.models.LightBallModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * LightBallView class.
 * 
 */
public final class LightBallView extends DynamicGameObjectView {
    //Attributes:
    private static final Image IMG_LIGHT_BALL = new Image(LightBallModel.class.getResource("/Lightball.gif").toExternalForm());
    private LightBallModel lightBallModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param lightBallModel
     * 
     */
    public LightBallView(final Pane layer, final LightBallModel lightBallModel) {
        super(layer, IMG_LIGHT_BALL, lightBallModel);
        this.lightBallModel = lightBallModel;
    }

    //Getters&Setters:
    public LightBallModel getLightBallModel() {
        return lightBallModel;
    }

    public void setLightBallModel(final LightBallModel lightBallModel) {
        this.lightBallModel = lightBallModel;
    }

    public static Image getImgLightBall() {
        return IMG_LIGHT_BALL;
    }
}
