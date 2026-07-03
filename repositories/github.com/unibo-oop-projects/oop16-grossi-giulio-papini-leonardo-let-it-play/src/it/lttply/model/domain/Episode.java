package it.lttply.model.domain;

/**
 * This interface provides the base to build an episode of a {@link TVSerie}.
 */
public interface Episode extends Video {
    /**
     * 
     * @return the progressive number of the Episode.
     */
    int getProgressiveNumber();
}
