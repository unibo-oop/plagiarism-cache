package utility;

public interface Counter {

    /**
     * @return the actual value of the counter
     */
    int getValue();

    /**
     * increment of one unit the counter
     */

    void increment();

    /**
     * reset the counter to the start value
     */

    void reset();

}
