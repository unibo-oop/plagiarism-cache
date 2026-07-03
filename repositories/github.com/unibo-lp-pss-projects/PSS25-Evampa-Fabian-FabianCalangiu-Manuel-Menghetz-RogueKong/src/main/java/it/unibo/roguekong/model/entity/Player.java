package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.Velocity;
import it.unibo.roguekong.model.value.impl.LivesImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;
import java.util.List;

/**
 * This interface is going to represent player
 */

public interface Player{
    public Position getPosition();

    public Velocity getVelocity();

    public List<PowerUp> getActivePowerUps();

    public void addPowerUp(PowerUp powerUp);
    public void setPosition(double x, double y);
    public void moveX(double x);
    public void moveY(double y);
    public void setVelocity(VelocityImpl velocity);
    public String getSprite();
    public void setLives(LivesImpl lives);
    public LivesImpl getLives();
}