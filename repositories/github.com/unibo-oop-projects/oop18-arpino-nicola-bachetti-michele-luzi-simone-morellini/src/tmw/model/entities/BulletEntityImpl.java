package tmw.model.entities;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;

/**
 * This Class represents a bullet in the game.
 */
public class BulletEntityImpl extends AbstractGameEntity implements BulletEntity {

    private static final int DEFAULT_LIFE_BULLET = 1;
    private static final double DIMENSION_PROPORTIONS_BULLET = 0.03;
    private static final int STANDARD_SIZE = 800;
    private static final int DEFAULT_DAMAGE = 20;
    private static final double STANDARD_SPEED_BULLET = 5;

    private final int damage;

    /**
     * Construct a new bullet. This constructor is private because has the only
     * purpose to call the super constructor with the information passed from the
     * other two public constructors.
     * 
     * @param type      - the type of bullet to construct
     * @param pos       - the initial position of the bullet as a {@link P2d}
     * @param vel       - the initial velocity of the bullet as a {@link V2d}
     * @param fieldSize - the game resolution used to calculate the dimension of the
     *                  bullet
     * @param damage    - the damage that this bullet does when hits an entity
     */
    public BulletEntityImpl(final GameEntityType type, final P2d pos, final V2d vel, final Dim2D fieldSize,
            final int damage) {
        super(type,
                new P2d(pos.getX() - ((fieldSize.getWidth() * DIMENSION_PROPORTIONS_BULLET) / 2),
                        pos.getY() - ((fieldSize.getWidth() * DIMENSION_PROPORTIONS_BULLET) / 2)),
                vel, DEFAULT_LIFE_BULLET, STANDARD_SPEED_BULLET * fieldSize.getWidth() / STANDARD_SIZE,
                new Dim2D(fieldSize.getWidth() * DIMENSION_PROPORTIONS_BULLET,
                        fieldSize.getWidth() * DIMENSION_PROPORTIONS_BULLET));
        this.damage = damage;
    }

    /**
     * Construct a new bullet with default damage. This type of bullet is used by
     * the enemy.
     * 
     * @param pos       - the initial position of the bullet as a {@link P2d}
     * @param vel       - the initial velocity of the bullet as a {@link V2d}
     * @param fieldSize - the game resolution used to calculate the dimension of the
     *                  bullet
     */
    public BulletEntityImpl(final P2d pos, final V2d vel, final Dim2D fieldSize) {
        this(GameEntityType.BULLET, pos, vel, fieldSize, DEFAULT_DAMAGE);
    }

    /**
     * Construct a new bullet with a certain damage. This type of bullet is used by
     * the main character.
     * 
     * @param pos       - the initial position of the bullet as a {@link P2d}
     * @param vel       - the initial velocity of the bullet as a {@link V2d}
     * @param fieldSize - the game resolution used to calculate the dimension of the
     *                  bullet
     * @param damage    - the damage that this bullet does when hits an entity
     */
    public BulletEntityImpl(final P2d pos, final V2d vel, final Dim2D fieldSize, final int damage) {
        this(GameEntityType.PLAYER_BULLET, pos, vel, fieldSize, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean readyToShoot() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        this.resizeUpdate(dimension, STANDARD_SPEED_BULLET, DIMENSION_PROPORTIONS_BULLET);
    }
}
