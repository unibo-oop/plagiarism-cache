package view.gamescreen.handlers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.player.PlayerInfo;
import view.ViewImpl;
import view.gamescreen.FooterBattleInfo;
import view.gamescreen.FooterPlayerInfo;
import view.gamescreen.State;
import view.gamescreen.events.StateUpdatedEvent;

/**
 * 
 * Composite listener for a (view) State object; it's triggered every mouse click or whenever a {@link StateUpdatedEvent} is fired.
 *
 */
public class StateListener implements EventHandler<Event> {

    private final FooterPlayerInfo fpi;
    private final FooterBattleInfo fbi;

    /**
     * 
     * Class constructor.
     * 
     * @param fpi
     *              Footer of the application.
     * 
     * @param fbi
     *              Footer of the application. 
     * 
     */
    public StateListener(final FooterPlayerInfo fpi, final FooterBattleInfo fbi) {
        this.fpi = fpi;
        this.fbi = fbi;
    }

    private PlayerInfo getActualPlayer() {
        return ViewImpl.getIstance().getPlayerList().getHead();
    }

    /**
     * 
     * Action to be performed on a mouse click.
     * 
     * @param evt
     *              The mouse event (click).
     * 
     */
    private void handleMouseEvent(final MouseEvent evt) {
        this.fpi.updateStateName(((State) evt.getSource()).getName());
        this.fpi.updateOwnerName(((State) evt.getSource()).getOwner());
        this.fpi.updateTanksNumber(((State) evt.getSource()).getTanks());

        switch(ViewImpl.getIstance().getController().getGameStatus()) {
        case INITIALIZATION: case DEPLOYMENT:
            if (ViewImpl.getIstance().getDeployStateList().containsKey(((State) evt.getSource()).getStateRef())) {
                ViewImpl.getIstance().getDeployStateList().put(((State) evt.getSource()).getStateRef(), ViewImpl.getIstance().getDeployStateList().get(((State) evt.getSource()).getStateRef()) + 1);
            } else {
                ViewImpl.getIstance().getDeployStateList().put(((State) evt.getSource()).getStateRef(), 1);
            }
            ((State) evt.getSource()).updateTankIconNumber();
            this.fpi.updateRemainingTanks();
            ViewImpl.getIstance().setTanksToDeploy(ViewImpl.getIstance().getTanksToDeploy() - 1);
            if (ViewImpl.getIstance().getTanksToDeploy() < 1) {
                ViewImpl.getIstance().disableAllStates(true);
            }
            break;

        case ATTACK:
            if (!ViewImpl.getIstance().isMoveButtonVisible()) {
                if (getActualPlayer().getStates().contains(((State) evt.getSource()).getStateRef())) {
                    this.fbi.updateAtkStateName(((State) evt.getSource()).getName());
                    ViewImpl.getIstance().setAtkState(((State) evt.getSource()).getStateRef());
                } else {
                    this.fbi.updateDefStateName(((State) evt.getSource()).getName());
                    ViewImpl.getIstance().setDefState(((State) evt.getSource()).getStateRef());
                }
            } else 
                if (evt.getButton().equals(MouseButton.PRIMARY)) {
                    ViewImpl.getIstance().setOriginState((((State) evt.getSource()).getStateRef()));
                    this.fbi.updateAtkStateName(((State) evt.getSource()).getName());
                } else if (evt.getButton().equals(MouseButton.SECONDARY)) {
                           ViewImpl.getIstance().setDestState(((State) evt.getSource()).getStateRef());
                           this.fbi.updateDefStateName(((State) evt.getSource()).getName());
            }
            break;
        default:
            break;

        }
    }
    /**
     * 
     * Action to be performed when state list is updated.
     * 
     * @param evt
     *             The state updated event.
     * 
     */
    public void handleActionEvent(final StateUpdatedEvent evt) {
        ((State) evt.getSource()).updateTankIcon();
    }

    @Override
    public void handle(final Event event) {
        if (event instanceof MouseEvent && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            handleMouseEvent((MouseEvent) event);
        }
        if (event instanceof StateUpdatedEvent) {
            handleActionEvent((StateUpdatedEvent) event);
        }
    }

}
