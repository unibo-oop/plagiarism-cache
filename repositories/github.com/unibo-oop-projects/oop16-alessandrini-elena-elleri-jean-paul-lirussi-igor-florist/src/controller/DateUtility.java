package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * A final class with some utility methods working with @LocalDate.
 *
 */
public final class DateUtility {
    private DateUtility() {
    }
    /**
     * Convert from @Date to @LocalDate.
     * 
     * @param d
     *            the date to convert
     * @throws IllegalArgumentException
     *             if argument is @null
     * @return the corresponding @LocalDate
     */
    public static LocalDate convert(final Date d) {
        if (Objects.isNull(d)) {
            throw new IllegalArgumentException("Cannot have null values");
        }
        return d.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
