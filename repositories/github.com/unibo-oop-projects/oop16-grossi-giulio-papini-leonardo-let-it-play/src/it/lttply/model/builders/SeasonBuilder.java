package it.lttply.model.builders;

import it.lttply.model.domain.Episode;
import it.lttply.model.domain.Season;

/**
 * It provides an half-built interface to create a builder of {@link Season}.
 * 
 * @param <T>
 *            the builder
 */
public interface SeasonBuilder<T> extends MultiMediaBuilder<T, Season, Episode> {
    /**
     * Sets the progressive number of the season.
     * 
     * @param progressiveNumber
     *            the season's progressive number
     * @return the <T> builder
     */
    T progressiveNumber(int progressiveNumber);

    /**
     * Sets the full size of the season.
     * 
     * @param size
     *            the season's size (sum of the sizes of the {@link Episode})
     * @return the <T> builder
     */
    T size(int size);
}
