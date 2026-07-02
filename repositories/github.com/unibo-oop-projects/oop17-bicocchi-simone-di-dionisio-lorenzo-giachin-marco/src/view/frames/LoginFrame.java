package view.frames;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.CustomViewComponent;
import view.Difficulty;
import view.View.Frames;

/**
 * The frame for the login.
 */
public class LoginFrame extends AbstractViewFrame {

    private static final String VALIDNAME = "Inserisci un nome valido (Max 10 char)";
    private static final String INSERTNAME = "Scegli il tuo avatar e inserisci un nome";
    private static final String AVVIA = "Avvia";
    private static final String DIFFICULTY = "Difficulty";
    private static final String EASY = "Easy";
    private static final String MEDIUM = "Medium";
    private static final String EXTREME = "Extreme";

    private Difficulty difficulty = Difficulty.EASY;
    private TilePane tilePane = new TilePane();
    private BorderPane mainPane = new BorderPane();
    private ImageWithUrl currentLogo;

    @Override
    public void start(final Stage newPrimaryStage) throws Exception {
        setStage(newPrimaryStage);
        setFrame();
        setScene(mainPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        setExitOperation();
    }

    @Override
    public void setFrame() {
        getStage().setTitle("Tamagotchi");
        TextField nameField = new TextField();
        nameField.setId("nameField");
        nameField.setStyle("-fx-font-size: " + LOGINFONTSIZE + "em;");
        nameField.setPadding(new Insets(0));
        Text insertNameTxt = new Text(INSERTNAME);
        insertNameTxt.setId("coloredPacificoText");
        insertNameTxt.setStyle("-fx-font-size: " + LOGINFONTSIZE + "em;");
        HBox logoDifficulty = new HBox();
        HBox logos = new HBox();
        logos.setAlignment(Pos.CENTER);

        final String logo1 = LoginFrame.class.getClassLoader().getResource("tama.png").toExternalForm();
        final String logo2 = LoginFrame.class.getClassLoader().getResource("tama1.png").toExternalForm();
        final String logo3 = LoginFrame.class.getClassLoader().getResource("tama2.png").toExternalForm();
        final String logo4 = LoginFrame.class.getClassLoader().getResource("tama4.png").toExternalForm();
        final String logo5 = LoginFrame.class.getClassLoader().getResource("tama5.png").toExternalForm();
        this.currentLogo = new ImageWithUrl(new Image(logo1), logo1);
        logos.getChildren().addAll(new ImageWithUrl(new Image(logo1), logo1),
                new ImageWithUrl(new Image(logo2), logo2), new ImageWithUrl(new Image(logo3), logo3),
                new ImageWithUrl(new Image(logo4), logo4), new ImageWithUrl(new Image(logo5), logo5));
        logos.getChildren().forEach(l -> {
            ImageWithUrl img = ((ImageWithUrl) l);
            img.setFitWidth(LOGOLOGINSIZE);
            img.setFitHeight(LOGOLOGINSIZE);
            l.setOnMouseClicked(e -> {
                this.currentLogo = ((ImageWithUrl) l);
                logos.getChildren().forEach(x -> {
                    ImageWithUrl image = ((ImageWithUrl) x);
                    image.setFitWidth(LOGOLOGINSIZE);
                    image.setFitHeight(LOGOLOGINSIZE);
                });
                img.setFitWidth(CURRENTLOGOSIZE);
                img.setFitHeight(CURRENTLOGOSIZE);
            });
        });

        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton(EASY);
        rb1.setToggleGroup(group);
        rb1.setStyle("-fx-font-size: " + DIFFICULTYFONTSIZE + "em;");
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton(MEDIUM);
        rb2.setToggleGroup(group);
        rb2.setStyle("-fx-font-size: " + DIFFICULTYFONTSIZE + "em;");
        RadioButton rb3 = new RadioButton(EXTREME);
        rb3.setToggleGroup(group);
        rb3.setStyle("-fx-font-size: " + DIFFICULTYFONTSIZE + "em;");
        VBox difficultyBox = new VBox();
        difficultyBox.setId("difficultyBox");
        Label diff = new Label(DIFFICULTY);
        diff.setId("coloredPacificoText");
        diff.setStyle("-fx-font-size: " + DIFFICULTYFONTSIZE + "em;");
        difficultyBox.getChildren().addAll(diff, rb1, rb2, rb3);
        rb1.setOnAction(e -> {
            this.difficulty = Difficulty.EASY;
        });
        rb2.setOnAction(e -> {
            this.difficulty = Difficulty.MEDIUM;
        });
        rb3.setOnAction(e -> {
            this.difficulty = Difficulty.EXTREME;
        });
        logoDifficulty.getChildren().addAll(logos, difficultyBox);
        logoDifficulty.setSpacing(DIFFICULTYSPACING);
        logoDifficulty.setAlignment(Pos.CENTER);
        ((ImageWithUrl) logos.getChildren().get(0)).setFitWidth(CURRENTLOGOSIZE);
        ((ImageWithUrl) logos.getChildren().get(0)).setFitHeight(CURRENTLOGOSIZE);
        Button submitBtn = new Button(AVVIA);
        submitBtn.setId("brickRedButton");
        submitBtn.setStyle("-fx-font-size: " + LOGINBUTTONFONTSIZE + "em;" + "-fx-margin: 0 0 20 0;");
        tilePane.setId("defaultBackGroundColor");
        tilePane.setAlignment(Pos.CENTER);
        tilePane.getChildren().addAll(insertNameTxt, logoDifficulty, nameField, submitBtn);
        mainPane.setCenter(tilePane);
        mainPane.getStylesheets().add(LoginFrame.class.getClassLoader().getResource("application.css").toExternalForm());

        nameField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.checkInput(event, nameField);
            }
        });

        submitBtn.setOnAction((ActionEvent event) -> {
            this.checkInput(event, nameField);
        });
    }

    @Override
    public void clearStage() {
        getStage().getScene().getWindow().hide();
        getStage().close();
        getStage().setScene(null);
        tilePane.getChildren().clear();
        mainPane.getChildren().clear();
    }

    /**
     * Checks the name and goes to the play view.
     * 
     * @param event
     *            is the input on the TextField
     */
    private void checkInput(final Event event, final TextField nameField) {
        if (getController().setCharacterName(nameField.getText())) {
            clearStage();
            getController().setTamagotchiUrl(currentLogo.getUrl());
            getView().startFrame(Frames.GAME);
            getController().setDifficulty(this.difficulty);
            getController().startTimer();
        } else {
            Alert error = new Alert(AlertType.WARNING);
            error.setTitle("");
            error.setHeaderText("");
            error.setContentText(VALIDNAME);
            error.initStyle(StageStyle.UTILITY);
            error.getDialogPane()
                    .setStyle("-fx-background-color: white;" + "-fx-font-size: 25px;\r\n" + "-fx-font-weight: bold;");
            error.showAndWait();
            nameField.setText("");
        }
    }

    /**
     * 
     */
    private class ImageWithUrl extends ImageView implements CustomViewComponent {
        private final String url;

        ImageWithUrl(final Image img, final String newUrl) {
            super(img);
            this.url = newUrl;
        }

        /**
         * 
         * @return Url
         */
        protected String getUrl() {
            return this.url;
        }
    }
}
