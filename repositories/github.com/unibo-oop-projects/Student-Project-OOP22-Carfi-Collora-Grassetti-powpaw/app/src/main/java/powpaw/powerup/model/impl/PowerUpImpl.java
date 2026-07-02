package powpaw.powerup.model.impl;

import java.util.Random;

import javafx.scene.shape.Circle;
import powpaw.player.model.api.PlayerStats;
import powpaw.powerup.model.api.PowerUp;
import powpaw.world.controller.ScreenController;

/**
 * abstract PowerUp. This class create the base of the powerUp. POWNUMBER is a
 * static double which
 * indicate how much points a stats will get when increased by the powerUp
 * 
 * 
 * @author Simone Collorà
 */
public abstract class PowerUpImpl implements PowerUp {

    /**
     * Stats added by powerUp.
     */
    protected static final double POWNUMBER = 0.2;
    /**
     * PowerUp duration.
     */
    protected static final int POWERUPDURATION = 7;
    private final double radius = ScreenController.SIZE_HD_W / 40;
    private final Random rand = new Random();
    private final Circle hurtbox;
    private boolean isVisible = true;

    /**
     * PowerUp costructor that create a new circle.
     */
    public PowerUpImpl() {
        this.hurtbox = new Circle(rand.nextDouble(radius + 10, ScreenController.SIZE_HD_W - radius - 10),
                rand.nextDouble(radius, ScreenController.SIZE_HD_H / 3), radius);
    }

    /**
     * Return Hurtbox.
     * 
     * @return Hurtbox.
     */
    @Override
    public Circle getHurtbox() {
        return this.hurtbox;
    }

    /**
     * Set visibility.
     * 
     * @param b
     */
    @Override
    public void setVisible(final boolean b) {
        this.isVisible = b;
        this.hurtbox.setVisible(b);
    }

     /**
     * Return visibility.
     * 
     * @return visibility.
     */
    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    @Override
    public abstract void statPowerUp(PlayerStats stats);
}
