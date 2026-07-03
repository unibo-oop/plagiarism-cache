package view.mainmenu;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.dialogs.CloseGameDiag;
import view.dialogs.CreditDiag;
import view.mainmenu.handlers.FileChooserHandler;


/**
 * 
 * This class shows application's first menu. It build a non resizable 400x600 window.
 * Extends Application class.
 *
 */
public final class MainMenu extends Application {

    /* Setting up constants. */
    private static final double WIDTH = 400;
    private static final double HEIGHT = 600;
    private static final double SPACING = 23;

    private static final String MENU_BUTTON_STYLE = "menu-button";
    private static final String GAMERULES_PATH = "/documentation/Risk!!Rules.pdf";

    @Override
    public void start(final Stage stage) {
        final StackPane mainPane = new StackPane();
        final VBox buttonContainer = new VBox(MainMenu.SPACING);
        final Scene scene = new Scene(mainPane, MainMenu.WIDTH, MainMenu.HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Risiko!!");
        scene.getStylesheets().add("/Style.css");

        /* Setting up buttons. */
        final JFXButton newGame = new JFXButton("New Game");
        newGame.getStyleClass().add(MENU_BUTTON_STYLE);
        newGame.setOnMouseClicked(e -> {
            Platform.runLater(new Runnable() {
                public void run() {
                    new view.playersetup.PlayerSetup().start(new Stage());
                }
            });
            stage.close();
        });

        final JFXButton loadGame = new JFXButton("Load Game");
        loadGame.getStyleClass().add(MENU_BUTTON_STYLE);
        loadGame.addEventFilter(MouseEvent.MOUSE_CLICKED, new FileChooserHandler());
        loadGame.setOnMouseClicked(e -> stage.close());

        final JFXButton gameRules = new JFXButton("Game Rules");
        gameRules.getStyleClass().add(MENU_BUTTON_STYLE);
        gameRules.setOnMouseClicked(e -> {
            try {
                final File temp = File.createTempFile("manual", ".pdf");
                temp.deleteOnExit();
                Files.copy(MainMenu.class.getResourceAsStream(GAMERULES_PATH), Paths.get(temp.toURI()), StandardCopyOption.REPLACE_EXISTING);
                getHostServices().showDocument(temp.toURI().toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });


        final JFXButton options = new JFXButton("Credits");
        options.getStyleClass().add(MENU_BUTTON_STYLE);
        options.setOnMouseClicked(e-> new CreditDiag(mainPane).show()); 

        final JFXButton exit = new JFXButton("Exit");
        exit.getStyleClass().add(MENU_BUTTON_STYLE);
        exit.setOnMouseClicked(e -> {
            final CloseGameDiag exitDiag = new CloseGameDiag(mainPane);
            exitDiag.show();
        });

        /* Setting up Logo Image. */
        final LogoContainer logo = new LogoContainer();
        logo.setContent(MainMenu.class.getResourceAsStream("/images/tankLogo.txt"));

        /* Completing Stage. */
        buttonContainer.getChildren().addAll(logo, newGame, loadGame, gameRules, options, exit);
        buttonContainer.setAlignment(Pos.CENTER);
        mainPane.getChildren().add(buttonContainer);

        /* Add icon */
        final Image icon = new Image(MainMenu.class.getResource("/images/icon.png").toExternalForm());
        stage.getIcons().add(icon);

        /* Reset Focus. */
        mainPane.requestFocus();

        stage.setResizable(false);
        stage.show();
    }

}
