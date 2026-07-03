package it.lttply.model.builders;

import java.util.List;
import java.util.Set;

import it.lttply.model.domain.Credits;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.Picture;

/**
 * It provides an half-built interface to create a builder of {@link Movie}.
 * 
 * @param <T>
 *            the builder
 */
public interface MovieBuilder<T> extends VideoBuilder<T, Movie> {
    /**
     * Sets the {@link List} of countries of the movie.
     * 
     * @param countries
     *            the movie's {@link List} of countries, if none
     *            <code>null</code> is preferred
     * @return the <T> builder
     */
    T countries(Set<String> countries);

    /**
     * Sets the backdrop {@link Picture} of the movie.
     * 
     * @param backdrop
     *            the movie's backdrop, if none <code>null</code> is preferred
     * @return the <T> builder
     */
    T backdrop(Picture backdrop);

    /**
     * Sets the credits for the movie.
     * 
     * @param credits
     *            the movie's credits, if none <code>null</code> is preferred
     * @return the <T> builder
     */
    T credits(Credits credits);
}
