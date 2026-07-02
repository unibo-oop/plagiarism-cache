package pvz.utilities;

/**
 * Represents an entity in the game with a specific {@link EntityType} and its {@link Position}.
 *
 * <p>This record is used to associate game objects such as plants, zombies, bullets, etc.,
 * with their type and location in the game map.
 *
 * @param type     the type of the entity (e.g., PEASHOOTER, ZOMBIE)
 * @param position the position of the entity on the game grid or field
 */
public record GameEntity(EntityType type, Position position) {
}
