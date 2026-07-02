package view.menu;

import controller.base.BaseController;
import controller.menu.ChoiceMenuController;
import controller.menu.ChoiceMenuControllerImpl;

import java.util.Objects;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the implementation of {@link ChoiceMenuView} interface.
 */
public class ChoiceMenuViewImpl implements ChoiceMenuView {

    private static final double TRANSLATE_RATIO = 1.0 / 6.0;
    private final ChoiceMenuController menuController;
    private final Stage stage;
    private final Pane pane;
    private final TextField nameField;
    private final ComboBox<String> mapBox;
    private final ComboBox<String> characterBox;
    private final Button startButton;
    private final Button goBackButton;
    private final Label nameLabel;
    private final Label mapLabel;
    private final Label characterLabel;

    /**
     * ChoiceMenuView's constructor.
     * @param baseController
     *      The instance of {@link BaseController}.
     * @param stage
     *      The main stage.
     */
    public ChoiceMenuViewImpl(final BaseController baseController, final Stage stage) {
        this.menuController = new ChoiceMenuControllerImpl(baseController);
        this.stage = stage;
        this.pane = new Pane();
        this.stage.getScene().setRoot(this.pane);
        this.nameField = new TextField();
        this.mapBox = new ComboBox<>();
        this.characterBox = new ComboBox<>();
        this.startButton = new Button("START!");
        this.goBackButton = new Button("Back");
        this.nameLabel = new Label("Player name: ");
        this.mapLabel = new Label("Map: ");
        this.characterLabel = new Label("Character: ");
    }

    @Override
    public final void show() {
        this.setupElementsPosition();
        this.menuController.setMapBox(this.mapBox);
        this.menuController.setCharacterBox(this.characterBox);
        this.startButton.setOnMouseClicked(e -> {
            if (this.nameField.getText().isBlank() || this.nameField.getText().contains(" ")) {
                final Alert alert = new Alert(AlertType.ERROR, "Warning: your name cannot contain white spaces");
                final Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    alert.close();
                }
            } else if (Objects.isNull(this.mapBox.getValue())) {
                final Alert alert = new Alert(AlertType.ERROR, "Warning: you must choose a map");
                final Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    alert.close();
                }
            } else if (Objects.isNull(this.characterBox.getValue())) {
                final Alert alert = new Alert(AlertType.ERROR, "Warning: you must choose a character");
                final Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    alert.close();
                }
            } else {
                this.menuController.startHit(this.mapBox.getValue(), this.nameField.getText(), this.characterBox.getValue());
            }
        });
        this.goBackButton.setOnMouseClicked(e -> this.menuController.goBackHit());
        this.pane.getChildren().addAll(
                this.nameField, this.mapBox, this.characterBox,
                this.nameLabel, this.mapLabel, this.characterLabel,
                this.startButton, this.goBackButton
        );
    }
    // Puts the elements in a fixed position.
    private void setupElementsPosition() {
        this.nameField.setTranslateX(this.stage.getWidth() / 2);
        this.nameField.setTranslateY(this.stage.getHeight() * TRANSLATE_RATIO);
        this.mapBox.setTranslateX(this.stage.getWidth() / 2);
        this.mapBox.setTranslateY(this.stage.getHeight() * 2 * TRANSLATE_RATIO);
        this.characterBox.setTranslateX(this.stage.getWidth() / 2);
        this.characterBox.setTranslateY(this.stage.getHeight() * 3 * TRANSLATE_RATIO);
        this.startButton.setTranslateX(this.stage.getWidth() / 2);
        this.startButton.setTranslateY(this.stage.getHeight() * 4 * TRANSLATE_RATIO);
        this.nameLabel.setTranslateX(this.stage.getWidth() / 3);
        this.nameLabel.setTranslateY(this.nameField.getTranslateY());
        this.mapLabel.setTranslateX(this.stage.getWidth() / 3);
        this.mapLabel.setTranslateY(this.mapBox.getTranslateY());
        this.characterLabel.setTranslateX(this.stage.getWidth() / 3);
        this.characterLabel.setTranslateY(this.characterBox.getTranslateY());
    }
}
