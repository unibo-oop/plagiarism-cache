package it.unibo.plantsfarm.controller.gamepanel;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.controller.player.ImplActionHandler;
import it.unibo.plantsfarm.controller.player.ManagerSavingPlayer;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
import it.unibo.plantsfarm.controller.inventario.ImplControllerInventario;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.controller.action.SeedController;
import it.unibo.plantsfarm.controller.animation.ImplSelectorFrames;
import it.unibo.plantsfarm.model.camera.ImplCamera;
import it.unibo.plantsfarm.model.garden.CollisionDetector;
import it.unibo.plantsfarm.model.garden.SoilSaving;
import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.player.ImplFactoryPlayer;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantEffect;
import it.unibo.plantsfarm.model.plant.OrnamentalBehavior;

import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.selectorplayer.SelectorPlayerView;

/**
 * Implementation of the ControllerGamePanel interface, responsible for managing the game loop,
 * handling user input, and coordinating interactions between the model and view components.
 */
public final class ImplControllerGamePanel extends Thread implements ControllerGamePanel {
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private ImplViewGamePanel view;
    private final ImplFactoryPlayer factoryPlayer = new ImplFactoryPlayer();
    private final BlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private Player player;
    private final GardenController gardenController;
    private ImplSelectorFrames controllerAnimation;
    private ImplCamera camera;
    private final TileMap map;
    private final CollisionDetector collisionDetector;
    private final ActionHandler actionHandler;
    private final SoilSaving saver = new SoilSaving();
    private final SpawningBuffsController spawningBuffsController;
    private final ControllerInventario controllerInventario;
    private final ManagerSavingPlayer managerSavingPlayer;
    private final SelectorPlayerView selectPlayer;
    private PlantType currentSelectedPlant;

    /**
     * Creates a new ImplControllerGamePanel with the specified GameState.
     *
     * @param gameState The initial state of the game.
     */
    public ImplControllerGamePanel(final GameStateImpl gameState) {
        this.map = new TileMap();
        this.map.loadMap("/maps/map.txt");
        selectPlayer = new SelectorPlayerView();
        setPlayer();
        this.actionHandler = new ImplActionHandler(this.player);
        this.controllerInventario = new ImplControllerInventario(this.player);
        this.gardenController = new GardenController(gameState, this.player);
        this.collisionDetector = new CollisionDetector(this.player);
        this.spawningBuffsController = new SpawningBuffsController(map);
        this.managerSavingPlayer = new ManagerSavingPlayer();
        managerSavingPlayer.loadManager(player.getInventory(), player);
    }

   @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            final long now = System.currentTimeMillis();
            final long delta = now - lastTime;
            lastTime = now;
            final UserInput input = queue.poll();
            view.show(player.getPosx(), player.getPosy(), camera.getCameraPosX(),
            camera.getCameraPosY(), List.copyOf(gardenController.getSoilList()),
            List.copyOf(spawningBuffsController.getBuffList()));
            try {
                sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
                if (input != null) {
                    switch (input) {
                    case DOWN, UP, RIGHT, LEFT, FERMO -> actionHandler.updateDirection(input);

                    case SELECT_SEED -> {
                        this.view.playSeed();
                        openSeedSelectionMenu();
                    }

                    case ACTIONHOE -> {
                        this.view.playPlant();
                        actionHandler.handleActionHoe(gardenController, currentSelectedPlant);
                        saver.saveGame(gardenController.getSoilList());
                        managerSavingPlayer.saveManager(player.getInventory(), player);
                    }
                    case ACTIONWATER -> {
                        this.view.playWater();
                        actionHandler.handleWater(gardenController, now, currentSelectedPlant);
                        saver.saveGame(gardenController.getSoilList());
                        managerSavingPlayer.saveManager(player.getInventory(), player);
                    }
                    case REMOVE_PLANT -> {
                        this.view.playPlant();
                        actionHandler.handleAxe(gardenController);
                        saver.saveGame(gardenController.getSoilList());
                        managerSavingPlayer.saveManager(player.getInventory(), player);
                    }
                }
                    controllerAnimation.takeInput(input);
                }
            } catch (final InterruptedException e) {
                break;
            }

            spawningBuffsController.updateUpGrade(now);
            if (actionHandler.playerActionBuff(spawningBuffsController)) {
                this.view.playExp();
            }
            collisionDetector.collisionDetection();
            controllerAnimation.update(System.nanoTime());
            player.updatePlayer(delta);
            if (gardenController.updateSoil(now)) {
                this.view.playGrowth();
            }
            camera.followPlayer((int) player.getPosx(), (int) player.getPosy());
        }
    }

    /**
     * Open the seed selection menu.
     */
    private void openSeedSelectionMenu() {
        new SeedController(plant -> {
            this.currentSelectedPlant = plant;
        }, true).start();
    }

    @Override
    public void takeInput(final UserInput input) {
        if (input != null) {
            this.queue.add(input);
        }
    }

    @Override
    public void addView() {
        this.controllerAnimation = new ImplSelectorFrames();
        this.view = new ImplViewGamePanel(this, controllerAnimation);
        this.controllerInventario.addView(this.view);
        camera = new ImplCamera(view.getWidth(), view.getHeight());
    }

    @Override
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The View is a complex Swing component and cannot be deeply cloned. "
                      + "The Controller must provide the actual instance to the main application frame."
    )
    public ImplViewGamePanel getView() {
        return this.view;
    }

    @Override
    public void setPlayer() {
        selectPlayer.setVisible(true);
        this.player = factoryPlayer.createPlayer(selectPlayer.getSelectedType());
    }

    @Override
    public void openInventory() {
        if (controllerInventario != null) {
            this.controllerInventario.updateInventory();
            this.controllerInventario.openViewInv();
            managerSavingPlayer.saveManager(player.getInventory(), player);
        }
    }

    @Override
    public PlantStatus getPlantStatus(final PlantImpl plant) {
        if (plant.isMature()) {
            if (plant.isEdible()) {
                return PlantStatus.READY_TO_HARVEST;
            } else {
                if (plant.getType().getBehavior() instanceof OrnamentalBehavior) {
                    final OrnamentalBehavior behavior = (OrnamentalBehavior) plant.getType().getBehavior();
                    final PlantEffect effectType = behavior.getEffect();
                    if (effectType == PlantEffect.GROWTH_SPEED) {
                        return PlantStatus.EFFECT_SPEED;
                    } else if (effectType == PlantEffect.BIG_HARVEST) {
                        return PlantStatus.EFFECT_HARVEST;
                    }
                }
            }
        } else if (plant.needsWater()) {
            return PlantStatus.NEEDS_WATER;
        }
        return PlantStatus.GROWING;
    }
}
