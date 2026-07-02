package dungeon.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import dungeon.Direction;
import dungeon.character.Worker;
import dungeon.character.player.AttackMode;
import dungeon.character.player.GrabMode;
import dungeon.logic.GameLogic;

public class Command {

  private final InputMap inputMap;
  private final ActionMap actionMap;
  private static final int MOVE_UP = 0;
  private static final int MOVE_LEFT = 1;
  private static final int MOVE_DOWN = 2;
  private static final int MOVE_RIGHT = 3;
  private static final int LOOK_UP = 4;
  private static final int LOOK_LEFT = 5;
  private static final int LOOK_DOWN = 6;
  private static final int LOOK_RIGHT = 7;
  private static final int OPEN_INVENTORY = 8;
  private static final int ATTACK_MODE = 9;
  private static final int GRAB_MODE  = 10;

  /**
   * Instantiates a new command.
   *
   * @param panel the panel
   * @param panelInventory the panel inventory
   * @param gameLogic the game logic
   * @param worker the worker
   */
  public Command(final JPanel panel,
      final JPanel panelInventory, final GameLogic gameLogic,
      final Worker worker) {
    inputMap = panel.getInputMap();
    actionMap = panel.getActionMap();
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), MOVE_UP);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), MOVE_LEFT);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), MOVE_DOWN);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), MOVE_RIGHT);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), LOOK_UP);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LOOK_LEFT);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), LOOK_DOWN);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), LOOK_RIGHT);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), OPEN_INVENTORY);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), ATTACK_MODE);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), GRAB_MODE);

    actionMap.put(MOVE_UP, new AbstractAction() {

      private static final long serialVersionUID = 7657577727902199144L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.movePlayer(Direction.UP));
      }
    });

    actionMap.put(MOVE_LEFT, new AbstractAction() {

      private static final long serialVersionUID = 6926929278048601698L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.movePlayer(Direction.LEFT));
      }
    });

    actionMap.put(MOVE_DOWN, new AbstractAction() {

      private static final long serialVersionUID = 8636071886635209383L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.movePlayer(Direction.DOWN));
      }
    });

    actionMap.put(MOVE_RIGHT, new AbstractAction() {

      private static final long serialVersionUID = 1030388037695553162L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.movePlayer(Direction.RIGHT));
      }
    });

    actionMap.put(LOOK_UP, new AbstractAction() {

      private static final long serialVersionUID = 7503705588939354305L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.takeOrAttack(Direction.UP));
      }
    });

    actionMap.put(LOOK_LEFT, new AbstractAction() {

      private static final long serialVersionUID = 9061003035347856840L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.takeOrAttack(Direction.LEFT));
      }
    });

    actionMap.put(LOOK_DOWN, new AbstractAction() {

      private static final long serialVersionUID = 7880425968015930747L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.takeOrAttack(Direction.DOWN));
      }
    });

    actionMap.put(LOOK_RIGHT, new AbstractAction() {

      private static final long serialVersionUID = 1301355528929045959L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.takeOrAttack(Direction.RIGHT));
      }
    });

    actionMap.put(OPEN_INVENTORY, new AbstractAction() {

      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        worker.add(() -> gameLogic.setInventoryOpen());
        if (panelInventory.isVisible()) {
          panel.setVisible(true);
          panelInventory.setVisible(false);
        } else {
          panelInventory.setVisible(true);
        }
      }
    });

    actionMap.put(ATTACK_MODE, new AbstractAction() {

      private static final long serialVersionUID = 1301355528929045959L;

      @Override
      public void actionPerformed(final ActionEvent event) {
        worker.add(() -> gameLogic.setPlayerMode(new AttackMode()));
      }
    });

    actionMap.put(GRAB_MODE, new AbstractAction() {

      private static final long serialVersionUID = 1301355528929045959L;

      @Override
      public void actionPerformed(final ActionEvent event) {
          worker.add(() -> gameLogic.setPlayerMode(new GrabMode()));
      }
    });
  }
}
