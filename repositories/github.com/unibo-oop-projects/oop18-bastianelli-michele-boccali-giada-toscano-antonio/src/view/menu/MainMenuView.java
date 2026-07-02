package view.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.MsgStrings;
import controller.menu.Controller;
import enumerators.Level;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.user.CurrentUser;
import view.GenericView;

public class MainMenuView extends GenericView {

    private static final String WELCOME = "Welcome ";
    private static final String SCORE = "Score: ";
    private static final String COIN = "Coin: ";

    private final Label title;
    private final Label score;
    private final Label coin;

    public MainMenuView(final Controller c) {
        super(c);
        final BorderPane borderPane = new BorderPane();
        super.init(borderPane, 80.0, 100.0, 0.0, 0.0);

        title = new Label();
        title.setId("title");
        final VBox titleBox = new VBox();
        score = new Label("0");
        coin = new Label("0");
        final HBox infoBox = new HBox();
        infoBox.getChildren().addAll(score, coin);
        titleBox.getChildren().addAll(title, infoBox);

        borderPane.setTop(titleBox);

        // center
        final int levelUnlock = CurrentUser.getInstance().getUser().getUserData().getLevel().ordinal();
        final List<Button> levelBtns = createLevelButtons(levelUnlock);
        final TilePane levelBox = new TilePane();

        final TilePane optionsPane = new TilePane();

        final Button statisticBtn = new MsgEventButton(this, MsgStrings.STATISTICS).getButton();
        final Button shopBtn = new MsgEventButton(this, MsgStrings.SHOP).getButton();
        final Button audioBtn = new AudioEventButton(this).getButton();

        optionsPane.getChildren().addAll(statisticBtn, shopBtn, audioBtn);

        levelBox.getChildren().addAll(levelBtns);

        borderPane.setCenter(levelBox);
        borderPane.setBottom(optionsPane);
    }

    private List<Button> createLevelButtons(final int unlocked) {
        final List<Button> buttons = new ArrayList<Button>();
        Arrays.asList(Level.values()).forEach(e -> {
            final Button b = new MsgEventButton(this, e.toString()).getButton();
            buttons.add(b);
            if (e.ordinal() > unlocked) {
                b.setDisable(true);
            }
        });
        return buttons;
    }

    public void setTitle(final String title) {
        this.title.setText(WELCOME + title);
    }

    public void setScore(final String score) {
        this.score.setText(SCORE + score);
    }

    public void setCoin(final String coin) {
        this.coin.setText(COIN + coin);
    }

}
