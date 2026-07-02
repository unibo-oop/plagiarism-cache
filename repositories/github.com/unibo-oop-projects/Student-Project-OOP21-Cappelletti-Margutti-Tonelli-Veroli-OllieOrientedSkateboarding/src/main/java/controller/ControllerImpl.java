package controller;

import input.InputObserver;
import model.Model;
import model.ModelImpl;
import sound.Sound;
import sound.SoundFactory;
import sound.SoundFactoryImpl;
import view.View;

/**
 * 
 * Implementation of {@link Controller}.
 *
 */
public class ControllerImpl implements Controller {

    private Model model;
    private final View view;
    private final AnimationTimerImpl timer;
    private final InputObserver obs;
    private final SoundFactory soundFactory;
    private final Sound soundtrack;
    private final GameInfo gameInfo;

    /**
     * Creates a new ControllerImpl and initializes a new AnimationTimerImpl.
     * @param view the {@link View} where the application starts.
     * @param obs the {@link InputObserver}.
     */
    public ControllerImpl(final View view, final InputObserver obs) {
        super();
        this.view = view;
        this.obs = obs;
        this.timer = new AnimationTimerImpl(this);
        this.soundFactory = new SoundFactoryImpl();
        this.soundtrack = this.soundFactory.createGameSoundtrack();
        this.gameInfo = new GameInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
        this.model = new ModelImpl(this.gameInfo.getWidth(), this.gameInfo.getHeight(), this.soundFactory);
        this.view.game();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        this.obs.getCommands().forEach(cmd -> {
            cmd.execute();
        });
        this.obs.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (!this.model.getGameState().isGameOver()) {
            if (!this.soundtrack.isPlaying()) {
                this.soundtrack.play();
            }
            this.model.update();
        } else {
            this.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        if (!this.model.getGameState().isGameOver()) {
            this.view.render();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.soundtrack.play();
        this.setup();
        this.timer.start();
        this.timer.setRunning(true);
        this.model.getStatisticsUpdater().start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.soundtrack.stop();
        this.soundFactory.createGameOverSound().play();
        this.timer.stop();
        this.timer.setRunning(false);
        this.model.getStatisticsUpdater().stop();
        this.view.gameOver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOnClose() {
        if (this.timer.isRunning()) {
            this.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.gameInfo.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.gameInfo.getHeight();
    }

}
