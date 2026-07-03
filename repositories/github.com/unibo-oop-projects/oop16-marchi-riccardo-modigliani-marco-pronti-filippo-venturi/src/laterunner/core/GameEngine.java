package laterunner.core;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import laterunner.model.collisions.BorderHitEvent;
import laterunner.model.collisions.ObstacleHitEvent;
import laterunner.model.collisions.WorldEvent;
import laterunner.model.collisions.WorldEventListener;
import laterunner.model.saving.FileManager;
import laterunner.model.world.GameState;
import laterunner.model.world.GameStateImpl;
import laterunner.model.world.World;
import laterunner.graphics.Menu;
import laterunner.graphics.Scene;
import laterunner.graphics.SceneImpl;
import laterunner.input.Command;
import laterunner.input.Controller;

/**
 * Game's engine.
 */
public class GameEngine implements Controller, WorldEventListener {

    private static final long PERIOD = 16; /* 16 ms = 60 frame al secondo */
    private GameState gameState;
    private Scene view;
    private boolean cmdList = false;
    private BlockingQueue<Command> cmdQueue;
    private LinkedList <WorldEvent> eventQueue;
    private int lvlNumb;
    private boolean isSurvival = false;

    /**
     * Instantiates game engine.
     */
    public GameEngine() {
        cmdQueue = new ArrayBlockingQueue<Command>(100);
        eventQueue = new LinkedList<WorldEvent>();
    }

    /**
     * Loads main settings and shows main menu.
     */
    public void gameInit() {
        FileManager.loadFromFile();
        view = new SceneImpl(this, this);
    }

    /**
     * Loads level.
     * 
     * @param levelNumber
     *          level's number
     * @param score
     *          level's score
     */
    public void setupLevel(final int levelNumber, final int score) {
        this.cmdQueue.clear();
        this.lvlNumb = levelNumber;
        if (levelNumber > 10 && !(this.isSurvival)) {
            this.isSurvival = true;
            this.lvlNumb = 1;
        } else if (this.lvlNumb > 10) {
            this.lvlNumb = 10;
        }
        this.gameState = new GameStateImpl(this, this.lvlNumb, score);
        this.view.getRoad().setGameState(this.gameState);
    }

    /**
     * The loop which manages the gameplay.
     */
    public void mainLoop() {
        this.cmdList = true;
        view.getRoad().getAudio().play();
        long lastTime = System.currentTimeMillis();
        while (!gameState.isLevelFinished() && !this.gameState.isEndSurvival()) {
            long current = System.currentTimeMillis();
            int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
        this.gameState.updateStats();
        endLevel();
    }

    private void endLevel() {
        if (!this.isSurvival) {
            this.cmdList = false;
            view.getRoad().getAudio().stop();
            SceneImpl.changePanel("menu");
            Menu.updateLevel();
        } else {
            this.setupLevel(++this.lvlNumb, this.gameState.getScore());
            new Thread(this.view.getRoad()).start();
        }
    }

    /**
     * Makes the main loop sleeping.
     * 
     * @param current
     *          time used to calculate how much to waits
     */
    protected void waitForNextFrame(final long current) {
        long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (Exception ex) { }
        }
    }

    private void processInput() {
        Command cmd = cmdQueue.poll();
        if (cmd != null) {
            cmd.execute(this.gameState);
        }
    }

    private void updateGame(final int elapsed) {
        this.gameState.update(elapsed);
        checkEvents();
    }

    private void checkEvents() {
        World w = gameState.getWorld();
        for (WorldEvent ev : eventQueue) {
            if (ev instanceof ObstacleHitEvent) {
                ObstacleHitEvent cev = (ObstacleHitEvent) ev;
                if (this.isSurvival) {
                    this.gameState.setEndSurvival(true);
                    this.isSurvival = false;
                } else {
                    w.removeObstacle(cev.getCollisionObj());
                    gameState.decScore(cev.getCollisionObj());
                }
            } else if (ev instanceof BorderHitEvent) {
                gameState.decScorebyBorder();
                }
            }
            if (eventQueue.isEmpty()) {
                gameState.incScore(1 * 2);
            }
            eventQueue.clear();
    }

    private void render() {
        view.render();
    }

    /**
     * Adds the command to the command queue.
     * 
     * @param command
     *          Command executed
     */
    public void notifyCommand(final Command command) {
        if (this.cmdList) {
            cmdQueue.add(command);
        }
    }

    /**
     * Returns the command queue.
     * 
     * @return
     *          command queue
     */
    public BlockingQueue<Command> getCmdQueue() {
        return this.cmdQueue;
    }

    /**
     * Adds the event to the event queue.
     * 
     * @param event
     *          Event happened
     */
    public void notifyEvent(final WorldEvent event) {
        eventQueue.add(event);
    }

    /**
     * Gets survival mode.
     * 
     * @return
     *          true if survival.
     */
    public boolean isSurvival() {
        return isSurvival;
    }
}
