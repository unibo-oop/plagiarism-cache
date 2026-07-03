package it.lttply.model.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * It represents the base to build a Movie.
 */
public interface Movie extends Video {
    /**
     * @return an {@link Optional} {@link List} of countries where the movie was
     *         shot.
     */
    Optional<Set<String>> getCountries();

    /**
     * @return an {@link Optional} {@link Picture} which represents the backdrop
     *         of a movie.
     */
    Optional<Picture> getBackdrop();

    /**
     * @return an {@link Optional} object that contains the credits for this
     *         movie.
     */
    Optional<Credits> getCredits();
}
