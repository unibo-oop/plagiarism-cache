package tmw.common;

import tmw.model.entities.EnemyAbbraccio;
import tmw.model.entities.BossEntity;
import tmw.model.entities.BossEntityImpl;
import tmw.model.entities.BulletEntity;
import tmw.model.entities.BulletEntityImpl;
import tmw.model.entities.Enemy;
import tmw.model.entities.MilkEntity;
import tmw.model.entities.MilkEntityImpl;
import tmw.model.entities.EnemyStelle;

/**
 * Implementation of the EntityFactory interface.
 *
 */
public class EntityFactoryImpl implements EntityFactory {

    private Dim2D fieldSize;

    /**
     * Construct a new instance of the game factory.
     * 
     * @param fieldSize - the game resolution that is used in the creation of the
     *                  entities
     */
    public EntityFactoryImpl(final Dim2D fieldSize) {
        this.fieldSize = fieldSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(final Dim2D newFieldSize) {
        this.fieldSize = newFieldSize;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public MilkEntity createMilk(final P2d pos, final V2d vel) {
        return new MilkEntityImpl(pos, vel, this.fieldSize);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public BulletEntity createEnemyBullet(final P2d pos, final V2d vel) {
        return new BulletEntityImpl(pos, vel, this.fieldSize);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public BulletEntity createCharacterBullet(final P2d pos, final V2d vel, final int damage) {
        return new BulletEntityImpl(pos, vel, this.fieldSize, damage);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Enemy createAbbraccio(final P2d pos, final V2d vel) {
        return new EnemyAbbraccio(pos, vel, this.fieldSize);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Enemy createStelle(final P2d pos, final V2d vel) {
        return new EnemyStelle(pos, vel, this.fieldSize);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public BossEntity createBoss(final P2d pos, final V2d vel) {
        return new BossEntityImpl(pos, vel, this.fieldSize);
    }
}
