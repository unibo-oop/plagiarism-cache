package com.geoquiz.view.menu;

import com.geoquiz.view.utility.Background;

import java.io.IOException;

import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.ButtonsCategory;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The scene where user can choose game category.
 */
public class CategoryScene extends Scene {

    private static final double POS_1_X = 100;
    private static final double POS_1_Y = 450;
    private static final double POS_2_X = 250;
    private static final double POS_2_Y = 300;
    private static final double POS_X_BACK = 450;
    private static final double POS_Y_BACK = 600;
    private static final double BUTTON_WIDTH = 350;
    private static final Text BUTTON_PRESSED = new Text();
    private static final double USER_LABEL_FONT = 40;

    private final Pane panel = new Pane();

    private final HBox hbox = new HBox(10);
    private final HBox hbox2 = new HBox(10);
    private final VBox vbox = new VBox();

    /**
     * @param mainStage
     *            the stage where the scene is called.
     */
    public CategoryScene(final Stage mainStage) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyButton capitals;
        final MyButton currencies;
        final MyButton dishes;
        final MyButton monuments;
        final MyButton flags;
        final MyButton back;

        final MyLabel userLabel = MyLabelFactory.createMyLabel("USER: " + LoginMenuScene.getUsername(), Color.BLACK,
                USER_LABEL_FONT);

        capitals = MyButtonFactory.createMyButton(ButtonsCategory.CAPITALI.toString(), Color.BLUE, BUTTON_WIDTH);
        currencies = MyButtonFactory.createMyButton(ButtonsCategory.VALUTE.toString(), Color.BLUE, BUTTON_WIDTH);
        dishes = MyButtonFactory.createMyButton(ButtonsCategory.CUCINA.toString(), Color.BLUE, BUTTON_WIDTH);
        monuments = MyButtonFactory.createMyButton(ButtonsCategory.MONUMENTI.toString(), Color.BLUE, BUTTON_WIDTH);
        flags = MyButtonFactory.createMyButton(ButtonsCategory.BANDIERE.toString(), Color.BLUE, BUTTON_WIDTH);
        back = MyButtonFactory.createMyButton(Buttons.INDIETRO.toString(), Color.BLUE, BUTTON_WIDTH);

        hbox.setTranslateX(POS_1_X);
        hbox.setTranslateY(POS_1_Y);

        hbox2.setTranslateX(POS_2_X);
        hbox2.setTranslateY(POS_2_Y);

        vbox.setTranslateX(POS_X_BACK);
        vbox.setTranslateY(POS_Y_BACK);

        hbox.getChildren().addAll((Node) flags, (Node) currencies, (Node) dishes);
        hbox2.getChildren().addAll((Node) monuments, (Node) capitals);
        vbox.getChildren().add((Node) back);

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

        ((Node) capitals).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            BUTTON_PRESSED.setText(ButtonsCategory.CAPITALI.toString());
            getCategoryPressed();
            mainStage.setScene(new ModeScene(mainStage));
        });

        ((Node) monuments).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            BUTTON_PRESSED.setText(ButtonsCategory.MONUMENTI.toString());
            getCategoryPressed();
            mainStage.setScene(new ModeScene(mainStage));
        });

        ((Node) currencies).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            BUTTON_PRESSED.setText(ButtonsCategory.VALUTE.toString());
            getCategoryPressed();
            mainStage.setScene(new ModeScene(mainStage));
        });

        ((Node) flags).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            BUTTON_PRESSED.setText(ButtonsCategory.BANDIERE.toString());
            getCategoryPressed();
            mainStage.setScene(new ModeScene(mainStage));
        });

        ((Node) dishes).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            BUTTON_PRESSED.setText(ButtonsCategory.CUCINA.toString());
            getCategoryPressed();
            mainStage.setScene(new ModeScene(mainStage));
        });

        this.panel.getChildren().addAll(Background.getImage(), Background.createBackground(), hbox, hbox2, vbox,
                Background.getLogo(), (Node) userLabel);

        this.setRoot(this.panel);
    }

    /**
     * @return category.
     */
    public static String getCategoryPressed() {
        return BUTTON_PRESSED.getText();
    }
}
