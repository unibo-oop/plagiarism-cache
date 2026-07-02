package dungeon.floor;

import java.util.List;
import java.util.Map.Entry;

import dungeon.character.player.Player;

/**
 * An object able to generate a {@code Floor}.
 */
public interface FloorGenerator {

  /**
   * Returns a new floor or the initial floor of a set of linked floors.
   *
   * @param floors the list of entries where the key is a floor name and the
   *        value its description, the {@code size()} of the list is the number
   *        of floors to generate
   * @param players the list of players to place in the initial floor
   * @return the initial floor
   */
  Floor getNew(List<Entry<String, String>> floors, List<Player> players);
}
