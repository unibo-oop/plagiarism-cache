package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.impl.EnemyImpl;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.impl.PositionImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the logic of each level
 */
public class LevelModel implements Level {
    private final List<EnemyImpl> enemies;
    private final PositionImpl spawnPosition;
    private final PositionImpl endPoint;
    private final PlayerImpl player;
    private boolean isComplete;
    private final TileManager tileManager;

    /**
     * Create a new LevelModel
     * @param spawnPoint is the player position spawn
     * @param endPoint is the escape position of each level
     * @param enemies is a list that contains every enemy of each level
     * @param player is the main player
     * @param tileManager the map manager of each level
     */
    public LevelModel(
            final PositionImpl spawnPoint,
            final PositionImpl endPoint,
            final List<EnemyImpl> enemies,
            final PlayerImpl player,
            final TileManager tileManager) {
        this.spawnPosition = spawnPoint;
        this.endPoint = endPoint;
        this.enemies = new ArrayList<>(enemies);
        this.player = player;
        this.isComplete = false;
        this.tileManager = tileManager;
    }

    @Override
    public List<EnemyImpl> getEnemies() { return List.copyOf(enemies); }

    @Override
    public PlayerImpl getPlayer() { return this.player; }

    @Override
    public PositionImpl getSpawnPoint() { return this.spawnPosition; }

    @Override
    public PositionImpl getEndPoint() { return this.endPoint; }

    @Override
    public boolean isLevelComplete() { return this.isComplete; }

    public TileManager getTileManager() { return tileManager; }

    /**
     * This method initialize each level, setting the player position on the spawn point
     */
    @Override
    public void init() {
        this.isComplete = false;
        this.setPlayerOnSpawn();
    }

    /**
     * Check if the player position is between the endpoint positions
     */
    @Override
    public void checkIfPlayerIsOnEndPoint() {
        if((this.player.getPosition().getX() + 16 >= this.endPoint.getX() && this.player.getPosition().getX() <= this.endPoint.getX() + 32)
                && (this.player.getPosition().getY() + 16 >= this.endPoint.getY() && this.player.getPosition().getY() <= this.endPoint.getY() + 32)) {
            this.isComplete = true;
        }
    }

    /**
     * Set the player on spawn position
     */
    private void setPlayerOnSpawn() {
        this.player.setPosition(this.spawnPosition.getX(), this.spawnPosition.getY());
    }
}