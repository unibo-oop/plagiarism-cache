package model.entity.powerup;

import java.awt.geom.Point2D.Double;
import java.util.TimerTask;

import javafx.scene.image.Image;
import model.Model;
import model.entity.Coin;
import model.entity.AbstractDynamicEntity;
import model.entity.SpawnLevel;
import model.entity.EntityType;

public class Mushroom extends AbstractDynamicEntity {

    private final EffectTimer mushroomTimer; 
    private static final int DOUBLECOIN = 2; 
    private static final int STDCOIN = 1; 

    /**
     * @param coordinates the coordinates of the powerup. 
     * @param image the image of the powerup. 
     * @param level the level on which spawns the powerup. 
     * @param type the type of the DynamicEntity. 
     * @param distance the distance where the next powerup will spawn. 
     */
    public Mushroom(final Double coordinates, final Image image, final SpawnLevel level, final EntityType type, final double distance) {
        super(coordinates, image, level, type, distance);
        mushroomTimer = new EffectTimer(); 
    }

    /**
     * Activates the Mushroom effect changing coins' value from 1 to 2.
     */
    @Override
    protected final void activateEffect(final Model model) {
        Coin.setCoinValue(DOUBLECOIN);
        mushroomTask(); 
    }

    /**
     * Restore initial parameters after a specific time. 
     */
    private void mushroomTask() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Coin.setCoinValue(STDCOIN);
            }
        };
        mushroomTimer.scheduleTask(task);
    }

}
