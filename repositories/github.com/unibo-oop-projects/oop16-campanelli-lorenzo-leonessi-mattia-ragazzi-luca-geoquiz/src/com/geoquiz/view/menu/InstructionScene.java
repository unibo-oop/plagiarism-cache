package com.geoquiz.view.menu;

import java.io.IOException;

import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.utility.Background;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * The scene where user can read instructions game play.
 */
public class InstructionScene extends Scene {

    private static final double POS_X_ISTRUCTIONS = 100;
    private static final double POS_Y_ISTRUCTIONS = 160;
    private static final double INSTRUCTIONS_FONT = 23;
    private static final double BUTTON_WIDTH = 350;
    private static final double POS_X_BACK_BOX = 900;
    private static final double POS_Y_BACK_BOX = 640;

    private final Pane panel = new Pane();
    private final VBox instructionsBox = new VBox();
    private final VBox backBox = new VBox();
    private final Label instructionsLabel = new Label();

    /**
     * @param mainStage
     *            the stage where the scene is called.
     */
    public InstructionScene(final Stage mainStage) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyButton back;
        back = MyButtonFactory.createMyButton(Buttons.INDIETRO.toString(), Color.BLUE, BUTTON_WIDTH);

        instructionsLabel.setText("ISTRUZIONI:\n" + "Il gioco consta di 5 tipologia differenti di quiz:\n"
                + "Capitali\n" + "Monumenti\n" + "Bandiere\n" + "Valute\n" + "Cucina\n" + "\n"
                + "Ogni categoria presenta 3 modalità di gioco:\n"
                + "Classica: sono disponibili 3 aiuti di gioco e 3 vite\n"
                + "Sfida: solo 1 vita disponibile e non ci sono gli aiuti\n"
                + "Allenamento: vite illimitate, senza aiuti, per un puro esercizio\n" + "\n"
                + "Solo le categorie Capitali e Monumenti presentano nella modalità classica 3 difficoltà\n"
                + "Buon GeoQuiz!");
        instructionsBox.getChildren().add((Node) instructionsLabel);
        instructionsLabel.setFont(Font.font("Italic", FontWeight.BOLD, INSTRUCTIONS_FONT));
        instructionsLabel.setTextFill(Color.BLACK);
        instructionsBox.setTranslateX(POS_X_ISTRUCTIONS);
        instructionsBox.setTranslateY(POS_Y_ISTRUCTIONS);
        backBox.setTranslateX(POS_X_BACK_BOX);
        backBox.setTranslateY(POS_Y_BACK_BOX);
        backBox.getChildren().add((Node) back);

        ((Node) back).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            try {
                mainStage.setScene(new MainMenuScene(mainStage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.panel.getChildren().addAll(ModeScene.createBackgroundImage(), Background.createBackground(),
                Background.getLogo(), instructionsBox, backBox);

        this.setRoot(this.panel);
    }
}
