package it.unibo.game.app.view.jswing.implementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the panel for GameOver view.
 */
public class Victory extends AbstractView {

  /**
   * Constructor of the class.
   * 
   * @param uiCtrl is the controller that will change the views
   */
  public Victory(final UIControllerImpl uiCtrl) {
    super(uiCtrl, "YOU WIN", new CustomBtn(SIZE_BTN, "Save"), new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent arg0) {
        new SaveScore(uiCtrl);
      }
    });
  }
}
