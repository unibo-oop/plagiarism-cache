package view.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utilities.enumerations.IconType;

/**
 * Dialog to close the game.
 */
public final class CloseDialog extends Dialog {

    private static final CloseDialog SINGLETON = new CloseDialog();

    /**
     * Instance of CloseDialog.
     * 
     * @return the instance
     */
    public static CloseDialog getDialog() {
        return SINGLETON;
    }

    /**
     * Creation of the pane for the dialog.
     */
    public void createCloseDialog() {
        final Stage dialog = setStage();

        final BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setStyle("-fx-background-color: #" + IconType.WINNER.getBackgound() + ";");

        final Label title = new Label("Do you really want to exit?");
        title.getStyleClass().add("title");
        BorderPane.setAlignment(title, Pos.CENTER);
        rootPane.setTop(title);

        final HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(getPadding());
        buttonsPane.setPadding(new Insets(getPadding(), 0, 0, 0));
        final Button btnYes = new Button("YES");
        buttonsPane.getChildren().add(btnYes);
        final Button btnNo = new Button("NO");
        buttonsPane.getChildren().add(btnNo);
        rootPane.setCenter(buttonsPane);

        btnYes.setOnAction(e -> {
            dialog.close();
            System.exit(0);
        });

        btnNo.setOnAction(e -> {
            dialog.close();
        });

        final Scene scene = new Scene(rootPane);
        scene.getStylesheets().add("/style/style.css");
        dialog.setScene(scene);
        dialog.show();
    }
}