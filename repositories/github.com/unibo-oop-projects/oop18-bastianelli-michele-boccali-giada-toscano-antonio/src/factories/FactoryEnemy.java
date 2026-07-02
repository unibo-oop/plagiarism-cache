package factories;

import org.jbox2d.common.Vec2;

import controller.entities.Enemy;
import controller.entities.Entity;
import enumerators.EnemyCharacter;
import enumerators.SpecificType;
import model.components.AttackImpl;
import model.components.CoinValueImpl;
import model.entities.EnemyModel;
import model.entities.EntityModel;
import model.physics.BodyBuilderImpl;
import model.physics.Size2D;
import view.entities.EntityView;

/**
 * Factory to generate a new enemy.
 */
public class FactoryEnemy extends GenericFactory {

    @Override
    protected final Entity createEntity(final SpecificType type, final Vec2 position) {
        final EnemyModel model = (EnemyModel) createModel(type, position);
        final EntityView view = createView(model);
        return new Enemy(model, view);
    }

    @Override
    protected final EntityModel createModel(final SpecificType type, final Vec2 position) {
        if (type instanceof EnemyCharacter) {
            final EnemyCharacter c = EnemyCharacter.class.cast(type);
            switch (c) {
            case GOOMBA:
                return new Goomba(position);
            case FROSTY:
                return new Frosty(position);
            case BOMB:
                return new Bomb(position);
            case PARABEETLE:
                return new Parabeetle(position);
            default:
                throwErrorBadCharacter(c.toString());
            }
        } else {
            throwErrorBadClass(type.getClass());
        }
        return null;
    }

    /**
     * Parabeetle model class. Specialization of the EnemyModel class
     */
    private static final class Parabeetle extends EnemyModel {

        private static final EnemyCharacter E_TYPE = EnemyCharacter.PARABEETLE;

        Parabeetle(final Vec2 position) {
            super(E_TYPE, BodyBuilderImpl.getInstance()
                            .position(position)
                            .size(new Size2D(E_TYPE.getWidth(), E_TYPE.getHeight()))
                            .moveable(false)
                            .solid(true)
                            .restitution(0)
                            .gravity(0)
                            .build());
            this.addDefaultEnemyLife();
            this.addDefaultEnemyCollisionEffect();
            this.addDefaultEnemyAttack();
        }

    }

    /**
     * Goomba model class. Specialization of the EnemyModel class
     */
    private static final class Goomba extends EnemyModel {

        private static final EnemyCharacter E_TYPE = EnemyCharacter.GOOMBA;

        Goomba(final Vec2 position) {
            super(E_TYPE, BodyBuilderImpl.getInstance()
                                .position(position)
                                .size(new Size2D(E_TYPE.getWidth(), E_TYPE.getHeight()))
                                .gravity(0)
                                .moveable(false)
                                .restitution(0)
                                .solid(true)
                                .build());
            this.addDefaultEnemyLife();
            this.addDefaultEnemyCollisionEffect();
            this.addDefaultEnemyAttack();
        }
    }

    /**
     * Bomb model class. Specialization of the EnemyModel class
     */
    private static final class Bomb extends EnemyModel {

        private static final EnemyCharacter E_TYPE = EnemyCharacter.BOMB;
        private static final int COIN_VALUE = 1;

        Bomb(final Vec2 position) {
            super(E_TYPE, BodyBuilderImpl.getInstance()
                            .position(position)
                            .size(new Size2D(E_TYPE.getWidth(), E_TYPE.getHeight()))
                            .moveable(false)
                            .solid(true)
                            .restitution(0)
                            .gravity(0)
                            .build());
            this.addDefaultEnemyLife();
            this.addDefaultEnemyCollisionEffect();
            this.addDefaultEnemyAttack();
            this.add(new CoinValueImpl(COIN_VALUE));
        }

    }

    /**
     * Frosty model class. Specialization of the EnemyModel class
     */
    private static final class Frosty extends EnemyModel {

        private static final EnemyCharacter E_TYPE = EnemyCharacter.FROSTY;
        private static final int ATTACK_VALUE = 2;
        private static final int COIN_VALUE = 2;

        Frosty(final Vec2 position) {
            super(E_TYPE, BodyBuilderImpl.getInstance()
                                .position(position)
                                .size(new Size2D(E_TYPE.getWidth(), E_TYPE.getHeight()))
                                .moveable(false)
                                .solid(true)
                                .restitution(0)
                                .gravity(0)
                                .build());
            this.addDefaultEnemyLife();
            this.addDefaultEnemyCollisionEffect();
            this.add(new CoinValueImpl(COIN_VALUE));
            this.add(new AttackImpl(ATTACK_VALUE));
        }
    }

}
