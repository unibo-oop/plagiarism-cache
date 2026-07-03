package com.geoquiz.view.menu;

import com.geoquiz.view.utility.Background;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.ButtonsCategory;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The scene where user can choose game modality.
 */
public class ModeScene extends Scene {

    private static final double POS_2_X = 250;
    private static final double POS_2_Y = 350;
    private static final double POS_3_Y = 200;
    private static final double POS_Y_BACK = 600;
    private static final double POS_1_X = 100;
    private static final double LABEL_FONT = 35;
    private static final double BUTTON_WIDTH = 350;
    private static final double OPACITY = 0.5;
    private static final double USER_LABEL_FONT = 40;

    private final Pane panel = new Pane();
    private final VBox vbox = new VBox();
    private final VBox vbox2 = new VBox();
    private final VBox vbox3 = new VBox();
    private static final Text BUTTONPRESSED = new Text();
    private final Label label = new Label();

    /**
     * @param mainStage
     *            the stage where the scene is called.
     */
    public ModeScene(final Stage mainStage) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyButton back;
        final MyButton classic;
        final MyButton challenge;
        final MyButton training;

        final MyLabel userLabel = MyLabelFactory.createMyLabel("USER: " + LoginMenuScene.getUsername(), Color.BLACK,
                USER_LABEL_FONT);

        back = MyButtonFactory.createMyButton(Buttons.INDIETRO.toString(), Color.BLUE, BUTTON_WIDTH);
        classic = MyButtonFactory.createMyButton(ButtonsCategory.CLASSICA.toString(), Color.BLUE, BUTTON_WIDTH);
        challenge = MyButtonFactory.createMyButton(ButtonsCategory.SFIDA.toString(), Color.BLUE, BUTTON_WIDTH);
        training = MyButtonFactory.createMyButton(ButtonsCategory.ALLENAMENTO.toString(), Color.BLUE, BUTTON_WIDTH);

        if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.CAPITALI.toString())) {
            label.setText("Sai indicare la capitale di ciascun paese?\nScegli prima la modalità di gioco!");
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.MONUMENTI.toString())) {
            label.setText(
                    "Sai indicacare dove si trovano questi famosi monumenti?\nScegli prima la modalità di gioco!");
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.BANDIERE.toString())) {
            label.setText("Sai indicare i paesi in base alla bandiera nazionale?\nScegli prima la modalità di gioco!");
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.CUCINA.toString())) {
            label.setText("Sai indicare di quali paesi sono tipici questi piatti?\nScegli prima la modalità di gioco!");
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.VALUTE.toString())) {
            label.setText(
                    "Sai indicare qual e' la valuta adottata da ciascun paese?\nScegli prima la modalità di gioco!");
        }

        label.setFont(Font.font("Italic", FontWeight.BOLD, LABEL_FONT));

        vbox.setTranslateX(POS_2_X);
        vbox.setTranslateY(POS_2_Y);
        vbox.getChildren().addAll((Node) classic, (Node) challenge, (Node) training);
        vbox2.getChildren().add((Node) back);
        vbox3.getChildren().add(label);

        vbox2.setTranslateX(POS_1_X);
        vbox2.setTranslateY(POS_Y_BACK);
        vbox3.setTranslateX(POS_2_X);
        vbox3.setTranslateY(POS_3_Y);

        ((Node) back).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            mainStage.setScene(new CategoryScene(mainStage));
        });

        ((Node) classic).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            ModeScene.BUTTONPRESSED.setText(ButtonsCategory.CLASSICA.toString());
            getModalityPressed();
            if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.CAPITALI.toString())
                    || CategoryScene.getCategoryPressed().equals(ButtonsCategory.MONUMENTI.toString())) {
                mainStage.setScene(new LevelScene(mainStage));
            } else {
                try {
                    try {
                        mainStage.setScene(new QuizGamePlay(mainStage));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        });

        ((Node) challenge).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            ModeScene.BUTTONPRESSED.setText(ButtonsCategory.SFIDA.toString());
            getModalityPressed();
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

        ((Node) training).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            ModeScene.BUTTONPRESSED.setText(ButtonsCategory.ALLENAMENTO.toString());
            getModalityPressed();
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

        this.panel.getChildren().addAll(ModeScene.createBackgroundImage(), Background.createBackground(),
                Background.getLogo(), vbox, vbox2, vbox3, (Node) userLabel);

        this.setRoot(this.panel);

    }

    /**
     * @return game modality.
     */
    public static String getModalityPressed() {
        return BUTTONPRESSED.getText();
    }

    /**
     * @return background image for own category.
     */
    public static ImageView createBackgroundImage() {
        if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.CAPITALI.toString())) {
            final ImageView img;
            img = Background.getCategoryImage("/images/capitali.jpg");
            img.setOpacity(OPACITY);
            return img;
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.MONUMENTI.toString())) {
            final ImageView img;
            img = Background.getCategoryImage("/images/monumenti.jpg");
            img.setOpacity(OPACITY);
            return img;
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.VALUTE.toString())) {
            final ImageView img;
            img = Background.getCategoryImage("/images/valute.jpg");
            img.setOpacity(OPACITY);
            return img;
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.CUCINA.toString())) {
            final ImageView img;
            img = Background.getCategoryImage("/images/cucina.jpg");
            img.setOpacity(OPACITY);
            return img;
        } else if (CategoryScene.getCategoryPressed().equals(ButtonsCategory.BANDIERE.toString())) {
            final ImageView img;
            img = Background.getCategoryImage("/images/bandiere.jpg");
            img.setOpacity(OPACITY);
            return img;
        } else {
            return Background.getImage();
        }
    }

}
