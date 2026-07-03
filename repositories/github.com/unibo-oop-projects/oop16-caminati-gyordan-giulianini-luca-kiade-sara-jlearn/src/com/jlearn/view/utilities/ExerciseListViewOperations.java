package com.jlearn.view.utilities;

import java.util.List;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jlearn.view.utilities.enums.ExerciseType;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

/**
 *
 * Class for {@link JFXListView} behviour.
 *
 * src/com/jlearn/view/factories/ListViewBehaviour.java
 */
public final class ExerciseListViewOperations {
    private ExerciseListViewOperations() {
    }

    /**
     * Takes a {@link List} and fills the {@link JFXListView} consuming it.
     *
     * @param listView
     *            the {@link JFXListView}
     * @param list
     *            the {@link List}
     * @exception IllegalStateException
     *                the {@link IllegalStateException}
     */
    public static void fillQuestionListView(final JFXListView<BorderPane> listView, final List<String> list)
            throws IllegalStateException {
        listView.getItems()
                .stream()
                .map(t -> (Label) t.getCenter())
                .forEach(k -> {
                    k.setText(list.remove(0));
                });
    }

    /**
     * Takes a {@link List} and fills the {@link JFXComboBox} consuming it.
     *
     * @param listView
     *            the {@link JFXListView}
     * @param list
     *            the {@link List}
     * @exception IllegalStateException
     *                the {@link IllegalStateException}
     */
    @SuppressWarnings("unchecked")
    public static void fillComboBoxListView(final JFXListView<BorderPane> listView, final List<List<String>> list)
            throws IllegalStateException {
        listView.getItems()
                .stream()
                .map(t -> (JFXComboBox<String>) t.getRight())
                .forEach(k -> {
                    k.getItems().setAll(list.remove(0));
                });
    }

    /**
     * Read the {@link JFXListView}.
     *
     * @param exercise
     *            the {@link ExerciseType}
     * @param listView
     *            the {@link JFXListView}
     * @return the {@link List}
     *
     */
    @SuppressWarnings("unchecked")
    public static List<?> readListView(final JFXListView<BorderPane> listView, final ExerciseType exercise) {
        switch (exercise) {
        case TRUE_FALSE:
            return listView.getItems()
                    .stream()
                    .map(t -> (JFXRadioButton) t.getRight())
                    .map(k -> k.isSelected())
                    .collect(Collectors.toList());
        case MULTI:
            return listView.getItems()
                    .stream()
                    .map(t -> (JFXComboBox<String>) t.getRight())
                    .map(k -> k.getSelectionModel().getSelectedItem())
                    .collect(Collectors.toList());
        case COMPLETE:
            return listView.getItems()
                    .stream()
                    .map(t -> (JFXTextField) t.getRight())
                    .map(k -> k.getText())
                    .collect(Collectors.toList());
        case AUDIO:
            return listView.getItems()
                    .stream()
                    .map(t -> (JFXRadioButton) t.getRight())
                    .map(k -> k.isSelected())
                    .collect(Collectors.toList());
        default:
            return null;
        }
    }

    /**
     * Reset the {@link ListView} true false.
     *
     * @param listView
     *            the {@link JFXListView}
     * @param exercise
     *            the {@link ExerciseType}
     */
    @SuppressWarnings("unchecked")
    public static void resetModule(final JFXListView<BorderPane> listView, final ExerciseType exercise) {
        switch (exercise) {
        case TRUE_FALSE:
            listView.getItems()
                    .stream()
                    .map(t -> (JFXRadioButton) t.getRight())
                    .forEach(k -> {
                        k.setSelected(false);
                        k.setText("F");
                    });
            break;
        case MULTI:
            listView.getItems()
                    .stream()
                    .map(t -> (JFXComboBox<String>) t.getRight())
                    .forEach(k -> {
                        k.getSelectionModel().clearSelection();
                        k.getItems().clear();
                    });
            break;
        case COMPLETE:
            listView.getItems()
                    .stream()
                    .map(t -> (JFXTextField) t.getRight())
                    .forEach(k -> k.setText(""));
            resetLabels(listView);
            break;
        case AUDIO:
            listView.getItems()
                    .stream()
                    .map(t -> (JFXRadioButton) t.getRight())
                    .forEach(k -> {
                        k.setSelected(false);
                        k.setText("F");
                    });
            break;
        default:
            break;
        }
        resetLabels(listView);
    }

    /**
     * Reset the {@link ListView} true false.
     *
     * @param listView
     *            the {@link JFXListView}
     */
    public static void resetLabels(final JFXListView<BorderPane> listView) {
        listView.getItems()
                .stream()
                .map(t -> (Label) t.getCenter())
                .forEach(k -> k.setText("Select Level"));
    }

}
