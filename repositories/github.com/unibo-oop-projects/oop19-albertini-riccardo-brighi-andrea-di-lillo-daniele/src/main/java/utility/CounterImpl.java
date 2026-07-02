package utility;

public class CounterImpl implements Counter {

    private final int start;
    private int value;

    /**
     * create a counter that start from 0
     */
    public CounterImpl() {
        this(0);
    }

    /**
     * @param start the number to start counting
     *              <p>
     *              create a counter that start from @param start
     *              </p>
     */

    public CounterImpl(final int start) {
        this.start = start;
        this.resetImpl();
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void increment() {
        this.value++;
    }

    @Override
    public void reset() {
        this.resetImpl();
    }

    private void resetImpl() {
        this.value = this.start;
    }
}
