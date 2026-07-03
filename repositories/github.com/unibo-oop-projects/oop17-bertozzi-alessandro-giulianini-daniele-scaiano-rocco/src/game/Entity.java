package game;

import java.awt.Rectangle;

import utilities.Pair;

/**
 * A class describing the basic behavior of every entity in the game.
 */
public abstract class Entity implements GameObject {

    private final ID id;
    private Rectangle hitbox;
    private Pair<Integer, Integer> position;
    private boolean dead;
    private Pair<Integer, Integer> velocity;

    /**
     * @param position the starting position of the entity
     * @param velocityX the starting x velocity of the entity
     * @param velocityY the starting y velocity of the entity
     * @param id the id identifying what this entity is
     */
    public Entity(final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final ID id) {
        this.position = position;
        this.id = id;
        this.hitbox = null;
        this.velocity = new Pair<>(velocityX, velocityY);
    }

    /**
     * @param id id
     */
    public Entity(final ID id) {
        this(null, 0, 0, id);
    }

    @Override
    public abstract void update();

    @Override
    public abstract void collide(GameObject entity);

    /**{@link game.GameObject#isDead()}. */
    @Override
    public boolean isDead() {
        return this.dead;
    }

    @Override
    public final ID getID() {
        return this.id;
    }
 
    /**{@link game.Entity#getHitbox()}. */
    @Override
    public Rectangle getHitbox() {
        return this.hitbox;
    }

    /**{@link game.GameObject#getPosition()}. */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**{@link game.GameObject#getVelocity()}. */
    @Override
    public Pair<Integer, Integer> getVelocity() {
        return this.velocity;
    }

    @Override
    public final void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    /**{@link game.GameObject#setDead()}. */
    @Override
    public void setDead() {
        this.dead = true;
    }

    /** Method to set the life to alive. */
    public void setAlive() {
        this.dead = false;
    }

    /**{@link game.GameObject#setHitbox(Rectangle hitbox)}. */
    @Override
    public void setHitbox(final Rectangle hitbox) {
        this.hitbox = hitbox;
    }
 
    /**
     * @param velocityX velocityX
     * @param velocityY velocityY
     */
    public void setVelocity(final int velocityX, final int velocityY) {
        this.velocity = new Pair<Integer, Integer>(velocityX, velocityY);
    }
}
