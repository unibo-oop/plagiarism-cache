package controller.menu;

import common.EventBusConnection;
import common.MsgStrings;
import common.events.SceneEvent;
import enumerators.SceneType;
import javafx.scene.Scene;
import view.menu.StatisticsView;

/**
 * The statistic menu controller class. This class create a StatisticView and
 * manage the events.
 */
public class StatisticMenu extends EventBusConnection implements Controller {

    private final StatisticsView view;

    public StatisticMenu() {
        super();
        this.view = new StatisticsView(this);
    }

    @Override
    public final void sendMsg(final String msg) {
        switch (msg) {
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
