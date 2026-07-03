package view.gamescreen.handlers;

import javafx.event.EventHandler;
import view.gamescreen.events.InfoPlayerEvent;
import view.ViewImpl;
import view.gamescreen.FooterPlayerInfo;

/**
 * 
 * Listener that activates when player list is updated (e.g.: every turn start).
 *
 */
public class InfoPlayerListener implements EventHandler<InfoPlayerEvent> {

    @Override
    public void handle(final InfoPlayerEvent e) {
        ((FooterPlayerInfo) e.getSource()).updatePlayerInfo(ViewImpl.getIstance().getPlayerList().getHead());
    }
}