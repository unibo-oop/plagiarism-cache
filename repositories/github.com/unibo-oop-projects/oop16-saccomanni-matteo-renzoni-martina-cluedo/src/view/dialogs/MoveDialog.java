package view.dialogs;

import application.Controller;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilities.enumerations.IconType;
import utilities.enumerations.RoomCard;
import view.scenes.MenuScene;

/**
 * Dialog to choose the room to reach.
 */
public final class MoveDialog extends Dialog {
    private static final MoveDialog SINGLETON = new MoveDialog();

    /**
     * Instance of MoveDialog.
     * 
     * @return the instance
     */
    public static MoveDialog getDialog() {
        return SINGLETON;
    }

    /**
     * Creation of the pane for the dialog.
     * 
     * @param dice
     *            dice result.
     */
    public void createChooseDialog(final int dice) {
        final Stage dialog = setStage();

        final BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setStyle("-fx-background-color: #" + IconType.WINNER.getBackgound() + ";");

        final Label title = new Label("Choose a room");
        title.getStyleClass().add("title");
        BorderPane.setAlignment(title, Pos.CENTER);
        rootPane.setTop(title);

        final VBox innerPane = new VBox();
        innerPane.setPadding(new Insets(getPadding()));
        innerPane.setAlignment(Pos.CENTER);
        innerPane.setSpacing(getPadding());

        final Label subtitle = new Label("The result of the dice roll is " + dice);
        subtitle.getStyleClass().add("subtitle");
        innerPane.getChildren().add(subtitle);

        final ComboBox<RoomCard> cmbRooms = new ComboBox<>();
        RoomCard.getRoomCards().forEach(cmbRooms.getItems()::add);
        cmbRooms.setValue(RoomCard.STUDIO);
        cmbRooms.setPrefWidth(getComboW());
        final ImageView imgRoom = new ImageView(
                new Image(MenuScene.class.getResourceAsStream((cmbRooms.getValue()).getImagePath())));
        imgRoom.setPreserveRatio(true);
        imgRoom.setFitHeight(getImgH());
        cmbRooms.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends RoomCard> arg0, RoomCard arg1, RoomCard arg2) -> {
                    imgRoom.setImage(new Image(MenuScene.class.getResourceAsStream(arg2.getImagePath())));
                });
        innerPane.getChildren().add(cmbRooms);
        innerPane.getChildren().add(imgRoom);

        final HBox buttonsPane = new HBox();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(getPadding());
        final Button btnCancel = new Button("STAY");
        buttonsPane.getChildren().add(btnCancel);
        final Button btnMove = new Button("MOVE");
        buttonsPane.getChildren().add(btnMove);
        innerPane.getChildren().add(buttonsPane);

        btnMove.setOnAction(e -> {
            dialog.close();
            final RoomCard r = cmbRooms.getValue();
            Controller.getController().chooseRoom(r);
        });

        btnCancel.setOnAction(e -> {
            dialog.close();
        });

        rootPane.setCenter(innerPane);
        final Scene scene = new Scene(rootPane);
        scene.getStylesheets().add("/style/style.css");
        dialog.setScene(scene);
        dialog.show();
        dialog.hide();
        dialog.setX(getScreenW() - dialog.getWidth());
        dialog.setY(getScreenH() - dialog.getHeight());
        dialog.show();
    }
}