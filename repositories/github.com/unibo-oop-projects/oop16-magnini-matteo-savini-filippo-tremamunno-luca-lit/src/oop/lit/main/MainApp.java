package oop.lit.main;

import java.io.File;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import oop.lit.controller.Controller;
import oop.lit.controller.ControllerImpl;
import oop.lit.model.GameFactory;
import oop.lit.model.GameModel;
import oop.lit.util.FileType;
import oop.lit.view.CustomViewComponents;
import oop.lit.view.EnvironmentLayout;
import oop.lit.view.ViewRequests;
import oop.lit.view.ViewRequestsImpl;

/**
 * This class manages the correct initialization of all the graphic components (and relative controllers)
 * of the LIT application when it starts being used.
 */
public class MainApp extends Application {
    private static final double JAVA_VERSION = getVersion();
    private static final double REQUIRED = 1.8;
    private static final String MESSAGE = "La tua versione di Java non è compatibile con i requisiti minimi richiesti per una corretta esecuzione dell'applicazione.";

    private static final double PADDING = 20.0;
    private static final int CARAMEL_R = 167;
    private static final int CARAMEL_G = 107;
    private static final int CARAMEL_B = 41;
    private static final double INTRO_HEIGHT = 300.0;
    private static final double INTRO_WIDTH = 400.0;
    private static final double ROOT_LAYOUT_HEIGHT = 625.0;
    private static final double ROOT_LAYOUT_WIDTH = 1100.0;

    private Stage primaryStage;
    private final BorderPane seed = new BorderPane();

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LIT - Ludoteca In Tasca");
        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:res/lit_icon.png"));
        this.loadInitialScreen();
    }

    /**
     * Shows the initial screen of the LIT application, waiting for the user to click the button. Once
     * it is clicked, it generates the functioning environment of the LIT application.
     */
    private void loadInitialScreen() {
        final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        this.primaryStage.setX((screenBounds.getWidth() - this.primaryStage.getWidth()) / 2.0);
        this.primaryStage.setY((screenBounds.getHeight() - this.primaryStage.getHeight()) / 2.0);
        final BackgroundFill stageColor = new BackgroundFill(Color.rgb(CARAMEL_R, CARAMEL_G, CARAMEL_B), null, null);

        final BorderPane firstScreen = new BorderPane();
        final HBox buttonRow = new HBox(PADDING);
        final Button clickHere = CustomViewComponents.mainButton("START");
        final Button load = CustomViewComponents.mainButton("LOAD SAVE");
        final Button loadGame = CustomViewComponents.mainButton("LOAD GAME");
        buttonRow.getChildren().addAll(clickHere, load, loadGame);
        buttonRow.setAlignment(Pos.CENTER);
        firstScreen.setBackground(new Background(stageColor));
        firstScreen.setPrefSize(INTRO_WIDTH, INTRO_HEIGHT);
        firstScreen.setCenter(buttonRow);

        final Scene initialScene = new Scene(firstScreen, INTRO_WIDTH, INTRO_HEIGHT);
        this.primaryStage.setScene(initialScene);
        this.primaryStage.setResizable(true);

        this.primaryStage.show();

        final ViewRequests view = new ViewRequestsImpl();
        clickHere.setOnMouseClicked(e -> {
            this.initGame(Games.getDefaultGame(view), view);
        });
        load.setOnMouseClicked(e -> {
            final File file = CustomViewComponents.getFileChooser(FileType.LITSAV, "Open lit save")
                    .showOpenDialog(load.getScene().getWindow());
            if (file != null) {
                try {
                    this.initGame(Games.loadGameSave(view, file), view);
                } catch (IllegalArgumentException ex) {
                    this.showErrorMessage(ex.getMessage());
                }
            }
        });
        loadGame.setOnMouseClicked(e -> {
            final File file = CustomViewComponents.getFileChooser(FileType.JAR, "Open lit game")
                    .showOpenDialog(loadGame.getScene().getWindow());
            if (file != null) {
                try {
                    final Set<GameFactory> factories = Games.getGameFactories(file);
                    final Alert alert = new Alert(AlertType.NONE, null, ButtonType.OK, ButtonType.CANCEL);
                    final ChoiceBox<GameFactory> cb = new ChoiceBox<>();
                    cb.setConverter(new StringConverter<GameFactory>() {
                        @Override
                        public String toString(final GameFactory object) {
                            return object.getName();
                        }
                        @Override
                        public GameFactory fromString(final String string) {
                            return null;
                        }
                    });
                    cb.getItems().addAll(factories);
                    cb.getSelectionModel().selectFirst();
                    alert.getDialogPane().setContent(new HBox(new Label("Choose game: "), cb));
                    alert.showAndWait().ifPresent(result -> {
                        if (result.equals(ButtonType.OK)) {
                            this.initGame(cb.getValue().getGame(view), view);
                        }
                    });
                } catch (IllegalArgumentException ex) {
                    this.showErrorMessage(ex.getMessage());
                }
            }
        });
    }

    private void showErrorMessage(final String message) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
 
    private void initGame(final GameModel gameModel, final ViewRequests view) {
        final Scene scene = new Scene(this.seed, ROOT_LAYOUT_WIDTH, ROOT_LAYOUT_HEIGHT);

        primaryStage.setScene(scene);

        final Controller controller = new ControllerImpl(gameModel, view);
        final EnvironmentLayout environment = new EnvironmentLayout(scene, gameModel, controller);
        this.seed.setCenter(environment.getEnvironment());

        this.primaryStage.setMaximized(true);
        this.primaryStage.show();
    }

    /**
     * Returns the main stage.
     * @return the stage of the application
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    // Metodo privato statico per generare il messaggio all'utente che non abbia Java 8 installato sulla
    // sua macchina.
    private static void showDenialMessage() {
        final Stage denialStage = new Stage();
        final BorderPane denialScreen = new BorderPane();
        final Label denialMessage = new Label(MESSAGE);
        denialScreen.setPrefSize(INTRO_WIDTH, INTRO_HEIGHT);
        denialMessage.setAlignment(Pos.CENTER);
        denialScreen.setCenter(denialMessage);

        final Scene denialScene = new Scene(denialScreen);
        denialStage.setScene(denialScene);
    }

    // Metodo privato statico che permette al main di ottenere la versione di Java installata nel sistema
    // in un formato appropriato per un confronto con il requisito, espresso come un numero double.
    private static double getVersion() {
        final String version = System.getProperty("java.version");
        int pos = version.indexOf('.');
        pos = version.indexOf('.', pos + 1);
        return Double.parseDouble(version.substring(0, pos));
    }

    /**
     * The main method of the application.
     * @param args the supplied command-line arguments as an array of String objects.
     */
    public static void main(final String[] args) {
        // Dato l'uso frequente di stream e sintassi Lambda all'interno dell'applicazione, è importante
        // verificare che la corretta versione di Java (la 8) sia installata all'interno del sistema. Se
        // tale requisito è soddisfatto l'applicazione può iniziare la sua esecuzione, altrimenti l'utente
        // viene informato di tale incompatibilità.
        if (JAVA_VERSION >= REQUIRED) {
            launch(args);
        } else {
            showDenialMessage();
        }
    }
}
