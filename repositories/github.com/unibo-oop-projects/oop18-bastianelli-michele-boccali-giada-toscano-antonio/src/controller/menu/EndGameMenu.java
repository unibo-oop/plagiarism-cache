package controller.menu;

import common.EventBusConnection;
import common.MsgStrings;
import common.events.SceneEvent;
import enumerators.SceneType;
import javafx.scene.Scene;
import view.menu.EndGameView;

public class EndGameMenu extends EventBusConnection implements Controller {

    private final EndGameView view;

    public EndGameMenu() {
        super();
        view = new EndGameView(this);
    }

    @Override
    public final void sendMsg(final String msg) {
        switch (msg) {
        case MsgStrings.MENU:
            getBus().post(new SceneEvent(SceneType.MENU));
            getBus().unregister(this);
            break;
        case MsgStrings.RESTART:
            getBus().post(new SceneEvent(SceneType.NEW_GAME));
//            GameController.getInstance().initNewGame(
//                    CurrentUser.getInstance().getUser().getUserData().getCurrentCharacter(),
//                    CurrentUser.getInstance().getCurrentLevel());
            getBus().unregister(this);
            break;
        default:
            break;
        }
    }

    @Override
    public Scene getScene() {
        return view.getScene();
    }
}
