package ballblast.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import ballblast.controller.sound.Sound;
import ballblast.model.Model;
import ballblast.model.inputs.InputManager.PlayerTag;
import ballblast.model.inputs.InputType;
import ballblast.model.levels.GameStatus;
import ballblast.view.View;

/**
 * Represents a generic {@link GameLoop} that starts in a new thread.
 */
public abstract class AbstractGameLoop extends Thread implements GameLoop {
    private static final double MS_TO_S = 0.001;

    private final List<GameLoopObserver> observers = new ArrayList<GameLoopObserver>();
    private final ListMultimap<PlayerTag, InputType> inputs;
    private final View view;
    private final Model model;
    private final long frameRate;
    private boolean stopped;
    private boolean paused;

    /**
     * Creates a new game loop instance.
     * 
     * @param view      The view to render on each frame.
     * @param model     The model to update the world on each frame.
     * @param frameRate The refresh rate of the loop.
     */
    public AbstractGameLoop(final Model model, final View view, final int frameRate) {
        super();
        this.setName("Game Loop");
        this.setDaemon(true);
        this.view = view;
        this.model = model;
        this.frameRate = (long) (1 / (frameRate * MS_TO_S));
        this.inputs = MultimapBuilder.treeKeys().arrayListValues().build();
    }

    @Override
    public final void run() {
        this.stopped = false;
        long lastTime = System.currentTimeMillis();
        while (!this.isStopped()) {
            final long current = System.currentTimeMillis();
            this.processInput();
            if (!this.isPaused()) {
                final long elapsed = current - lastTime;
                this.updateGame(elapsed * MS_TO_S);
                this.render();
                this.processSounds(this.model);
                // In order to lock the frame rate.
                this.waitForNextFrame(current);
            }
            lastTime = current;
        }
        this.view.setGameOver(true);
        this.updateLeaderboard();
        this.gameOverSound();
    }

    @Override
    public final synchronized void stopLoop() {
        this.stopped = true;
        this.interrupt();
    }

    @Override
    public final synchronized void pause() {
        this.paused = true;
    }

    @Override
    public final synchronized void resumeLoop() {
        this.paused = false;
    }

    @Override
    public final synchronized void addInput(final PlayerTag tag, final InputType input) {
        this.inputs.put(tag, input);
    }

    @Override
    public final void addObserver(final GameLoopObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public final void updateLeaderboard() {
        this.observers.forEach(GameLoopObserver::updateLeaderboard);
    }

    /**
     * Stops the theme.
     */
    protected void gameOverSound() {
        Sound.THEME.stopSound();
    }

    /**
     * Processes the sound effects.
     * 
     * @param model The {@link Model}.
     */
    protected abstract void processSounds(Model model);

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < this.frameRate) {
            try {
                Thread.sleep(this.frameRate - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void processInput() {
        this.inputs.keySet().forEach(k -> this.model.resolveInputs(k, ImmutableList.copyOf(this.inputs.get(k))));
        this.inputs.clear();
    }

    private boolean isStopped() {
        return this.stopped || model.getGameStatus().equals(GameStatus.OVER);
    }

    private boolean isPaused() {
        return this.paused || model.getGameStatus().equals(GameStatus.PAUSE);
    }

    private void updateGame(final double elapsed) {
        this.model.update(elapsed);
    }

    private void render() {
        this.view.render();
    }

}
