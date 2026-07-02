package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 *PurgatoryWalkingMiniBossView, class that implements the graphic part of the enemy type in object. 
 */
public class PurgatoryWalkingMiniBossView extends StandardEnemyView {
    //Attributes:
    private static final Image PURGATORY_MINI_BOSS_WALKING_LEFT = new Image(HellWalkingMiniBossView.class.getResource("/PurgatoryWalkingMiniBossLeft.gif").toExternalForm());
    private static final Image PURGATORY_MINI_BOSS_WALKING_RIGHT = new Image(HellWalkingMiniBossView.class.getResource("/PurgatoryWalkingMiniBossRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * 
     * @param layer
     * @param standardEnemyModel
     */
    public PurgatoryWalkingMiniBossView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, PURGATORY_MINI_BOSS_WALKING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getPurgatoryEnemyWalkingLeft() {
        return PURGATORY_MINI_BOSS_WALKING_LEFT;
    }

    public final void purgatoryMiniBossWalkingLeft() {
        this.getImageView().setImage(PURGATORY_MINI_BOSS_WALKING_LEFT);
    }

    public static Image getPurgatoryEnemyWalkingRight() {
        return PURGATORY_MINI_BOSS_WALKING_RIGHT;
    }

    public final void purgatoryMiniBossWalkingRight() {
        this.getImageView().setImage(PURGATORY_MINI_BOSS_WALKING_RIGHT);
    }
}
