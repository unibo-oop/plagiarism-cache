package pvz.controller.gamecontroller.impl;

import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.api.ViewListener;
import pvz.controller.maincontroller.api.MainController;
import pvz.utilities.EntityType;
import pvz.utilities.GameEntity;
import pvz.model.game.api.Difficulty;
import pvz.model.game.api.GameModel;
import pvz.model.game.impl.GameModelImpl;
import pvz.utilities.Position;
import pvz.utilities.Resolution;
import pvz.view.gameview.api.GameView;
import pvz.view.gameview.impl.GameViewImpl;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the {@link GameController} and {@link ViewListener} interfaces.
 * Manages the game loop, user inputs, and interaction between the model and the view.
 */
public class GameControllerImpl implements GameController, ViewListener {
    private static final int FPS = 60;
    private static final long TIME_PER_TICK = 1000 / FPS;
    private final BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private final MainController parentController;
    private GameModel model;
    private boolean running;
    private EntityType selectedPlantType;
    private Resolution resolution;
    /**
     * Marker interface for queued input events.
     */
    interface Event { }

    /**
     * Input event representing plant selection from the roaster.
     *
     * @param inputRoaster the selected plant
     */
    record InputRoaster(UserInputRoaster inputRoaster) implements Event { }

    /**
     * Input event representing a grid cell selection.
     *
     * @param position the position clicked
     */
    record InputGrid(Position position) implements Event { }

    /**
     * Constructs the game controller with a reference to the main controller.
     *
     * @param controller the parent controller
     */
    public GameControllerImpl(final MainController controller) {
        this.parentController = controller;
    }

    /** {@inheritDoc} */
    @Override
    public void startGame(final Difficulty difficulty, final Resolution resolution) {
        this.resolution = resolution;
        this.running = true;
        this.model = new GameModelImpl(difficulty);
        final GameView view = GameViewImpl.createGameViewImpl(this, resolution);
        new GameLoop(model, view).start();
    }

    /** {@inheritDoc} */
    @Override
    public void stopGame() {
        this.running = false;
    }

    /**
     * Inner class representing the main game loop, running in a dedicated thread.
     * Handles game updates, rendering, user inputs, and game-over logic.
     */
    private class GameLoop extends Thread {
        private final GameModel model;
        private final GameView view;

        GameLoop(final GameModel model, final GameView view) {
            this.model = Objects.requireNonNull(model);
            this.view = Objects.requireNonNull(view);
        }

        /** {@inheritDoc} */
        @Override
        public void run() {
            Objects.requireNonNull(view);
            long previousTime = System.currentTimeMillis();

            while (running) {
                final long currentTime = System.currentTimeMillis();
                final long deltaTime = currentTime - previousTime;

                model.update(deltaTime);
                view.render(model.getGameEntities(), model.getSunCount(), model.getKillCount());

                previousTime = currentTime;

                waitForNextFrame(currentTime);
                handleInput();

                if (model.isGameOver()) {
                    stopGame();
                    parentController.goToEndGame(model.isVictory(), resolution);
                    view.close();
                }
            }
        }

        /**
         * Sleeps the thread to maintain consistent frame timing.
         *
         * @param currentTime the timestamp at the start of the frame
         */
        private void waitForNextFrame(final long currentTime) {
            final long frameDuration = System.currentTimeMillis() - currentTime;
            if (frameDuration < TIME_PER_TICK) {
                try {
                    sleep(TIME_PER_TICK - frameDuration);
                } catch (InterruptedException e) {
                    handleException(e);
                }
            }
        }

        /**
         * Processes any pending user input events from the queue.
         */
        private void handleInput() {
            try {
                Event event = queue.poll(1, TimeUnit.MILLISECONDS);
                while (event != null) {
                    switch (event) {
                        case InputRoaster(var inputRoaster) -> {
                            selectedPlantType = switch (inputRoaster) {
                                case PEASHOOTER -> EntityType.PEASHOOTER;
                                case SUNFLOWER -> EntityType.SUNFLOWER;
                                case WALLNUT -> EntityType.WALLNUT;
                            };
                        }
                        case InputGrid(var pos) -> {
                            if (selectedPlantType != null && !isCellOccupied(pos)) {
                                model.placePlant(selectedPlantType, pos);
                            }
                            selectedPlantType = null;
                        }
                        default -> { }
                    }
                    event = queue.poll(1, TimeUnit.MILLISECONDS);
                }
            } catch (InterruptedException e) {
                handleException(e);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void processInputRoaster(final UserInputRoaster input) {
        queue.add(new InputRoaster(input));
    }

    /** {@inheritDoc} */
    @Override
    public void processInputGrid(final Position position) {
        queue.add(new InputGrid(position));
    }

    /** {@inheritDoc} */
    @Override
    public void handleException(final Exception exception) {
        parentController.handleException(exception);
    }

    /**
     * Requests the termination of the application by delegating
     * the operation to the parent controller.
     * <p>
     * This method propagates the quit command to the main controller,
     * which is responsible for properly shutting down the application.
     */
    @Override
    public void quit() {
        parentController.quit();
    }

    /**
     * Checks if a plant already occupies the specified position.
     *
     * @param position the position to check
     * @return {@code true} if a plant is present, {@code false} otherwise
     */
    private boolean isCellOccupied(final Position position) {
        final GameModel currentModel = Objects.requireNonNull(this.model);
        final Set<GameEntity> entities = currentModel.getGameEntities();
        return entities.stream().anyMatch(e -> e.position().equals(position)
                        && switch (e.type()) {
                    case PEASHOOTER, SUNFLOWER, WALLNUT -> true;
                    case BASICZOMBIE, STRONGZOMBIE, FASTZOMBIE, BEASTZOMBIE, BULLET, LAWNMOWER -> false;
                }
        );
    }
}
