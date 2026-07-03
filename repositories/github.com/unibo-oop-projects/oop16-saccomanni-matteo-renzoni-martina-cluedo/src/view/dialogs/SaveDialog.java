package view.dialogs;

import application.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utilities.enumerations.IconType;
import view.ViewImpl;

/**
 * Dialog to save the game.
 */
public final class SaveDialog extends Dialog {

    private static final SaveDialog SINGLETON = new SaveDialog();

    /**
     * Instance of SaveDialog.
     * 
     * @return the instance
     */
    public static SaveDialog getDialog() {
        return SINGLETON;
    }

    /**
     * Creation of the pane for the dialog.
     */
    public void createSaveDialog() {
        final Stage dialog = setStage();

        final BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setStyle("-fx-background-color: #" + IconType.WINNER.getBackgound() + ";");

        final Label title = new Label("What do you want to do?");
        title.getStyleClass().add("title");
        BorderPane.setAlignment(title, Pos.CENTER);
        rootPane.setTop(title);

        final HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(getPadding());
        buttonsPane.setPadding(new Insets(getPadding(), 0, 0, 0));
        final Button btnCancel = new Button("BACK TO GAME");
        final Button btnSave = new Button("SAVE AND QUIT");
        final Button btnExit = new Button("QUIT WITHOUT SAVE");
        buttonsPane.getChildren().add(btnCancel);
        buttonsPane.getChildren().add(btnSave);
        buttonsPane.getChildren().add(btnExit);
        rootPane.setCenter(buttonsPane);
        BorderPane.setAlignment(buttonsPane, Pos.CENTER);

        btnExit.setOnAction(e -> {
            dialog.close();
            Controller.getController().backToMenu();
        });

        btnSave.setOnAction(e -> {
            dialog.close();
            Controller.getController().save();
            Controller.getController().backToMenu();
            Notification.getNotification().drawNotification(ViewImpl.getPrimaryStage(), "Game saved!", IconType.INFO);
        });

        btnCancel.setOnAction(e -> {
            dialog.close();
        });

        final Scene scene = new Scene(rootPane);
        scene.getStylesheets().add("/style/style.css");
        dialog.setScene(scene);
        dialog.show();
    }
}