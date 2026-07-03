package it.lttply.model.builders;

import it.lttply.model.domain.Episode;

/**
 * It provides an half-built interface to create a builder of {@link Episode}.
 * 
 * @param <T>
 *            the builder
 */
public interface EpisodeBuilder<T> extends VideoBuilder<T, Episode> {
    /**
     * Sets the progressive number of the episode.
     * 
     * @param progressiveNumber
     *            the episode's progressive number
     * @return the <T> builder
     */
    T progressiveNumber(int progressiveNumber);
}
