package view.scenecontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.players.Player;
import utilities.Colors;
import view.alert.AlertWindow;
import view.alert.AlertWindowImpl;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino 
 * Controller for player menu scene.
 *
 */

public class PlayersMenuController extends SceneControllerImpl {
    private AlertWindow alert;
    private final List<Colors> availableColors = new ArrayList<>();
    private final List<Colors> colorsSelected = new ArrayList<>();
    private final List<ComboBox<String>> colorComboBoxList = new ArrayList<>();
    private final ObservableMap<ComboBox<String>, TextField> mapColorAndPlayer = FXCollections.observableHashMap();
    private final List<Image> mazesImages = new ArrayList<>();
    private int selectedMaze;

    @FXML
    private ComboBox<String> colorFirstPlayer;

    @FXML
    private ComboBox<String> colorSecondPlayer;

    @FXML
    private ComboBox<String> colorThirdPlayer;

    @FXML
    private ComboBox<String> colorFourthPlayer;

    @FXML
    private ImageView firstMaze;

    @FXML
    private ImageView secondMaze;

    @FXML
    private ImageView thirdMaze;

    @FXML
    private TextField firstPlayer;

    @FXML
    private TextField secondPlayer;

    @FXML
    private TextField thirdPlayer;

    @FXML
    private TextField fourthPlayer;

    @FXML
    private Button nextMaze;

    private void addPlayer(final ComboBox<String> cb) {
        if ((this.mapColorAndPlayer.get(cb).getText().length() > 0) && (cb.getValue() != null)) {
            this.getController()
                    .addPlayer(new Player(this.mapColorAndPlayer.get(cb).getText(), Colors.valueOf(cb.getValue())));
        }
    }

    @FXML
    private boolean areAllElementsInserted() {
        return ((this.mapColorAndPlayer.keySet().stream()
                .filter(cb -> ((cb.getValue() != null) && (this.mapColorAndPlayer.get(cb).getText().length() > 0)))
                .collect(Collectors.toList()).size() >= 2));
    }

    private void checkColorsAlreadySelected(final ComboBox<String> cbSelected) {
        this.mapColorAndPlayer.keySet().forEach(cb -> {
            if ((cb != cbSelected) && (cb.getValue() != null)) {
                this.colorsSelected.add(Colors.valueOf(cb.getValue()));
            }
        });
    }

    private ComboBox<String> getComboBoxArmed() {
        if (this.colorFirstPlayer.isHover()) {
            return this.colorFirstPlayer;
        } else if (this.colorSecondPlayer.isHover()) {
            return this.colorSecondPlayer;
        } else if (this.colorThirdPlayer.isHover()) {
            return this.colorThirdPlayer;
        } else {
            return this.colorFourthPlayer;
        }
    }

    @FXML
    private void initialize() {
        this.mapColorAndPlayer.put(this.colorFirstPlayer, this.firstPlayer);
        this.mapColorAndPlayer.put(this.colorSecondPlayer, this.secondPlayer);
        this.mapColorAndPlayer.put(this.colorThirdPlayer, this.thirdPlayer);
        this.mapColorAndPlayer.put(this.colorFourthPlayer, this.fourthPlayer);

        this.colorComboBoxList.addAll(Arrays.asList(this.colorFirstPlayer, this.colorSecondPlayer,
                this.colorThirdPlayer, this.colorFourthPlayer));

        this.availableColors.addAll(Arrays.asList(Colors.Red, Colors.Blue, Colors.Yellow, Colors.White));

        this.mazesImages.addAll(Arrays.asList(new Image("/menuImages/maze1.jpg"), new Image("/menuImages/maze2.jpg"),
                new Image("/menuImages/maze3.jpg"), new Image("/menuImages/maze4.jpg")));

        this.selectedMaze = this.getController().getSelectedMaze() - 1;

        this.firstMaze.setImage(this.mazesImages.get(0));
        this.secondMaze.setImage(this.mazesImages.get(this.selectedMaze));
        this.thirdMaze.setImage(this.mazesImages.get(this.selectedMaze + 1));
    }

    @FXML
    private void openGameMenu() throws IOException {
        if (this.areAllElementsInserted()) {
            this.updatePlayersModel();
            this.updateMaze();
            this.getSceneLoader().getStage().close();
            this.getController().openBoardView();
        } else {
            this.alert = new AlertWindowImpl("Elementi mancanti!", "Inserire almeno due giocatori (nome e colore)",
                    "ok");
            this.alert.showAndWait();
        }
    }

