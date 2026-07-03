package it.lttply.model.builders;

/**
 * It provides an interface to create a builder of generic video containers.
 * 
 * @param <T>
 *            the builder
 * @param <X>
 *            the type to build
 */
public interface VideoBuilder<T, X> extends SingleMediaBuilder<T, X> {
    /**
     * Sets the duration of the video.
     * 
     * @param duration
     *            the video's duration in seconds
     * @return the <T> builder
     */
    T duration(int duration);
}
