package factories;

import org.jbox2d.common.Vec2;

import controller.entities.Entity;
import controller.entities.Player;
import enumerators.PlayerCharacter;
import enumerators.SpecificType;
import model.components.AttackImpl;
import model.components.ComandableMovement;
import model.components.JumpImpl;
import model.entities.EntityModel;
import model.entities.PlayerModel;
import model.physics.BodyBuilderImpl;
import model.physics.Size2D;
import view.entities.EntityView;

/**
 * Factory to generate the {@link Player}.
 */
public class FactoryPlayer extends GenericFactory {

    @Override
    protected final Entity createEntity(final SpecificType type, final Vec2 position) {
        final PlayerModel model = (PlayerModel) createModel(type, position);
        final EntityView view = createView(model);
        return new Player(model, view);
    }

    @Override
    protected final EntityModel createModel(final SpecificType type, final Vec2 position) {
        if (type instanceof PlayerCharacter) {
            final PlayerCharacter c = PlayerCharacter.class.cast(type);
            switch (c) {
            case BIRD:
                return new Bird(position);
            case SHEEP:
                return new Sheep(position);
            case TUX:
                return new Tux(position);
            default:
                throwErrorBadCharacter(c.toString());
            }
        } else {
            throwErrorBadClass(type.getClass());
        }
        return null;
    }

    /**
     *  Bird player character.
     */
    private static final class Bird extends PlayerModel {
        private static final PlayerCharacter TYPE = PlayerCharacter.BIRD;

        Bird(final Vec2 position) {
            super(TYPE, BodyBuilderImpl.getInstance()
                            .position(position)
                            .size(new Size2D(TYPE.getWidth(), TYPE.getHeight()))
                            .moveable(true)
                            .solid(true)
                            .friction(0)
                            .density(1)
                            .restitution(0)
                            .gravity(1)
                            .build());
            this.addDefaultPlayerJump();
            this.addDefaultPlayerMovement();
            this.addDefaultPlayerAttack();
            this.addDefaultPlayerLife();
            this.addDefaultPlayerCollisionEffect();
        }
    }

    /**
     * Sheep player character.
     */
    private static final class Sheep extends PlayerModel {
        private static final PlayerCharacter TYPE = PlayerCharacter.SHEEP;

        private static final float JUMP = 80.0f;
        private static final float SPEED = 2.1f;
        private static final float GRAVITY = 0.9f;

        Sheep(final Vec2 position) {
            super(TYPE, BodyBuilderImpl.getInstance()
                    .position(position)
                    .size(new Size2D(TYPE.getWidth(), TYPE.getHeight()))
                    .moveable(true)
                    .density(1)
                    .friction(0)
                    .restitution(0)
                    .gravity(GRAVITY)
                    .build());
            this.addDefaultPlayerAttack();
            this.addDefaultPlayerLife();
            this.add(new ComandableMovement(this, SPEED));
            this.add(new JumpImpl(this, JUMP));
            this.addDefaultPlayerCollisionEffect();
        }
    }

    /**
     * Tux player character.
     */
    private static final class Tux extends PlayerModel {
        private static final PlayerCharacter TYPE = PlayerCharacter.TUX;

        private static final float JUMP = 85.0f;
        private static final float SPEED = 2.2f;
        private static final float GRAVITY = 0.8f;
        private static final int ATTACK = 2;

        Tux(final Vec2 position) {
            super(TYPE, BodyBuilderImpl.getInstance()
                            .position(position)
                            .size(new Size2D(TYPE.getWidth(), TYPE.getHeight()))
                            .moveable(true)
                            .density(1)
                            .friction(0)
                            .restitution(0)
                            .gravity(GRAVITY)
                            .build());
            this.addDefaultPlayerLife();
            this.add(new AttackImpl(ATTACK));
            this.add(new ComandableMovement(this, SPEED));
            this.add(new JumpImpl(this, JUMP));
            this.addDefaultPlayerCollisionEffect();
        }
    }

}
