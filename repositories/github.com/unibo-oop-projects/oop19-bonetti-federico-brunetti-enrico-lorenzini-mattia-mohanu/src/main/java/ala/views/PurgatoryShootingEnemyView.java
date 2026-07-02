package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 *PurgatoryShootingEnemyView, class that implements the graphic part of the enemy type in object. 
 */
public class PurgatoryShootingEnemyView extends StandardEnemyView {
    //Attributes:
    private static final Image PURGATORY_ENEMY_SHOOTING_LEFT = new Image(PurgatoryShootingEnemyView.class.getResource("/PurgatoryShootingEnemyLeft.gif").toExternalForm());
    private static final Image PURGATORY_ENEMY_SHOOTING_RIGHT = new Image(PurgatoryShootingEnemyView.class.getResource("/PurgatoryShootingEnemyRight.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * 
     * @param layer
     * @param standardEnemyModel
     */
    public PurgatoryShootingEnemyView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, PURGATORY_ENEMY_SHOOTING_LEFT, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static final  Image getPurgatoryEnemyShootingLeft() {
        return PURGATORY_ENEMY_SHOOTING_LEFT;
    }

    public final void purgatoryEnemyShootingLeft() {
        this.getImageView().setImage(PURGATORY_ENEMY_SHOOTING_LEFT);
    }

    public static Image getPurgatoryEnemyShootingRight() {
        return PURGATORY_ENEMY_SHOOTING_RIGHT;
    }

    public final void purgatoryEnemyShootingRight() {
        this.getImageView().setImage(PURGATORY_ENEMY_SHOOTING_RIGHT);
    }
}
