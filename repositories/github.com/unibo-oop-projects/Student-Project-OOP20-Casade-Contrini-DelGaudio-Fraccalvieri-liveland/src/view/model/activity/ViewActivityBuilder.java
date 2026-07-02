package view.model.activity;

import java.util.Optional;

/**
 * This class implements a strategy to build {@link ViewActivityImpl}
 * objects step-by-step, regardless of activity type.
 */
public class ViewActivityBuilder {

    private Optional<Integer> capacity = Optional.empty();
    private Optional<Integer> minPrice = Optional.empty();
    private Optional<Integer> maxPrice = Optional.empty();
    private final String name;
    private final ActivityType activityType;

    public ViewActivityBuilder(final String name, final ActivityType actType) {
        this.name = name;
        this.activityType = actType;
    }

    /**
     * @param capacity to be set
     * @return current ViewActivityBuilder
     */
    public ViewActivityBuilder capacity(final int capacity) {
        this.capacity = Optional.ofNullable(capacity);
        return this;
    }

    /**
     * @param minPrice : minimum price to be set
     * @return current ViewActivityBuilder
     */
    public ViewActivityBuilder minPrice(final int minPrice) {
        this.minPrice = Optional.ofNullable(minPrice);
        return this;
    }

    /**
     * @param maxPrice : maximum price to be set
     * @return current ViewActivityBuilder
     */
    public ViewActivityBuilder maxPrice(final int maxPrice) {
        this.maxPrice = Optional.ofNullable(maxPrice);
        return this;
    }

    /**
     * @return current ViewActivityBuilder effectively built
     */
    public ViewActivityImpl build() {
        return new ViewActivityImpl(this.name, this.capacity, this.minPrice,
                this.maxPrice, this.activityType);
    }

}
