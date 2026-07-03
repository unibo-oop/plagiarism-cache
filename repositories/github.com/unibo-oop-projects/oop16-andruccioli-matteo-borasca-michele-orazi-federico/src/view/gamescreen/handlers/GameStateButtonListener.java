package view.gamescreen.handlers;

import com.jfoenix.controls.JFXButton;

import javafx.event.Event;
import javafx.event.EventHandler;
import utils.enumerations.Status;
import view.ViewImpl;

/**
 * 
 * Event handler to manage all clicks in GameStateButton. It has variable behavior related on which game state is current active.
 *
 */
public class GameStateButtonListener implements EventHandler<Event> {

    @Override
    public void handle(final Event evt) {
        switch(ViewImpl.getIstance().getController().getGameStatus()) {

        /* If Status = Initialization or Deployment */
        case INITIALIZATION: case DEPLOYMENT:
            if (ViewImpl.getIstance().getTanksToDeploy() == 0) {
                ViewImpl.getIstance().getController().checkDeployment(ViewImpl.getIstance().getDeployStateList());
                ViewImpl.getIstance().getDeployStateList().clear();
                ViewImpl.getIstance().resetView();
                if (ViewImpl.getIstance().getController().getGameStatus().equals(Status.DEPLOYMENT)) {
                    ((JFXButton) evt.getSource()).setText("End Attack Phase");
                    ViewImpl.getIstance().disableAllStates(false);
                }
            }
            break;
 
        /* If Status = Attack */
        case ATTACK:
            if (!ViewImpl.getIstance().isMoveButtonVisible()) {
                ((JFXButton) evt.getSource()).setText("End Turn");
                ViewImpl.getIstance().showTanksMovementDiag();
            } else {
                ViewImpl.getIstance().moveTanks(false);
                ViewImpl.getIstance().getController().endTurn();
                ((JFXButton) evt.getSource()).setText("End Deploy Phase");
            }
            ViewImpl.getIstance().resetView();
            break;

        /* If Status = Movement */
        case MOVEMENT:
            ViewImpl.getIstance().getController().endTurn();
            ((JFXButton) evt.getSource()).setText("End Deploy Phase");
            ViewImpl.getIstance().resetView();
            break;
        default:
            break;
        }
    //ViewImpl.getIstance().showVictory();
    }
}
