package it.lttply.model.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * It is a particular {@link MediaContainer} that is able to store none, one or
 * more {@link Season}.
 */
public interface TVSerie extends MultiMediaContainer<Season> {

    /**
     * @return an {@link Optional} {@link List} of countries where the tv serie
     *         was shot.
     */
    Optional<Set<String>> getCountries();

    /**
     * @return an {@link Optional} user rating of the multimedial file.
     */
    Optional<Double> getRating();

    /**
     * @return an {@link Optional} {@link Picture} which represents the backdrop
     *         of a tv serie.
     */
    Optional<Picture> getBackdrop();

    /**
     * @return an {@link Optional} object that contains the credits for this tv
     *         serie.
     */
    Optional<Credits> getCredits();
}
