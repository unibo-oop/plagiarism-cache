package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 *PurgatoryWalkingEnemyView, class that implements the graphic part of the enemy type in object. 
 */
public class PurgatoryWalkingEnemyView extends StandardEnemyView {

    //Attributes:
    private static final Image PURGATORY_ENEMY_WALKING_LEFT = new Image(PurgatoryWalkingEnemyView.class.getResource("/PurgatoryWalkingEnemyLeft.gif").toExternalForm());
    private static final Image PURGATORY_ENEMY_WALKING_RIGHT = new Image(PurgatoryWalkingEnemyView.class.getResource("/PurgatoryWalkingEnemyRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * 
     * @param layer
     * @param standardEnemyModel
     */
    public PurgatoryWalkingEnemyView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, PURGATORY_ENEMY_WALKING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getPurgatoryEnemyWalkingLeft() {
        return PURGATORY_ENEMY_WALKING_LEFT;
    }

    public final void purgatoryEnemyWalkingLeft() {
        this.getImageView().setImage(PURGATORY_ENEMY_WALKING_LEFT);
    }
    public static Image getPurgatoryEnemyWalkingRight() {
        return PURGATORY_ENEMY_WALKING_RIGHT;
    }

    public final void purgatoryEnemyWalkingRight() {
        this.getImageView().setImage(PURGATORY_ENEMY_WALKING_RIGHT);
    }
}
