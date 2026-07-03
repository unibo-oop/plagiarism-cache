package com.geoquiz.view.menu;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.ButtonsCategory;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;
import com.geoquiz.view.utility.Background;
import com.geoquiz.view.button.MyButton;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The scene where user can choose difficulty level.
 */
public class LevelScene extends Scene {

    private static final double POS_2_X = 250;
    private static final double POS_2_Y = 300;
    private static final double POS_Y_BACK = 600;
    private static final double POS_1_X = 100;
    private static final double BUTTON_WIDTH = 350;
    private static final double USER_LABEL_FONT = 40;

    private final Pane panel = new Pane();
    private final VBox vbox = new VBox();
    private final VBox vbox2 = new VBox();
    private static final Text BUTTONPRESSED = new Text();

    /**
     * @param mainStage
     *            the stage where the scene is called.
     */
    public LevelScene(final Stage mainStage) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyLabel userLabel = MyLabelFactory.createMyLabel("USER: " + LoginMenuScene.getUsername(), Color.BLACK,
                USER_LABEL_FONT);

        final MyButton back;
        final MyButton easy;
        final MyButton medium;
        final MyButton hard;

        back = MyButtonFactory.createMyButton(Buttons.INDIETRO.toString(), Color.BLUE, BUTTON_WIDTH);
        easy = MyButtonFactory.createMyButton(ButtonsCategory.FACILE.toString(), Color.BLUE, BUTTON_WIDTH);
        medium = MyButtonFactory.createMyButton(ButtonsCategory.MEDIO.toString(), Color.BLUE, BUTTON_WIDTH);
        hard = MyButtonFactory.createMyButton(ButtonsCategory.DIFFICILE.toString(), Color.BLUE, BUTTON_WIDTH);

        vbox.getChildren().addAll((Node) easy, (Node) medium, (Node) hard);
        vbox2.getChildren().add((Node) back);

        vbox.setTranslateX(POS_2_X);
        vbox.setTranslateY(POS_2_Y);

        vbox2.setTranslateX(POS_1_X);
        vbox2.setTranslateY(POS_Y_BACK);

        ((Node) back).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            mainStage.setScene(new CategoryScene(mainStage));
        });

        ((Node) easy).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            LevelScene.BUTTONPRESSED.setText(ButtonsCategory.FACILE.toString());
            getLevelPressed();
            try {
                try {
                    mainStage.setScene(new QuizGamePlay(mainStage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        ((Node) medium).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            LevelScene.BUTTONPRESSED.setText(ButtonsCategory.MEDIO.toString());
            getLevelPressed();
            try {
                try {
                    mainStage.setScene(new QuizGamePlay(mainStage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        ((Node) hard).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            LevelScene.BUTTONPRESSED.setText(ButtonsCategory.DIFFICILE.toString());
            getLevelPressed();
            try {
                try {
                    mainStage.setScene(new QuizGamePlay(mainStage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        this.panel.getChildren().addAll(ModeScene.createBackgroundImage(), Background.createBackground(), vbox, vbox2,
                Background.getLogo(), (Node) userLabel);

        this.setRoot(this.panel);

    }

    /**
     * @return difficulty level.
     */
    public static String getLevelPressed() {
        return BUTTONPRESSED.getText();
    }

}
