package dungeon.movelogic;

import dungeon.character.monster.Monster;
import dungeon.floor.Floor;
import java.util.ArrayList;
import java.util.List;

/**
 * Some utilities (currently only 1) related to {@code MoveLogic}.
 */
public final class Utilities {

  private Utilities() {
  }

  /**
   * Reposition monsters as needed on previous floor(s) to make sure all
   * positions are valid for {@code MoveWhenSameRoom}.
   *
   * @param current the current floor
   * @throws IllegalStateException if a monster cannot be moved
   */
  public static void repositionMonsters(final Floor current) {
    final List<Floor> previous = new ArrayList<>();

    current.getPlayers().keySet().forEach(point -> {
      if (current.getExits().containsKey(point)) {
        previous.add(current.getExits().get(point));
      }
    });

    previous.forEach(floor -> {
      floor.getMonsters().forEach((point, monster) -> {
        if (floor.getRoom(point).isEmpty()) {
          floor.getItem(point).ifPresent(item -> {
            if (Monster.class.isAssignableFrom(item.getClass())) {
              floor.move(point, monster.getSpawn());
              monster.setPoint(monster.getSpawn());
            } else {
              throw new IllegalStateException();
            }
          });
        }
      });
    });
  }
}
