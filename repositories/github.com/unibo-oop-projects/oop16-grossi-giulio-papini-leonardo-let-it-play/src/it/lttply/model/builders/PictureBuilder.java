package it.lttply.model.builders;

import it.lttply.model.domain.Picture;
import it.lttply.model.domain.PictureFormat;

/**
 * It provides an half-built interface to create a builder of {@link Picture}.
 * 
 * @param <T>
 *            the builder
 */
public interface PictureBuilder<T> extends SingleMediaBuilder<T, Picture> {
    /**
     * Sets the format of the picture.
     * 
     * @param format
     *            the picture's format
     * @return the <T> builder
     */
    T format(PictureFormat format);

    /**
     * Sets the author of the picture.
     * 
     * @param author
     *            the picture's author, if none <code>null</code> is preferred
     * @return the <T> builder
     */
    T author(String author);
}
