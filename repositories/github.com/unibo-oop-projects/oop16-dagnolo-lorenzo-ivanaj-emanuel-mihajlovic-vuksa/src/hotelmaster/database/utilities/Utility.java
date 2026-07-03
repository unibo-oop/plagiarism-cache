package hotelmaster.database.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * A generic class for utilities.
 * 
 * @param <T>
 */
public abstract class Utility<T> {

    /**
     * 
     */
    public Utility() { }

    /**
     * Get all the available instances of T.
     * 
     * @return a set of {@link T}
     */
    public abstract Set<T> getAll();

    /**
     * Converts a string to a LocalDate, according to a format pattern.
     * 
     * @param date
     *            the date to be converted
     * @return the {@link LocalDate}
     */
    static LocalDate toLocalDate(final String date) {
        final LocalDate dt = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dt;
    }
}
