package com.geoquiz.view.menu;

import com.geoquiz.view.utility.Background;
import com.geoquiz.view.utility.ScreenAdapter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Creation of game loading.
 */
public class SplashScreen extends StackPane {

    private static final double PI_WIDTH = 200;
    private static final double PI_HEIGHT = 200;
    private static final double PI_OPACITY = 0.8;
    private static final double LOADING_FONT = 50;

    private final Stage window = new Stage();
    private final Text label = new Text("Loading...");

    /**
     * 
     */
    public SplashScreen() {
        window.initStyle(StageStyle.UNDECORATED);
        final StackPane root = new StackPane();
        final Scene scene = new Scene(root);
        final VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);

        final ProgressIndicator pi = new ProgressIndicator(-1.0);
        pi.setMinWidth(PI_WIDTH);
        pi.setMinHeight(PI_HEIGHT);
        pi.setOpacity(PI_OPACITY);
        pi.setStyle(" -fx-progress-color: black;");

        label.setFont(Font.font(LOADING_FONT));

        box.getChildren().addAll(pi, label);

        root.getChildren().addAll(Background.getImage(), Background.createBackground(), box);

        window.setWidth(ScreenAdapter.getScreenWidth());
        window.setHeight(ScreenAdapter.getScreenHeight());

        window.setScene(scene);
    }

    /**
     * show the loading window.
     */
    public void showWindow() {
        window.show();
    }

    /**
     * hide the loading window.
     */
    public void hideWindow() {
        window.hide();
    }

}
