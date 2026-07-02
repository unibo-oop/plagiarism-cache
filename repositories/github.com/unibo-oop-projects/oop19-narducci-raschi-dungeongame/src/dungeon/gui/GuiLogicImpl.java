package dungeon.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;

import dungeon.GuiUtilities;
import dungeon.floor.Floor;

public final class GuiLogicImpl implements GuiLogic {

  private final JPanel mapPanel;
  private final WinLossPanel bigPanel;
  private final JFrame frame;
  private final PlayerTextArea playerInfo;
  public GuiLogicImpl(final JFrame frame,
      final JPanel mapPanel, final WinLossPanel bigPanel,
      final PlayerTextArea playerInfo) {
    this.bigPanel = bigPanel;
    this.frame = frame;
    this.mapPanel = mapPanel;
    this.playerInfo = playerInfo;
  }

  @Override
  public Floor generateLevel(final Floor prevFloor) {
    final Floor floor = new GraphicsFloor(
        prevFloor, mapPanel, GuiUtilities.SIZE);
    return floor;
  }

  @Override
  public void drawEndGame(final String text) {
    this.bigPanel.drawPanel(frame, text);
  }

  @Override
  public void clearMap() {
    mapPanel.removeAll();
  }

  @Override
  public void revalidateMap() {
    mapPanel.revalidate(); 
  }

  @Override
  public void updatePlayerInfo(final String name, final String stats,
      final String gold, final String weapon, final String currentHealth) {
    playerInfo.renderer(name, stats, gold, weapon, currentHealth);
  }
}
