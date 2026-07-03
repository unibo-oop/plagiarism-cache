package com.jlearn.view.factories;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.screens.ExerciseScreenController;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.ExerciseType;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.SoundFX;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * A simil factory class for the creation of gui components ({@link JFXListView}).
 */
public final class ListViewFactory {
    private static final double SMALL_SIZE_MARGIN = 200;

    private ListViewFactory() {
    }

    /**
     * Create the {@link JFXDrawer} and the {@link JFXListView}.
     *
     * @param drawer
     *            the {@link JFXDrawer}
     * @param levels
     *            the number of levels
     * @param listView
     *            the {@link JFXListView}
     */
    public static void buildDrawerListView(final JFXDrawer drawer, final JFXListView<Label> listView,
            final int levels) {
        final BorderPane borderDrawer = new BorderPane(listView);
        drawer.setSidePane(borderDrawer);

        for (int i = 0; i < levels; i++) {
            final int k = i + 1;
            final Label label = new Label(k + ") " + FileManagerImpl.getAllUnitName().get(i));
            listView.getItems().add(label);
        }
        listView.setId("list-view");
    }

    /**
     * Create a {@link JFXListView} for the true false panel.
     *
     * @param listView
     *            the {@link JFXListView}
     * @param elemNumber
     *            the elem number
     * @param exerciseType
     *            the {@link ExerciseType}
     */
    public static void addElemToListView(final JFXListView<BorderPane> listView, final int elemNumber,
            final ExerciseType exerciseType) {
        final int base = listView.getItems().size();
        for (int i = 0; i < elemNumber; i++) {
            final int k = i + 1;
            final BorderPane pane = new BorderPane();
            final Label labelNumber = new Label(k + base + ")  ");
            final Label label = new Label("Select Exercise");
            switch (exerciseType) {
            case TRUE_FALSE:
                final JFXRadioButton radio = new JFXRadioButton("F");
                radio.setId("true-false-RadioButton");
                pane.setRight(radio);
                break;

            case MULTI:
                final JFXComboBox<String> combo = new JFXComboBox<>();
                combo.setId("exercise-combo-box");
                combo.setPromptText("Choose Value");
                pane.setRight(combo);
                break;
            case COMPLETE:
                final JFXTextField text = new JFXTextField();
                text.setId("exercise-text-field");
                text.setPromptText("Insert");
                pane.setRight(text);
                break;
            case AUDIO:
                final JFXRadioButton radioAudio = new JFXRadioButton("F");
                radioAudio.setId("true-false-RadioButton");
                pane.setRight(radioAudio);
                break;
            default:
            }

            label.setId("exercise-text-field"); // NOPMD
            labelNumber.setId("exercise-number-label");
            pane.setCenter(label);
            pane.setLeft(labelNumber);
            label.maxWidthProperty()
                    .bind(FXEnvironment.getInstance().getMainStage().widthProperty().subtract(SMALL_SIZE_MARGIN));
            label.textProperty().addListener((observable, oldValue, newValue) -> {
                label.setTooltip(new Tooltip(newValue));
            });
            BorderPane.setAlignment(labelNumber, Pos.CENTER_LEFT);
            BorderPane.setAlignment(label, Pos.CENTER_LEFT);
            listView.getItems().add(pane);
        }
    }

    /**
     * Set the handlers on the {@link JFXListView}.
     *
     * @param listView
     *            the {@link JFXListView}
     * @param exerciseType
     *            the {@link ExerciseType}
     */
    @SuppressWarnings("unchecked")
    public static void lsitViewBehaviour(final JFXListView<BorderPane> listView, final ExerciseType exerciseType) {
        switch (exerciseType) {
        case TRUE_FALSE:
            listView.getItems().stream().map(t -> (JFXRadioButton) t.getRight()).forEach(t -> t.setOnAction(k -> {
                if (t.getText().equals("F")) {
                    t.setText("T");
                } else {
                    t.setText("F");
                }
                ExerciseScreenController.getInstance().playAudio(SoundFX.SELECTION1);
            }));
            break;
        case MULTI:
            listView.getItems()
                    .stream()
                    .map(t -> (JFXComboBox<String>) t.getRight())
                    .forEach(k -> {
                        k.setOnMouseClicked(e -> ExerciseScreenController.getInstance().playAudio(SoundFX.SELECTION1));
                    });
            break;
        case COMPLETE:
            listView.getItems()
                    .stream()
                    .map(t -> (JFXTextField) t.getRight())
                    .forEach(k -> {
                        k.setOnMouseClicked(e -> ExerciseScreenController.getInstance().playAudio(SoundFX.SELECTION1));
                    });
            break;
        case AUDIO:
            listView.getItems().stream().map(t -> (JFXRadioButton) t.getRight()).forEach(t -> t.setOnAction(k -> {
                if (t.getText().equals("F")) {
                    t.setText("T");
                } else {
                    t.setText("F");
                }
                ExerciseScreenController.getInstance().playAudio(SoundFX.SELECTION1);
            }));
            break;
        default:
            break;
        }

    }

    /**
     * Create a {@link JFXListView} for the Players.
     *
     * @param listView
     *            the {@link JFXListView}
     * @param imageProfile
     *            the {@link Image} profile
     * @param nickname
     *            the {@link String} nickname
     */
    // CHECKSTYLE:OFF AH DI MI TOCCA FARE COSI
    public static void addPlayersToListView(final JFXListView<BorderPane> listView, final Image imageProfile,
            final String nickname) {
        final BorderPane pane = new BorderPane();
        final Label label = new Label(nickname);
        final ImageView imageView = new ImageView(imageProfile);
        final JFXButton deletePlayer = new JFXButton();
        deletePlayer.setGraphic(ViewUtilities.iconSetter(Material.DELETE, IconDim.MEDIUM));
        JFXDepthManager.setDepth(deletePlayer, 2);
        JFXDepthManager.setDepth(pane, 1);
        label.setId("player-listView-label");
        pane.setId("player-listView-border");
        imageView.setFitHeight(70);
        imageView.setFitWidth(90);
        BorderPane.setAlignment(imageView, Pos.CENTER_LEFT);
        BorderPane.setAlignment(label, Pos.CENTER_LEFT);
        BorderPane.setAlignment(deletePlayer, Pos.CENTER_RIGHT);
        pane.setRight(deletePlayer);
        pane.setLeft(imageView);
        pane.setCenter(label);
        listView.getItems().add(pane);
    }
    // CHECKSTYLE:ON

}
