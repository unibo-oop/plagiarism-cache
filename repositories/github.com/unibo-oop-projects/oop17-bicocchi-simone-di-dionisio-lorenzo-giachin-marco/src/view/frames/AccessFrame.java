package view.frames;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.View.Frames;

/**
 * The first view to be shown where you can choose between the login and the
 * ranking page.
 */
public class AccessFrame extends AbstractViewFrame {

    private static final String RANK = "CLASSIFICA";
    private static final String PLAY = "GIOCA";
    private static final String WELCOME = "Welcome to";
    private static final String TAMA = "TAMAGOTCHI";
    private static final String TUTORIAL = "TUTORIAL";

    private TilePane tilePane = new TilePane();
    private BorderPane borderPane = new BorderPane();

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        setStage(newPrimaryStage);
        setFrame();
        setScene(borderPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        setExitOperation();
    }

    @Override
    public void setFrame() {
        getStage().setTitle("Tamagotchi");
        borderPane.setId("defaultBackGroundColor");
        borderPane.setCenter(tilePane);
        Button rankBtn = new Button(RANK);
        rankBtn.setId("brickRedButton");
        rankBtn.setStyle("-fx-font-size: " + TAMAGOTCHIFONTSIZE + "em;");
        Button startBtn = new Button(PLAY);
        startBtn.setId("brickRedButton");
        startBtn.setStyle("-fx-font-size: " + TAMAGOTCHIFONTSIZE + "em;");
        Button tutorialBtn = new Button(TUTORIAL);
        tutorialBtn.setId("yellowRoundButton");
        tutorialBtn.setStyle("-fx-font-size: " + TUTORIALBUTTONFONTSIZE + "em;");
        HBox tutBox = new HBox();
        tutBox.getChildren().add(tutorialBtn);
        tutBox.setPadding(new Insets(TUTORIALBUTTONINSETS));
        borderPane.setTop(tutBox);
        Text welcomeTxt = new Text(WELCOME);
        welcomeTxt.setId("coloredPacificoText");
        welcomeTxt.setStyle("-fx-font-size: " + TAMAGOTCHIFONTSIZE + "em;");
        Text tamaTxt = new Text(TAMA);
        tamaTxt.setId("coloredPacificoText");
        tamaTxt.setStyle("-fx-font-size: " + TAMAGOTCHIFONTSIZE + "em;");
        tilePane.getChildren().addAll(welcomeTxt, tamaTxt, rankBtn, startBtn);
        tilePane.setAlignment(Pos.CENTER);
        borderPane.getStylesheets().add(AccessFrame.class.getClassLoader().getResource("application.css").toExternalForm());

        startBtn.setOnAction((ActionEvent event) -> {
            clearStage();
            getView().goToLogin();
        });
        rankBtn.setOnAction((ActionEvent event) -> {
            getController().loadRanking();
            this.getView().startFrame(Frames.RANKING);
        });
        tutorialBtn.setOnAction(e -> {
            this.getView().startFrame(Frames.TUTORIAL);
        });
    }

    @Override
    public void clearStage() {
        getStage().getScene().getWindow().hide();
        getStage().close();
        getStage().setScene(null);
        borderPane.getChildren().clear();
        tilePane.getChildren().clear();
    }
}
