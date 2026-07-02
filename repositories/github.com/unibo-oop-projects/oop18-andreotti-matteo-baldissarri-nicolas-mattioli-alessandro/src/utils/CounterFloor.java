package utils;

/**
 * Counts the stuntman's climbed plans.
 */
public final class CounterFloor implements Counter {

    private int scaledFloors = 0;

    @Override
    public int getValue() {
        return this.scaledFloors;
    }

    @Override
    public void increment() {
        this.scaledFloors++;
    }

}
