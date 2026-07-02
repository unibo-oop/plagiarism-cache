package dungeon.template;

import dungeon.item.Item;
import dungeon.point.Point;
import java.util.Optional;

/**
 * An object capable to create new instances of itself with the same
 * configuration.
 *
 * @param <T> the generic type
 */
public interface Template<T extends Item> {

  /**
   * Returns the appearance frequency.
   *
   * @return the frequency
   */
  int getFrequency();

  /**
   * Returns a new instance of this template.
   *
   * @param point the point where the instance will be placed
   * @return the instance
   */
  T getInstance(Optional<Point> point);
}
