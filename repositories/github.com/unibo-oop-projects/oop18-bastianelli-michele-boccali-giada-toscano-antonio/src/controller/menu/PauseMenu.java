package controller.menu;

import common.EventBusConnection;
import common.MsgStrings;
import common.events.SceneEvent;
import controller.GameController;
import enumerators.SceneType;
import javafx.scene.Scene;
import view.BackgroundMusic;
import view.menu.PauseView;

/**
 * The pause menu controller. This class create and handle the PauseView.
 */
public class PauseMenu extends EventBusConnection implements Controller {

    private final PauseView view;

    public PauseMenu() {
        super();
        view = new PauseView(this);
    }

    @Override
    public final void sendMsg(final String msg) {
        switch (msg) {
        case MsgStrings.RESUME:
            getBus().post(new SceneEvent(SceneType.RESUME_GAME));
            getBus().unregister(this);
            break;
        case MsgStrings.SWITCH_AUDIO_MODE:
            BackgroundMusic.getInstance().switchAudioMode();
            break;
        case MsgStrings.MENU:
            GameController.getInstance().gameOver();
            getBus().post(new SceneEvent(SceneType.MENU));
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
