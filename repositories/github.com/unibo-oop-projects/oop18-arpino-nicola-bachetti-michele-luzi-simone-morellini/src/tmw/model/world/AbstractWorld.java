package tmw.model.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import javafx.scene.shape.Rectangle;
import tmw.common.P2d;
import tmw.controller.entities.EntityController;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.BulletEntity;
import tmw.model.entities.Enemy;
import tmw.model.entities.MilkEntity;
import tmw.model.item.Item;
import tmw.model.objects.GameObject;

/**
 * Game world abstract class which implements {@link GameWorld} interface.
 * Should be extended by each gameLevel.
 * 
 * @version 1.3
 */
public abstract class AbstractWorld extends Observable implements GameWorld {

    private Rectangle worldArea;
    private final List<Item> wolrdItems = new ArrayList<Item>();
    private final List<Enemy> enemies = new ArrayList<Enemy>();
    private final List<BulletEntity> bullets = new ArrayList<BulletEntity>();
    private final List<GameObject> obstacles = new ArrayList<GameObject>();
    private Optional<MilkEntity> player = Optional.empty();
    private Optional<P2d> playerPosition = Optional.empty();

    /**
     * Public constructor.
     * 
     * @param area {@link Rectangle} dimension of game world area
     */
    public AbstractWorld(final Rectangle area) {
        super();
        this.worldArea = area;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void populateWorld(final List<? extends EntityController<?>> entities,
            final List<AbstractItemController> items) {

        items.forEach(i -> {

            wolrdItems.add(i.getItem());
        });

        entities.forEach((c) -> {
            if (c.getEntity() instanceof Enemy) {
                this.insertEnemy((Enemy) c.getEntity());
            }

            if (c.getEntity() instanceof MilkEntity) {
                this.insertPlayer((MilkEntity) c.getEntity());
            }

        });
    }

    @Override
    public final void insertItem(final Item obj) {
        this.wolrdItems.add(obj);
    }

    @Override
    public final void removeItem(final Item obj) {
        if (this.wolrdItems.contains(obj)) {
            this.wolrdItems.remove(obj);
            setChanged();
            notifyObservers(WorldEvents.ITEMPICK);
        }
    }

    @Override
    public final void insertEnemy(final Enemy enemy) {
        if (this.checkInBounds(enemy)) {
            this.enemies.add(enemy);
        }
    }

    @Override
    public final void removeEnemy(final Enemy enemy) {
        if (this.enemies.contains(enemy)) {
            this.enemies.remove(enemy);
            setChanged();
            notifyObservers(WorldEvents.ENEMY_KILLED);
            notifyObservers(Integer.valueOf(enemy.getScore()));
        }
    }

    @Override
    public final void insertBullet(final BulletEntity bullet) {
        if (checkInBounds(bullet)) {
            this.bullets.add(bullet);
        }
        setChanged();
        notifyObservers(WorldEvents.SHOOT);
    }

    @Override
    public final void removeBullet(final BulletEntity bullet) {
        if (this.bullets.contains(bullet)) {
            this.bullets.remove(bullet);
        }
    }

    @Override
    public final void insertObstacle(final GameObject obstacle) {
        if (this.checkInBounds(obstacle)) {
            this.obstacles.add(obstacle);
        }
    }

    @Override
    public final void removeObstacle(final GameObject obstacle) {
        if (this.obstacles.contains(obstacle)) {
            this.obstacles.remove(obstacle);
            setChanged();
            notifyObservers(WorldEvents.OBSTACLE_DESTROYED);
        }
    }

    @Override
    public final void insertPlayer(final MilkEntity player) {
        if (this.checkInBounds(player)) {
            this.player = Optional.ofNullable(player);
            this.playerPosition = Optional.ofNullable(player.getCurrentPos());
        }
    }

    @Override
    public final void killPlayer(final MilkEntity player) {
        this.player = Optional.empty();
        setChanged();
        notifyObservers(WorldEvents.PLAYER_DEATH);
    }

    @Override
    public final Optional<P2d> getEnemyPosition(final Enemy enemy) {
        if (this.enemies.contains(enemy)) {
            return Optional.of(enemy.getCurrentPos());
        }
        return Optional.empty();
    }

    @Override
    public final Optional<P2d> getItemPosition(final Item item) {
        if (this.wolrdItems.contains(item)) {
            return Optional.of(wolrdItems.get(wolrdItems.indexOf(item)).getCurrentPos());
        }
        return Optional.empty();
    }

    @Override
    public final Optional<P2d> getPlayerPosition() {
        return this.playerPosition;
    }

    @Override
    public final List<Item> getItems() {
        final List<Item> out = new ArrayList<Item>(this.wolrdItems);
        return Collections.unmodifiableList(out);
    }

    @Override
    public final List<Enemy> getEnemies() {
        return Collections.unmodifiableList(this.enemies);
    }

    @Override
    public final Optional<MilkEntity> getPlayer() {
        return this.player;
    }

    @Override
    public final List<GameObject> getObstacles() {
        return Collections.unmodifiableList(this.obstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getWorldArea() {
        return this.worldArea;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorldArea(final Rectangle area) {
        this.worldArea = area;
    }
}
