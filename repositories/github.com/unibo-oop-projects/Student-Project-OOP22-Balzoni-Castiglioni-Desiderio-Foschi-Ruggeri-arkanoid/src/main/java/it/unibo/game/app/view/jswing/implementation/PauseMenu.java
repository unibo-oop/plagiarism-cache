package it.unibo.game.app.view.jswing.implementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the panel for Pause view.
 */
public class PauseMenu extends AbstractView {

  /**
   * Constructor of the class.
   * 
   * @param uiCtrl is the controller that will change the views
   */
  public PauseMenu(final UIControllerImpl uiCtrl) {
    super(uiCtrl, "PAUSE", new CustomBtn(SIZE_BTN, "Resume"), new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        uiCtrl.gameView();
      }
    });
  }
}
