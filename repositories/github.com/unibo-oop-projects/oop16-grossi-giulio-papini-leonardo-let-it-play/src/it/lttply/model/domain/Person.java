package it.lttply.model.domain;

import java.util.Optional;

/**
 * Represents the base to build an object (abstraction) which represents a human
 * being.
 */
public interface Person {
    /**
     * @return the identifier of the {@link Person}
     */
    String getID();

    /**
     * @return the name of the human being.
     */
    String getName();

    /**
     * @return an {@link Optional} profile image of the human being.
     */
    Optional<Picture> getPicture();
}
