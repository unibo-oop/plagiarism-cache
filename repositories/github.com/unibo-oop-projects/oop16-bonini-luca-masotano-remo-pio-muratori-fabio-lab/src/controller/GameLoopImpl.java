package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import model.GameModel;
import model.entities.Bullet;
import model.entities.Enemy;
import model.entities.Item;
import model.hitbox.HitboxRectangle;
import model.map.Room;
import model.map.StandardRoom;
import model.utils.Direction;
import util.Logger;
import util.Pair;
import static util.ImageLoaderProxy.ImageID;

/**
 * Defines the class which contains the game model and performs all the
 * operations to update the model and pass the wrapped model's object to the
 * view.
 */
public class GameLoopImpl implements GameLoop, Runnable {

    private static final int SECONDNANO = 1000000000;
    private static final int SECONDMICRO = 1000000;
    private int targetFps;
    private static final int MSWAIT = 5;
    private long lastLoop;
    private int optimalTime;
    private boolean running;
    private Thread thread;
    private final GameModel gm;
    private boolean loadCheck;
    private boolean doorclosed = true;
    private final List<ModelWrapper> mwdynamic = new ArrayList<>();
    private final List<ModelWrapper> mwstatic = new ArrayList<>();
    private final Map<Pair<Integer, Integer>, ImageID> map = new HashMap<>();

    /**
     * Constructs a new instance of GameLoopImpl.
     * 
     * @param gm
     *            GameModel
     */
    public GameLoopImpl(final GameModel gm) {
        this.gm = gm;

    }

    @Override
    public synchronized void start() {
        if (!running) {
            targetFps = ControllerImpl.get().getGameView().getFPSSelected();
            optimalTime = SECONDNANO / targetFps;
            loadCheck = true;
            running = true;
            lastLoop = System.nanoTime();
            thread = new Thread(this, "Game Loop");
            thread.start();
        }
    }

