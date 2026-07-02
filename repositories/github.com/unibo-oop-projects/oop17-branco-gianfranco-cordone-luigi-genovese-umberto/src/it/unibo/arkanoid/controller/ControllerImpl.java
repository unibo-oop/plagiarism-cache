package it.unibo.arkanoid.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

import it.unibo.arkanoid.engine.GameLoop;
import it.unibo.arkanoid.engine.GameLoopImpl;
import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.model.LevelState;
import it.unibo.arkanoid.model.Model;
import it.unibo.arkanoid.model.ModelImpl;
import it.unibo.arkanoid.view.SubView;
import it.unibo.arkanoid.view.View;

/**
 * The implementation of Controller interface.
 *
 */
public class ControllerImpl implements Controller {

    private static final int LEVELS = 5;

    private View view;
    private Model model;
    private GameLoop gameLoop;
    private Iterator<Level> levelIterator;
    private State currentState;
    private final ScoreLoader scoreLoader;

    /**
     * 
     */
    public ControllerImpl() {
        this.scoreLoader = new FileScoreLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void next() {
        currentState.updateState(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore(final String username) {
        try {
            this.scoreLoader.saveScore(username, this.model.getScore());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScoreList getScore() {
        try {
            return this.scoreLoader.loadScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final View view) {
        this.view = view;
        this.setState(GameState.INITIALIZE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

    private void startNextSession() {
        this.model = new ModelImpl();
        this.levelIterator = new IteratorLevel(new FileLevelGenerator(), () -> this.model.getLevelBuilder(),
                LEVELS);
        this.loadNextLevel();
    }

    private void onLevelFinished(final LevelState levelState) {
        this.gameLoop.stopGame();
        if (levelState == LevelState.LOSE) {
            if (this.model.getPlayerLife() == 0) {
                this.setState(GameState.GAME_OVER);
            } else {
                this.gameLoop = new GameLoopImpl(this.view, this.model);
                this.model.resetPosition();
                this.setState(GameState.READY);
            }
        } else {
            this.setState(GameState.WIN);
        }
    }

    private void loadNextLevel() {
        if (this.levelIterator.hasNext()) {
            this.model.setLevel(this.levelIterator.next());
            this.gameLoop = new GameLoopImpl(this.view, this.model);
            this.setState(GameState.READY);
            this.model.getCurrentLevel().getLevelFinishedEvent().register(this::onLevelFinished);
        } else {
            this.setState(GameState.GAME_FINISHED);
        }
    }

    private void setState(final GameState gameState) {
        switch (gameState) {
        case GAME_OVER:
            this.currentState = new StateGameOver(this::setState);
            this.view.setSubView(SubView.GAME_OVER);
            break;
        case GAME_FINISHED:
            this.currentState = new StateGameFinished(this::setState);
            this.view.setSubView(SubView.GAME_FINISHED);
            break;
        case INITIALIZE:
            this.currentState = new StateInitialize(this::setState, () -> this.startNextSession());
            this.view.setSubView(SubView.HOME);
            break;
        case READY:
            this.currentState = new StateReady(this::setState);
            this.view.setSubView(SubView.IN_GAME);
            this.view.render(this.model.getCurrentLevel().getAllSubjects().collect(Collectors.toList()));
            break;
        case RUNNING:
            this.currentState = new StateRunning(this::setState);
            break;
        case WIN:
            this.currentState = new StateWin(this::setState, () -> this.loadNextLevel());
            this.view.setSubView(SubView.LEVEL_END);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getGameState() {
        return this.currentState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStats getGameStats() {
        return new GameStats(this.model.getGameWorldWidth(), this.model.getGameWorldHeight(),
                () -> this.model.getScore(), () -> this.model.getPlayerLife());
    }
}
