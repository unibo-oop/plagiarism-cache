package view.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilities.enumerations.IconType;

/**
 * Class to display informations during the game.
 */
public final class Notification extends Dialog {

    private static final Notification SINGLETON = new Notification();

    /**
     * Instance of GuessAccuseDialog.
     * 
     * @return the instance
     */
    public static Notification getNotification() {
        return SINGLETON;
    }

    /**
     * Draw notification of an information.
     * 
     * @param stage
     *            stage over the notification appear.
     * @param content
     *            notification background.
     * @param icon
     *            notification icon.
     */
    public void drawNotification(final Stage stage, final String content, final IconType icon) {
        final Stage dialog = setStage();

        final VBox rootPane = new VBox();
        rootPane.setMinWidth(getMinStageW());
        rootPane.setMaxWidth(getMaxStageW());
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setSpacing(getPadding());
        rootPane.setStyle("-fx-background-color: #" + icon.getBackgound() + ";");

        final HBox innerPane = new HBox();
        innerPane.setAlignment(Pos.CENTER_LEFT);
        innerPane.setSpacing(getPadding());

        final ImageView imageIcon = new ImageView(icon.getIconPath());
        imageIcon.setFitHeight(100);
        imageIcon.setPreserveRatio(true);
        final Label label = new Label(content);
        label.setWrapText(true);
        label.setMinHeight(100);
        innerPane.getChildren().add(imageIcon);
        innerPane.getChildren().add(label);

        rootPane.getChildren().add(innerPane);
        final Button btnOk = new Button("OK");
        rootPane.getChildren().add(btnOk);

        btnOk.setOnAction(e -> {
            dialog.close();
        });

        final Scene scene = new Scene(rootPane);
        scene.getStylesheets().add("/style/style.css");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}