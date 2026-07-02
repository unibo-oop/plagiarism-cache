package frogger.model.implementations;

/**
 * Enum representing the possible dependencies for a pickable object in the game.
 * These dependencies indicate the context or entity that a pickable object may rely on.
 *
 * <ul>
 *   <li>{@link #PLAYER} - The pickable object depends on the player.</li>
 *   <li>{@link #OBSTACLE} - The pickable object depends on an obstacle.</li>
 *   <li>{@link #GAME_CONTROLLER} - The pickable object depends on the game controller.</li>
 *   <li>{@link #NULL} - The pickable object has no specific dependency.</li>
 * </ul>
 */
public enum PickableObjectDependency {
    /**
     * The pickable object depends on the player.
     */
    PLAYER,
    /**
     * The pickable object depends on an obstacle.
     */
    OBSTACLE,
    /**
     * The pickable object depends on the game controller.
     */
    GAME_CONTROLLER,
    /**
     * The pickable object has no specific dependency.
     */
    NULL
}
