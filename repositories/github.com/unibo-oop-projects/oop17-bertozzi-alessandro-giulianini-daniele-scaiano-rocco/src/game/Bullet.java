package game;

import java.awt.Rectangle;

import utilities.Pair;

/**
 * creating the class bullet which extends entity.
 */
public final class Bullet extends Entity implements BulletInterface {
    private final ID owner;
    private static final int BULLETSIZE = 20;
    private static final int WIDTH = GameImpl.GAMEAREA_WIDTH / 40;
    private static final int HEIGHT = GameImpl.GAMEAREA_HEIGHT / 22;
    private static final int VELOCITY = GameImpl.GAMEAREA_HEIGHT / 50;
    /**
     * basic constructor for the class bullet.
     * @param x of the bullet.
     * @param y of the bullet.
     * @param owner of the bullet.
     */
    public Bullet(final Integer x, final Integer y, final ID owner) {
        super(new Pair<Integer, Integer>(x, y), 0, VELOCITY, ID.BULLET);
        if (owner == ID.PLAYER) {
            setVelocity(getVelocity().getX(), getVelocity().getY() * -1);
        }
        this.owner = owner;
        setHitbox(new Rectangle(this.getPosition().getX() - BULLETSIZE, this.getPosition().getY() - BULLETSIZE, WIDTH, HEIGHT));
    }
    /**
     * alternative constructor.
     * @param x of the bullet.
     * @param y of the bullet.
     * @param velX of the bullet.
     * @param velY of the bullet.
     * @param owner of the bullet.
     */
    public Bullet(final Integer x, final Integer y, final int velX, final int velY, final ID owner) {
        super(new Pair<Integer, Integer>(x, y), velX, velY, ID.BULLET);
        this.owner = owner;
    }

    /**
     * updates the bullet object. if it gets out of the window is set to dead.
     */
    public void update() {
        getPosition().setX(getPosition().getX() + getVelocity().getX());
        getPosition().setY(getPosition().getY() + getVelocity().getY());
        setHitbox(new Rectangle(this.getPosition().getX() - 10, this.getPosition().getY() - 10, BULLETSIZE, BULLETSIZE));
        if (this.getPosition().getY() == GameImpl.GAMEAREA_HEIGHT || this.getPosition().getY() < 0) {
            this.setDead();
        }
    }

    /**
     *sets the bullet to dead when it collides.
     *@param entity which collides with the bullet.
     */
    public void collide(final GameObject entity) {
        this.setDead();
    }

    @Override
    public ID getOwner() {
        return this.owner;
    }
}

