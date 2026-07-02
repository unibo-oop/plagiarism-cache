package model.components;

import org.jbox2d.common.Vec2;

import model.entities.EntityModel;

public class JumpImpl extends AbstractComponent implements Jump {

    private final float jumpHeight;
    private static final float X_VALUE = 0;

    public JumpImpl(final EntityModel owner, final float jumpHeight) {
        super(owner);
        this.jumpHeight = jumpHeight;
    }

    @Override
    public final void jump() {
        this.getOwner().getPhysicEntity().setLinearVelocity(new Vec2(X_VALUE, -jumpHeight));
    }

    @Override
    public final void jumpFromExternalForce(final float externalForce) {
        this.getOwner().getPhysicEntity().setLinearVelocity(new Vec2(X_VALUE, -externalForce));
    }

    @Override
    public final void jumpMax(final float externalForce) {
        final float maxJump = Math.max(externalForce, jumpHeight);
        this.getOwner().getPhysicEntity().setLinearVelocity(new Vec2(X_VALUE, -maxJump));
    }
}
