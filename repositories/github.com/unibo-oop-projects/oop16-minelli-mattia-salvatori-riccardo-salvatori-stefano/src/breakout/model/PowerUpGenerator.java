package breakout.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import breakout.model.entities.PowerUp;
import breakout.model.entities.PowerUpEffect;
import javafx.geometry.Point2D;

/**
 * Class that handle power up spawns during the advanced game.
 *
 */
class PowerUpGenerator {

    private final List<PowerUpEffect> types;
    private final int probability;

    /**
     * Private constructure.
     * 
     * @param probability
     *            the probability for a power up to spawn.
     */
    PowerUpGenerator(final int probability) {
        this.types = new ArrayList<>(Arrays.asList(PowerUpEffect.values()));
        this.probability = probability;
    }

    /**
     * Randomly generates a power up in the given position from all the
     * possible. power up from {@link PowerUpEffect}
     * 
     * @param pos
     *            The position where the power up must spawn
     * @return An optional that contains the spefic power up if a power up has
     *         been generated, otherwise is empty
     */
    public Optional<PowerUp> generateFrom(final Point2D pos) {
        if (this.isToGenerate()) {
            return Optional.of(AdvancedFactory.get().createPowerUp(this.getTypeFromList(), pos));
        } else {
            return Optional.empty();
        }

    }

    /**
     * Determines the result of an aleatory event.
     * 
     * @return true if the event was successful.<br/>
     *         false otherwise
     */
    private boolean isToGenerate() {
        return this.probability >= new Random().nextInt(100);
    }

    /**
     * Get a random power up type from the list of power up types
     * 
     * @return a power up type
     */
    private PowerUpEffect getTypeFromList() {
        return this.types.get(new Random().nextInt(this.types.size()));

    }

}
