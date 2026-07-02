package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import controller.Controller;
import controller.input.InputController;
import enums.GameMode;
import enums.GameScene;
import model.World;
import model.command.Command;
import model.command.FireCommand;
import model.command.MoveDownCommand;
import model.command.MoveLeftCommand;
import model.command.MoveRightCommand;
import model.command.MoveUpCommand;

/**
 * The Game Loop class. The game run here.
 */
public final class GameLoopImpl implements GameLoop, Runnable {

    // Milliseconds that determinate frame per second.
    private static final long PERIOD = 20L; // 20ms = 50 frame per seconds.

    // The controller of the game.
    private final Controller controller;
    private final GameSession session;
    private final World world;
    private final List<Queue<Command>> playersCommands;
    private final List<InputController> inputControllers;

    /**
     * The constructor method.
     * 
     * @param controller the controller of the game.
     */
    public GameLoopImpl(final Controller controller) {
        this.controller = controller;
        this.world = new World();
        this.playersCommands = new ArrayList<Queue<Command>>();
        this.inputControllers = controller.getInputControllers();
        final Queue<Level> levels = new LinkedList<>();
        levels.add(LevelFactoryUtils.getLevel(controller.getCurrentStage()));
        this.setPlayersCommands();
        this.session = new GameSession(this.world, levels, this.playersCommands);
    }

    @Override
    public void setup() {
        controller.setGameScene(GameScene.GAME_GUI);
        controller.setGameStatus(session.getGameStatus());
        this.session.nextLevelSetup();
    }

    @Override
    public void run() {
        mainLoop();
    }

    @Override
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (controller.isGameRunning()) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
    }

    /*
     * Method for the synch with the frame rate.
     * 
     * @param current the current time
     */
    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (final Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void processInput() {
        if (controller.getGameMode().equals(GameMode.ONE_PLAYER)
                || controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            if (inputControllers.get(0).isMoveLeft()) {
                playersCommands.get(0).add(new MoveLeftCommand());
            } else if (inputControllers.get(0).isMoveRight()) {
                playersCommands.get(0).add(new MoveRightCommand());
            } else if (inputControllers.get(0).isMoveUp()) {
                playersCommands.get(0).add(new MoveUpCommand());
            } else if (inputControllers.get(0).isMoveDown()) {
                playersCommands.get(0).add(new MoveDownCommand());
            } else if (inputControllers.get(0).isFire()) {
                playersCommands.get(0).add(new FireCommand());
            }
        }
        if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            if (inputControllers.get(1).isMoveLeft()) {
                playersCommands.get(1).add(new MoveLeftCommand());
            } else if (inputControllers.get(1).isMoveRight()) {
                playersCommands.get(1).add(new MoveRightCommand());
            } else if (inputControllers.get(1).isMoveUp()) {
                playersCommands.get(1).add(new MoveUpCommand());
            } else if (inputControllers.get(1).isMoveDown()) {
                playersCommands.get(1).add(new MoveDownCommand());
            } else if (inputControllers.get(1).isFire()) {
                playersCommands.get(1).add(new FireCommand());
            }
        }

    }

    @Override
    public void updateGame(final int elapsed) {

//        if (this.session.isLevelFinished()) { // TODO notifica alla view del livello cambiato
//            if (this.session.hasOtherLevel()) {
//                this.session.nextLevelSetup();
//            }
        this.world.updateState();
        this.session.populateOfEnemy();
        this.session.updateAI();
        // }
        if (this.session.getGameStatus().getGameState().equals(State.LOSE)) {
            controller.notifyLose();
        }
        if (this.session.getGameStatus().getGameState().equals(State.WIN)) {
            controller.notifyWin();
        }
    }

    @Override
    public void render() {
        controller.renderScene(world.getWorldEntity());
    }

    /*
     * Method that initialize the players commands queues.
     */
    private void setPlayersCommands() {
        if (controller.getGameMode().equals(GameMode.ONE_PLAYER)) {
            playersCommands.add(new LinkedList<Command>());
        } else if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            playersCommands.add(new LinkedList<Command>());
            playersCommands.add(new LinkedList<Command>());
        }
    }

}
