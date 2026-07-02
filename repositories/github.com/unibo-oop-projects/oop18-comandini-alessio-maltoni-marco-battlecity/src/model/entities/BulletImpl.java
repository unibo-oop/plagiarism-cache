package model.entities;

import enums.Sprite;
import model.common.Dimension;
import model.common.DimensionImpl;
import model.common.Movement;
import model.common.Position;

public class BulletImpl extends AbstractGameEntity implements Bullet {

    private static final Dimension DEFAULT_BULLET_DIMENSION = new DimensionImpl(0.4, 0.4);
    private final Power power;
    private final Tank attachedTank;

    public BulletImpl(final Position position, final Movement movement, final Power power, final Tank attacchedTank) {
        super(Sprite.BULLET, position, movement, DEFAULT_BULLET_DIMENSION);
        this.power = power;
        this.attachedTank = attacchedTank;
    }

    @Override
    public Power getPower() {
        return this.power;
    }

    @Override
    public Tank getAttachedTank() {
        return this.attachedTank;
    }

}
