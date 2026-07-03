package it.lttply.model.domain;

import java.util.Optional;

/**
 * It represents the base to build a particular {@link MediaContainer} that is
 * able to store one and only one multimedial file.
 */
public interface SingleMediaContainer extends MediaContainer {

    /**
     * @return an {@link Optional} user rating of the multimedial file.
     */
    Optional<Double> getRating();
}
