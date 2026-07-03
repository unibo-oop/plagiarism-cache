package view.Menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import view.Utilities.Utilities;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Class that displays the project's authors and the work division.
 * 
 * Author: Linda Farneti.
 *
 */
public class Credits extends Application implements MenuInterface {

    private Stage stage;
    private Scene scene;
    private GridPane panel;
    private Text scenetitle;
    private Label text;
    private Button home;

    /**
     * Constructor.
     */
    public Credits() {
        stage = new Stage();
        panel = new GridPane();
        home = new Button();
    }

    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("CheckMate!");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Menu/chessIcon.png")));
        stage.setResizable(false);

        panel.setHgap(2);
        panel.setVgap(2);
        panel.setAlignment(Pos.CENTER);

        scenetitle = new Text("Credits");
        panel.add(scenetitle, 0, 0);
        scenetitle.setId("title-text");
        BorderPane.setAlignment(scenetitle, Pos.BOTTOM_CENTER);

        text = new Label("\nModel : Giulio Bacchilega\nController : Alex Ravaglia\nView : Giulia Maglieri\n\t   Linda Farneti");
        panel.add(text, 0, 1);
        BorderPane.setAlignment(text, Pos.BOTTOM_CENTER);

        home.setGraphic(new ImageView(new Image(OptionsMenu.class.getResourceAsStream("/Menu/home.png"), Utilities.MENU_ICON, Utilities.MENU_ICON, true, true)));
        panel.add(home, 1, 0);
        home.setOnAction(e-> this.actionPerformed(e));

        scene = new Scene(panel, Utilities.WIDTH_MENU, Utilities.HEIGHT_MENU);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(MainMenu.class.getResource("InitialMenu.css").toExternalForm());
        primaryStage.show();
    }

    /**
     * Useful method for button's actionListener.
     * 
     * @param e event
     */
    private void actionPerformed(final ActionEvent e) {
        MainMenu mainMenuInterface = new MainMenu();
        mainMenuInterface.start(stage);
    }

    /**
     * Method that return the primaryStage.
     * 
     * @return stage
     */
    public Stage getPrimaryStage() {
        return stage;
    }
}
