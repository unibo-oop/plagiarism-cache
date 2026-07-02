package it.unibo.modularcheckers.model;

/**
 * Enum class to describe the various type of players.
 */
public enum PlayerType {
    /**
     * The human player, he needs to interact with the view to choose the move.
     */
    HUMAN_PLAYER,
    /**
     * The AI player, he choose the move automatically.
     */
    AI_PLAYER,
    /**
     * The History player. He performs the moves from a file (the replay file). Note
     * that he can be used only to replicate game, and can't be seen as an effective
     * player.
     * 
     */
    HISTORY_PLAYER;

}
