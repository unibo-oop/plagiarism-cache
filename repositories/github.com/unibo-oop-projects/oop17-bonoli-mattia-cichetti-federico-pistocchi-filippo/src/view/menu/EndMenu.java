package view.menu;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.AbstractScene;
import view.View;
import view.ViewImpl;
/**
 * end menu.
 */
public class EndMenu extends AbstractScene {

    /**
     * @param width of the scene
     * @param height of the scene
     * @param v view reference
     */
    public EndMenu(final double width, final double height, final View v) {
        super(new BorderPane(), width, height);
        final View view = v;
        final BorderPane pane = (BorderPane) getRoot();
        final Label points = new Label();
        final Label infoLogo = new Label("GAME OVER");
        final TextField username = new TextField();
        final Button save = new Button("SAVE");
        final Button leaderboardMenu = new Button("LEADERBOARD");
        final Button mainMenu = new Button("MENU");
        final Button exit = new Button("EXIT");
        final HBox inputBox = new HBox(username, save);
        final HBox bottomBox = new HBox(leaderboardMenu, mainMenu);
        final VBox topBox = new VBox(infoLogo, points);
        //todo: set label point label with value from controller
        pane.setTop(topBox);
        topBox.setAlignment(Pos.CENTER);
        pane.setCenter(inputBox);
        pane.setBottom(bottomBox);
        leaderboardMenu.setOnAction(e -> view.changeScene(ViewImpl.GameScreen.LEADERBOARD));
        mainMenu.setOnAction(e -> view.changeScene(ViewImpl.GameScreen.MAINMENU));
        save.setOnAction(e -> { });
        //add new leaerboard entry, if username is empty show alert
        exit.setOnAction(e -> Platform.exit());
    }

    @Override
    public void initialize() { }

}
