package it.lttply.model.builders;

import java.util.Set;

import it.lttply.model.domain.Credits;
import it.lttply.model.domain.Picture;
import it.lttply.model.domain.Season;
import it.lttply.model.domain.TVSerie;

/**
 * It provides an half-built interface to create a builder of {@link TVSerie}.
 * 
 * @param <T>
 *            the builder
 */
public interface TVSerieBuilder<T> extends MultiMediaBuilder<T, TVSerie, Season> {
    /**
     * Sets the countries of the {@link TVSerie}.
     * 
     * @param countries
     *            the tv serie's countries, if none <code>null</code> is
     *            preferred
     * @return the <T> builder
     */
    T countries(Set<String> countries);

    /**
     * Sets the rating of the tv serie.
     * 
     * @param rating
     *            the tv serie's user rating, if none <code>null</code> is
     *            preferred
     * @return the <T> builder
     */
    T rating(Double rating);

    /**
     * Sets the backdrop of the tv serie.
     * 
     * @param backdrop
     *            the tv serie's backdrop, if none <code>null</code> is
     *            preferred
     * @return the <T> builder
     */
    T backdrop(Picture backdrop);

    /**
     * Sets the credits for the tv serie.
     * 
     * @param credits
     *            the tv serie's credits, if none <code>null</code> is preferred
     * @return the <T> builder
     */
    T credits(Credits credits);
}
