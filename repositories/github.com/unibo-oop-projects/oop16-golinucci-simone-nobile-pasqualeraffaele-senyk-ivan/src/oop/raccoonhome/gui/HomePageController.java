package oop.raccoonhome.gui;

import java.net.URL;
import java.util.ResourceBundle;

import io.datafx.controller.FXMLController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *  A class that control Welcome view.
 *
 */
@FXMLController(value = "/resources/fxml/HomeScreen.fxml", title = "Set HomeScreen Controller")

public class HomePageController implements Initializable {
    private static final int DURATE = 3000;
    @FXML
    private ImageView logo;

    @FXML
    private Label welcome;

    @FXML
    private Label down;

    /**
     * Init The animation of Welcome Screen.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        FadeTransition fadeout = new FadeTransition(Duration.millis(DURATE), welcome);
        fadeout.setFromValue(0);
        fadeout.setToValue(1);
        fadeout.setCycleCount(1);
        fadeout.play();
        FadeTransition fadeBenvenuto = new FadeTransition(Duration.millis(DURATE), logo);
        fadeBenvenuto.setFromValue(0);
        fadeBenvenuto.setToValue(1);
        fadeBenvenuto.setCycleCount(1);
        fadeBenvenuto.play();
        FadeTransition fade = new FadeTransition(Duration.millis(DURATE), down);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);
        fade.play();

    }


}
