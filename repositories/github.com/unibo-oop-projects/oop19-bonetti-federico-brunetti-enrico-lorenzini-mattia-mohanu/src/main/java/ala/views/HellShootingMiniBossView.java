package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * HellShootingMiniBossView class.
 * 
 */
public final class HellShootingMiniBossView extends StandardEnemyView {

    private static final Image HELL_MINI_BOSS_SHOOTING_LEFT = new Image(HellShootingMiniBossView.class.getResource("/HellShootingMiniBossLeft.gif").toExternalForm());
    private static final Image HELL_MINI_BOSS_SHOOTING_RIGHT = new Image(HellShootingMiniBossView.class.getResource("/HellShootingMiniBossRight.gif").toExternalForm());

    private Pane layer;
    /**
     * HellShootingMiniBossView class.
     * @param layer
     * @param standardEnemyModel
     */
    public HellShootingMiniBossView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, HELL_MINI_BOSS_SHOOTING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getHellMiniBossShootingLeft() {
        return HELL_MINI_BOSS_SHOOTING_LEFT;
    }

    public void hellMiniBossShootingLeft() {
        this.getImageView().setImage(HELL_MINI_BOSS_SHOOTING_LEFT);
    }

    public static Image getHellMiniBossShootingRight() {
        return HELL_MINI_BOSS_SHOOTING_RIGHT;
    }

    public void hellMiniBossShootingRight() {
        this.getImageView().setImage(HELL_MINI_BOSS_SHOOTING_RIGHT);
    }
}
