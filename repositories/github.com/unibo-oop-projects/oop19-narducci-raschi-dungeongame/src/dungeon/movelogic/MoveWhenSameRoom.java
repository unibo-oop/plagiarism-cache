package dungeon.movelogic;

import dungeon.Direction;
import dungeon.character.player.Player;
import dungeon.floor.Floor;
import dungeon.point.Point;
import dungeon.room.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An implementation of {@code MoveLogic} that starts moving when a player is in
 * the same room as the point passed to the constructor. This implementation
 * expects that the object that calls {@code move()} follows the target point
 * returned by this object, to reset the position create a new instance with the
 * new starting point.
 */
public class MoveWhenSameRoom implements MoveLogic {

  private final Floor floor;
  private final Room room;
  private Point point;
  private Optional<Player> target;

  /**
   * Instantiates a new {@code MoveWhenSameRoom}.
   *
   * @param floor the floor
   * @param point the point
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  public MoveWhenSameRoom(final Floor floor, final Point point) {
    this.floor = Objects.requireNonNull(floor);
    this.point = Objects.requireNonNull(point);
    this.target = Optional.empty();
    this.room = this.floor.getRoom(this.point).orElseThrow();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Point move() {
    if (this.target.isEmpty()) {
      this.floor.getPlayers().forEach((point, player) -> {
        if (this.target.isEmpty() && room.contains(point)) {
          this.target = Optional.of(player);
        }
      });
    } else {
      final Point point = this.point;
      final Point player = this.target.get().getPoint();
      final List<Direction> moves = new ArrayList<>();

      if (this.point.getX() != player.getX()) {
        if (this.point.getX() > player.getX()) {
          moves.add(Direction.UP);
        } else {
          moves.add(Direction.DOWN);
        }
      }

      if (this.point.getY() != player.getY()) {
        if (this.point.getY() > player.getY()) {
          moves.add(Direction.LEFT);
        } else {
          moves.add(Direction.RIGHT);
        }
      }

      moves.forEach(move -> {
        if (this.point.equals(point)) {
          final Point target = this.point.move(move.getModifier());

          if (!this.floor.isBlocking(target)) {
            this.point = target;
          }
        }
      });
    }

    return this.point;
  }
}
