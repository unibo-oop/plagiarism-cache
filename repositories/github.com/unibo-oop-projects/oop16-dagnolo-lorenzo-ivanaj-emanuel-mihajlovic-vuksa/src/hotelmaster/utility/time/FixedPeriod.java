package hotelmaster.utility.time;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

/**
 * A period of time with fixed beginning and end. The comparison between two
 * instances should prioritize the beginning date.
 */
public interface FixedPeriod extends Comparable<FixedPeriod> {

    /**
     * Get the beginning date of the period.
     * 
     * @return the date
     */
    LocalDate getBeginning();

    /**
     * Get the end date of the period.
     * 
     * @return the date
     */
    LocalDate getEnd();

    /**
     * Get the duration in days/months/years between the beginning and the end.
     * 
     * @return the period
     */
    Period getDuration();

    /**
     * Get the duration in days. This is a commodity method equivalent to
     * ChronoUnit.DAYS.between(instance.getBeginning(), instance.getEnd()).
     * 
     * @return the long value
     */
    long getTotalDays();

    /**
     * Checks whether this FixedPeriod instance overlaps with a given
     * FixedPeriod.
     * 
     * @param other
     *            the other FixedPeriod to be checked
     * 
     * @return the boolean
     */
    boolean overlaps(final FixedPeriod other);

    /**
     * Returns the intersection period with another FixedPeriod.
     * 
     * @param other
     *            the other FixedPeriod
     * @return the intersection, or {@link Optional#empty()} if it doesn't exist
     */
    Optional<FixedPeriod> getIntersectionWith(final FixedPeriod other);

    /**
     * Instantiate a new FixedPeriod from a beginning date and an end date.
     * Beginning date must be before the end date, and the dates must be
     * different.
     * 
     * @param beginningInclusive
     *            the beginning of the FixedPeriod
     * @param endExclusive
     *            the end of the FixedPeriod
     * @return the new instanced FixedPeriod
     * @throws IllegalArgumentException
     *             the beginning date is after the end date, and the dates must
     *             be different
     */
    static FixedPeriod of(final LocalDate beginningInclusive, final LocalDate endExclusive)
            throws IllegalArgumentException {
        return new FixedPeriodImpl(beginningInclusive, endExclusive);
    }
}
