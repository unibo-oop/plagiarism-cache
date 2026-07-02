package model.entity.powerup;

import java.awt.geom.Point2D.Double;
import java.util.TimerTask;

import javafx.scene.image.Image;
import model.Model;
import model.entity.AbstractDynamicEntity;
import model.entity.SpawnLevel;
import model.entity.EntityType;

public class Superjump extends AbstractDynamicEntity {

    private static final boolean ACTIVATESUPERJUMP = true; 
    private final EffectTimer jumpTimer; 

    /**
     * @param coordinates the coordinates of the powerup. 
     * @param image the image of the powerup. 
     * @param level the level on which spawns the powerup. 
     * @param type the type of the DynamicEntity. 
     * @param distance the distance where the next powerup will spawn. 
     */
    public Superjump(final Double coordinates, final Image image, final SpawnLevel level, final EntityType type, final double distance) {
        super(coordinates, image, level, type, distance);
        jumpTimer = new EffectTimer(); 
    }

    /**
     * Activates the Superjump effect which doubles the jump heigth. 
     */
    @Override
    protected final void activateEffect(final Model model) {
        model.getGameState().getPlayer().setDoubleJump(ACTIVATESUPERJUMP);
        jumpTask(model);
    }

    /**
     * Restore initial parameters after a specific time. 
     * @param model the game model. 
     */
    private void jumpTask(final Model model) {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                model.getGameState().getPlayer().setDoubleJump(!ACTIVATESUPERJUMP);
            }
        };
        jumpTimer.scheduleTask(task);
    }

}
