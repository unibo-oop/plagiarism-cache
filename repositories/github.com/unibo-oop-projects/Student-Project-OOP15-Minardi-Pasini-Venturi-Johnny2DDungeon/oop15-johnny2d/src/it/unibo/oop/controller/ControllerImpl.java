package it.unibo.oop.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Random;

import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.Record;
import it.unibo.oop.model.RecordImpl;
import it.unibo.oop.model.Score;
import it.unibo.oop.utilities.MusicPlayer;
import it.unibo.oop.utilities.MusicPlayerImpl;
import it.unibo.oop.utilities.Settings;
import it.unibo.oop.view.View;
import it.unibo.oop.view.ViewImpl;

/**
 * Class implementing the Controller of the MVC model.
 */
public final class ControllerImpl implements Controller {

    private static final int LEVELS = 10;
    private static Optional<ControllerImpl> singleton = Optional.empty();
    private Optional<AgentInterface> gLAgent = Optional.empty();
    private final View view = ViewImpl.getInstance();
    private final GameState gameState = GameStateImpl.getInstance();
    private final Record record = RecordImpl.getInstance();
    private final MusicPlayer mPlayer = MusicPlayerImpl.getInstance();

    private ControllerImpl() {
        this.record.setValue(this.getStatFromFile());
        this.mPlayer.setMusic(true);
        this.view.showView(AppState.LAUNCHING);
    }

    /**
     * @return the SINGLETON instance of the class.
     */
    public static synchronized Controller getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ControllerImpl());
        }
        return singleton.get();
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
        final int levelNumber = new Random().nextInt(LEVELS);
        this.gameState.initialize(levelNumber);
        this.view.getLevelView().initialize(levelNumber);
        this.play();
    }

    @Override
    public void play() { // pause -> play
        view.reset();
        this.view.hideView();
        this.mPlayer.stopAll();
        this.mPlayer.playLoop(MusicPlayerImpl.LEVEL_BACKGROUND);
        if (!this.gLAgent.isPresent()) {
            this.gLAgent = Optional.ofNullable(new GameLoopAgent());
            new Thread(this.gLAgent.get()).start();
        } else {
            this.gLAgent.get().play();
        }
    }

    private synchronized void createStatFile() {
        final File statDir = new File(Settings.HIGHSCORE_FOLDER);
        final File statFile = new File(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE);
        try {
            final boolean a = statDir.mkdir();
            final boolean b = statFile.createNewFile();
            System.out.println(a && b ? "StatFile created." : "File still exist.");
        } catch (IOException e) {
            System.out.println("Error in file reading, is it empty?");
        }
    }

    private synchronized Score getStatFromFile() {
        Score topScore = new Score();
        try (ObjectInputStream inStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {
            topScore = (Score) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error in file reading, is it empty?");
            this.createStatFile();
        }
        return topScore;
    }

    @Override
    public synchronized void putStatToFile() throws IOException {
        try (ObjectOutputStream outStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {
            this.createStatFile();
            outStream.writeObject(this.record.getValue());
        }
    }
}