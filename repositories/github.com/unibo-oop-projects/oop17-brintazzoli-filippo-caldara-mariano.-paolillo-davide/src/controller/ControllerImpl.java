package controller;

import controller.collision.FactoryCollision;
import controller.collision.FactoryCollisionImpl;
import controller.input.ProcessInput;
import controller.input.ProcessInputImpl;
import controller.levels.Level;
import controller.levels.LevelImpl;
import controller.loader.FileControllerImpl;
import controller.objects.AI;
import controller.objects.ControllerObjects;
import controller.output.ControllerOutput;
import controller.utility.CheckCollision;
import controller.utility.Convertitor;
import model.Model;
import view.View;

/**
 * Concrete implementation of {@link Controller} interface.
 */
public final class ControllerImpl implements Controller {
    private static final ControllerImpl SINGLETON = new ControllerImpl();
    private static final double DEFAULT_TIME_TO_SHOT = 3000;
    private ControllerObjects controllerObjects;
    private Model world;
    private View view;
    private GameLoopImpl gameLoop;
    private double timeToShot;
    private Level level;
    private FactoryCollision factoryCollision;
    private ProcessInput controllerInput;

    /**
     * Private constructor.
     */
    private ControllerImpl() {
    }

    /**
     * Getter of the object {@link ControllerImpl}.
     * 
     * @return the object {@link ControllerImpl}.
     */
    public static ControllerImpl getController() {
        return SINGLETON;
    }

    @Override
    public void initializeController(final Model world, final View view) {
        this.world = world;
        this.view = view;
        Convertitor.initialize(this.world, this.view);
        CheckCollision.initialize(this.world);
        this.level = new LevelImpl(new FileControllerImpl(this.world), this);
        this.timeToShot = DEFAULT_TIME_TO_SHOT;
        this.factoryCollision = new FactoryCollisionImpl(this.world.getBounds());
    }

    @Override
    public ControllerOutput getControllerOutput() {
        return this.controllerObjects.getControllerOutput();
    }

    @Override
    public ControllerObjects getControllerObjects() {
        return this.controllerObjects;
    }

    @Override
    public ProcessInput getControllerInput() {
        return this.controllerInput;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public void startGameLoop() {
        this.gameLoop = new GameLoopImpl(this, this.view);
        new Thread(this.gameLoop).start();
    }

    @Override
    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

    @Override
    public void initializeObjects() {
        this.controllerObjects = new ControllerObjects(this.factoryCollision, this.world.getPlayer(),
                this.world.getEnemy(), this.world.getPlayerInput(), this.timeToShot);
        AI.initialize(this.world.getBounds(), this.world.getEnemyInput());
        this.controllerInput = new ProcessInputImpl(this.controllerObjects, this.level);
    }

    @Override
    public void setTimeToShot(final double time) {
        this.timeToShot = time;
    }
}
