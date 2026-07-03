package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.impl.EnemyImpl;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.LevelBuilder;
import it.unibo.roguekong.model.value.impl.PositionImpl;

import java.util.List;
import java.util.Objects;

/**
 * This is a LevelModel Builder, to guarantee dryness when we need to create more than one level
 */
public class LevelBuilderImpl implements LevelBuilder {
    private List<EnemyImpl> enemies;
    private PositionImpl spawnPosition;
    private PositionImpl endPoint;
    private PlayerImpl player;
    private TileManager tileManager;

    /**
     * Sets the player spawnPosition of the level
     * @param spawnPosition PositionImpl with X and Y
     * @return this
     */
    public LevelBuilderImpl setSpawnPosition(final PositionImpl spawnPosition) {
        this.spawnPosition = spawnPosition;
        return this;
    }

    /**
     * Sets the player endPoint of the level
     * @param endPoint PositionImpl with X and Y
     * @return this
     */
    public LevelBuilderImpl setEndPoint(final PositionImpl endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    /**
     * Sets the playable player of the game
     * @param player the player entity, controlled by the player
     * @return this
     */
    public LevelBuilderImpl setPlayer(final PlayerImpl player) {
        this.player = player;
        return this;
    }

    /**
     * Sets the tile manager for each level
     * @param tileManager tile manager entity, that control map
     * @return this
     */
    public LevelBuilderImpl setTileManager(final TileManager tileManager) {
        this.tileManager = tileManager;
        return this;
    }

    /**
     * Build the enemy (Might be deleted)
     * @param enemies enemy entity inside the level
     * @return this
     */
    public LevelBuilderImpl setEnemiesList(final List<EnemyImpl> enemies) {
        this.enemies = enemies;
        return this;
    }

    /**
     * Method that builds a brand-new level
     * @return LevelModel
     */
    public LevelModel build() {
        Objects.requireNonNull(this.spawnPosition, "Spawn position is required");
        Objects.requireNonNull(this.enemies, "Enemies is required");
        Objects.requireNonNull(this.endPoint, "End point is required");
        Objects.requireNonNull(this.player, "Player is required");
        Objects.requireNonNull(this.tileManager, "TileManager is required");

        return new LevelModel(this.spawnPosition, this.endPoint, this.enemies, this.player, this.tileManager);
    }
}
