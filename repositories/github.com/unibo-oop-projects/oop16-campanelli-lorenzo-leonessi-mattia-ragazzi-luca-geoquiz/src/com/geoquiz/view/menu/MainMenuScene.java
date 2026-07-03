package com.geoquiz.view.menu;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.geoquiz.controller.account.Account;
import com.geoquiz.controller.account.AccountImpl;
import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;
import com.geoquiz.view.utility.Background;
import com.geoquiz.view.utility.ExitProgram;
import com.geoquiz.view.button.MyButton;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The scene where user can choose how to do.
 */
public class MainMenuScene extends Scene {

    private static final double POS_1_X = 100;
    private static final double POS_1_Y = 450;
    private static final double BUTTON_WIDTH = 350;
    private static final double USER_LABEL_FONT = 40;
    private static final double POS_X_INSTRUCTIONS = 900;
    private static final double POS_Y_INSTRUCTIONS = 638;

    private final Pane panel = new Pane();
    private final VBox vbox = new VBox();
    private final VBox instructionsButtonBox = new VBox();

    /**
     * @param mainStage
     *            the stage where the scene is called.
     * @throws IOException
     *             for exception.
     */
    public MainMenuScene(final Stage mainStage) throws IOException {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyButton play;
        final MyButton ranking;
        final MyButton options;
        final MyButton exit;
        final MyButton stats;
        final MyButton instructions;
        final Account a = new AccountImpl("account.txt");

        final MyLabel userLabel = MyLabelFactory.createMyLabel("USER: " + LoginMenuScene.getUsername(), Color.BLACK,
                USER_LABEL_FONT);

        play = MyButtonFactory.createMyButton(Buttons.GIOCA.toString(), Color.BLUE, BUTTON_WIDTH);
        ranking = MyButtonFactory.createMyButton(Buttons.CLASSIFICA.toString(), Color.BLUE, BUTTON_WIDTH);
        options = MyButtonFactory.createMyButton(Buttons.OPZIONI.toString(), Color.BLUE, BUTTON_WIDTH);
        exit = MyButtonFactory.createMyButton(Buttons.ESCI.toString(), Color.BLUE, BUTTON_WIDTH);
        stats = MyButtonFactory.createMyButton(Buttons.STATISTICHE.toString(), Color.BLUE, BUTTON_WIDTH);
        instructions = MyButtonFactory.createMyButton(Buttons.ISTRUZIONI.toString(), Color.BLUE, BUTTON_WIDTH);

        instructionsButtonBox.setTranslateX(POS_X_INSTRUCTIONS);
        instructionsButtonBox.setTranslateY(POS_Y_INSTRUCTIONS);
        instructionsButtonBox.getChildren().add((Node) instructions);

        vbox.setTranslateX(POS_1_X);
        vbox.setTranslateY(POS_1_Y);
        vbox.getChildren().addAll((Node) play, (Node) stats, (Node) ranking, (Node) options, (Node) exit);

        ((Node) exit).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            a.logout();
            ExitProgram.exitProgram(mainStage);
        });

        ((Node) instructions).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            mainStage.setScene(new InstructionScene(mainStage));
        });

        ((Node) stats).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            try {
                mainStage.setScene(new MyRankingScene(mainStage));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        ((Node) ranking).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            try {
                mainStage.setScene(new AbsoluteRankingScene(mainStage));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        ((Node) options).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            mainStage.setScene(new OptionScene(mainStage));
        });

        ((Node) play).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            mainStage.setScene(new CategoryScene(mainStage));
        });

        this.panel.getChildren().addAll(Background.getImage(), Background.createBackground(), vbox,
                Background.getLogo(), (Node) userLabel, instructionsButtonBox);

        this.setRoot(this.panel);
    }

}
