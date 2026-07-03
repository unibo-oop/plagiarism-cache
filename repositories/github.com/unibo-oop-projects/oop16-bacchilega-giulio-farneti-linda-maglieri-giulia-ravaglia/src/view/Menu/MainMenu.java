package view.Menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import view.Utilities.Utilities;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import controller.ControllerImp;

/**
 * Class that implements the initial panel of the game. 
 * 
 * Author: Linda Farneti.
 *
 */
public class MainMenu extends Application implements MenuInterface {

    private Stage stage;
    private List<Button> buttons;
    private BorderPane border;
    private Text scenetitle;
    private VBox bBox;
    private ImageView chessImage;
    private Scene scene;
    private GridPane grid;

    /**
     * Constructor.
     */
    public MainMenu() {
        buttons = new ArrayList<>();
        border = new BorderPane();
        grid = new GridPane();
    }

    @Override
    public void start(final Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("CheckMate!");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Menu/chessIcon.png")));
        stage.setResizable(false);
        grid.setAlignment(Pos.CENTER);
        grid.add(border, 0, 0);

        scenetitle = new Text("Welcome on CheckMate!");
        scenetitle.setId("title-text");
        border.setTop(scenetitle);

        bBox = new VBox(10);
        bBox.setPrefSize(Utilities.HEIGHT_MENU / 3, Utilities.HEIGHT_MENU / 8);
        bBox.setAlignment(Pos.BOTTOM_CENTER);

        for (int i = 0; i < 4; i++) {
            Button btn = new Button();
            btn.setMinSize(bBox.getPrefWidth(), bBox.getPrefHeight());
            switch(i) {
                case 0:
                    btn.setText("Play");
                    break;
                case 1:
                    btn.setText("Options");
                    break;
                case 2:
                    btn.setText("Credits");
                    break;
                case 3:
                    btn.setText("Exit");
                    break;
                default:
            }
            buttons.add(btn);
            btn.setOnAction(e -> this.actionPerformed(e));
        }

        bBox.getChildren().addAll(buttons);
        bBox.setAlignment(Pos.BOTTOM_RIGHT);
        border.setCenter(bBox);

        chessImage = new ImageView(new Image(getClass().getResource("/Menu/chess2.png").toExternalForm()));
        chessImage.setFitHeight(Utilities.HEIGHT_MENU / Utilities.UTILITIES_NUMBER);
        chessImage.setFitWidth(Utilities.HEIGHT_MENU / Utilities.UTILITIES_NUMBER);

        border.setLeft(chessImage);

        scene = new Scene(grid, Utilities.WIDTH_MENU, Utilities.HEIGHT_MENU);
        stage.setScene(scene);

        scene.getStylesheets().add(MainMenu.class.getResource("InitialMenu.css").toExternalForm());
        stage.show();
    }

    /**
     * Useful method for button's actionListener.
     * 
     * @param e event
     */
    private void actionPerformed(final ActionEvent e) {
        int idx = buttons.indexOf(e.getSource());
        switch(idx) {
            case 0: 
                ControllerImp.getLog().gameStart();
                stage.close();
                break;
            case 1:
                OptionsMenu optionInterface = new OptionsMenu();
                optionInterface.start(stage);
                break;
            case 2:
                Credits creditsInterface = new Credits();
                creditsInterface.start(stage);
                break;
            case 3:
                stage.close();
                break;
            default:
        }
    }

    /**
     * Method that return the primaryStage.
     * 
     * @return stage
     */
    public Stage getPrimaryStage() {
        return this.stage;
    }
}