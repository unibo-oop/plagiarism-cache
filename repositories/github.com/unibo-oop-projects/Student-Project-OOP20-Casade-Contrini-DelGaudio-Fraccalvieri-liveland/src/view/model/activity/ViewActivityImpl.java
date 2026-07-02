package view.model.activity;

import java.util.Optional;

public final class ViewActivityImpl implements ViewActivity {

    private final Optional<Integer> capacity;
    private final Optional<Integer> minPrice;
    private final Optional<Integer> maxPrice;
    private final String name;
    private final ActivityType activityType;

    public ViewActivityImpl(final String name, final Optional<Integer> capacity, 
                            final Optional<Integer> minPrice,
                            final Optional<Integer> maxPrice, 
                            final ActivityType activityType) {
        this.name = name;
        this.capacity = capacity;
        this.activityType = activityType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getCapacity() {
        return this.capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getMinPrice() {
        return this.minPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getMaxPrice() {
        return this.maxPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityType getActivityType() {
        return this.activityType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ViewActivityImpl [capacity=" + capacity + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
                    + ", name=" + name + ", activityType=" + activityType + "]";
    }

}
