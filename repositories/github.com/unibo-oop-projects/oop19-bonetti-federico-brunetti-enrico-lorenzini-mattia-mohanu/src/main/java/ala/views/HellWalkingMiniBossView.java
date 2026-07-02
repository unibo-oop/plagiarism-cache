package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * HellWalkingMiniBossView class.
 * 
 */
public final class HellWalkingMiniBossView extends StandardEnemyView {

    //Attributes:
    private static final Image HELL_MINI_BOSS_WALKING_LEFT = new Image(HellWalkingMiniBossView.class.getResource("/HellWalkingMiniBossLeft.gif").toExternalForm());
    private static final Image HELL_MINI_BOSS_WALKING_RIGHT = new Image(HellWalkingMiniBossView.class.getResource("/HellWalkingMiniBossRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param standardEnemyModel
     * 
     */
    public HellWalkingMiniBossView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, HELL_MINI_BOSS_WALKING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public Pane getLayer() {
        return layer;
    }

    public void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getHellEnemyWalkingLeft() {
        return HELL_MINI_BOSS_WALKING_LEFT;
    }

    public void hellMiniBossWalkingLeft() {
        this.getImageView().setImage(HELL_MINI_BOSS_WALKING_LEFT);
    }

    public static Image getHellEnemyWalkingRight() {
        return HELL_MINI_BOSS_WALKING_RIGHT;
    }

    public void hellMiniBossWalkingRight() {
        this.getImageView().setImage(HELL_MINI_BOSS_WALKING_RIGHT);
    }
}
