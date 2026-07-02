package it.unibo.model.camera.impl;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.api.AltitudeSubject;
import it.unibo.model.gameobj.api.Alien;

/**
 * Manages the calculation of the player's altitude. Implements
 * {@link AltitudeSubject}.
 */
@SuppressFBWarnings(
    value = "EI_EXPOSE_REP",
    justification = "This component is designed to directly modify the shared game state (Alien)."
    + "A defensive copy would render the game logic ineffective."
)
public class AltitudeManager implements AltitudeSubject {

    private final List<AltitudeObserver> observerList = new ArrayList<>();
    private final double thresholdY;
    private final Alien alien;

    /**
     * Constructs an AltitudeManager with the given alien and thresholdY.
     * 
     * @param alien      alien the player to monitor
     * @param thresholdY the threshold Y value that triggers the altitude update
     *                   when the alien moves above it
     */
    public AltitudeManager(final Alien alien, final double thresholdY) {
        this.alien = alien;
        this.thresholdY = thresholdY;
    }

    /**
     * Checks the current position of the alien with the thresholdY.
     * If the alien's Y position is below the thresholdY, it calculates the delta
     * and notifies the observers.
     */
    public void verifiedAltitude() {
        final double currentY = alien.getPosY();
        if (currentY < thresholdY) {
            final double delta = thresholdY - currentY;
            notifyObserver(delta);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final AltitudeObserver observer) {
        observerList.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(final AltitudeObserver observer) {
        observerList.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObserver(final double delta) {
        for (final AltitudeObserver altitudeObserver : observerList) {
            altitudeObserver.update(delta);
        }
    }

}
