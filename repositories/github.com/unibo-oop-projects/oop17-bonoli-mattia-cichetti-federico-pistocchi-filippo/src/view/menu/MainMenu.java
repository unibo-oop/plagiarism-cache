package view.menu;

import java.util.Arrays;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utilities.Direction;
import view.AbstractScene;
import view.TextureLocation;
import view.View;
import view.ViewImpl;
import view.ViewImpl.GameScreen;
import view.game.GameScene;
import view.game.ViewableEntity;

/**
 * Layout for main menu.
 */
public class MainMenu extends AbstractScene {

    /**
     * @param width The width of the stage.
     * @param height The height of the stage.
     * @param v view
     */
    public MainMenu(final double width, final double height, final View v) {
        super(new BorderPane(), width, height);
        final View view = v;
        final BorderPane pane = (BorderPane) getRoot();
        final Text logo = new Text("FROGGER");
        final Button start = new Button("START GAME");
        final Button options = new Button("OPTIONS");
        final Button leaderboard = new Button("LEADERBOARD");
        final Button exit = new Button("EXIT");
        final Button credits = new Button("CREDITS");
        final Button rules = new Button("ISTRUCTIONS");
        final VBox box = new VBox(start, options, leaderboard, rules, exit);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(30);

        pane.setTop(logo);
        BorderPane.setAlignment(logo, Pos.BASELINE_CENTER);
        pane.setCenter(box);
        pane.setBottom(credits);
        BorderPane.setAlignment(credits, Pos.BOTTOM_RIGHT);

        exit.setOnAction(e -> Platform.exit());
        options.setOnAction(e -> view.changeScene(ViewImpl.GameScreen.OPTIONS));
        leaderboard.setOnAction(e ->  view.changeScene(ViewImpl.GameScreen.LEADERBOARD));
        start.setOnAction(e -> { 
            view.changeScene(ViewImpl.GameScreen.GAME);
            //to be implemented
            GameScene s = (GameScene) view.changeScene(GameScreen.GAME);
            s.initialize();
            //this is an example of how to initially draw the frog 
            s.drawEntities(Arrays.asList(new ViewableEntity(30, 10, 
                                        TextureLocation.FROG.getUrl(), 12,
                                        Direction.FACING_UP),
                                        new ViewableEntity(60, 10, TextureLocation.FROG.getUrl(),
                                                            12, Direction.FACING_DOWN),
                                        new ViewableEntity(90, 10, TextureLocation.FROG.getUrl(),
                                                            12, Direction.FACING_RIGHT),
                                        new ViewableEntity(0, 10, TextureLocation.FROG.getUrl(),
                                                12, Direction.FACING_LEFT)));
                                                
        });
        credits.setOnAction(e -> {
            final Alert creditsAlert = new Alert(AlertType.INFORMATION);
            creditsAlert.setTitle("CREDITS");
            creditsAlert.setHeaderText("AUTHORS");
            creditsAlert.setContentText("Bonoli Mattia\nCichetti Federico\nPistocchi Filippo\nSponziello Nicolò");
            creditsAlert.showAndWait();
        });
        rules.setOnAction(e -> {
            Alert creditsAlert = new Alert(AlertType.INFORMATION);
            creditsAlert.setTitle("Instructions");
            creditsAlert.setHeaderText("Rules");
            creditsAlert.setContentText("\nCommands: use WASD to move the frog\n\n\n"
                    + "Try to avoid cars and get to the safe\n"
                    + "and warm home! Along the path you may find\n"
                    + "some strange \"things\", it's up to you to\n"
                    + "decide if it's worth taking them!");
            creditsAlert.showAndWait();
        });
    }

    @Override
    public void initialize() {   }
}
