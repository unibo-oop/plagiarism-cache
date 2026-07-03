package it.lttply.model.builders;

import java.util.Set;

import it.lttply.model.domain.MediaContainer;
import it.lttply.model.domain.MultiMediaContainer;

/**
 * It provides an interface to create a builder of generic
 * {@link MultiMediaContainer} containers.
 * 
 * @param <T>
 *            the builder
 * @param <X>
 *            the type to build
 * 
 * @param <Y>
 *            the type of the elements to store into the
 *            {@link MultiMediaContainer}
 */
public interface MultiMediaBuilder<T, X, Y extends MediaContainer> extends MediaBuilder<T, X> {
    /**
     * Sets the elements of the {@link MultiMediaContainer}.
     * 
     * @param elements
     *            the <Y> elements to be stored, if none <code>null</code> is
     *            preferred
     * @return the <T> builder
     */
    T elements(Set<Y> elements);
}
