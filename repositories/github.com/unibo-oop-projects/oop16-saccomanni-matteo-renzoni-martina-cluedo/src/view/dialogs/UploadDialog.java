package view.dialogs;

import java.util.List;
import application.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilities.enumerations.IconType;

/**
 * Dialog to upload a game.
 */
public final class UploadDialog extends Dialog {

    private static final UploadDialog SINGLETON = new UploadDialog();

    private static final int COMBO_W = 270;

    /**
     * Instance of UploadDialog.
     * 
     * @return the instance
     */
    public static UploadDialog getDialog() {
        return SINGLETON;
    }

    /**
     * Creation of the pane for the dialog.
     * 
     * @param savedGames
     *            list of saved game.
     */
    public void createUploadDialog(final List<String> savedGames) {
        final Stage dialog = setStage();

        final VBox rootPane = new VBox();
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setSpacing(getPadding());
        rootPane.setStyle("-fx-background-color: #" + IconType.WINNER.getBackgound() + ";");

        final Label title = new Label("Which game do you want to resume?");
        title.getStyleClass().add("title");
        BorderPane.setAlignment(title, Pos.CENTER);
        rootPane.getChildren().add(title);

        final ComboBox<String> comboBox = new ComboBox<String>();
        savedGames.forEach(comboBox.getItems()::add);
        comboBox.setValue(savedGames.get(0));
        comboBox.setPrefWidth(COMBO_W);
        rootPane.getChildren().add(comboBox);

        final HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(getPadding());
        final Button btnCancel = new Button("CANCEL");
        buttonsPane.getChildren().add(btnCancel);
        final Button btnOk = new Button("RESUME");
        buttonsPane.getChildren().add(btnOk);
        rootPane.getChildren().add(buttonsPane);

        btnOk.setOnAction(e -> {
            dialog.close();
            Controller.getController().resumeSelectedGame(comboBox.getValue());
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