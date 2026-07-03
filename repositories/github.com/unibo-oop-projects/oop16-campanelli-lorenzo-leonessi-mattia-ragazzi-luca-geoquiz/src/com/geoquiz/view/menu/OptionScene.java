package com.geoquiz.view.menu;

import com.geoquiz.view.utility.Background;

import java.io.IOException;

import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The scene where user can change game options.
 */
public class OptionScene extends Scene {

    private static final double POS_2_X = 250;
    private static final double POS_O = 275;
    private static final double POS = 575;
    private static final double POS_1_X = 100;
    private static final double BUTTON_WIDTH = 350;
    private static final double USER_LABEL_FONT = 40;

    private final Pane panel = new Pane();
    private final CheckBox sound = new CheckBox("MUSICA");
    private final CheckBox effect = new CheckBox("EFFETTI SONORI");
    private final VBox vbox = new VBox(sound, effect);
    private final VBox vbox2 = new VBox();

    /**
     * @param mainStage
     *            the stage where the scene is called.
     */
    public OptionScene(final Stage mainStage) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyLabel userLabel = MyLabelFactory.createMyLabel("USER: " + LoginMenuScene.getUsername(), Color.BLACK,
                USER_LABEL_FONT);

        final MyButton back;
        final MyButton save;
        back = MyButtonFactory.createMyButton(Buttons.INDIETRO.toString(), Color.BLUE, BUTTON_WIDTH);
        save = MyButtonFactory.createMyButton(Buttons.SALVA.toString(), Color.BLUE, BUTTON_WIDTH);

        sound.setSelected(MainWindow.isMusicDisabled() ? false : true);
        sound.setStyle("-fx-font-size: 35");
        effect.setSelected(MainWindow.isWavDisabled() ? false : true);
        effect.setStyle("-fx-font-size: 35");

        vbox.setTranslateX(POS_2_X);
        vbox.setTranslateY(POS_O);
        vbox2.setTranslateX(POS_1_X);
        vbox2.setTranslateY(POS);
        vbox2.getChildren().addAll((Node) save, (Node) back);

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

        ((Node) save).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            this.save();
        });

        this.panel.getChildren().addAll(Background.getImage(), Background.createBackground(), vbox, vbox2,
                Background.getLogo(), (Node) userLabel);

        this.setRoot(this.panel);

    }

    private void save() {
        if (!sound.isSelected()) {
            MainWindow.disableMusic();
        } else {
            MainWindow.resumeMusic();
        }
        if (!effect.isSelected()) {
            MainWindow.stopClick();
        } else {
            MainWindow.playClick();
        }
    }

}
