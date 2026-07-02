package ala.views;

import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
/**
 * ParadiseShootingEnemyView class.
 * 
 */
public class ParadiseShootingEnemyView extends StandardEnemyView {

    //attributes:
    private static final Image ANGEL = new Image(StandardEnemyView.class.getResource("/Angel.gif").toExternalForm());
    private static final Image ANGEL_ATTACK = new Image(StandardEnemyView.class.getResource("/AngelAttack.gif").toExternalForm());

    private Pane layer;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param layer
     * @param standardEnemyModel
     * 
     */
    public ParadiseShootingEnemyView(final Pane layer, final StandardEnemyModel standardEnemyModel) {
        super(layer, ANGEL, standardEnemyModel);
        this.layer = layer;
    }

    //Getters&Setters:
    public static Image getAngel() {
        return ANGEL;
    }

    public static Image getAngelAttack() {
        return ANGEL_ATTACK;
    }

    public final Pane getLayer() {
        return layer;
    }

    public final void setLayer(final Pane layer) {
        this.layer = layer;
    }

    public final  void angelWalk() {
        this.getImageView().setImage(ANGEL);
    }

    public final void angelAttack() {
        this.getImageView().setImage(ANGEL_ATTACK);
    }
}
