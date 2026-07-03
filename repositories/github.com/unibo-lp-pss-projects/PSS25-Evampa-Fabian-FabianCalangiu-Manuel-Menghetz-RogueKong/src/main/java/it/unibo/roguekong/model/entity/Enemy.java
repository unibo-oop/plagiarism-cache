package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.value.*;
import it.unibo.roguekong.model.value.impl.PositionImpl;

/**
 * This interface is going to represent the game enemies
 */

public interface Enemy {
    public Position getPosition();

    public String getSprite();
    public Velocity getVelocity();

    public boolean hitPlayer(PositionImpl player);

    public boolean isMovable();

    public void setIsDead(boolean isDead);

    public int getLives();

    public boolean isDead();

    public void setIsMovable(boolean isMovable);
}
