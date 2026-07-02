package it.unibo.the100dayswar.controller.maincontroller.impl;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.the100dayswar.controller.gamecontroller.api.GameController;
import it.unibo.the100dayswar.controller.gamecontroller.impl.GameControllerImpl;
import it.unibo.the100dayswar.controller.maincontroller.api.MainController;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.controller.mapcontroller.impl.MapControllerImpl;
import it.unibo.the100dayswar.controller.movementcontroller.api.MovementController;
import it.unibo.the100dayswar.controller.movementcontroller.impl.MovementControllerImpl;
import it.unibo.the100dayswar.controller.shopcontroller.api.ShopController;
import it.unibo.the100dayswar.controller.shopcontroller.impl.ShopControllerImpl;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.controller.statisticscontoller.impl.StatisticControllerImpl;
import it.unibo.the100dayswar.model.Model;
import it.unibo.the100dayswar.model.ModelImpl;
import it.unibo.the100dayswar.view.startmenu.StartMenuView;


/**
 * The implementation of the main controller of the game.
 */
public class MainControllerImpl implements MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());
    private static final int TIMEOUT = 30;

    private final StatisticController statisticController;
    private final ShopController shopController;
    private final MovementController movementController;
    private final MapController mapController;
    private final GameController gameController;
    private Model model;

    /**
     * Constructor of the main controller.
     */
    public MainControllerImpl() {
        this.statisticController = new StatisticControllerImpl();
        this.shopController = new ShopControllerImpl();
        this.mapController = new MapControllerImpl();
        this.gameController = new GameControllerImpl();
        this.movementController = new MovementControllerImpl();
        this.model = null;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        new StartMenuView().initialize();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
        justification = "Exposing 'model' is intentional for design reasons.")
    @Override
    public Model getGameInstance() {
        return this.model;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public StatisticController getStatisticController() {
        return this.statisticController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapController getMapController() {
        return this.mapController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopController getShopController() {
        return this.shopController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovementController getMovementController() {
        return this.movementController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startNewGame(final String username) {
        this.model = new ModelImpl(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveGame(final String path) {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Callable<Boolean> task = () -> {
            return this.getGameInstance().saveGame(path);
        };

        final Future<Boolean> future = executor.submit(task);
        try {
            return future.get(TIMEOUT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            LOGGER.log(Level.SEVERE, "Save game operation timed out", e);
            return false;
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.log(Level.SEVERE, "Error during save game: " + e.getMessage(), e);
            return false;
        } finally {
            executor.shutdownNow();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean loadOldGame(final String path) {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Callable<Boolean> task = () -> {
            try {
                this.model = new ModelImpl(Optional.ofNullable(path));
                return true;
            } catch (IllegalStateException e) {
                return false;
            }
        };

        final Future<Boolean> future = executor.submit(task);
        try {
            return future.get(TIMEOUT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            LOGGER.log(Level.SEVERE, "Load old game timed out", e);
            return false;
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.log(Level.SEVERE, "Error during load old game: " + e.getMessage(), e);
            return false;
        } finally {
            executor.shutdownNow();
        }
    }
}
