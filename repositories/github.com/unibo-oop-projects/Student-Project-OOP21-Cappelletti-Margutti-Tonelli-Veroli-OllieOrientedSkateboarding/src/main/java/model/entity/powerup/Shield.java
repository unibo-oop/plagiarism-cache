package model.entity.powerup;

import java.awt.geom.Point2D.Double;
import java.util.TimerTask;

import javafx.scene.image.Image;
import model.Model;
import model.entity.AbstractDynamicEntity;
import model.entity.SpawnLevel;
import model.entity.EntityType;

public final class Shield extends AbstractDynamicEntity {
 
    private static final boolean ACTIVATESHIELD = true; 
    private final EffectTimer shieldTimer; 

    /**
    * @param coordinates the coordinates of the powerup. 
    * @param image the image of the powerup. 
    * @param level the level on which spawns the powerup. 
    * @param type the type of the DynamicEntity. 
    * @param distance the distance where the next powerup will spawn. 
    */
    public Shield(final Double coordinates, final Image image, final SpawnLevel level, final EntityType type, final double distance) {
        super(coordinates, image, level, type, distance);
        shieldTimer = new EffectTimer(); 
    }

    /**
     * Activates the Shield effect which allows the player not to die whenever he hits an obstacle. 
     */
    @Override
    protected void activateEffect(final Model model) {
        model.getGameState().getPlayer().setShield(ACTIVATESHIELD);
        shieldTask(model); 
    }

    /**
     * Restore initial parameters after a specific time. 
     * @param model the game model. 
     */
    private void shieldTask(final Model model) {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                model.getGameState().getPlayer().setShield(!ACTIVATESHIELD);
            }
        };
        shieldTimer.scheduleTask(task);
    }

}
