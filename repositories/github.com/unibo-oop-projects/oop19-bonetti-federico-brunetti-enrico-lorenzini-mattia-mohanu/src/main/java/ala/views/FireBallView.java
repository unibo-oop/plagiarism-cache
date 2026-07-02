package ala.views;

import ala.models.FireBallModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * FireBallView class.
 * 
 */
public class FireBallView extends DynamicGameObjectView {
    //Attributes:
    private static final Image IMG_FIRE_BALL = new Image(FireBallModel.class.getResource("/fireball.gif").toExternalForm());
    private FireBallModel fireBallModel;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param fireBallModel
     * 
     */
    public FireBallView(final Pane layer, final FireBallModel fireBallModel) {
        super(layer, IMG_FIRE_BALL, fireBallModel);
        this.fireBallModel = fireBallModel;
    }

    //Getters&Setters:
    public final FireBallModel getFireBallModel() {
        return fireBallModel;
    }

    public final void setFireBallModel(final FireBallModel fireBallModel) {
        this.fireBallModel = fireBallModel;
    }

    public static Image getImgFireBall() {
        return IMG_FIRE_BALL;
    }
}
