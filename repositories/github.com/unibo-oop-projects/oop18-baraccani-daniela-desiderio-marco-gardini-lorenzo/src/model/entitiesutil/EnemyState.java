package model.entitiesutil;

/**
 * Possible states a {@link Enemy} could find itself in.
 */
public enum EnemyState {
    /**
     * The {@link Enemy} is behaving as normally, running towards the {@link Hero}.
     */
	NORMAL,
    /**
     * The {@link Enemy} is bubbled and can die if touched by the {@link Hero}.
     */
	BUBBLED
}
