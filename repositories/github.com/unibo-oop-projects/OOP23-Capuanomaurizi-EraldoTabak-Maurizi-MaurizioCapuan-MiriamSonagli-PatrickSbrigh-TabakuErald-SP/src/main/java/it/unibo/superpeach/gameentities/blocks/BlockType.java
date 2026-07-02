package it.unibo.superpeach.gameentities.blocks;

/**
 * Enumeration defining each block type of the game,
 * some figures of background and world are built with more than one block.
 */
public enum BlockType {
    /**
     * brick block.
     */
    BRICK,
    /**
     * ground block.
     */
    TERRAIN,
    /**
     * lucky block.
     */
    LUCKY,
    /**
     * lucky block after being popped by player.
     */
    POPPED_LUCKY,
    /**
     * bottom-left pipe block.
     */
    PIPE_LEFT,
    /**
     * bottom-right pipe block.
     */
    PIPE_RIGHT,
    /**
     * top-left pipe block.
     */
    PIPE_TOP_LEFT,
    /**
     * top-right pipe block.
     */
    PIPE_TOP_RIGHT,
    /**
     * stone block.
     */
    STONE,
    /**
     * left side of background bush.
     */
    BUSH_LEFT,
    /**
     * middle side of background bush.
     */
    BUSH_MIDDLE,
    /**
     * right side of background bush.
     */
    BUSH_RIGHT,
    /**
     * top-left side of background cloud.
     */
    CLOUD_TOP_LEFT,
    /**
     * top-middle side of background cloud.
     */
    CLOUD_TOP_MIDDLE,
    /**
     * top-right side of background cloud.
     */
    CLOUD_TOP_RIGHT,
    /**
     * bot-left side of background cloud.
     */
    CLOUD_BOT_LEFT,
    /**
     * bot-middle side of background cloud.
     */
    CLOUD_BOT_MIDDLE,
    /**
     * bot-right side of background cloud.
     */
    CLOUD_BOT_RIGHT,
    /**
     * hill block.
     */
    HILL_UP,
    /**
     * hill block.
     */
    HILL_BLANK,
    /**
     * hill block.
     */
    HILL_SPOTS1,
    /**
     * hill block.
     */
    HILL_SPOTS2,
    /**
     * hill block.
     */
    HILL_TOP,
    /**
     * hill block.
     */
    HILL_DOWN,
    /**
     * end of level pole flag tip.
     */
    FLAG_TIP,
    /**
     * end of level pole flag block.
     */
    FLAG_POLE,
    /**
     * left side of end of level flag.
     */
    FLAG_LEFT,
    /**
     * right side of end of level flag.
     */
    FLAG_RIGHT,
    /**
     * background castle brick.
     */
    CASTLE_BRICK,
    /**
     * background castle balcony left.
     */
    CASTLE_BALCONY1,
    /**
     * background castle balcony right.
     */
    CASTLE_BALCONY2,
    /**
     * background castle left window block.
     */
    CASTLE_WINDOW_LEFT,
    /**
     * background castle right window block.
     */
    CASTLE_WINDOW_RIGHT,
    /**
     * background castle bottom door block.
     */
    CASTLE_DOOR_BOT,
    /**
     * background castle top door block.
     */
    CASTLE_DOOR_TOP,
    /**
     * invisible block that, if collided with, kills the target.
     */
    DEATH_BLOCK,
    /**
     * invisible block used to stop enemies from falling.
     */
    ALT_BLOCK
}
