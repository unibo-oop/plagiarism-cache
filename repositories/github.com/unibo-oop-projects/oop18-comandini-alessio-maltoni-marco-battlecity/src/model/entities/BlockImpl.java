package model.entities;

import enums.Sprite;
import model.common.MovementImpl;
import model.common.Position;

public final class BlockImpl extends AbstractGameEntity implements Block {

    private final Type type;

    public BlockImpl(final Sprite sprite, final Position position, final Type type) {
        super(sprite, position, new MovementImpl(), type.getDimension());
        this.type = type;

    }

    @Override
    public Type getType() {
        return this.type;
    }

}
