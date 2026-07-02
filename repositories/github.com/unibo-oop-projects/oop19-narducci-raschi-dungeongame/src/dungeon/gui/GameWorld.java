package dungeon.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;

import dungeon.GuiUtilities;
import dungeon.character.Worker;
import dungeon.logic.GameLogic;
import dungeon.logic.GameLogicImpl;

//You do not silence checkstyle like this. MagicNumber OFF
public final class GameWorld extends JFrame {


  /**
   * 
   */
  private static final long serialVersionUID = 778448932325084200L;

  /**
   * Instantiates a new game world.
   *
   * 
   * @param name the Player name
   */
  public GameWorld(final String name) {
    WinLossPanel bigPanel = new WinLossPanel();
    LayoutManager overlay = new OverlayLayout(bigPanel);
    bigPanel.setLayout(overlay);
    JPanel mapPanel = new JPanel();

    final Worker worker = new Worker();
    worker.start();

    PlayerTextArea playerDescription = new PlayerTextArea(8, 10);
    playerDescription.setPreferredSize(new Dimension(16 * 32, 16 * 32));
    playerDescription.setFocusable(false);

    GuiLogic guiLogic = new GuiLogicImpl(
        this, mapPanel, bigPanel, playerDescription);

    GameLogic gameLogic = new GameLogicImpl(guiLogic);

    final JPanel panelInventory = new PanelInventory(gameLogic);
    panelInventory.setLayout(new BorderLayout());

    JTextArea textArea = new JTextArea(50, 10);

    PrintStream printStream = new PrintStream(
        new CustomOutputStream(textArea));
    System.setOut(printStream);

    new GraphicsFloor(gameLogic.createNew(name), mapPanel, GuiUtilities.SIZE);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(32 * 16, 32 * 16));
    textArea.setFocusable(false);
    this.getContentPane().add(BorderLayout.WEST, scrollPane);
    bigPanel.add(panelInventory);
    bigPanel.add(mapPanel);
    this.getContentPane().add(BorderLayout.CENTER, bigPanel);
    this.getContentPane().add(BorderLayout.EAST, playerDescription);
    new Command(mapPanel, panelInventory, gameLogic, worker);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false);
    //this.
  }

}
