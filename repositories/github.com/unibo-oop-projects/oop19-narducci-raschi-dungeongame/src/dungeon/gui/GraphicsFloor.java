package dungeon.gui;

import dungeon.Utilities;
import dungeon.character.player.Player;
import dungeon.floor.Floor;
import dungeon.floor.FloorDecorator;
import dungeon.item.Item;
import dungeon.point.ImmutablePoint;
import dungeon.point.Point;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * A graphical decorator of {@code Floor} using swing, other objects must call
 * this one to make sure the graphical interface and the underlying state are
 * consistent. Using the floor passed to the constructor this object will draw
 * and update the graphical panel. The priority list used is the same as the one
 * defined in {@code Floor}.
 */
public final class GraphicsFloor extends FloorDecorator {

  private final Map<Point, JLabel> grid;
  private final JPanel panel;
  private final int size;

  /**
   * Instantiates a new {@code GraphicsFloor}.
   *
   * @param floor the floor to draw
   * @param panel the panel where to draw the floor
   * @param size the size of floor
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if {@code size} is &lt; 1
   */
  public GraphicsFloor(final Floor floor, final JPanel panel, final int size) {
    this(floor, panel, size, Optional.empty());
  }

  private GraphicsFloor(
    final Floor floor,
    final JPanel panel,
    final int size,
    final Optional<List<JPanel>> list) {

    super(floor);
    this.grid = new HashMap<>();
    this.panel = Objects.requireNonNull(panel);
    this.size = Utilities.requireGreaterThanZero(size);

    final JPanel floorPanel = new JPanel(new GridLayout(this.size, this.size));

    if (list.isEmpty()) {
      this.panel.add(floorPanel);
    } else {
      list.get().add(floorPanel);
    }

    floorPanel.setBackground(Color.BLACK);

    for (int x = 0; x < this.size; x++) {
      for (int y = 0; y < this.size; y++) {
        final JLabel label = new JLabel();

        label.setSize(16, 16);
        this.grid.put(new ImmutablePoint(x, y), label);
        floorPanel.add(label);
      }
    }

    this.getAll().forEach((point, item) -> {
      this.grid.get(point).setIcon(new ImageIcon(item.getCell().getImageURL()));
    });

    this.getPlayers().values().forEach(player -> player.setFloor(this));
  }

  private Map<Point, Item> getAll() {
    final Map<Point, Item> all = new HashMap<>();

    all.putAll(this.getItems());
    all.putAll(this.getExits());
    all.putAll(this.getGolds());
    all.putAll(this.getWeapons());
    all.putAll(this.getMonsters());
    all.putAll(this.getPlayers());

    return all;
  }

  private void update(final List<Point> points) {
    final Map<Point, Item> all = this.getAll();
    final Map<JLabel, Icon> labels = new HashMap<>();

    points.forEach(point -> {
      final Item item = all.get(point);
      final Icon icon;

      if (item == null) {
        icon = null;
      } else {
        icon = new ImageIcon(item.getCell().getImageURL());
      }

      labels.put(this.grid.get(point), icon);
    });

    SwingUtilities.invokeLater(() -> {
      labels.forEach((label, icon) -> {
        label.setIcon(icon);
        label.revalidate();
      });
    });
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   */
  @Override
  public void remove(final Point point) {
    super.remove(Objects.requireNonNull(point));
    this.update(List.of(point));
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  @Override
  public void move(final Point from, final Point to) {
    super.move(Objects.requireNonNull(from), Objects.requireNonNull(to));
    this.update(List.of(from, to));
  }

  /**
   * This method is not supported, check {@code exit()} documentation.
   *
   * @throws UnsupportedOperationException
   */
  @Override
  public void enter(final Player player, final Floor floor) {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   *
   * The new {@code GraphicsFloor} is created after calling {@code exit()} on
   * the underlying floor so calling {@code enter()} on this object is
   * unnecessary and unsupported. The new instance of this object is set here on
   * the player.
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  @Override
  public void exit(final Point player, final Point floor) {
    Objects.requireNonNull(player);
    Objects.requireNonNull(floor);

    final List<JPanel> panels = new ArrayList<>();
    final Player move = this.getPlayers().get(player);

    super.exit(player, floor);

    Objects.requireNonNull(move).setFloor(new GraphicsFloor(
        this.getExits().get(floor),
        this.panel,
        this.size,
        Optional.of(panels)));

    SwingUtilities.invokeLater(() -> {
      this.panel.removeAll();
      panels.forEach(panel -> this.panel.add(panel));
      this.panel.revalidate();
    });
  }
}
