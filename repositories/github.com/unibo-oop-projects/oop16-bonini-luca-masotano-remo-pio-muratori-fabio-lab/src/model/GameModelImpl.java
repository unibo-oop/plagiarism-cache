package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.entities.CharacterType;
import model.ai.StandardAI;
import model.entities.Bullet;
import model.entities.BulletType;
import model.entities.ItemImpl;
import model.entities.Movable;
import model.entities.ItemFactory;
import model.entities.Player;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;
import model.hitbox.HitboxRectangle;
import model.entities.Character;
import model.map.Map;
import model.map.MapImpl;
import model.map.StandardRoom;
import model.strategies.InputMovement;
import model.strategies.InputShoot;
import model.utils.Collisions;
import model.utils.Direction;
import model.utils.ModelVariables;

/**
 * Defines the main class of the model. Contains all the model entities and
 * performs all the needed operation to update this entities.
 *
 */
public final class GameModelImpl implements Serializable, GameModel {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 4683524972145592356L;
    private final Map m;
    private final Character p;
    private final Collection<Bullet> bulletsEnemy;
    private final Collection<Bullet> bulletsPlayer;
    private final Collection<Bullet> deadBulletsPlayer;
    private final Collection<Bullet> deadBulletsEnemy;
    private boolean doorCollision;
    private boolean gameOver;
    private final boolean godMode;

    /**
     * Constructor of the class GameModel. Initialize all the necessary
     * variables.
     * 
     * @param godMode
     *            True if the player cannot die, false otherwise.
     */
    public GameModelImpl(final boolean godMode) {
        final CharacterType cp = CharacterType.PLAYER;
        final HitboxRectangle space = StandardRoom.getRoomSpace();

        this.m = new MapImpl();
        this.p = new Player(
                new HitboxCircle(space.getX() + space.getWidth() / 2, space.getY() + space.getHeight() / 2,
                        cp.getRadius()),
                new StandardAI(new InputMovement(), new InputShoot()), cp.getSteps(), cp.getDamage(), cp.getLife(),
                cp.getKnockbackDelay(), cp.getFireRate(), BulletType.BULLET_PLAYER.getDamage(),
                BulletType.BULLET_PLAYER.getRange(), BulletType.BULLET_PLAYER.getSteps());

        this.bulletsEnemy = new ArrayList<>();
        this.bulletsPlayer = new ArrayList<>();
        this.deadBulletsEnemy = new ArrayList<>();
        this.deadBulletsPlayer = new ArrayList<>();
        this.doorCollision = true;
        this.gameOver = false;
        this.godMode = godMode;
        ModelVariables.setPlayerHitbox(this.p.getHitbox());
    }

