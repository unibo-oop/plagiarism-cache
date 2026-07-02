package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * ParadiseShootingMiniBossView class.
 * 
 */
public class ParadiseShootingMiniBossView extends StandardEnemyView {
    //Attributes:
    private static final Image PARADISE_MINI_BOSS_SHOOTING = new Image(HellShootingMiniBossView.class.getResource("/ParadiseShootingMiniBossWalk.gif").toExternalForm());
    private static final Image PARADISE_MINI_BOSS_SHOOTING_ATTACK = new Image(HellShootingMiniBossView.class.getResource("/ParadiseShootingMiniBossAttack.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    public ParadiseShootingMiniBossView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, PARADISE_MINI_BOSS_SHOOTING, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getParadiseMiniBossShooting() {
        return PARADISE_MINI_BOSS_SHOOTING;
    }

    public static Image getParadiseMiniBossShootingAttack() {
        return PARADISE_MINI_BOSS_SHOOTING_ATTACK;
    }

    public final void paradiseMiniBossShooting() {
        this.getImageView().setImage(PARADISE_MINI_BOSS_SHOOTING);
    }

    public final void paradiseMiniBossShootingAttack() {
        this.getImageView().setImage(PARADISE_MINI_BOSS_SHOOTING_ATTACK);
    }
}

