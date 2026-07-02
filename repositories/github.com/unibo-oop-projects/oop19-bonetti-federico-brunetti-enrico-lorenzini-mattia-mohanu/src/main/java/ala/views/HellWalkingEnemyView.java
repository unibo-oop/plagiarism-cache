package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * HellWalkingEnemyView class.
 * 
 */
public class HellWalkingEnemyView extends StandardEnemyView {

    //Attributes:
    private static final Image HELL_ENEMY_WALKING_LEFT = new Image(HellWalkingEnemyView.class.getResource("/HellWalkingEnemyLeft.gif").toExternalForm());
    private static final Image HELL_ENEMY_WALKING_RIGHT = new Image(HellWalkingEnemyView.class.getResource("/HellWalkingEnemyRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param standardEnemyModel
     * 
     */
    public HellWalkingEnemyView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, HELL_ENEMY_WALKING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getHellEnemyWalkingLeft() {
        return HELL_ENEMY_WALKING_LEFT;
    }

    public final void hellEnemyWalkingLeft() {
        this.getImageView().setImage(HELL_ENEMY_WALKING_LEFT);
    }

    public static Image getHellEnemyWalkingRight() {
        return HELL_ENEMY_WALKING_RIGHT;
    }

    public final void hellEnemyWalkingRight() {
        this.getImageView().setImage(HELL_ENEMY_WALKING_RIGHT);
    }
}
