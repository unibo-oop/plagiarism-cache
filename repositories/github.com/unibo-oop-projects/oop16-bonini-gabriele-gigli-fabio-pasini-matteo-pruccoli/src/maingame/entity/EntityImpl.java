package maingame.entity;

import java.awt.Rectangle;
import java.util.List;

import maingame.entity.item.Item;
import maingame.entity.mob.Mob;
import maingame.entity.mob.MobImpl;
import maingame.entity.mob.player.Player;
import maingame.graphics.Sprite;
import maingame.level.Level;
import maingame.level.tile.Tile;
import maingame.level.tile.TileImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Implementazione dell'interfaccia entity.
 */
public abstract class EntityImpl implements Entity {

    private Sprite sprite;
    private Vector2<Integer> position;
    private boolean removed;
    private Level level;
    private Rectangle hitbox;

    /**
     * Crea un Entity, setta la rimozione a false.
     */
    public EntityImpl() {
        this.removed = false;
    }

    /**
     * Crea un Entity in una specifica posizione.
     * 
     * @param position
     *            posizione dell' entità
     */
    public EntityImpl(final Vector2<Integer> position) {
        this.position = new Vector2Impl<Integer>(position);
    }

    @Override
    public abstract void update();

    @Override
    public void setRemove() {
        removed = true;
    }

    @Override
    public Vector2<Integer> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Vector2<Integer> position) {
        this.position = position;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }

    @Override
    public void init(final Level level) {
        this.level = level;

    }

    @Override
    public boolean tileCollision(final Vector2<Integer> movement) {
        /* se x < 0 direzione ovest */
        if (movement.getX() < 0) {
            for (int y = (int) hitbox.getMinY(); y < (int) hitbox.getMaxY(); y++) {
                if (isSolidTile(new Vector2Impl<Integer>((int) hitbox.getMinX(), y), movement)) {
                    return true;
                }
            }
        } else if (movement.getX() > 0) {
            for (int y = (int) hitbox.getMinY(); y < (int) hitbox.getMaxY(); y++) {
                if (isSolidTile(new Vector2Impl<Integer>((int) hitbox.getMaxX(), y), movement)) {
                    return true;
                }
            }
        }
        /* se y > 0 direzione nord */
        if (movement.getY() > 0) {
            for (int x = (int) hitbox.getMinX(); x < (int) hitbox.getMaxX(); x++) {
                if (isSolidTile(new Vector2Impl<Integer>(x, (int) hitbox.getMaxY()), movement)) {
                    return true;
                }
            }
        } else if (movement.getY() < 0) {
            for (int x = (int) hitbox.getMinX(); x < (int) hitbox.getMaxX(); x++) {
                if (isSolidTile(new Vector2Impl<Integer>(x, (int) hitbox.getMinY()), movement)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Mob mobCollision(final Vector2<Integer> movement, final boolean projectile) {
        final int range = 20;
        final List<Mob> mobs = level.getMobs(this, range);

        for (final Mob e : mobs) {
            if (projectile && e instanceof Player) {
                continue;
            }
            if (intersectLine(e, movement)) {
                return e;
            }
        }

        return null;
    }

    @Override
    public Item itemCollision(final Vector2<Integer> movement) {
        final int range = 60;
        final List<Item> items = getLevel().getItems(this, range);

        for (final Item e : items) {
            if (intersectLine(e, movement)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Controlla se la tile verso la quale mi muovo è solida.
     * 
     * @param position
     *            posizione dove si trova
     * @param movement
     *            coordinate che esprimono la direzione
     * @return true se la tile è solida
     */
    protected boolean isSolidTile(final Vector2<Integer> position, final Vector2<Integer> movement) {
        final Tile newTile = level
                .getTile(new Vector2Impl<Integer>(Math.floorDiv(position.getX() + movement.getX(), TileImpl.TILE_SIZE),
                        Math.floorDiv(position.getY() + movement.getY(), TileImpl.TILE_SIZE)));

        return newTile.isSolid() || (this instanceof Mob && newTile.isSwimmable() && !((MobImpl) this).isCanSwim());
    }

    /**
     * Setter livello.
     * 
     * @param lev
     *            livello al quale farà riferimento l'entità
     */
    protected void setLevel(final Level lev) {
        this.level = lev;
    }

    /**
     * Livello al quale l'entità fa rifermento.
     * 
     * @return getter di level
     */
    protected Level getLevel() {
        return this.level;
    }

    /**
     * Setta l'hitbox dell'entità.
     * 
     * @param hit
     *            setter di hitbox
     */
    protected void setHitbox(final Rectangle hit) {
        this.hitbox = hit;
    }

    /**
     * Setta una specifica sprite per l'entità.
     * 
     * @param newsprite
     *            nuova sprite
     */
    protected void setSprite(final Sprite newsprite) {
        this.sprite = newsprite;
    }

    private boolean intersectLine(final Entity e, final Vector2<Integer> movement) {

        final Rectangle nextHitbox = new Rectangle((int) getHitbox().getMinX() + movement.getX(),
                (int) getHitbox().getMinY() + movement.getY(), getHitbox().width, getHitbox().height);

        if (movement.getX() < 0 && nextHitbox.intersectsLine(e.getHitbox().getMaxX(), e.getHitbox().getMinY(),
                e.getHitbox().getMaxX(), e.getHitbox().getMaxY())) {
            return true;
        }
        if (movement.getX() > 0 && nextHitbox.intersectsLine(e.getHitbox().getMinX(), e.getHitbox().getMinY(),
                e.getHitbox().getMinX(), e.getHitbox().getMaxY())) {
            return true;
        }
        if (movement.getY() > 0 && nextHitbox.intersectsLine(e.getHitbox().getMinX(), e.getHitbox().getMinY(),
                e.getHitbox().getMaxX(), e.getHitbox().getMinY())) {
            return true;
        }
        if (movement.getY() < 0 && nextHitbox.intersectsLine(e.getHitbox().getMinX(), e.getHitbox().getMaxY(),
                e.getHitbox().getMaxX(), e.getHitbox().getMaxY())) {
            return true;
        }
        return (movement.getX() == 0 && movement.getY() == 0 && nextHitbox.intersects(e.getHitbox()));
    }
}
