package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import model.GameModel;
import model.GameModelImpl;
import model.map.StandardRoom;
import model.utils.Direction;
import util.Logger;
import view.View;
import view.ViewImpl;

/**
 * Defines the class that implements the Controller interface.
 * {@link Controller}
 */
public final class ControllerImpl implements Controller {

    private GameLoop gameloop;

    private final View gui;

    private static ControllerImpl singleton;

    private GameModel gm;

    private final Set<Direction> movements = new HashSet<>();
    private final Set<Direction> shoots = new HashSet<>();

    private static final String SAVE_PATH = "save.bin";

    private ControllerImpl() {
        gui = ViewImpl.get();

    }

    /**
     * Get the instance of the ControllerImpl.
     * 
     * @return the instance of Controller
     */
    public static Controller get() {
        if (Objects.isNull(singleton)) {
            singleton = new ControllerImpl();
        }
        return singleton;
    }

    @Override
    public void save() {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(gm);
            out.flush();
            final byte[] data = bos.toByteArray();
            Files.write(Paths.get(SAVE_PATH), data, StandardOpenOption.CREATE);
            bos.close();
        } catch (IOException e) {
            Logger.getLog().write(e.getStackTrace());
        }
    }

    @Override
    public void resumeGameLoop() {
        if (!Objects.isNull(gameloop)) {
            gameloop.start();
        }
    }

    @Override
    public void stopGameLoop() {
        this.cleanKeys();
        gui.foundSavedGame(isASavedGame());
        gameloop.stop();

    }

    @Override
    public void initView() {
        gui.init(isASavedGame(), StandardRoom.getWidth(), StandardRoom.getHeight());

    }

    @Override
    public View getGameView() {
        return gui;
    }

    @Override
    public void newGame() {
        gm = new GameModelImpl(getGameView().isGodModeSelected());
        gameloop = new GameLoopImpl(gm);
        resumeGameLoop();

    }

    @Override
    public void exit() {
        if (!Objects.isNull(gameloop)) {
            gameloop.stop();
        }

    }

    @Override
    public Set<Direction> getObserverMovements() {
        return movements;
    }

    @Override
    public Set<Direction> getObserverShoot() {
        return shoots;
    }

    @Override
    public void gameOver() {
        gui.gameOver();
        cleanKeys();
        gameloop.stop();
        try {
            Files.deleteIfExists(Paths.get(SAVE_PATH));
        } catch (IOException e) {
            Logger.getLog().write(e.getStackTrace());
        }

        gui.foundSavedGame(isASavedGame());
    }

    private void cleanKeys() {
        movements.clear();
        shoots.clear();

    }

    @Override
    public void loadGame() {
        if (isASavedGame()) {

            try {
                final ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(SAVE_PATH)));
                final ObjectInput in = new ObjectInputStream(bis);
                gm = (GameModel) in.readObject();
                gameloop = new GameLoopImpl(gm);
                gm.clearBullets();
                resumeGameLoop();

            } catch (IOException e) {
                Logger.getLog().write(e.getStackTrace());

            } catch (ClassNotFoundException e) {
                Logger.getLog().write(e.getStackTrace());

            }
        }

    }

    @Override
    public void addMovements(final Direction d) {
        movements.add(d);

    }

    @Override
    public void addShoots(final Direction d) {
        shoots.add(d);

    }

    @Override
    public void removeMovements(final Direction d) {
        movements.remove(d);

    }

    @Override
    public void removeShoots(final Direction d) {
        shoots.remove(d);

    }

    @Override
    public boolean isASavedGame() {
        return Files.exists(Paths.get(SAVE_PATH));
    }

}