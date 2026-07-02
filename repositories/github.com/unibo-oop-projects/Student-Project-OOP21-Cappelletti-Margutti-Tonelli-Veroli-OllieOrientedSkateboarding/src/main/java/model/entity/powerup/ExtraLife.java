package model.entity.powerup;

import java.awt.geom.Point2D.Double;

import javafx.scene.image.Image;
import model.Model;
import model.entity.AbstractDynamicEntity;
import model.entity.SpawnLevel;
import model.entity.EntityType;

public class ExtraLife extends AbstractDynamicEntity {

    /**
     * @param coordinates the coordinates of the powerup. 
     * @param image the image of the powerup. 
     * @param level the level on which spawns the powerup. 
     * @param type the type of the DynamicEntity. 
     * @param distance the distance where the next powerup will spawn. 
     */
    public ExtraLife(final Double coordinates, final Image image, final SpawnLevel level, final EntityType type, final double distance) {
        super(coordinates, image, level, type, distance);
    }

    /**
     * Activates the Extralife effect increasing the player's lives of 1.
     */
    @Override
    protected final void activateEffect(final Model model) {
        model.getGameState().getPlayer().setNumberOfLives(1);
    }

}
