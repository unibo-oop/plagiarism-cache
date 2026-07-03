package it.lttply.model.domain;

import java.util.Optional;
import java.util.Set;

/**
 * Represents the base of a particular {@link MediaContainer} which is able to
 * contain others {@link MediaContainer}.
 * 
 * @param <T>
 *            extends {@link MediaContainer}
 */
public interface MultiMediaContainer<T extends MediaContainer> extends MediaContainer {
    /**
     * @return an {@link Optional} {@link Set} of {@link MediaContainer}. See
     *         {@link Season} or {@link TVSerie} for details.
     */
    Optional<Set<T>> getElements();
}
