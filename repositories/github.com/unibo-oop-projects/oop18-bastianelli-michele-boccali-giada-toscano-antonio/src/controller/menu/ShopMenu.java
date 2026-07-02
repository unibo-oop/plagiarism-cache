package controller.menu;

import common.EventBusConnection;
import common.MsgStrings;
import common.events.SceneEvent;
import enumerators.SceneType;
import javafx.scene.Scene;
import model.user.CurrentUser;
import view.menu.ShopView;

public class ShopMenu extends EventBusConnection implements Controller {

    private final ShopView view;

    public ShopMenu() {
        super();
        this.view = new ShopView(this);
    }

    @Override
    public final void sendMsg(final String msg) {
        switch (msg) {
        case MsgStrings.BUY_CHARACTER:
            CurrentUser.getInstance().getUser().getUserData().addCharacter(view.getSelectedPlayer());
            CurrentUser.getInstance().getUser().getUserData().subtractCoin(view.getSelectedPlayer().getCost());
            System.out.println(CurrentUser.getInstance().getUser().getUserData().getCharacters());
            view.updateActivation();
            break;
        case MsgStrings.SET_CHARACTER:
            CurrentUser.getInstance().getUser().getUserData().setCurrentCharacter(view.getSelectedPlayer());
            view.updateActivation();
            break;
        case MsgStrings.MENU:
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
