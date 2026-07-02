package ala.views;
/**
 * 
 * StandardEnemyView, class that set the image on the screen for the enemies.
 */
import ala.models.StandardEnemyModel;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class StandardEnemyView extends GameObjectAliveView {
    /**
     * 
     * @param layer
     * @param image
     * @param standardEnemyModel
     */
    public StandardEnemyView(final Pane layer, final Image image, final StandardEnemyModel standardEnemyModel) {
        super(layer, image, standardEnemyModel);
    }
}

