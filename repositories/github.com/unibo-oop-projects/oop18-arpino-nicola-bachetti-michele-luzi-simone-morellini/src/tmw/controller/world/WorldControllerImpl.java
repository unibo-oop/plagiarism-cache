package tmw.controller.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import tmw.common.Dim2D;
import tmw.common.EntityFactory;
import tmw.common.EntityFactoryImpl;
import tmw.common.GameStatesList;
import tmw.common.P2d;
import tmw.controller.camera.CameraController;
import tmw.controller.camera.CameraControllerImpl;
import tmw.controller.entities.AbstarctBulletController;
import tmw.controller.entities.EntityController;
import tmw.controller.entities.MilkController;
import tmw.controller.entities.MilkControllerImpl;
import tmw.controller.hud.HudController;
import tmw.controller.hud.HudControllerImpl;
import tmw.controller.input.PlayerInputControllerImpl;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.BulletEntity;
import tmw.model.entities.Enemy;
import tmw.model.entities.MilkEntity;
import tmw.model.inventory.InventoryImpl;
import tmw.model.objects.BaseGameObject;
import tmw.model.objects.EscapeDoor;
import tmw.model.objects.Trigger;
import tmw.model.world.AbstractWorld;
import tmw.model.world.WorldEvents;
import tmw.view.level.RoomView;
import tmw.view.level.LevelViewEvents;

/**
 * This class implements {@link WorldController}.
 * 
 * @version 1.4
 */
public class WorldControllerImpl extends Observable implements WorldController {

    private int score;
    private final AbstractWorld world;
    private final RoomView levelView;
    private EscapeDoor door;
    private HudController hudController;
    private CameraController cameraController;
    private RoomSwitcherPolicy switcher;
    private MilkController playerController;
    private WorldDispenser room;
    private CopyOnWriteArrayList<EntityController<? extends Enemy>> entitiesLoaded = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<AbstractItemController> items = new CopyOnWriteArrayList<AbstractItemController>();
    private final CopyOnWriteArrayList<AbstarctBulletController> bullets = new CopyOnWriteArrayList<AbstarctBulletController>();
    private List<BaseGameObject> obstacles = new ArrayList<>();
    private CopyOnWriteArrayList<Trigger> triggers = new CopyOnWriteArrayList<>();
    private final EntityFactory factory;

    /**
     * Public constructor.
     * 
     * @param world     world reference
     * @param levelView world view reference
     */
    public WorldControllerImpl(final AbstractWorld world, final RoomView levelView) {
        super();
        this.world = world;
        this.levelView = levelView;
        this.factory = new EntityFactoryImpl(this.levelView.getGameRes());
    }

    @Override
    public final void init() {

        this.hudController = new HudControllerImpl(this);
        this.levelView.setHud(this.hudController.getHud());
        this.cameraController = new CameraControllerImpl(levelView.getCamera(), this.levelView.getHud(),
                this.levelView.getGameRes());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable o, final Object arg) {
        if (arg instanceof Integer) {
            this.incrementScore(((Integer) arg).intValue());
            return;
        }

        if (arg.equals(LevelViewEvents.ZOOM)) {
            this.cameraController.zoomOnPlayer(0.5);
            return;
        }

        if (arg.equals(LevelViewEvents.FULLSCREEN)) {
            this.getView().getMainView().updateGameResolution();
            this.world.setWorldArea(new Rectangle(this.getView().getMainView().getStage().getScene().getWidth(),
                    this.getView().getMainView().getStage().getScene().getHeight()));
            this.cameraController.setResolution(this.getView().getGameRes());
            updateEntitiesHitBox(this.getView().getGameRes());

            return;
        }

        if (arg.equals(LevelViewEvents.WINDOWED)) {
            this.getView().getMainView().updateGameResolution();
            this.world.setWorldArea(new Rectangle(this.getView().getMainView().getStage().getScene().getWidth(),
                    this.getView().getMainView().getStage().getScene().getHeight()));

            this.cameraController.setResolution(this.getView().getMainView().getDefaultGameResolution());
            updateEntitiesHitBox(this.getView().getMainView().getDefaultGameResolution());
            return;
        }

        if (arg.equals(WorldEvents.PLAYER_DEATH)) {
            this.switcher.executeSwitch();
        }
    }

