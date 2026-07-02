package controller.menu;

import java.util.Arrays;

import common.EventBusConnection;
import common.MsgStrings;
import common.events.SceneEvent;
import enumerators.Level;
import enumerators.SceneType;
import javafx.scene.Scene;
import model.user.CurrentUser;
import view.BackgroundMusic;
import view.menu.MainMenuView;

/**
 * The main menu controller class. This class create the MainMenuView.
 */
public class MainMenu extends EventBusConnection implements Controller {

    private final MainMenuView view;

    public MainMenu() {
        super();
        this.view = new MainMenuView(this);
        this.view.setTitle(CurrentUser.getInstance().getUser().getUserProfile().getUsername());
        this.view.setCoin(String.valueOf(CurrentUser.getInstance().getUser().getUserData().getCoin()));
        this.view.setScore(String.valueOf(CurrentUser.getInstance().getUser().getUserData().getMaxHeight()));
    }

    @Override
    public final Scene getScene() {
        return view.getScene();
    }

    @Override
    public final void sendMsg(final String msg) {
        switch (msg) {
        case MsgStrings.SWITCH_AUDIO_MODE:
            BackgroundMusic.getInstance().switchAudioMode();
            break;
        case MsgStrings.STATISTICS:
            getBus().post(new SceneEvent(SceneType.STATISTICS));
            getBus().unregister(this);
            break;
        case MsgStrings.SHOP:
            getBus().post(new SceneEvent(SceneType.SHOP));
            getBus().unregister(this);
            break;
        default:
            Arrays.asList(Level.values()).forEach(e -> {
                if (e.toString().equals(msg)) {
                    CurrentUser.getInstance().setCurrentLevel(e);
                    getBus().post(new SceneEvent(SceneType.NEW_GAME));
                    getBus().unregister(this);
                }
            });
            break;
        }
    }

}