    @Override
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join(MSWAIT);
            } catch (InterruptedException e) {
                Logger.getLog().write(e.getStackTrace());
            }
        }
    }

    @Override
    public void run() {

        while (running) {

            final long now = System.nanoTime();
            final long updateLength = now - lastLoop;
            lastLoop = now;
            final double delta = updateLength / ((double) SECONDNANO / 60);

            update(delta);
            render();
            checkEndGame();

            final long sleepTime = (lastLoop - System.nanoTime() + optimalTime) / SECONDMICRO;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                    Logger.getLog().write(e.getStackTrace());
                }
            }
        }

    }

    private void update(final double delta) {
        gm.update(new ArrayList<Direction>(ControllerImpl.get().getObserverMovements()),
                new ArrayList<Direction>(ControllerImpl.get().getObserverShoot()), delta);

    }

    private void render() {

        if (gm.roomChanged() || loadCheck) {
            renderMap();
            staticRender();
            doorclosed = true;
        }

        if (gm.getMap().getCurrentRoom().getEnemies().size() == 0 && doorclosed) {
            staticRender();
            doorclosed = false;
            ControllerImpl.get().save();
        }

        dynamicRender();

    }

    private void checkEndGame() {
        if (gm.isGameOver()) {
            ControllerImpl.get().gameOver();
        }
    }

    private void staticRender() {
        if (!loadCheck) {
            Platform.runLater(() -> ControllerImpl.get().getGameView().roomChanging());
        }

        loadCheck = false;

        mwstatic.clear();

        for (final HitboxRectangle elem : gm.getMap().getCurrentRoom().getWalls()) {
            if (elem.getWidth() > elem.getHeight()) {
                mwstatic.add(new ModelWrapperImpl(elem, ImageID.HORIZONTAL_WALL));

            } else {
                mwstatic.add(new ModelWrapperImpl(elem, ImageID.VERTICAL_WALL));

            }
        }

        for (final Direction elem : gm.getMap().getCurrentRoom().getDoors()) {
            if (elem.equals(Direction.UP)) {
                mwstatic.add(new ModelWrapperImpl(StandardRoom.getDoor(elem),
                        gm.getMap().getCurrentRoom().getEnemies().size() != 0 ? ImageID.NORTH_DOOR
                                : ImageID.NORTH_DOOR_OPEN));
            }
            if (elem.equals(Direction.RIGHT)) {
                mwstatic.add(new ModelWrapperImpl(StandardRoom.getDoor(elem),
                        gm.getMap().getCurrentRoom().getEnemies().size() != 0 ? ImageID.EAST_DOOR
                                : ImageID.EAST_DOOR_OPEN));
            }
            if (elem.equals(Direction.DOWN)) {
                mwstatic.add(new ModelWrapperImpl(StandardRoom.getDoor(elem),
                        gm.getMap().getCurrentRoom().getEnemies().size() != 0 ? ImageID.SOUTH_DOOR
                                : ImageID.SOUTH_DOOR_OPEN));
            }
            if (elem.equals(Direction.LEFT)) {
                mwstatic.add(new ModelWrapperImpl(StandardRoom.getDoor(elem),
                        gm.getMap().getCurrentRoom().getEnemies().size() != 0 ? ImageID.WEST_DOOR
                                : ImageID.WEST_DOOR_OPEN));
            }
        }

        Platform.runLater(() -> ControllerImpl.get().getGameView().setStaticCollection(mwstatic));
    }

    private void dynamicRender() {
        mwdynamic.clear();

        for (final Bullet elem : gm.getPlayerBullets()) {
            mwdynamic.add(new ModelWrapperImpl(elem.getHitbox(), ImageID.BULLET));
        }

        for (final Bullet elem : gm.getEnemiesBullets()) {
            mwdynamic.add(new ModelWrapperImpl(elem.getHitbox(), ImageID.BULLET_ENEMY));
        }

        if (!gm.getMap().isBossRoom()) {

            for (final Enemy elem : gm.getMap().getCurrentRoom().getEnemies()) {
                mwdynamic.add(new ModelWrapperImpl(elem.getHitbox(), ImageID.ENEMY_SOLDIER));
            }

        } else {

            for (final Enemy elem : gm.getMap().getCurrentRoom().getEnemies()) {
                mwdynamic.add(new ModelWrapperImpl(elem.getHitbox(), ImageID.BOSS));
            }
        }

        mwdynamic.add(new ModelWrapperImpl(gm.getPlayer().getHitbox(), ImageID.PLAYER));

        for (final Item elem : gm.getMap().getCurrentRoom().getAllItems()) {
            mwdynamic.add(new ModelWrapperImpl(elem.getHitbox(), ImageID.PICKUP));
        }

        Platform.runLater(() -> ControllerImpl.get().getGameView().setDynamicCollection(new ArrayList<>(mwdynamic)));

        Platform.runLater(() -> ControllerImpl.get().getGameView().setPlayerLife(gm.getPlayer().getLife()));
    }

    private void renderMap() {
        map.clear();
        recursiveExplore(gm.getMap().getCurrentRoom(), null, 0, 0);
        Platform.runLater(
                () -> ControllerImpl.get().getGameView().setMap(new HashMap<Pair<Integer, Integer>, ImageID>(map)));
    }

    private void recursiveExplore(final Room currRoom, final Direction dir, final int x, final int y) {

        for (final Pair<Integer, Integer> elem : map.keySet()) {
            if (elem.equals(new Pair<Integer, Integer>(x, y))) {
                return;
            }
        }

        if (currRoom.equals(gm.getMap().getCurrentRoom())) {
            map.put(new Pair<Integer, Integer>(x, y), ImageID.CURRENT_ROOM);
        } else if (currRoom.getEnemies().size() == 0) {
            map.put(new Pair<Integer, Integer>(x, y), ImageID.CHECKED_ROOM);
        } else {
            map.put(new Pair<Integer, Integer>(x, y), ImageID.UNCHECKED_ROOM);
        }

        for (final Direction elem : currRoom.getDoors()) {
            if (!elem.equals(dir)) {
                switch (elem) {
                case UP:
                    recursiveExplore(gm.getMap().getAllRooms().get(currRoom.getConnection(Direction.UP)),
                            Direction.DOWN, x, y - 1);
                    break;
                case DOWN:
                    recursiveExplore(gm.getMap().getAllRooms().get(currRoom.getConnection(Direction.DOWN)),
                            Direction.UP, x, y + 1);
                    break;
                case LEFT:
                    recursiveExplore(gm.getMap().getAllRooms().get(currRoom.getConnection(Direction.LEFT)),
                            Direction.RIGHT, x - 1, y);
                    break;
                case RIGHT:
                    recursiveExplore(gm.getMap().getAllRooms().get(currRoom.getConnection(Direction.RIGHT)),
                            Direction.LEFT, x + 1, y);
                    break;
                default:
                    break;
                }
            }
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

}