    @FXML
    private void returnModalityMenu() throws IOException {
        this.alert = new AlertWindowImpl("Back To Modality Menù",
                "Sei sicuro di voler tornare indietro?\nLe modifiche non salvate andranno perse!", "yesNo");
        if (this.alert.showAndWait().get().getButtonData() == ButtonData.YES) {
            this.getSceneLoader().load(Scenes.MODALITYMENU);
        }
    }

    private void setSelectionColorAndStyle(final ComboBox<String> cb) {
        cb.getStylesheets().add("/style/stylesheet.css");
        cb.buttonCellProperty().bind(Bindings.createObjectBinding(() -> {
            final String name = cb.getValue();
            Colors color = Colors.None;
            if (name != null) {
                color = Colors.valueOf(cb.getValue());
            }

            final Color finalColor = color.getFxPath();
            // Get the arrow button of the combo-box
            final StackPane arrowButton = (StackPane) cb.lookup(".arrow-button");

            return new ListCell<String>() {

                @Override
                protected void updateItem(final String item, final boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || (item == null)) {
                        this.setBackground(Background.EMPTY);
                        this.setText("");

                    } else {
                        this.setBackground(
                                new Background(new BackgroundFill(finalColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        this.setText(item);

                    }
                    // Set the background of the arrow also
                    if (arrowButton != null) {
                        arrowButton.setBackground(this.getBackground());
                        if (empty || (item == null)) {
                            this.setBackground(new Background(
                                    new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    }
                }
            };
        }, cb.valueProperty()));
    }

    @FXML
    private void setValuesCombobox() {
        final ComboBox<String> cbSelected = this.getComboBoxArmed();
        this.checkColorsAlreadySelected(cbSelected);
        final List<Colors> tmpList = this.availableColors.stream().filter(s -> !this.colorsSelected.contains(s))
                .collect(Collectors.toList());

        if (tmpList.isEmpty()) {
            /*
             * Particular case: When all colors are assigned and we click again on a
             * Combobox to select a color, the Combobox shows the only color that we can
             * select is the color we had already selected and not a empty list.
             */
            final ObservableList<String> colorsToSet1 = FXCollections.observableArrayList();
            colorsToSet1.add(cbSelected.getValue());
            cbSelected.setItems(colorsToSet1);
        } else {
            final ObservableList<String> colorsToSet = FXCollections.observableArrayList();
            tmpList.forEach(Color -> colorsToSet.add(Color.toString()));
            cbSelected.setItems(colorsToSet);
        }
        this.colorsSelected.clear();
        this.setSelectionColorAndStyle(cbSelected);
    }

    private void switchImages(final ImageView maze1, final ImageView maze2) {
        maze1.setVisible(true);
        maze1.setImage(this.mazesImages.get(this.selectedMaze));
        this.secondMaze.setImage(
                this.mazesImages.get(maze1.equals(this.firstMaze) ? this.selectedMaze + 1 : this.selectedMaze - 1));
        if (this.secondMaze.getImage()
                .equals(this.mazesImages.get(maze1.equals(this.firstMaze) ? this.mazesImages.size() - 1 : 0))) {
            maze2.setVisible(false);
        } else {
            maze2.setVisible(true);
            maze2.setImage(
                    this.mazesImages.get(maze1.equals(this.firstMaze) ? this.selectedMaze + 2 : this.selectedMaze - 2));
        }
        if (maze1.equals(this.firstMaze)) {
            this.selectedMaze++;
        } else {
            this.selectedMaze--;
        }
    }

    private void updateMaze() {
        this.getController().setMaze(this.selectedMaze + 1);
    }

    private void updatePlayersModel() {
        this.colorComboBoxList.forEach(cb -> this.addPlayer(cb));
    }

    @FXML
    private void updateViewMaze() {
        if (this.nextMaze.isArmed()) {
            if (this.selectedMaze != (this.mazesImages.size() - 1)) {
                this.switchImages(this.firstMaze, this.thirdMaze);
            }
        } else {
            if (this.selectedMaze != 0) {
                this.switchImages(this.thirdMaze, this.firstMaze);
            }
        }

    }

}
