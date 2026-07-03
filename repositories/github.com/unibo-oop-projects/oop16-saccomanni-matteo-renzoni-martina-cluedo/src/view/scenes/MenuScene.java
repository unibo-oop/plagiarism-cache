package view.scenes;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import application.AudioPlayer;
import application.Controller;
import application.ViewObserver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import view.dialogs.CloseDialog;

/**
 * Initial menu scene.
 */
public final class MenuScene {
    private static final double SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double IMAGE_DIMENSION = SCREEN_H * 0.3;
    private static final double BUTTON_DISTANCE = 30;
    private static AudioPlayer music;

    private MenuScene() {
    }

    /**
     * To set AudioPlayer Object.
     * 
     * @param backgroundMusic
     *            AudioPlayer object initialized in controller.
     */
    public static void setMusic(final AudioPlayer backgroundMusic) {
        music = backgroundMusic;
    }

    /**
     * Method which creates the menu scene.
     * 
     * @return parent to show.
     */
    public static Parent createPane() {
        final ViewObserver observer = Controller.getController();
        final VBox rootPane = new VBox();
        rootPane.setAlignment(Pos.CENTER);
        final Button btnNewGame = new Button("NEW GAME");
        final Button btnLoadGame = new Button("LOAD GAME");
        final Button btnInstructions = new Button("INSTRUCTIONS");
        final Button btnExit = new Button("EXIT");
        final Button btnMute = new Button("MUTE MUSIC");
        final ImageView muteIm = new ImageView("/images/soundOn.png");
        btnMute.setGraphic(muteIm);
        btnMute.setOnAction(e -> {
            music.changeMute();
            if (music.isMute()) {
                muteIm.setImage(new Image("/images/soundOff.png"));
                btnMute.setText("PLAY MUSIC");
            } else {
                muteIm.setImage(new Image("/images/soundOn.png"));
                btnMute.setText("MUTE MUSIC");
            }
        });
        final ImageView imgLogo = new ImageView(new Image("/images/logo.png"));
        imgLogo.setPreserveRatio(true);
        imgLogo.setFitHeight(IMAGE_DIMENSION);
        rootPane.getChildren().add(imgLogo);
        VBox.setMargin(imgLogo, new Insets(0, 0, imgLogo.getFitHeight() / 4, 0));
        rootPane.getChildren().add(btnNewGame);
        rootPane.getChildren().add(btnLoadGame);
        rootPane.getChildren().add(btnInstructions);
        rootPane.getChildren().add(btnExit);
        rootPane.getChildren().add(btnMute);
        rootPane.setSpacing(BUTTON_DISTANCE);

        btnExit.setOnAction(e -> {
            CloseDialog.getDialog().createCloseDialog();
        });

        btnNewGame.setOnAction(e -> {
            observer.newGame();
        });

        btnLoadGame.setOnAction(e -> {
            observer.upload();
        });

        btnInstructions.setOnAction(e -> {
            try {
                final File tmp = File.createTempFile("clue", ".pdf");
                tmp.deleteOnExit();
                Files.copy(MenuScene.class.getResourceAsStream("/clueRules.pdf"), Paths.get(tmp.toURI()),
                        StandardCopyOption.REPLACE_EXISTING);
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        Desktop.getDesktop().open(tmp);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return rootPane;
    }
}