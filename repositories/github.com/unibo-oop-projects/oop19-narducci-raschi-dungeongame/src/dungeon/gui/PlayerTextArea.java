package dungeon.gui;
import javax.swing.JTextArea;

public final class PlayerTextArea extends JTextArea {

  /**
   * 
   */
  private static final long serialVersionUID = -2820569246682125593L;

  public PlayerTextArea(final int rows, final int cols) {
    this.setRows(rows);
    this.setColumns(cols);

  }

  /**
   * Renderer.
   *
   * @param name the name
   * @param stats the stats
   * @param gold the gold
   * @param weapon the weapon
   * @param currentHealth 
   */
  public void renderer(final String name, final String stats,
      final String gold, final String weapon, final String currentHealth) {
    this.setText("");
    this.append("Name: " + name + "\n");
    this.append(stats + "\n");
    this.append("Gold: " + gold + "\n");
    this.append("Weapon: " + weapon + "\n");
    this.append("HP left: " + currentHealth);
    this.revalidate();
    this.repaint();
  }
}
