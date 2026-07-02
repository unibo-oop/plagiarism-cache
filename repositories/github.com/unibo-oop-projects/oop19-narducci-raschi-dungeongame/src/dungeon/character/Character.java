package dungeon.character;
import dungeon.character.stats.Stats;

/**
 * The Interface Character.
 */
public interface Character {

  /**
   * Gets the stats.
   *
   * @return the stats
   */
  Stats getStats();

  /**
   * Gets the health remained.
   *
   * @return the health remained
   */
  int getHealthRemained();

  /**
   * Checks if is dead.
   *
   * @return true, if is dead
   */
  boolean isDead();

  /**
   * Sets the health remained.
   *
   * @param damage the damage taken
   */
  void setHealthRemained(int damage);

  /**
   * Returns the name.
   *
   * @return the name
   */
  String getName();

  /**
   * Returns the description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Checks if is blocking.
   *
   * @return true, if is blocking
   */
  boolean isBlocking();

  /**
   * Gets the single instance of Character.
   *
   * @param hpRemained the hp remained
   * @return single instance of Character
   */
  CharacterImpl getInstance(int hpRemained);
}
