package ballblast.model.physics;

/**
 * Represents a simple collision between two GameObjects.
 */
public class Collision {

    private final Collidable obj;
    private final Collidable other;

    /**
     * The class constructor.
     * 
     * @param object the CollisionComponent of the game object that
     *               collides.
     * @param other  the CollisionComponent of the game object that loses
     *               out the collision.
     */
    public Collision(final Collidable object, final Collidable other) {
        this.obj = object;
        this.other = other;
    }

    /**
     * The getter for the GameObject that collides.
     * 
     * @return the {@link Collidable} that collides.
     */
    public Collidable getObj() {
        return this.obj;
    }

    /**
     * The getter for the GameObject that loses out the {@link Collision}.
     * 
     * @return the {@link Collidable} that lose out the collision.
     */
    public Collidable getOther() {
        return this.other;
    }

}