    @Override
    public final void updateEntitiesHitBox(final Dim2D dim) {
        this.factory.setSize(dim);
        this.playerController.resizeEntity(dim);
        entitiesLoaded.forEach(e -> {
            e.resizeEntity(dim);
        });

        this.items.forEach(i -> {
            i.getItem().resetDefaultDimension(dim);
        });

        bullets.forEach(b -> {
            b.resizeEntity(dim);
        });

        obstacles.forEach(o -> {
            o.resetDefaultDimension(dim);
        });
        door.resetDefaultDimension(dim);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPlayer(final Optional<MilkController> playerController) {
        if (!playerController.isPresent()) {
            this.playerController = new MilkControllerImpl(this, new P2d(100, 100),
                    new PlayerInputControllerImpl(getView()), new InventoryImpl());
        } else {

            this.playerController = playerController.get();
        }

        this.world.insertPlayer((MilkEntity) this.playerController.getEntity());
        this.cameraController.associtateToEntity(this.playerController.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void loadRoom(final WorldDispenser room) {

        this.room = room;
        final WorldInitializer initializer = new WorldInitializer(room);
        this.entitiesLoaded = room.getEntities();
        this.items = room.getItems();
        this.triggers = room.getTriggers();
        this.obstacles = room.getObstacles();
        this.door = room.getEscapeDoor();
        initializer.execute(getGameWorld());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntities(final double dt) {
        this.switcher.executeSwitch();
        if (this.entitiesLoaded.isEmpty()) {
            this.door.setTriggered(true);
        }
        this.playerController.update();
        this.bullets.forEach(b -> {
            b.update();
        });

        entitiesLoaded.forEach(e -> {
            e.update();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderEntites() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                levelView.resetViewStack();
                playerController.draw();
                cameraController.followEntiy();
                bullets.forEach(b -> {
                    b.draw();
                });
                items.forEach(i -> {
                    i.draw();
                });
                entitiesLoaded.forEach(e -> {
                    e.draw();
                });
                hudController.draw();
                obstacles.forEach(o -> {
                    getView().getMainView().render(o);
                });

                getView().getMainView().render(door);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<EntityController<? extends Enemy>> getEntitiesLoaded() {
        return this.entitiesLoaded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeEntityController(final EntityController<? extends Enemy> en) {
        if (this.entitiesLoaded.contains(en) || en.getEntity() instanceof Enemy) {
            this.getGameWorld().removeEnemy((Enemy) en.getEntity());
            this.entitiesLoaded.remove(en);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final RoomView getView() {
        return this.levelView;
    }

    @Override
    public final void incrementScore(final int value) {
        this.score += value;
    }

    @Override
    public final void addBullet(final AbstarctBulletController blt) {
        this.bullets.add(blt);
        this.getGameWorld().insertBullet((BulletEntity) blt.getEntity());
    }

    @Override
    public final void removeBullet(final AbstarctBulletController blt) {
        if (this.bullets.contains(blt)) {
            this.getGameWorld().removeBullet((BulletEntity) blt.getEntity());
            this.bullets.remove(blt);
        }
    }

    @Override
    public final void removeItem(final AbstractItemController item) {
        if (this.items.contains(item)) {
            this.getGameWorld().removeItem(item.getItem());
            this.items.remove(item);
        }
    }

    @Override
    public final int getActualScore() {
        return this.score;
    }

    @Override
    public final HudController getHud() {
        return this.hudController;
    }

    @Override
    public final MilkController getPlayer() {
        return this.playerController;
    }

    @Override
    public final AbstractWorld getGameWorld() {
        return this.world;
    }

    @Override
    public final Scene getScene() {
        return this.levelView.getLevelScene();
    }

    @Override
    public final List<BaseGameObject> getObstacleLoaded() {
        return this.obstacles;
    }

    @Override
    public final CopyOnWriteArrayList<Trigger> getTriggers() {
        return this.triggers;
    }

    @Override
    public final CopyOnWriteArrayList<AbstractItemController> getItemLoaded() {
        return this.items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeRoomSwitcher(final Optional<RoomSwitcherPolicy> switcher) {

        if (!switcher.isPresent()) {
            final RoomSwitcherPolicy sw = () -> {
                if (this.door.isTriggered() && this.door.intersect(this.playerController.getEntity())) {
                    setChanged();
                    notifyObservers(GameStatesList.SWITCH_ROOM);
                }

                if (!this.getGameWorld().getPlayer().isPresent()) {
                    setChanged();
                    notifyObservers(WorldEvents.PLAYER_DEATH);
                }
            };

            this.switcher = sw;
        } else {
            this.switcher = switcher.get();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<AbstarctBulletController> getBulletLoaded() {
        return this.bullets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetEntities() {
        entitiesLoaded.clear();
        items.clear();
        bullets.clear();
        obstacles.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void killPlayer() {
        this.getGameWorld().killPlayer(this.playerController.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldDispenser getRoom() {
        return this.room;
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
    public EntityFactory getFactory() {
        return this.factory;
    }
}
