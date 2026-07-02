package it.unibo.javajump.model.entities.platforms;

/**
 * The interface Breakable platform.
 */
public interface BreakablePlatform extends Platform {

    /**
     * Flag to check if the platform is broken.
     *
     * @return true if is broken, false otherwise
     */
    boolean isBroken();

    /**
     * Breaks the platform.
     */
    void breakPlatform();

    /**
     * Sets the breaking of the platform as finished.
     */
    void setFinished();

    /**
     * Flag to check if the platform is finished.
     *
     * @return true if is finished, false otherwise
     */
    boolean isFinished();
}
