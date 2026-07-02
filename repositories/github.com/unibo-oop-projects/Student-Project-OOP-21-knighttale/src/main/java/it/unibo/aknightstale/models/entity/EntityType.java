package it.unibo.aknightstale.models.entity;

public enum EntityType {
    /**
     * The character moved by the player.
     */
    PLAYER,
    /**
     * A "block" of the map.
     */
    TILE,
    /**
     * A map tile that can't be walked on/through.
     */
    OBSTACLE,
    /**
     * A character that can be attacked and can attack the player.
     */
    ENEMY,
    /**
     * A character that the player can't attack but helps the player attacking enemies.
     */
    ALLY
}
