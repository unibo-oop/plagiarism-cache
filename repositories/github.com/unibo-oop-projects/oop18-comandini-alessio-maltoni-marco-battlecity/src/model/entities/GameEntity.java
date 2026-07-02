package model.entities;

import model.command.Direction;
import model.common.Dimension;
import model.common.Movement;
import model.common.Position;

import java.util.Optional;

import enums.Sprite;

public interface GameEntity {

    Position getActualPosition();

    Movement getActualMovement();

    void setMovement(Movement newMovement);

    void setPosition(Position initialPosition);

    void updateState();

    Sprite getSprite();

    void setSprite(Sprite sprite);

    Dimension getDimension();

    Optional<Direction> getDirection();

    void setDirection(Direction direction);
}
