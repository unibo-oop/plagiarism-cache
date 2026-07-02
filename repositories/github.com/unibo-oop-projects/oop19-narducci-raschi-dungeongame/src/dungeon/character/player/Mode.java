package dungeon.character.player;

import dungeon.floor.Floor;
import dungeon.point.Point;

public interface Mode {

  /**
   * Take action.
   *
   * @param pos the selected point
   * @param player the player
   * @param floor the floor
   */
  void takeAction(Point pos, Player player, Floor floor);

}