    @Override
    public void update(final Collection<Direction> pmove, final Collection<Direction> playerShoot, final double delta) {
        final List<Direction> d = new ArrayList<>();

        ModelVariables.setEnemies(this.m.getCurrentRoom().getEnemies());

        /* Move and collision with enemies */
        m.getCurrentRoom().getEnemies().forEach(t -> {
            t.move(delta);
            if (!Collisions.entityCollision(t, p).isEmpty()) {
                p.takeDamage(t.getCollisionDamage());
            }
            d.addAll(Collisions.entityCollision(t, p));
        });

        pmove.removeAll(d);

        ModelVariables.setInputs(pmove, playerShoot);
        p.move(delta);
        ModelVariables.setPlayerHitbox(p.getHitbox());

        /* updating bullets */
        this.updateBullets(bulletsPlayer, delta);
        this.updateBullets(bulletsEnemy, delta);

        bulletsPlayer.addAll(p.shoot(delta));
        m.getCurrentRoom().getEnemies().forEach(t -> bulletsEnemy.addAll(t.shoot(delta)));

        bulletsPlayer
                .removeAll(bulletsPlayer.stream().filter(t -> m.getCurrentRoom().getEnemies().stream().anyMatch(t2 -> {
                    if (!Collisions.entityCollision(t, t2).isEmpty()) {
                        t2.takeDamage(t.getCollisionDamage());
                        return true;
                    }
                    return false;
                })).collect(Collectors.toList()));
        bulletsPlayer.removeAll(bulletsPlayer.stream().filter(t -> {
            if (t.isDead()) {
                deadBulletsPlayer.add(t);
                return true;
            }
            return false;
        }).collect(Collectors.toList()));

        bulletsEnemy.removeAll(bulletsEnemy.stream().filter(t -> {
            if (!Collisions.entityCollision(p, t).isEmpty()) {
                p.takeDamage(t.getCollisionDamage());
                return true;
            }
            if (t.isDead()) {
                deadBulletsEnemy.add(t);
                return true;
            }
            return false;
        }).collect(Collectors.toList()));

        /*removing items and enemy spawn*/
        final Collection<ItemImpl> c = new ArrayList<>();
        m.getCurrentRoom().getEnemies().removeAll(m.getCurrentRoom().getEnemies().stream().filter(t -> {
            if (t.getLife() <= 0) {
                final Optional<ItemImpl> o = ItemFactory.randomItemDrop(t.getHitbox());
                if (!o.equals(Optional.empty())) {
                    c.add(o.get());
                }
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList()));

        m.getCurrentRoom().addAllItems(c); // adding items

        m.getCurrentRoom().getAllItems().removeAll(m.getCurrentRoom().getAllItems().stream().filter(i -> {
            if (rectangleCircleCollisions(p.getHitbox(), i.getHitbox())) {
                p.pickUpItem(i);
                return true;
            }
            return false;
        }).collect(Collectors.toList()));

        if ((p.getLife() <= 0 && !this.godMode) || m.isBossDead()) {
            gameOver = true;
        }

        checkDoorCollision(p);
        Collisions.checkBoundaryCollision(p.getHitbox());
    }

    private void updateBullets(final Collection<Bullet> bullets, final double delta) {
        bullets.removeAll(bullets.stream().filter(t -> Collisions.checkBoundaryCollision(t.getHitbox()))
                .collect(Collectors.toList()));
        bullets.forEach(b -> b.move(delta));
    }

    private boolean rectangleCircleCollisions(final HitboxCircle c, final HitboxRectangle r) {
        final double distX = Math.abs(c.getX() - r.getX() - r.getWidth() / 2);
        final double distY = Math.abs(c.getY() - r.getY() - r.getHeight() / 2);

        if (distX > ((r.getWidth() / 2) + c.getRadius()) || distY > ((r.getHeight() / 2) + c.getRadius())) {
            return false;
        }

        if (distX <= (r.getWidth() / 2) || distY <= (r.getHeight() / 2)) {
            return true;
        }

        final double dx = distX - (r.getWidth() / 2);
        final double dy = distY - (r.getHeight() / 2);
        return (dx * dx + dy * dy <= (c.getRadius() * c.getRadius()));
    }

    @Override
    public Character getPlayer() {
        return p;
    }

    @Override
    public Collection<Bullet> getDeadBullets() {
        final Collection<Bullet> c = new ArrayList<>();
        c.addAll(deadBulletsEnemy);
        c.addAll(deadBulletsPlayer);
        deadBulletsEnemy.clear();
        deadBulletsPlayer.clear();
        return c;
    }

    @Override
    public void clearBullets() {
        this.bulletsEnemy.clear();
        this.bulletsPlayer.clear();
    }

    @Override
    public Collection<Bullet> getPlayerBullets() {
        return this.bulletsPlayer;
    }

    @Override
    public Collection<Bullet> getEnemiesBullets() {
        return this.bulletsEnemy;
    }

    @Override
    public Map getMap() {
        return m;
    }

    @Override
    public boolean roomChanged() {
        final boolean result = doorCollision;
        doorCollision = false;
        return result;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    private void changeRoom(final Direction cd) {
        final Hitbox h = StandardRoom.getExitCoordinate(cd);
        m.changeRoomByDoor(cd);
        p.getHitbox().changePosition(h.getX(), h.getY());
        doorCollision = true;
        bulletsEnemy.clear();
        bulletsPlayer.clear();
        deadBulletsEnemy.clear();
        deadBulletsPlayer.clear();
    }

    private Collection<Direction> checkDoorCollision(final Movable a) {
        final HitboxCircle c = a.getHitbox();
        final Collection<Direction> d = new ArrayList<>();
        final List<Direction> doors = m.getCurrentRoom().getDoors();

        if (m.getCurrentRoom().getEnemies().size() <= 0 || this.godMode) {
            doors.forEach(t -> {
                if (this.rectangleCircleCollisions(c, StandardRoom.getDoor(t))) {
                    d.add(t);
                    changeRoom(t);
                }
            });
        }
        return d;
    }
}
