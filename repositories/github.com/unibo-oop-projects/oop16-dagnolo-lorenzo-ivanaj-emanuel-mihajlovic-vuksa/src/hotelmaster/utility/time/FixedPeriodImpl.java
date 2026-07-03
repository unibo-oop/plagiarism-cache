package hotelmaster.utility.time;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * A period of time with fixed beginning and end.
 */
public class FixedPeriodImpl implements FixedPeriod {

    private final LocalDate beginning;
    private final LocalDate end;

    FixedPeriodImpl(final LocalDate beginningInclusive, final LocalDate endExclusive) throws IllegalArgumentException {
        if (beginningInclusive.isAfter(endExclusive)) {
            throw new IllegalArgumentException("Starting date must be before ending date.");
        }
        if (beginningInclusive.isEqual(endExclusive)) {
            throw new IllegalArgumentException("The dates must be different.");
        }
        this.beginning = beginningInclusive;
        this.end = endExclusive;
    }

    @Override
    public LocalDate getBeginning() {
        return this.beginning;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public Period getDuration() {
        return Period.between(beginning, end);
    }

    @Override
    public long getTotalDays() {
        return ChronoUnit.DAYS.between(beginning, end);
    }

    @Override
    public boolean overlaps(final FixedPeriod other) {
        /*
         * By getting the earliest beginning and the latest end between the two
         * FixedPeriod objects, we can determine their total overlapping amount
         * of days. If the sum of the FixedPeriod individual objects' amount of
         * days is equal to or longer than this overlapped length, then the
         * FixedPeriod objects overlap with each other.
         */
        if (this.equals(other)) {
            return true;
        }
        final LocalDate earliestBeginning = this.getBeginning().isBefore(other.getBeginning()) ? this.getBeginning()
                : other.getBeginning();
        final LocalDate latestEnd = this.getEnd().isAfter(other.getEnd()) ? this.getEnd() : other.getEnd();
        return FixedPeriod.of(earliestBeginning, latestEnd).getTotalDays() <= this.getTotalDays()
                + other.getTotalDays();
    }

    @Override
    public Optional<FixedPeriod> getIntersectionWith(final FixedPeriod other) {
        if (!this.overlaps(other)) {
            return Optional.empty();
        }
        return Optional
                .of(FixedPeriod.of(this.beginning.isAfter(other.getBeginning()) ? this.beginning : other.getBeginning(),
                        this.end.isBefore(other.getEnd()) ? this.end : other.getEnd()));
    }

    @Override
    public int compareTo(final FixedPeriod other) {
        if (this.equals(other)) {
            return 0;
        }
        return !this.beginning.equals(other.getBeginning()) ? this.beginning.compareTo(other.getBeginning())
                : this.end.compareTo(other.getEnd());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beginning == null) ? 0 : beginning.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FixedPeriodImpl)) {
            return false;
        }
        final FixedPeriodImpl other = (FixedPeriodImpl) obj;
        if (beginning == null) {
            if (other.beginning != null) {
                return false;
            }
        } else if (!beginning.equals(other.beginning)) {
            return false;
        }
        if (end == null) {
            if (other.end != null) {
                return false;
            }
        } else if (!end.equals(other.end)) {
            return false;
        }
        return true;
    }

}
