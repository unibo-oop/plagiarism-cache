package utility;

public interface RangeFactory {

    /**
     * @param limit the limit of the range
     * @return a range with step 1, start 0 and limit @param limit
     */

    Range standardRange(int limit);

    /**
     * @param limit the limit of the range
     * @param step  the step of the range
     * @return a range with step @param step, start 0 and limit @param limit
     */

    Range rangeWithStep(int limit, int step);

    /**
     * @param start the start of the range
     * @param limit the limit of the range
     * @return a range with step 1, start @param start and limit @param limit
     */

    Range standardRangeFrom(int start, int limit);

    /**
     * @param start the start of the range
     * @param limit the limit of the range
     * @param step  the step of the range
     * @return a range with step @param step, start @param start and limit @param
     * limit
     */

    Range rangeWithStepFrom(int start, int limit, int step);
}
