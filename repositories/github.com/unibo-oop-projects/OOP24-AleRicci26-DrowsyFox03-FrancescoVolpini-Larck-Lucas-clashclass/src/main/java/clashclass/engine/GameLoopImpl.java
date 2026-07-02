package clashclass.engine;

import clashclass.view.graphic.Graphic;
import clashclass.view.graphic.components.AbstractGraphicComponent;


import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a GameLoop implementation.
 */
public class GameLoopImpl implements GameLoop {
    private static final int ONE_MILLION = 1_000_000;
    private static final int ONE_BILLION = 1_000_000_000;
    private final float secondsBetweenTwoFrames;
    private long lastTime;
    private float deltaTime;
    private long sleepTime;
    private GameScene currentScene;
    private final Optional<Graphic> graphics;

    /**
     * Constructs the GameLoop.
     *
     * @param graphic the graphics
     * @param fps the desired number of frames per seconds
     */
    public GameLoopImpl(final Optional<Graphic> graphic, final float fps) {
        this.graphics = graphic;
        this.secondsBetweenTwoFrames = 1.0f / fps;
        this.lastTime = 0;
        this.deltaTime = 0.0f;
        this.sleepTime = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void run() {
        this.lastTime = System.nanoTime();

        while (!Thread.currentThread().isInterrupted()) {
            this.calculateDeltaTime();

            this.updateGameObjects();
            this.drawGameObjects();
            this.checkForDestroyedGameObjects();

            this.calculateSleepTime();

            if (this.sleepTime > 0) {
                try {
                    Thread.currentThread().sleep(sleepTime / ONE_MILLION,
                            (int) (sleepTime % ONE_MILLION));
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

        private void updateGameObjects() {
        this.currentScene.updateGameObjects(deltaTime);
    }

        private void drawGameObjects() {
        final var gameObjectsCopy = this.currentScene.getGameObjectsCopy();
        this.graphics.ifPresent(graphic -> graphic
                .render(gameObjectsCopy.stream()
                        .flatMap(x -> x
                                .getComponentsOfType(AbstractGraphicComponent.class).stream())
                        .collect(Collectors.toUnmodifiableSet())));
    }

        private void checkForDestroyedGameObjects() {
        this.currentScene.checkForDestroyedGameObjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setCurrentScene(final GameScene scene) {
        this.currentScene = scene;
    }

        private void calculateDeltaTime() {
        final long currentTime = System.nanoTime();
        this.deltaTime = ((float) (currentTime - lastTime)) / ONE_BILLION;
        lastTime = currentTime;
    }

        private void calculateSleepTime() {
        final long frameTimeNano = (long) (secondsBetweenTwoFrames * ONE_BILLION);
        final long elapsedTime = System.nanoTime() - this.lastTime;
        this.sleepTime = frameTimeNano - elapsedTime;
    }
}
