package it.unibo.oop.manpac.view.screens.menu;

import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.Gdx;

import it.unibo.oop.manpac.controller.ControllerObserver;
import it.unibo.oop.manpac.main.ManpacGame;
import it.unibo.oop.manpac.utils.SoundManager;
import it.unibo.oop.manpac.utils.SoundManager.SoundName;
import it.unibo.oop.manpac.view.screens.game.GameView;

/**
 * Implementation of ScreenObserver and ScreensMessenger interfaces for screen
 * setting management.
 */
public final class ScreenObserverImpl implements ScreenObserver, ScreensMessenger {

    private static final int SOUND_VOLUME_INTRO = 8;
    private static final float SOUND_VELOCITY_INTRO = 1.5f;

    private final ManpacGame game;
    private Optional<ControllerObserver> controller;
    private final ObservableScreen mainMenuScreen;
    private final ObservableScreen settingsScreen;
    private ObservableScreen gameOverScreen;
    private ObservableScreen gameView;

    /**
     * Constructor for ScreenObserverImpl.
     * 
     * @param game The game application
     */
    public ScreenObserverImpl(final ManpacGame game) {
        this.game = game;
        this.settingsScreen = new SettingsScreen();
        this.settingsScreen.setObserver(this);

        this.mainMenuScreen = new MainMenuScreen();
        this.mainMenuScreen.setObserver(this);

        this.controller = Optional.empty();

        SoundManager.getSoundManager().setVolume(SOUND_VOLUME_INTRO).setVelocity(SOUND_VELOCITY_INTRO)
                    .play(SoundName.START_APP);
    }

    @Override
    public void setMainMenuScreen() {
        this.controllerOrThrows().setCurrentObservable((ScreensMessenger) this.mainMenuScreen);
        startScreen(this.mainMenuScreen);
    }

    @Override
    public void setGameOverScreen(final boolean win, final int currentScore, final int currentHighScore) {
        this.gameOverScreen = new GameOverScreen(this.controllerOrThrows(), win, currentScore, currentHighScore);
        this.gameOverScreen.setObserver(this);
        startScreen(this.gameOverScreen);
    }

    @Override
    public void setSettingsScreen() {
        this.controllerOrThrows().setCurrentObservable((ScreensMessenger) this.settingsScreen);
        startScreen(this.settingsScreen);
    }

    @Override
    public void setGameScreen() {
        this.gameView = new GameView();
        this.gameView.setObserver(this);
        this.controllerOrThrows().setCurrentObservable((ScreensMessenger)  this.gameView);
        startScreen(this.gameView);
    }

    @Override
    public void closeGame() {
        Gdx.app.exit();
    }

    @Override
    public void startControllerObserving(final ControllerObserver controller) {
        if (Objects.isNull(controller)) {
            throw new IllegalArgumentException("The controller passed as input in startControllerObserving is null!");
        }
        this.controller = Optional.ofNullable(controller);
    }

    @Override
    public void stopControllerObserving() {
        // In this "screen" the detach is not implemented: it is the screen manager that
        // notifies the controller of the change of the active screen, so it must always
        // be able to notify the controller.
    }

    /**
     * It is used to check if the controller has been set; in the positive case it
     * returns it, in the negative case the IllegalStateException (with message) is
     * thrown for the absence of it.
     */
    private ControllerObserver controllerOrThrows() throws IllegalStateException {
        return this.controller.orElseThrow(
                () -> new IllegalStateException(ControllerObserver.ERROR_INITIALIZE + " on " + this.getClass()));
    }

    private void startScreen(final ObservableScreen obs) {
        if (!Objects.isNull(obs)) {
            Gdx.input.setInputProcessor(obs.getStage());
            this.game.setScreen(obs);
        }
    }

    @Override
    public void dispose() {
        this.mainMenuScreen.dispose();
        this.settingsScreen.dispose();
        if (Objects.nonNull(this.gameOverScreen)) {
            this.gameOverScreen.dispose();
        }
        if (Objects.nonNull(this.gameView)) {
            this.gameView.dispose();
        }
    }

}
