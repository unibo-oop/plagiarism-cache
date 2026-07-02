package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
* ParadiseWalkingMiniBossView class.
* 
*/
public class ParadiseWalkingMiniBossView extends StandardEnemyView {
    //Attributes:
    private static final Image PARADISE_MINI_BOSS_WALKING = new Image(HellWalkingMiniBossView.class.getResource("/ParadiseWalkingMiniBossWalk.gif").toExternalForm());
    private static final Image PARADISE_MINI_BOSS_WALKING_ATTACK = new Image(HellWalkingMiniBossView.class.getResource("/ParadiseWalkingMiniBossAttack.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param standardEnemyModel
     * 
     */
    public ParadiseWalkingMiniBossView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, PARADISE_MINI_BOSS_WALKING, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public static Image getparadiseEnemyWalking() {
        return PARADISE_MINI_BOSS_WALKING;
    }

    public static Image getparadiseEnemyAttack() {
        return PARADISE_MINI_BOSS_WALKING_ATTACK;
    }

    public final  void paradiseMiniBossWalking() {
        this.getImageView().setImage(PARADISE_MINI_BOSS_WALKING);
    }

    public final void paradiseMiniBossAttack() {
        this.getImageView().setImage(PARADISE_MINI_BOSS_WALKING_ATTACK);
    }
}

