package view;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.GameMode;

/**
 * Classe che rappresenta la view di partenza, il menù principale.
 * @author Gnoli Mirco
 */
//valutare decorator//adapter
public class MainMenu {

    private static final int MAIN_WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
    private static final int MAIN_WINDOW_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);

    private static final int TITLE_HEIGHT = (int) (MAIN_WINDOW_HEIGHT * 0.1); //90
    private static final int MENU_BUTTON_WIDTH = (int) (MAIN_WINDOW_WIDTH / 2); //300
    private static final int VERTICAL_OFFSET = (int) (MAIN_WINDOW_HEIGHT * 0.025); //20

    private Stage stage;
    private Scene mainScene;

    /**
     * 
     * @param stage - finestra principale
     */
    public MainMenu(final Stage stage) {
        super();
        this.stage = stage;
        this.stage.setTitle("Arkanoid menu");
        this.stage.setWidth(MAIN_WINDOW_WIDTH);
        this.stage.setHeight(MAIN_WINDOW_HEIGHT);
        this.stage.setResizable(false);

        List<Button> buttons = new ArrayList<>();

        Button classic = new MenuButton("Classic game");
        classic.setOnAction(e -> {
            this.stage.close();
            new Thread(new Controller(new Stage(), GameMode.CLASSIC)).start();
        });

        Button survival = new MenuButton("Survival game");
        survival.setOnAction(e -> {
            this.stage.close();
            new Thread(new Controller(new Stage(), GameMode.SURVIVAL)).start();
        });

        Button score = new MenuButton("Score");
        score.setOnAction(e -> this.stage.setScene(new HighscoreView(new BorderPane(), this.stage)));

        Button credits = new MenuButton("Credits");
        credits.setOnAction(e -> this.stage.setScene(new CreditsView(new BorderPane(), this.stage)));

        buttons.add(classic);
        buttons.add(survival);
        buttons.add(score);
        buttons.add(credits);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(VERTICAL_OFFSET);

        for (int i = 0; i < buttons.size(); i++) {
            grid.add(buttons.get(i), 0, i);
        }

        FlowPane p = new FlowPane();
        p.setAlignment(Pos.CENTER);

        Label title = new Label("Arkanoid");
        title.setFont(Font.font("Serif", TITLE_HEIGHT));
        title.setTextFill(Color.BLUE);

        p.getChildren().add(title);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(p);
        mainPane.setCenter(grid);

        Scene s = new Scene(mainPane);
        this.mainScene = s;
        this.stage.setScene(s);
        this.stage.show();
    }

    /**
     * 
     * @return {@link Scene} - scena pricipale
     */
    public Scene getMainScene() {
        return this.mainScene;
    }

    /**
     * Classe che rappresenta bottoni personalizzati del menù principale.
     * @author Gnoli Mirco
     *
     */
    private class MenuButton extends Button {
        private final Font buttonFont = new Font("Algerian", (int) (MAIN_WINDOW_HEIGHT * 0.04));

        MenuButton(final String text) {
            super(text);
            this.setAlignment(Pos.CENTER);
            this.setFont(buttonFont);
            this.setPrefWidth(MENU_BUTTON_WIDTH);
        }
    }
}
