package controller.observer;

import controller.GameEngineImpl;
import controller.event.ButtonEvent;
import controller.event.Event;
import controller.utility.ButtonType;

/**
 *Observer for manage button events.
 */
public class ButtonObserver implements Observer {

    /**
     * Manage button event.
     */
    @Override
    public <E extends Event> void notifyEvent(final E event) {
        if (event instanceof ButtonEvent) {
            final ButtonEvent buttonEvent = (ButtonEvent) event;
            if (buttonEvent.getEvent().equals(ButtonType.START_GAME.toString())) {
                GameEngineImpl.get().newGame(buttonEvent.getPlayerName());
            } else if (buttonEvent.getEvent().equals(ButtonType.PAUSE_GAME.toString())) {
                GameEngineImpl.get().stopGame();
            } else if (buttonEvent.getEvent().equals(ButtonType.RESUME_GAME.toString())) {
                GameEngineImpl.get().resumeGameLoop();
            } else if (buttonEvent.getEvent().equals(ButtonType.QUIT_GAME.toString())) {
                Runtime.getRuntime().exit(0);
            }
        }
    }
}
