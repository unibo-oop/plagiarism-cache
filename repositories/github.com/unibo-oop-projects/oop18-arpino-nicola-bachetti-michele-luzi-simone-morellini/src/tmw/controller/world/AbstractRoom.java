package tmw.controller.world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.controller.entities.EntityController;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.Enemy;
import tmw.model.objects.BaseGameObject;
import tmw.model.objects.EscapeDoor;
import tmw.model.objects.Trigger;
import tmw.model.world.GameWorld;
import utils.Rooms;

/**
 * This class represents the basic room of the game. 
 */
public abstract class AbstractRoom implements WorldDispenser {

    private final WorldController worldController;
    private final CopyOnWriteArrayList<EntityController<? extends Enemy>> entities = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<AbstractItemController> items = new CopyOnWriteArrayList<AbstractItemController>();
    private final List<BaseGameObject> obstacles = new ArrayList<>();
    private final CopyOnWriteArrayList<Trigger> triggers = new CopyOnWriteArrayList<Trigger>();
    private final EscapeDoor door;
    private final Dim2D gameRes;
    private final Rooms type;

    /**
     * This represents the standard ideal map proportion. Should be used when
     * entities are located in world in a specific position.
     */
    public static final Dim2D MAPPROPORTIONS = new Dim2D(800, 600);

    /**
     * Public constructor.
     * 
     * @param worldController {@link WorldController} world controller reference
     * @param type            {@link Rooms} type of the room
     */
    public AbstractRoom(final WorldController worldController, final Rooms type) {
        this.worldController = worldController;
        this.type = type;
        this.gameRes = worldController.getView().getGameRes();
        this.door = new EscapeDoor(new P2d(10, 10), this.gameRes);
    }

    /**
     * This method should populate world with entities by adding
     * enemies/items/obstacles to their respective lists in order to be loaded when
     * populate method is called. This is the main difference between rooms so it
     * represents the specific strategy to apply.
     */
    protected abstract void createEntities();

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateWorld(final GameWorld world) {

        this.createEntities();
        world.populateWorld(entities, items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<EntityController<? extends Enemy>> getEntities() {
        return this.entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<AbstractItemController> getItems() {
        return this.items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BaseGameObject> getObstacles() {
        return this.obstacles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Trigger> getTriggers() {
        return this.triggers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EscapeDoor getEscapeDoor() {
        return this.door;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rooms getRoomType() {
        return this.type;
    }

    /**
     * Getter for local world controller.
     * 
     * @return {@link WorldController} controller reference.
     */
    public WorldController getWorldController() {
        return this.worldController;
    }

    /**
     * Getter for game resolution.
     * 
     * @return {@link Dimension2D} resolution
     */
    public Dim2D getGameRes() {
        return this.gameRes;
    }
}
