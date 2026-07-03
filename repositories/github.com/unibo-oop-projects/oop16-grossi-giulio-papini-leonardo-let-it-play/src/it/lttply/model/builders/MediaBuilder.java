package it.lttply.model.builders;

import java.util.List;
import java.util.Map;

import it.lttply.model.domain.MediaContainer;
import it.lttply.model.domain.Picture;

/**
 * It provides an interface to create a builder of generic
 * {@link MediaContainer}.
 * 
 * @param <T>
 *            the builder
 * @param <X>
 *            the type to build
 */
public interface MediaBuilder<T, X> extends FileBuilder<T, X> {
    /**
     * Sets the id of the media file.
     * 
     * @param id
     *            the media file's id
     * @return the <T> builder
     */
    T id(String id);

    /**
     * Sets the {@link List} of tag of the media file.
     * 
     * @param tags
     *            the media file's {@link List} of tags
     * @return the <T> builder
     */
    T tags(Map<String, String> tags);

    /**
     * Sets the overview of the media file.
     * 
     * @param overview
     *            the media file's overview, if none <code>null</code> is
     *            preferred
     * @return the <T> builder
     */
    T overview(String overview);

    /**
     * Sets the poster of the media file.
     * 
     * @param poster
     *            the media file's poster, if none <code>null</code> is
     *            preferred
     * @return the <T> builder
     */
    T poster(Picture poster);
}
