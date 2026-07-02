package view.frames;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 */
public class TutorialFrame extends AbstractViewFrame {
    private static final String SHOP = "SHOP";
    private static final String LOG = "LOGIN";
    private static final String INV = "INVENTORY";
    private static final String GAME = "GAME";
    static final double CATEGORYINSETSTOP = 150;
    static final double CATEGORYINSETSBOTTOMRIGTH = 10;
    static final double CATEGORYSPACING = 20;

    private BorderPane mainPane = new BorderPane();
    private Button buttonLogin = new Button(LOG);
    private Button buttonShop = new Button(SHOP);
    private Button buttonInv = new Button(INV);
    private Button buttonGame = new Button(GAME);
    private VBox buttonBox = new VBox();

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        setStage(newPrimaryStage);
        setFrame();
        setScene(mainPane, SCREEN_WIDTH * 3 / 4, SCREEN_HEIGHT);
        setExitOperation();
    }

    @Override
    public void setFrame() {
        buttonLogin.setId("brickRedButton");
        buttonShop.setId("brickRedButton");
        buttonInv.setId("brickRedButton");
        buttonGame.setId("brickRedButton");
        buttonLogin.setStyle("-fx-font-size: " + 3 + "em;");
        buttonShop.setStyle("-fx-font-size: " + 3 + "em;");
        buttonInv.setStyle("-fx-font-size: " + 3 + "em;");
        buttonGame.setStyle("-fx-font-size: " + 3 + "em;");
        mainPane.setId("defaultBackGroundColor");
        mainPane.setLeft(buttonBox);
        Text tutorial = new Text("TUTORIAL");
        tutorial.setId("coloredPacificoText");
        tutorial.setStyle("-fx-font-size: " + 3 + "em;");
        HBox titleBox = new HBox();
        titleBox.getChildren().add(tutorial);
        titleBox.setAlignment(Pos.CENTER);
        mainPane.setTop(titleBox);
        buttonBox.getChildren().addAll(buttonLogin, buttonShop, buttonInv, buttonGame);
        buttonBox.setPadding(new Insets(CATEGORYINSETSTOP, 0, CATEGORYINSETSBOTTOMRIGTH, CATEGORYINSETSBOTTOMRIGTH));
        buttonBox.setSpacing(CATEGORYSPACING);
        mainPane.getStylesheets().add(TutorialFrame.class.getClassLoader().getResource("application.css").toExternalForm());

        /*
         * Listeners
         */
        buttonLogin.setOnAction(e -> {
            ImageView img = new ImageView(new Image(TutorialFrame.class.getClassLoader().getResource("login.PNG").toExternalForm()));
            img.setFitHeight(TUTORIALIMGHEIGHT);
            img.setFitWidth(TUTORIALIMGWIDTH);
            mainPane.setCenter(img);
        });
        buttonGame.setOnAction(e -> {
            ImageView img = new ImageView(new Image(TutorialFrame.class.getClassLoader().getResource("mainGameTimer.PNG").toExternalForm()));
            img.setFitHeight(TUTORIALIMGHEIGHT);
            img.setFitWidth(TUTORIALIMGWIDTH);
            mainPane.setCenter(img);
        });
        buttonInv.setOnAction(e -> {
            ImageView img = new ImageView(new Image(TutorialFrame.class.getClassLoader().getResource("inventory.PNG").toExternalForm()));
            img.setFitHeight(TUTORIALSHOPINVIMGHEIGHT);
            img.setFitWidth(TUTORIALSHOPINVIMGWIDTH);
            mainPane.setCenter(img);
        });
        buttonShop.setOnAction(e -> {
            ImageView img = new ImageView(new Image(TutorialFrame.class.getClassLoader().getResource("shop.PNG").toExternalForm()));
            img.setFitHeight(TUTORIALSHOPINVIMGHEIGHT);
            img.setFitWidth(TUTORIALSHOPINVIMGWIDTH);
            mainPane.setCenter(img);
        });
    }

    @Override
    public void clearStage() {
        getStage().getScene().getWindow().hide();
        getStage().close();
        getStage().setScene(null);
        mainPane.getChildren().clear();
        buttonBox.getChildren().clear();
    }

    @Override
    public void setExitOperation() {
        getStage().setOnCloseRequest(e -> {
            clearStage();
        });
    }

}
