package tmw.model.entities;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;

/**
 * This Class represents the enemy Abbraccio; this particular entity run across
 * the map try to catch the main character.
 */
public class EnemyAbbraccio extends AbstractGameEntity implements Enemy {

    private static final int DEFAULT_HP_ABBRACCIO = 60;
    private static final double DIMENSION_PROPORTION_ABBRACCIO = 0.04;
    private static final double STANDARD_SPEED_ABBRACCIO = 1.5;
    private static final int STANDARD_SIZE = 800;
    private static final int SCORE = 200;
    private static final int CONTACT_DAMAGE = 10;

    /**
     * Construct a new enemy Abbraccio.
     * 
     * @param pos       - the initial position of the enemy as a {@link P2d}
     * @param vel       - the initial velocity of the enemy as a {@link V2d}
     * @param fieldSize - the game resolution used to calculate the enemy's
     *                  dimension
     */
    public EnemyAbbraccio(final P2d pos, final V2d vel, final Dim2D fieldSize) {
        super(GameEntityType.ABBRACCIO, pos, vel, DEFAULT_HP_ABBRACCIO,
                ((STANDARD_SPEED_ABBRACCIO * fieldSize.getWidth()) / STANDARD_SIZE),
                new Dim2D(fieldSize.getWidth() * DIMENSION_PROPORTION_ABBRACCIO,
                        fieldSize.getWidth() * DIMENSION_PROPORTION_ABBRACCIO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return SCORE;
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
        this.resizeUpdate(dimension, STANDARD_SPEED_ABBRACCIO, DIMENSION_PROPORTION_ABBRACCIO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getContactDamage() {
        return CONTACT_DAMAGE;
    }
}
