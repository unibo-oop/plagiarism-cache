package it.lttply.model.domain;

import java.util.Optional;

/**
 * It is the base to build a image that can be used for different goals.
 */
public interface Picture extends SingleMediaContainer {
    /**
     * @return the {@link PictureFormat} of the image
     */
    PictureFormat getFormat();

    /**
     * @return the author of the image
     */
    Optional<String> getAuthor();
}
