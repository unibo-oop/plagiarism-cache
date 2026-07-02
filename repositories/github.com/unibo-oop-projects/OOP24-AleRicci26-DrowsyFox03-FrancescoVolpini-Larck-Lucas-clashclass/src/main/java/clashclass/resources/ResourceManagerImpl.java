package clashclass.resources;

/**
 * Represents a ResourceManager implementation.
 */
public class ResourceManagerImpl implements ResourceManager {
    private final double maxValue;
    private double currentValue;

    /**
     * Construct ResourceManager.
     *
     * @param maxValue the maximum value of the resource
     */
    public ResourceManagerImpl(final int maxValue) {
        this.maxValue = maxValue;
        this.currentValue = 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increase(final double value) {
        this.currentValue = Math.min(maxValue, this.currentValue + value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decrease(final double value) {
        this.currentValue = Math.max(0, this.currentValue - value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentValue() {
        return this.currentValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxValue() {
        return this.maxValue;
    }
}
