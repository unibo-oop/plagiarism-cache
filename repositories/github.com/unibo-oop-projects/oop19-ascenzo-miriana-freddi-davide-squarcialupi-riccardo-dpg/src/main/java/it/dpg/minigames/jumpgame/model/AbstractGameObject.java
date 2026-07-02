package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Basic implementation of GameObject interface
 * @author Davide Picchiotti
 * @see GameObject
 * */

public abstract class AbstractGameObject implements GameObject {
    private final int width;
    private final int height;
    private int x;
    private int y;
    private int speedX;
    private int speedY;

    public AbstractGameObject(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return Pair.of(x, y);
    }

    @Override
    public void setPosition(final Pair<Integer, Integer> position) {
        x = position.getLeft();
        y = position.getRight();
    }

    @Override
    public int getSpeedX() {
        return speedX;
    }

    @Override
    public int getSpeedY() {
        return speedY;
    }

    @Override
    public void setSpeedX(final int speedX) {
        this.speedX = speedX;
    }

    @Override
    public void setSpeedY(final int speedY) {
        this.speedY = speedY;
    }

    @Override
    public void updatePosition() {
        x += speedX;
        y += speedY;
    }
}
