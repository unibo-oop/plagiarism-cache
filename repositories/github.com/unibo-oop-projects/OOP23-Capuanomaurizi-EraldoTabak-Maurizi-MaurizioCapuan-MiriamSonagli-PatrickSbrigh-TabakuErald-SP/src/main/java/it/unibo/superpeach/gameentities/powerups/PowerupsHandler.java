package it.unibo.superpeach.gameentities.powerups;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Power up handler class implementation.
 * @author  Miriam Sonaglia
 */
public class PowerupsHandler {

    private final List<PowerUp> powerups;

    /**
     * initializes a new list of power ups.
     */
    public PowerupsHandler() {
        powerups = new ArrayList<>();
    }

    /**
     * iterates through all the powerups alive,
     * if they die they are removed.
     */
    public final void tickPowerups() {
        final Set<PowerUp> powerupsToRemove = new HashSet<>();
        for (final PowerUp powerUp : powerups) {
            powerUp.tick();
            if (!powerUp.isAlive()) {
                powerupsToRemove.add(powerUp);
            }
        }

        for (final PowerUp powerUp : powerupsToRemove) {
            powerups.remove(powerUp);
        }
    }

    /**
     * Method that draws all the power ups in the list.
     * @param g
     */
    public final void renderPowerups(final Graphics g) {
        for (final PowerUp powerup : powerups) {
            powerup.render(g);
        }
    }

    /**
     * adds a power up to the power ups handler.
     * @param p
     */
    public final void addPowerUp(final PowerUp p) {
        powerups.add(p);
    }

    /**
     * removes a power up from the power ups handler.
     * @param p
     */
    public final void removePowerUp(final PowerUp p) {
        powerups.remove(p);
    }

    /**
     * @return a copy of the power up list.
     */
    public final List<PowerUp> getPowerups() {
        return List.copyOf(powerups);
    }

}

