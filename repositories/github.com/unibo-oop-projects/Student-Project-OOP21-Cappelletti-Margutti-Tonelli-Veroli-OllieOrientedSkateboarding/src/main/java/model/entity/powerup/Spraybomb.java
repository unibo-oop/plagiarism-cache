package model.entity.powerup;

import java.awt.geom.Point2D.Double;

import javafx.application.Platform;
import javafx.scene.image.Image;
import model.Model;
import model.entity.AbstractDynamicEntity;
import model.entity.SpawnLevel;
import model.entity.EntityType;

public class Spraybomb extends AbstractDynamicEntity {

    /**
     * @param coordinates the coordinates of the powerup. 
     * @param image the image of the powerup. 
     * @param level the level on which spawns the powerup. 
     * @param type the type of the DynamicEntity. 
     * @param distance the distance where the next powerup will spawn. 
     */
    public Spraybomb(final Double coordinates, final Image image, final SpawnLevel level, final EntityType type, final double distance) {
        super(coordinates, image, level, type, distance);
    }

    /**
     * Activates the Spraybomb effect which deletes all of the obstacles present on the map at the pickup time. 
     */
    @Override
    protected final void activateEffect(final Model model) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                model.getGameState().getEntities().clear();
            }
        }); 
    }

}
