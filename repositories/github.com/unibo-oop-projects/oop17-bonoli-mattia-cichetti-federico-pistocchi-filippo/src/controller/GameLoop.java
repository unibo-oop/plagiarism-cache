package controller;

import java.util.HashSet;
import java.util.Set;

import model.world.World;
import view.View;
import view.ViewImpl;

/**
 * game loop.
 */
public class GameLoop extends Thread {

    private enum State {
        READY, RUNNING, PAUSED, STOPPED
    }

    private static final Integer PLACEHOLDER = 0;

    private int fps;
    private View view;
    private Controller controller;
    private State state;
    private World world;
    private WorldCreatorFactory wcFactory;
    private GameDifficult diff;
    private Set<GameObserver> observers;

    /**
     * @param fps 
     * @param c controller
     * @param v view
     * @param d difficult
     */
    public GameLoop(final int fps, final Controller c, final View v, final GameDifficult d) {
        this.fps = fps;
        view = v;
        controller = c;
        state = State.READY;
        wcFactory = new WorldCreatorFactoryImpl();
        diff = d;
        observers = new HashSet<>();
    }
 
    /**
     * core method.
     */
    public void run() {
        while (!(state.equals(State.STOPPED))) {
            if (!state.equals(State.PAUSED)) {
                try {
                    sleep(1 / fps);
                } catch (InterruptedException e) { }
            } else {
                long startTime = System.currentTimeMillis();
                //input handling
                //update model
                world.getLane().forEach(l -> l.update());
                //draw view
                observers.forEach(o -> o.update(PLACEHOLDER));
                long current = System.currentTimeMillis();
                try {
                    sleep((1 / fps) - (current - startTime));
                } catch (InterruptedException e) { }
            }
        }
    }
    /**
     * stop the game loop.
     */
    public void stopGameLoop() {
        this.state = State.STOPPED;
    }
    /**
     * pause the game loop.
     */
    public void pauseGameLoop() {
        state = State.PAUSED;
    }
    /**
     * start the game loop.
     */
    public void startGameLoop() {
        // todo: create world based on difficult
        switch (diff) {
        case EASY: world = wcFactory.createEasyWorld(); break;
        case NORMAL: world = wcFactory.createNormalWorld(); break;
        case HARD: world = wcFactory.createHardWorld(); break;
        default:
            break;
        }
        state = State.RUNNING;
        this.start();
    }
    /**
     * get game loop state.
     * @return state
     */
    public State getGameLoopState() {
        return state;
    }

    /**
     * Simple getter for the World.
     * @return the world this GameLoop is updating
     */
    public World getWorld() {
        return this.world;
    }

    /**
     * Registers a GameObserver to the list of observers that is updated once per tick.
     * @param o The GameObserver to register.
     */
    public void registerObserver(final GameObserver o) {
        this.observers.add(o);
    }
}
