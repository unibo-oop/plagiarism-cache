package it.unibo.controller;

import it.unibo.view.GameView;
import it.unibo.controller.interfaces.GameLoopInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.PauseModel;
import it.unibo.model.StatusModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.Timer;

/**
 * The GameLoop class manages the main game loop, handling game updates and rendering.
 * It uses a Swing Timer to execute game logic at a consistent frame rate.
 */
public class GameLoop implements ActionListener, GameLoopInterface {
    
    /** The view responsible for rendering the game. */
    private GameView gameView;
    
    /** Model that manages the game's pause state. */
    private final PauseModel pauseModel;
    
    /** Model that tracks the game's status, including whether it has ended. */
    private final StatusModel statusModel;
    
    /** Timer responsible for executing the game loop at a fixed interval. */
    private Timer gameTimer;
    
    /** Minimum delay in milliseconds between frames, corresponding to 30 FPS. */
    private static final long MIN_DELAY = 1000 / 30;
    
    /** Stores the timestamp of the last frame update. */
    private long lastTime;
    
    /** A set of listeners that receive tick events for game updates. */
    private final Set<TickListenerInterface> tickListeners;

    /**
     * Constructs a GameLoop instance.
     * 
     * @param gameView The view used for rendering the game.
     * @param pauseModel The model managing game pauses.
     * @param statusModel The model tracking game status.
     * @param tickListeners A set of listeners notified on each game tick.
     */
    public GameLoop(GameView gameView, PauseModel pauseModel, StatusModel statusModel,
                    Set<TickListenerInterface> tickListeners) {
        this.gameView = gameView;
        this.pauseModel = pauseModel;
        this.statusModel = statusModel;
        this.tickListeners = tickListeners;
        this.gameTimer = new Timer(1, this);
    }

    /**
     * Updates the GameView instance.
     * 
     * @param gameView The new GameView instance to be set.
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Starts the game loop by initializing the timer and setting the last update time.
     */
    public void startGame() {
        this.lastTime = System.currentTimeMillis();
        this.gameTimer.start();
    }

    /**
     * Stops the game loop by stopping the timer.
     */
    public void stopGame() {
        this.gameTimer.stop();
    }

    /**
     * Adds a new tick listener that will receive game update notifications.
     * 
     * @param tickListener The tick listener to be added.
     */
    public void addTickListener(TickListenerInterface tickListener) {
        this.tickListeners.add(tickListener);
    }

    /**
     * Removes a tick listener from the set of listeners.
     * 
     * @param tickListener The tick listener to be removed.
     */
    public void removeTickListener(TickListenerInterface tickListener) {
        this.tickListeners.remove(tickListener);
    }

    /**
     * Updates the game state by notifying tick listeners, unless the game is paused or ended.
     */
    private void update() {
        if (pauseModel.getPause()) {
            return;
        }
        if (statusModel.isGameEnded()) {
            return;
        }
        for (TickListenerInterface tickListener : tickListeners) {
            tickListener.onTick();
        }
    }

    /**
     * Renders the game by repainting the GameView, if available.
     */
    private void render() {
        if (this.gameView == null) {
            return;
        }
        gameView.repaint();
    }

    /**
     * Handles the action event triggered by the Timer. It updates and renders the game
     * at the specified frame rate.
     * 
     * @param e The action event triggered by the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= MIN_DELAY) {
            lastTime = currentTime;
            update();
        }
        render();
    }
}
