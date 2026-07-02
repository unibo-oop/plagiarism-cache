package starcatraz.controller.game;

import javafx.event.ActionEvent;

/**
 * Controller for VictoryView and DefeatView.
 * @author gianni
 *
 */
public interface GameEndController {

   /**
    * Go back to the main menu.
    * @param event
    */
   void backButtonClick(ActionEvent event);

   /**
    * Restart the match.
    * @param event
    */
   void restartButtonClick(ActionEvent event);
}
