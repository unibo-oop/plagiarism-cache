package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 *PurgatoryShootingMiniBossView, class that implements the graphic part of the enemy type in object. 
 */
public class PurgatoryShootingMiniBossView extends StandardEnemyView {
    //Attributes:
    private static final Image PURGATORY_MINI_BOSS_SHOOTING_LEFT = new Image(HellShootingMiniBossView.class.getResource("/PurgatoryShootingMiniBossLeft.gif").toExternalForm());
    private static final Image PURGATORY_MINI_BOSS_SHOOTING_RIGHT = new Image(HellShootingMiniBossView.class.getResource("/PurgatoryShootingMiniBossRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * 
     * @param layer
     * @param standardEnemyModel
     */
    public PurgatoryShootingMiniBossView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, PURGATORY_MINI_BOSS_SHOOTING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getPurgatoryMiniBossShootingLeft() {
        return PURGATORY_MINI_BOSS_SHOOTING_LEFT;
    }

    public final void purgatoryMiniBossShootingLeft() {
        this.getImageView().setImage(PURGATORY_MINI_BOSS_SHOOTING_LEFT);
    }

    public static Image getPurgatoryMiniBossShootingRight() {
        return PURGATORY_MINI_BOSS_SHOOTING_RIGHT;
    }

    public final void purgatoryMiniBossShootingRight() {
        this.getImageView().setImage(PURGATORY_MINI_BOSS_SHOOTING_RIGHT);
    }
}
