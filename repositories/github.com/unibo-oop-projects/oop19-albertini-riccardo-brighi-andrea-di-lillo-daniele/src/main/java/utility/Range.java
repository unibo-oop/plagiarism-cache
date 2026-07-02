package utility;

public interface Range extends Iterable<Integer> {

    /**
     * @return the start of the range
     */
    int getStart();

    /**
     * @return the limit of the range
     */

    int getLimit();

    /**
     * @return the step of the range
     */

    int getStep();

}
