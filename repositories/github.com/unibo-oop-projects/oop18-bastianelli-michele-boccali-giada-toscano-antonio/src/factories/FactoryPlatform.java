package factories;

import org.jbox2d.common.Vec2;

import controller.entities.Entity;
import controller.entities.Platform;
import enumerators.EntityType;
import enumerators.PlatformType;
import enumerators.SpecificType;
import model.CollisionHandler.CollisionSide;
import model.components.CollisionImpl;
import model.components.JumpImpl;
import model.components.LifeImpl;
import model.entities.EntityModel;
import model.entities.PlatformModel;
import model.physics.BodyBuilderImpl;
import model.physics.Size2D;
import view.entities.EntityView;

/**
 * Factory to generate a new Platform.
 */
public class FactoryPlatform extends GenericFactory {

    @Override
    protected final Entity createEntity(final SpecificType type, final Vec2 position) {
        final PlatformModel model = (PlatformModel) createModel(type, position);
        final EntityView view = createView(model);
        return new Platform(model, view);
    }

    @Override
    public final EntityModel createModel(final SpecificType type, final Vec2 position) {
        if (type instanceof PlatformType) {
            final PlatformType c = PlatformType.class.cast(type);
            switch (c) {
            case SIMPLE:
                return new SimplePlatform(position);
            case SUPERJUMP:
                return new SuperJumpPlatform(position);
            case ONEJUMP:
                return new OneJumpPlatform(position);
            default:
                throwErrorBadCharacter(c.toString());
            }
        } else {
            throwErrorBadClass(type.getClass());
        }
        return null;
    }

    /**
     * Simple static platform.
     */
    private static final class SimplePlatform extends PlatformModel {

        private static final PlatformType P_TYPE = PlatformType.SIMPLE;

        SimplePlatform(final Vec2 position) {
            super(P_TYPE, BodyBuilderImpl.getInstance()
                                .size(new Size2D(P_TYPE.getWidth(), P_TYPE.getHeight()))
                                .position(position).allowedToSleep(true)
                                .friction(0)
                                .solid(true)
                                .moveable(false)
                                .restitution(0).build());
            this.add(new CollisionImpl((e, s) -> {
                if (e.getEntityType().equals(EntityType.PLAYER)) {
                    e.getComponent(JumpImpl.class).jump();
                }
            }));
        }
    }

    /**
     * A platform that give a super jump.
     */
    private static final class SuperJumpPlatform extends PlatformModel {

        private static final PlatformType P_TYPE = PlatformType.SUPERJUMP;

        SuperJumpPlatform(final Vec2 position) {
            super(P_TYPE, BodyBuilderImpl.getInstance()
                            .size(new Size2D(P_TYPE.getWidth(), P_TYPE.getHeight()))
                            .position(position)
                            .allowedToSleep(true)
                            .solid(true)
                            .moveable(false)
                            .restitution(0)
                            .build());
            this.add(new CollisionImpl((e, s) -> {
                if (e.getEntityType().equals(EntityType.PLAYER)) {
                    e.getComponent(JumpImpl.class).jumpFromExternalForce(100);
                }
            }));
        }
    }

    /**
     * A platform that will disappears after one jump.
     */
    private static final class OneJumpPlatform extends PlatformModel {

        private static final PlatformType P_TYPE = PlatformType.ONEJUMP;
        private static final int LIFE = 1;

        OneJumpPlatform(final Vec2 position) {
            super(P_TYPE, BodyBuilderImpl.getInstance()
                            .size(new Size2D(P_TYPE.getWidth(), P_TYPE.getHeight()))
                            .position(position)
                            .allowedToSleep(true)
                            .solid(true)
                            .moveable(false)
                            .restitution(0)
                            .build());
            this.add(new LifeImpl(this, LIFE, LIFE, LIFE));
            this.add(new CollisionImpl((e, s) -> {
                if (e.getEntityType().equals(EntityType.PLAYER) && s.equals(CollisionSide.BOTTOM)) {
                    e.getComponent(JumpImpl.class).jump();
                    if (this.contain(LifeImpl.class)) {
                    //  make the platform disappears after one jump
                        this.getComponent(LifeImpl.class).setDead();
                    }
                }
            }));
        }
    }

}
