package it.lttply.model;

import it.lttply.model.domain.MediaContainer;

/**
 * This interface permits to build a model which is able to manage only one
 * {@link MediaContainer} file.
 * 
 * @param <T>
 *            the type of the media stored
 */
public interface SingleMediaModel<T extends MediaContainer> extends Model {
    /**
     * This method permits to get the {@link MediaContainer} stored.
     * 
     * @return the <T> {@link MediaContainer}
     */
    T get();
}
