package gameengine;

import java.util.Optional;

import virtualworld.Stage;

public interface GameEngine {

    /**
     * Starts the Game Engine.
     * Could throw IllegalThreadStateException if already started.
     * @param stage set the running stage
     * @throw IllegalThreadStateException
     * */
    void start(Stage stage);

    /**
     * Stops the Game Engine.
     * */
    void stop();

    /**
     * @return return if the engine is been started
     * */
    boolean isStarted();

    /**
     * @return if the engine is been paused
     */
    boolean isPaused();

    /**
     * Pause the engine.
     */
    void pause();

    /**
     * Resume the engine.
     */
    void resume();

    /**
     * Return the last stage states.
     * @return the Optional Containing the last stage
     */
    Optional<Stage> getStage();
}
