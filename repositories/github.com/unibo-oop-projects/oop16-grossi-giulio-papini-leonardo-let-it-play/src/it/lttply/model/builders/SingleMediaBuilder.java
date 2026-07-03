package it.lttply.model.builders;

import it.lttply.model.domain.SingleMediaContainer;

/**
 * It provides an interface to create a builder of generic
 * {@link SingleMediaContainer} containers.
 * 
 * @param <T>
 *            the builder
 * @param <X>
 *            the type to build
 */
public interface SingleMediaBuilder<T, X> extends MediaBuilder<T, X> {

    /**
     * Sets the user rating of the media file.
     * 
     * @param rating
     *            the media's rating, if none <code>null</code> is preferred
     * @return the <T> builder
     */
    T rating(Double rating);

}
