package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * HellShootingEnemyView class.
 * 
 */
public final class HellShootingEnemyView extends StandardEnemyView {
    //Attributes:
    private static final Image HELL_ENEMY_SHOOTING_LEFT = new Image(HellShootingEnemyView.class.getResource("/HellShootingEnemyLeft.gif").toExternalForm());
    private static final Image HELL_ENEMY_SHOOTING_RIGHT = new Image(HellShootingEnemyView.class.getResource("/HellShootingEnemyRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param standardEnemyModel
     * 
     */
    public HellShootingEnemyView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, HELL_ENEMY_SHOOTING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public Pane getLayer() {
        return layer;
    }

    public void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getHellEnemyShootingLeft() {
        return HELL_ENEMY_SHOOTING_LEFT;
    }

    public void hellEnemyShootingLeft() {
        this.getImageView().setImage(HELL_ENEMY_SHOOTING_LEFT);
    }

    public static Image getHellEnemyShootingRight() {
        return HELL_ENEMY_SHOOTING_RIGHT;
    }

    public void hellEnemyShootingRight() {
        this.getImageView().setImage(HELL_ENEMY_SHOOTING_RIGHT);
    }
}
