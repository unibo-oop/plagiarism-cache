package view.dialogs;

import application.Controller;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.player.PlayerInfo;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.IconType;
import utilities.enumerations.RoomCard;
import utilities.enumerations.WeaponCard;
import view.scenes.MenuScene;

/**
 * Dialog to decide with which card you want to do the guess or the final
 * accuse.
 */
public final class GuessAccuseDialog extends Dialog {

    private static final GuessAccuseDialog SINGLETON = new GuessAccuseDialog();
    private static final int COMBO_W = 170;

    /**
     * Instance of GuessAccuseDialog.
     * 
     * @return the instance
     */
    public static GuessAccuseDialog getDialog() {
        return SINGLETON;
    }

    /**
     * Creation of the pane for the dialog.
     * 
     * @param b
     *            string to differentiate from guess or accuse.
     * @param p
     *            current player
     */
    public void createGuessAccuseDialog(final boolean b, final PlayerInfo p) {
        final Stage dialog = setStage();

        final BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(getPadding()));
        rootPane.setStyle("-fx-background-color: #" + IconType.WINNER.getBackgound() + ";");

        final Label title = new Label();
        final VBox head = new VBox();
        head.setAlignment(Pos.CENTER);
        if (b) {
            title.setText("Choose a character and a weapon for your guess");
            head.getChildren().add(title);
        } else {
            title.setText("Choose a character and a weapon for your final accusation");
            head.getChildren().add(title);
            final Label subtitle = new Label(
                    "Accuse only if you are sure to have the solution! If you're wrong, you lose the game.");
            head.getChildren().add(subtitle);
            subtitle.getStyleClass().add("subtitle");
        }
        rootPane.setTop(head);
        title.getStyleClass().add("title");
        BorderPane.setMargin(head, new Insets(0, 0, getPadding(), 0));

        final GridPane innerPane = new GridPane();

        final ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(getColW());
        final ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(getColW());
        final ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(getColW());
        innerPane.getColumnConstraints().addAll(c1, c2, c3);
        innerPane.setAlignment(Pos.CENTER);
        innerPane.setHgap(getPadding());
        innerPane.setVgap(getPadding());

        final ComboBox<CharacterCard> cmbCharacters = new ComboBox<>();
        final ComboBox<WeaponCard> cmbWeapons = new ComboBox<>();
        final ComboBox<RoomCard> cmbRooms = new ComboBox<>();
        CharacterCard.getCharacterCards().forEach(cmbCharacters.getItems()::add);
        WeaponCard.getWeaponCards().forEach(cmbWeapons.getItems()::add);
        RoomCard.getRoomCards().forEach(cmbRooms.getItems()::add);
        GridPane.setHalignment(cmbCharacters, HPos.CENTER);
        GridPane.setHalignment(cmbWeapons, HPos.CENTER);
        GridPane.setHalignment(cmbRooms, HPos.CENTER);
        cmbCharacters.setPrefWidth(COMBO_W);
        cmbWeapons.setPrefWidth(COMBO_W);
        cmbRooms.setPrefWidth(COMBO_W);
        cmbCharacters.setValue(CharacterCard.SCARLETT);
        cmbWeapons.setValue(WeaponCard.REVOLVER);
        cmbRooms.setValue(p.getRoom().get());
        cmbRooms.setDisable(true);

        final ImageView imgCharacter = new ImageView(
                new Image(MenuScene.class.getResourceAsStream(cmbCharacters.getValue().getImagePath())));
        imgCharacter.setPreserveRatio(true);
        imgCharacter.setFitHeight(getStageH() / 2);
        GridPane.setHalignment(imgCharacter, HPos.CENTER);
        final ImageView imgWeapon = new ImageView(
                new Image(MenuScene.class.getResourceAsStream(cmbWeapons.getValue().getImagePath())));
        imgWeapon.setPreserveRatio(true);
        imgWeapon.setFitHeight(getStageH() / 2);
        GridPane.setHalignment(imgWeapon, HPos.CENTER);
        final ImageView imgRoom = new ImageView(
                new Image(MenuScene.class.getResourceAsStream(cmbRooms.getValue().getImagePath())));
        imgRoom.setPreserveRatio(true);
        imgRoom.setFitHeight(getStageH() / 2);
        GridPane.setHalignment(imgRoom, HPos.CENTER);

        cmbCharacters.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends CharacterCard> arg0, CharacterCard arg1, CharacterCard arg2) -> {
                    imgCharacter.setImage(new Image(MenuScene.class.getResourceAsStream((arg2).getImagePath())));
                });
        cmbWeapons.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends WeaponCard> arg0, WeaponCard arg1, WeaponCard arg2) -> {
                    imgWeapon.setImage(new Image(MenuScene.class.getResourceAsStream((arg2).getImagePath())));
                });
        cmbRooms.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends RoomCard> arg0, RoomCard arg1, RoomCard arg2) -> {
                    imgRoom.setImage(new Image(MenuScene.class.getResourceAsStream((arg2).getImagePath())));
                });

        innerPane.add(cmbCharacters, 0, 0);
        innerPane.add(imgCharacter, 0, 1);
        innerPane.add(cmbWeapons, 1, 0);
        innerPane.add(imgWeapon, 1, 1);
        innerPane.add(cmbRooms, 2, 0);
        innerPane.add(imgRoom, 2, 1);

        final Button btnCancel = new Button("CANCEL");
        GridPane.setHalignment(btnCancel, HPos.CENTER);
        final Button btnOk;
        if (b) {
            btnOk = new Button("GUESS");
        } else {
            btnOk = new Button("ACCUSE");
        }
        GridPane.setHalignment(btnOk, HPos.CENTER);
        innerPane.add(btnCancel, 0, 2);
        innerPane.add(btnOk, 2, 2);

        rootPane.setCenter(innerPane);

        btnOk.setOnAction(e -> {
            dialog.close();

            final CharacterCard character = cmbCharacters.getValue();
            final WeaponCard weapon = cmbWeapons.getValue();
            final RoomCard room = cmbRooms.getValue();
            if (b) {
                Controller.getController().guess(room, weapon, character);
            } else {
                Controller.getController().accuse(room, weapon, character);
            }
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