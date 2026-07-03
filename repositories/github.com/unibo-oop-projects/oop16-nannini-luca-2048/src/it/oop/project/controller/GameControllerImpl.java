package it.oop.project.controller;

import it.oop.project.model.GameModel;
import it.oop.project.model.GameModelImpl;
import it.oop.project.sound.SoundPlayer;
import it.oop.project.sound.SoundPlayerImpl;
import it.oop.project.util.Direction;
import it.oop.project.view.GameView;
import it.oop.project.view.GameViewImpl;

import java.awt.Point;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class GameControllerImpl implements GameController {

    private static final int SIZE = 4;
    private final GameModel model;
    private final GameView view;
    private final SoundPlayer sounds;

    /**
     * Constructs a new game controller with classic view with specified size
     * board.
     * 
     * @param size
     *            board size.
     */
    public GameControllerImpl() {
        this.model = new GameModelImpl(SIZE);
        this.view = new GameViewImpl(SIZE);
        this.view.setController(this);
        this.view.setVolumeIcon(this.model.isVolumeOn());
        this.sounds = new SoundPlayerImpl();
        this.updateView();
        this.displayView();
    }

    private void updateView() {
        this.view.update(this.model.getBoard(),
                this.model.getSpawnCoordinates(),
                this.model.getFusionCoordinates(), this.model.getScore(),
                this.model.getBestScore());
        // Writes savegame only if user has seen the new state
        this.model.writeSavegame();
    }

    private void displayView() {
        this.view.display();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shift(Direction direction) {
        this.model.shift(direction);
        List<Point> spawnCoordinates = this.model.getSpawnCoordinates();
        // If no spawn coordinates nothing changed
        if (!spawnCoordinates.isEmpty()) {
            this.updateView();
            if (this.model.hasWon()) {
                this.model.setWonDisplayed();
                this.model.writeSavegame();
                this.view.showWinDialog();
                if (this.model.isVolumeOn()) {
                    this.sounds.playGameWon();
                }
            }
            if (this.model.isVolumeOn()) {
                this.sounds.playShift();
            }
        } else {
            if (this.model.isVolumeOn()) {
                this.sounds.playNoShift();
            }
        }
        /*
         * Always check lost game condition because player may had closed the
         * game without selecting try again or new game buttons causing the game
         * to load lost game without showing lost dialog.
         */
        if (this.model.noMovesAvailable()) {
            this.view.showLoseDialog();
            if (this.model.isVolumeOn()) {
                this.sounds.playGameOver();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        this.model.reset();
        this.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buttonPressed() {
        if (this.model.isVolumeOn()) {
            this.sounds.playShift();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVolume() {
        this.model.setVolume();
        this.view.setVolumeIcon(this.model.isVolumeOn());
        this.model.writeSavegame();
    }

}
