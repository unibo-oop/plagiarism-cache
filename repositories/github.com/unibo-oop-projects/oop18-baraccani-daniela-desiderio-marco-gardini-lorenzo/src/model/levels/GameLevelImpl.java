package model.levels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Model;
import model.entities.Hero;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.physics.CollisionManager;
import model.physics.CollisionManagerImpl;

/**
 * The implementation of {@link GameLevel}.
 */
public class GameLevelImpl implements GameLevel {

    private final int levelNumber;
    private final CollisionManager collisionManager;
    private final List<EntityMovable> toRecheckEntities;
    private final List<Entity> entities;
    private final Model model;
    private Optional<Hero> hero;

    /**
     * Constructors creates data structures for containing level entities and
     * updated entities.
     * 
     * @param model       used by entities.
     * @param levelNumber The actual level number in the game
     */
    public GameLevelImpl(final Model model, final int levelNumber) { // Constructor
        this.collisionManager = new CollisionManagerImpl(model);
        this.model = model;
        this.toRecheckEntities = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.hero = Optional.empty();
        this.levelNumber = levelNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity entity) {
        if (entity.getType().equals("Hero")) {
            this.hero = Optional.of((Hero) entity);
        }
        this.entities.add(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final String type, final int x, final int y, final int width, final int height,
            final EntityDirection direction) { // put entity
        final String error = "Can't create object ";
        try {
            final Class<?> entity = Class.forName("model.entities." + type); // Generate class from his string name
            Constructor<?> constructor = null;
            try {
                switch (entity.getSuperclass().getSimpleName()) {
                case "EntityMovable":
                    constructor = entity.getConstructor(Model.class, int.class, int.class, int.class, int.class,
                            EntityDirection.class);
                    this.addEntity((Entity) constructor.newInstance(this.model, x, y, width, height, direction));
                    break;
                case "EntityAbstract":
                    constructor = entity.getConstructor(Model.class, int.class, int.class, int.class, int.class);
                    this.addEntity((Entity) constructor.newInstance(this.model, x, y, width, height));
                    break;
                default:
                    this.model.getController().handleError(error + type + ", parent type ("
                            + entity.getSuperclass().getSimpleName() + ") not recognized", true);
                }
            } catch (NoSuchMethodException | SecurityException e) {
                this.model.getController().handleError(error + type + " (Parent type: "
                        + entity.getSuperclass().getSimpleName() + "), can't find the corresponding constructor", true);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                this.model.getController()
                        .handleError(error + type + " (Parent type: " + entity.getSuperclass().getSimpleName() + "),"
                                + (Objects.isNull(constructor) ? "constructor unavailable"
                                        : " be sure that file level has every required field "
                                                + Arrays.toString(constructor.getParameters())),
                                true);
            }
        } catch (ClassNotFoundException e) {
            this.model.getController().handleError(error + type + ", type not found", true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAll() {
        this.entities.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getEntities() {
        return this.entities.stream().filter(e -> e.isVisible()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hero getHero() {
        return this.hero.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToRecheckEntity(final EntityMovable entity) {
        if (!this.toRecheckEntities.contains(entity)) {
            this.toRecheckEntities.add(Objects.requireNonNull(entity));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntityMovable> getToRecheckEntities() {
        final List<EntityMovable> moved = this.toRecheckEntities.stream().filter(e -> e.isVisible())
                .collect(Collectors.toList());
        this.toRecheckEntities.clear();
        return moved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevelNumber() {
        return this.levelNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

}
