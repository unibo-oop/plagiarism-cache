package bubbleshooter.controller.engine;

/**
 * 
 * Class used to decorate the {@link GameLoop} and add features to it.
 *
 */
public abstract class GameEngineDecorator implements GameLoop {

    /**
    * The {@link BasicGameLoop} used by the Decorator.
    */
    private final GameLoop gameLoop;

    /**
     * @param gameLoop The {@link BasicGameLoop} used by the Decorator.
     */
    public GameEngineDecorator(final GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    /**
     * The method to Start the loop of the {@link BasicGameLoop}.
     */
    @Override
    public void startLoop() {
        this.gameLoop.startLoop();
    }

    /**
     * The method to Stop the loop of the {@link BasicGameLoop}.
     */
    @Override
    public void stopLoop() {
        this.gameLoop.stopLoop();
    }

    /**
     * The method to pause the loop of the {@link BasicGameLoop}.
     */
    @Override
    public void pauseLoop() {
        this.gameLoop.pauseLoop();
    }

    /**
     * The method to resume the loop of the {@link BasicGameLoop}.
     */
    @Override
    public void resumeLoop() {
        this.gameLoop.resumeLoop();
    }

    /**
     * @return if the {@link BasicGameLoop} is running or not.
     */
    @Override
    public final boolean isStopped() {
        return this.gameLoop.isStopped();
    }

    /**
     * @return if the {@link BasicGameLoop} is paused or not.
     */
    @Override
    public final boolean isPaused() {
        return this.gameLoop.isPaused();
    }
}
