package model;

import java.util.HashMap;
import java.util.Map;

import model.common.Counter;
import model.common.Position;
import model.common.PositionImpl;
import model.enemy.Enemy;
import model.entities.Tank;

public final class PlayerImpl implements Player {
    private final Position initialPosition;
    private int points;
    private int life;
    private final Tank playerTank;
    private final Map<Enemy, Counter> currentKilledTank;

    public PlayerImpl(final int life, final Tank playerTank) {
        this.playerTank = playerTank;
        this.initialPosition = new PositionImpl(playerTank.getActualPosition());
        this.points = 0;
        this.life = life;
        this.currentKilledTank = new HashMap<>();
        // Initialize the list with default value
        this.currentKilledTank.put(Enemy.NORMAL, new Counter());
        this.currentKilledTank.put(Enemy.FAST, new Counter());
        this.currentKilledTank.put(Enemy.POWER, new Counter());
        this.currentKilledTank.put(Enemy.ARMORED, new Counter());
    }

    @Override
    public Tank getTank() {
        return this.playerTank;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void initializeTankPosition() {
        this.playerTank.setPosition(initialPosition);
    }

    @Override
    public Map<Enemy, Counter> getKilledTank() {
        return this.currentKilledTank;
    }

}
