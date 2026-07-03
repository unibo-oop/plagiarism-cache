package it.lttply.model.domain;

/**
 * It is a particular {@link MediaContainer} that is able to store none, one or
 * more {@link Episode}.
 */
public interface Season extends MultiMediaContainer<Episode> {
    /**
     * @return the progressive number of the season.
     */
    int getProgressiveNumber();
}
