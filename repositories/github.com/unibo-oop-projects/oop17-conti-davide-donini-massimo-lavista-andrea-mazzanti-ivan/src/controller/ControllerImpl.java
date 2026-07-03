package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import controller.input.Command;
import controller.input.CommandType;
import controller.input.KeyInput;
import controller.input.KeyInputImpl;
import controller.input.MoveDown;
import controller.input.MoveLeft;
import controller.input.MoveRight;
import controller.input.MoveUp;
import controller.input.Shoot;
import controller.input.StopDown;
import controller.input.StopLeft;
import controller.input.StopMovement;
import controller.input.StopRight;
import controller.input.StopShoot;
import controller.input.StopUp;
import controller.utilities.Entities;
import model.Model;
import model.ModelImpl;
import model.data.GlobalData;
import settings.SettingsImpl;
import utility.GameModes;
import utility.StaticUtilities;
import view.View;
import view.entities.ViewEntity;

/**
 * 
 * This class represents the controller.
 * 
 */
public final class ControllerImpl implements Controller {

    private static final int GREEN_SEMAPHORE = 1;
    private static final int PERIOD = 16;

    private GameLoop gameLoop;
    private final Model model;
    private final View view;
    private final Semaphore inputMutex;
    private final LinkedList<Command> commandList;
    private final KeyInput keyInput;
    private final MoveDown moveDown;

    /**
     * The constructor of the class GameLoop.
     * 
     * @param view
     *            game's view
     */
    public ControllerImpl(final View view) {
        super();
        this.model = new ModelImpl(StaticUtilities.readGlobalDataFromFile());
        StaticUtilities.readSettingsFromFile();
        this.view = view;
        this.setMenuGlobalData(this.model.getGlobalData());
        this.commandList = new LinkedList<>();
        this.keyInput = new KeyInputImpl();
        moveDown = new MoveDown(this.keyInput);
        this.setChainOfKeyInput();
        this.inputMutex = new Semaphore(GREEN_SEMAPHORE);
    }

    private void setChainOfKeyInput() {
        final MoveUp moveUp = new MoveUp(this.keyInput);
        final MoveRight moveRight = new MoveRight(this.keyInput);
        final MoveLeft moveLeft = new MoveLeft(this.keyInput);
        final StopDown stopDown = new StopDown(this.keyInput);
        final StopUp stopUp = new StopUp(this.keyInput);
        final StopRight stopRight = new StopRight(this.keyInput);
        final StopLeft stopLeft = new StopLeft(this.keyInput);
        final Shoot shoot = new Shoot(this.keyInput);
        final StopShoot stopShoot = new StopShoot(this.keyInput);
        moveDown.setSuccessor(Optional.of(moveUp));
        moveUp.setSuccessor(Optional.of(moveRight));
        moveRight.setSuccessor(Optional.of(moveLeft));
        moveLeft.setSuccessor(Optional.of(stopDown));
        stopDown.setSuccessor(Optional.of(stopUp));
        stopUp.setSuccessor(Optional.of(stopRight));
        stopRight.setSuccessor(Optional.of(stopLeft));
        stopLeft.setSuccessor(Optional.of(shoot));
        shoot.setSuccessor(Optional.of(stopShoot));
        stopShoot.setSuccessor(Optional.empty());
    }

    @Override
    public void initGame(final GameModes gameMode) {
        this.model.initGame(gameMode);
        this.view.setStateGame(this.getEntitiesForView(), this.model.getGameData());
    }

    @Override
    public void startGame() {
        this.gameLoop = new GameLoop();
        this.gameLoop.start();
    }

    @Override
    public void stopGame() {
        this.gameLoop.stopGameLoop();
        this.view.stopViewRender();
    }

    @Override
    public void saveGame(final Optional<String> name) {
        final GlobalData globalData = this.model.getGlobalData();
        this.model.endGame(name);
        this.setMenuGlobalData(globalData);
        StaticUtilities.writeGlobalDataToFile(globalData);
    }

    @Override
    public void notifyCommand(final CommandType commandType) {
        Optional<List<Optional<Command>>> commandsList = Optional.empty();
        if (commandType == CommandType.PAUSE) {
            this.stopGame();
            this.clearKeyInput();
            this.view.getSceneFactory().openPauseScene();
        } else {
            commandsList = Optional.of(this.moveDown.processRequest(commandType));
        }
        commandsList.ifPresent(commands -> commands.forEach(command -> command.ifPresent(c -> {
            try {
                this.inputMutex.acquire();
                if (!this.commandList.contains(c)) {
                    if (c.getCommandType() == CommandType.STOP_DOWN || c.getCommandType() == CommandType.STOP_LEFT
                            || c.getCommandType() == CommandType.STOP_RIGHT
                            || c.getCommandType() == CommandType.STOP_UP) {
                        this.commandList.add(0, c);
                    } else {
                        this.commandList.add(c);
                    }
                }
                this.inputMutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })));
    }

    private List<ViewEntity> getEntitiesForView() {
        return this.model.getEntities().stream().map(e -> Entities.convertEntityForView(e)).collect(Collectors.toList());
    }

    private void clearKeyInput() {
        this.keyInput.clear();
        try {
            this.inputMutex.acquire();
            this.commandList.clear();
            this.commandList.add(new StopMovement());
            this.commandList.add(new StopShoot(this.keyInput));
            this.inputMutex.release();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void setMenuGlobalData(final GlobalData globalData) {
        this.view.setAchievements(globalData.getAchievements());
        this.view.setHighScores(globalData.getHighScores());
    }

    private void endGame() {
        this.view.closeGame(this.model.getGameData(), this.model.isHighScore() && !SettingsImpl.getSettings().isTrainingMode());
        this.stopGame();
    }

    private class GameLoop extends Thread {
        private volatile boolean running;

        GameLoop() {
            super();
            this.running = true;
        }

        public void run() {
            ControllerImpl.this.clearKeyInput();
            long lastTime = System.currentTimeMillis();
            while (this.running && !model.isGameOver()) {
                final long current = System.currentTimeMillis();
                final int elapsed = (int) (current - lastTime);
                processInput();
                model.update(elapsed);
                view.setStateGame(getEntitiesForView(), model.getGameData());
                StaticUtilities.waitForNextFrame(PERIOD, current);
                lastTime = current;
            }
            if (model.isGameOver()) {
                ControllerImpl.this.endGame();
            }
        }

        private void processInput() {
            try {
                ControllerImpl.this.inputMutex.acquire();
                if (!ControllerImpl.this.commandList.isEmpty()) {
                    ControllerImpl.this.commandList.pop().execute(model);
                }
                ControllerImpl.this.inputMutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void stopGameLoop() {
            this.running = false;
        }

        @Override
        public void start() {
            this.running = true;
            super.start();
        }

    }

}
