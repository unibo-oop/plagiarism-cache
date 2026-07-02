package clashclass.engine;

import clashclass.ecs.GameObject;
import clashclass.view.graphic.Graphic;

import java.util.Optional;

/**
 * Represents a GameEngine implementation.
 */
public class GameEngineImpl implements GameEngine {
    private static final float FRAMES_PER_SECOND = 60.0f;
    private final GameScene currentScene;
    private final GameLoop gameLoop;
    private final Thread gameLoopThread;

    /**
     * Constructs the game engine.
     *
     * @param graphic the graphics
     */
    public GameEngineImpl(final Optional<Graphic> graphic) {
        this.currentScene = new GameSceneImpl();
        this.gameLoop = new GameLoopImpl(graphic, FRAMES_PER_SECOND);
        this.gameLoop.setCurrentScene(this.currentScene);
        this.gameLoopThread = new Thread(this.gameLoop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start() {
        this.gameLoopThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() {
        this.gameLoopThread.interrupt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addGameObject(final GameObject gameObject) {
        this.currentScene.addGameObject(gameObject);
    }
}
