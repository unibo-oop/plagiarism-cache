package view.menu;

import java.util.ArrayList;
import java.util.List;

import common.MsgStrings;
import controller.menu.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import model.user.CurrentUser;
import model.user.UserDataInterface;
import view.GenericView;

public class StatisticsView extends GenericView {

    private static final String COIN = "Coin: ";
    private static final String MAX_HEIGHT = "Max Height: ";
    private static final String LEVELS = "Max level unlocked: ";
    private static final String USERNAME = "Username: ";
    private static final String CHARACTERS = "Characters unlocked: ";
    private static final String CURRENT_CHARACTERS = "Current character: ";

    /**
     * StatisticView constructor.
     * @param c : View controller.
     */
    public StatisticsView(final Controller c) {
        super(c);
        final TilePane box = new TilePane();
        super.init(box, 40.0, 40.0, 0.0, 0.0);

        final List<Label> listStat = new ArrayList<Label>(getStat());

        final Button menuBtn = new MsgEventButton(this, MsgStrings.MENU).getButton();

        box.getChildren().addAll(listStat);
        box.getChildren().add(menuBtn);
    }

    private List<Label> getStat() {
        final UserDataInterface data = CurrentUser.getInstance().getUser().getUserData();

        final List<Label> list = new ArrayList<>();
        list.add(new Label(USERNAME + CurrentUser.getInstance().getUser().getUserProfile().getUsername()));
        list.add(new Label(COIN + data.getCoin()));
        list.add(new Label(MAX_HEIGHT + data.getMaxHeight()));
        list.add(new Label(LEVELS + data.getLevel()));
        list.add(new Label(CHARACTERS + data.getCharacters().toString()));
        list.add(new Label(CURRENT_CHARACTERS + data.getCurrentCharacter().name()));
        return list;
    }

}
