package it.tbt.engine.impl;

import it.tbt.engine.api.GameLoop;
import javafx.animation.AnimationTimer;

/**
 * GameLoopManager implemented with an JavaFx AnimationTimer.
 */
public class AnimationTimerGameLoopManager {
    private final GameLoop loop;
    private final AnimationTimer gameLoopAnimationTimer;

    /**
     * @param gameLoop that the Manger will manage.
     */
    public AnimationTimerGameLoopManager(final GameLoop gameLoop) {
        this.loop = gameLoop;
        this.gameLoopAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                loop.loop();
            }
        };
    }

    /**
     * Starts the looping.
     */
    public void startLoop() {
        gameLoopAnimationTimer.start();
    }
}
