package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * ParadiseWalkingEnemyView class.
 * 
 */
public class ParadiseWalkingEnemyView extends StandardEnemyView {
    //Attibutes:
    private static final Image ARCHANGEL = new Image(StandardEnemyView.class.getResource("/Archangel.gif").toExternalForm());
    private static final Image ARCHANGEL_ATTACK = new Image(StandardEnemyView.class.getResource("/ArchangelAttack.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param standardEnemyModel
     * 
     */
    public ParadiseWalkingEnemyView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, ARCHANGEL, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public static Image getArchangel() {
        return ARCHANGEL;
    }

    public static Image getArchangelAttack() {
        return ARCHANGEL_ATTACK;
    }

    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public final void archangelWalk() {
        this.getImageView().setImage(ARCHANGEL);
    }

    public final void archangelAttack() {
        this.getImageView().setImage(ARCHANGEL_ATTACK);
    }
}
