package it.lttply.model.domain;

/**
 * It represents the base to build a container of a generic video.
 */
public interface Video extends SingleMediaContainer {
    /**
     * @return the duration (in seconds) of the video.
     */
    int getDuration();
}
