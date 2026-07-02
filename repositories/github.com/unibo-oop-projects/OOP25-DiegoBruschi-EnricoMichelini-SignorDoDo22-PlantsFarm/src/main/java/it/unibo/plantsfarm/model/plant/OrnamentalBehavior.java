package it.unibo.plantsfarm.model.plant;

/**
 * Strategy implementation for ornamental plants.
 */
public final class OrnamentalBehavior implements PlantBehavior {

    private static final long serialVersionUID = 1L;
    private final PlantEffect effect;
    private final double value;

    /**
     * Creates a new OrnamentalBehavior.
     *
     * @param effect the type of the effect
     * @param value  the value of the effect
     */
    public OrnamentalBehavior(final PlantEffect effect, final double value) {
        this.effect = effect;
        this.value = value;
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int generateHarvest() {
        return 0;
    }

    /**
     * Gets the plant effect.
     *
     * @return the effect
     */
    public PlantEffect getEffect() {
        return effect;
    }

    /**
     * Gets the effect value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }
}
