package it.unibo.isaccoop.model.room;

import it.unibo.isaccoop.model.common.Point2D;

/**
 * A façade interface to simplify the creation of {@link Room}, using {@link RoomBuilder}.
 */
public interface RoomFactory {

    /** 
     * Build a {@link RoomType#START} room.
     * @param coordInsideLevel the coordinate of this room inside the level
     * @return the room where the player starts the game.
     */
    Room buildStartRoom(Point2D coordInsideLevel);

    /** 
     * Build a {@link RoomType#STANDARD} room.
     * @param coordInsideLevel the coordinate of this room inside the level
     * @return a room with enemies and items.
     */
    Room buildStandardRoom(Point2D coordInsideLevel);

    /** 
     * Build a {@link RoomType#SHOP} room.
     * @param coordInsideLevel the coordinate of this room inside the level
     * @return a room where you can ONLY buy powerups using the collected money.
     */
    Room buildShopRoom(Point2D coordInsideLevel);

    /** 
     * Build a {@link RoomType#BOSS} room.
     * @param coordInsideLevel the coordinate of this room inside the level
     * @return the room with the final boss
     */
    Room buildBossRoom(Point2D coordInsideLevel);

    /** 
     * Build a {@link RoomType#TREASURE} room.
     * @param coordInsideLevel the coordinate of this room inside the level
     * @return the room where you can find a free powerup
     */
    Room buildTreasureRoom(Point2D coordInsideLevel);

    /**
     * Build a room in the proper order, that is, each time this method is called, will return a room of
     * the proper {@link RoomType}. <br><br>The FIRST will be a {@link RoomType#START} room, 
     * <br>the LAST will be a {@link RoomType#BOSS}, <br>the others will be created randomly among 
     * {@link RoomType#SHOP} (only ONE), <br>{@link RoomType#TREASURE} (only ONE) and 
     * <br>{@link RoomType#STANDARD} (all the remaing ones).
     * @param coordInsideLevel the coordinate of this room inside the level
     * @return a room of the proper {@link RoomType}
     */
    Room buildRoomInProperOrder(Point2D coordInsideLevel);
}
