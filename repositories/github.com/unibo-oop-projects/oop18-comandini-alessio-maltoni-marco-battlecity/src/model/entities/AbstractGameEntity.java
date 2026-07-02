package model.entities;

import java.util.Optional;

import enums.Sprite;
import model.command.Direction;
import model.common.Dimension;
import model.common.Movement;
import model.common.Position;
import model.common.PositionImpl;

public abstract class AbstractGameEntity implements GameEntity {
    private Position position;
    private Movement movement;
    private final Dimension dimension;
    private Sprite sprite;
    private Optional<Direction> direction;

    public AbstractGameEntity(final Sprite sprite, final Position position, final Movement movement,
            final Dimension dimension) {
        this.sprite = sprite;
        this.position = position;
        this.movement = movement;
        this.dimension = dimension;
        this.direction = Optional.empty();
    }

    @Override
    public void updateState() {
        this.position.update(movement);
    }

    @Override
    public final Position getActualPosition() {
        return new PositionImpl(this.position);
    }

    @Override
    public final Movement getActualMovement() {
        return this.movement;
    }

    @Override
    public final void setMovement(final Movement newMovement) {
        this.movement = newMovement;
    }

    @Override
    public final void setPosition(final Position newPosition) {
        this.position = newPosition;
    }

    @Override
    public final Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public final void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public final Dimension getDimension() {
        return this.dimension;

    }

    @Override
    public final Optional<Direction> getDirection() {
        return this.direction;
    }

    @Override
    public final void setDirection(final Direction direction) {
        this.direction = Optional.ofNullable(direction);
    }
}
