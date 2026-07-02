package dungeon.gui;

import dungeon.floor.Floor;

/**
 * The Interface GuiLogic.
 */
public interface GuiLogic {

  /**
   * Generate level.
   *
   * @param prevFloor the prev floor
   * @return the floor
   */
  Floor generateLevel(Floor prevFloor);

  /**
   * Draw the end game panel.
   *
   * @param text the text
   */
  void drawEndGame(String text);

  /**
   * Clear map.
   */
  void clearMap();

  /**
   * Revalidate map.
   */
  void revalidateMap();

  /**
   * Update player info.
   *
   * @param name the name
   * @param stats the stats
   * @param gold the gold
   * @param weapon the weapon
   * @param currentHealth the current health
   */
  void updatePlayerInfo(String name, String stats,
      String gold, String weapon, String currentHealth);
}
