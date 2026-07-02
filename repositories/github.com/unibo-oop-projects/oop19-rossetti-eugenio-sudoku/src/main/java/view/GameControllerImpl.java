package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.base.Optional;
import java.util.List;
import controller.SudokuGameHandler;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import utilities.Scenes;

/**
 * 
 * Implementation of GameController.
 *
 */

public class GameControllerImpl implements GameController {

    private static final String EMPTY_CELL = "";
    private static final String EMPTY = "0";
    private static final String INITIAL = "initial";
    private static final String NOT_INITIAL = "notInitial";
    private static final int FIRST_LINE = 2;
    private static final int SECOND_LINE = 5;
    private final PseudoClass right = PseudoClass.getPseudoClass("right");
    private final PseudoClass bottom = PseudoClass.getPseudoClass("bottom");
    private final PseudoClass error = PseudoClass.getPseudoClass("error");
    private final PseudoClass highlight = PseudoClass.getPseudoClass("highlight");
    private final PseudoClass samevalue = PseudoClass.getPseudoClass("samevalue");
    private Button delete;
    private TextField selectedTextField; 
    private List<TextField> textFieldList;
    private Map<TextField, Pair<Integer, Integer>> positionGrid;
    private View view;
    private SudokuGameHandler handler;

    @FXML
    private GridPane sudokuGrid;

    @FXML
    private HBox buttonsBox; 

    @FXML
    public final void exitBtn() throws IOException {
        final Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to quit? All progress will be saved.", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            this.handler.saveGame();
        }
    }

    @FXML
    public final void initialize() {
        this.delete = new Button();
        this.delete.setId("deleteBtn");
        this.buttonsBox.getChildren().add(delete);
    }

    /**
     * Build the initial grid and buttons.
     */
    private void buildGrid() {
        this.textFieldList = new ArrayList<>();
        this.positionGrid = new HashMap<>();
        this.setDeleteButton();
        this.setTextFields();
    }

    /**
     * Check if a string is empty.
     * @param s
     * @return string value 
     */
    private String checkStringValue(final String s) {
        if (s.equals(EMPTY)) {
            return EMPTY_CELL;
        }
        return s;
    }

    @Override
    public final void viewUpdate(final List<Pair<Optional<Integer>, Boolean>> list) {
            for (int i = 0; i < list.size(); i++) {
                this.textFieldList.get(i).setText(checkStringValue(String.valueOf(list.get(i).getKey().or(0))));
                if (this.handler.getSettings().assistsOn()) {
                    this.showErrors(textFieldList.get(i), list.get(i).getValue());
                }
            }
            if (this.handler.getLogic().isDone()) {
                final Alert alert = new Alert(AlertType.CONFIRMATION, "You WON!", ButtonType.OK);
                alert.showAndWait();
                this.view.setScene(Scenes.HOME);
            }
    }

    /**
     * Show errors.
     * @param field of the error
     * @param value of the cell
     */
    private void showErrors(final TextField field, final boolean value) {
        field.pseudoClassStateChanged(error, !value);
    }

    /**
     * Highlights same digits of current selected cell.
     */
    private void highlightSameDigits() {
        if (this.selectedTextField != null) {
            for (final TextField field: textFieldList) {
                if (!this.selectedTextField.getText().isBlank() && field.getText().equals(this.selectedTextField.getText())) {
                    field.pseudoClassStateChanged(samevalue, true);
                    } else {
                        field.pseudoClassStateChanged(samevalue, false);
                }
            }
        }
    }



    /**
     * Initialize the textfields grid.
     */
    private void setTextFields() {
        final String[] initialgrid = this.handler.getLogic().getInitialGrid();
        int i = 0;
        for (int r = 0; r <  this.handler.getLogic().getSize(); r++) {
            for (int c = 0; c < this.handler.getLogic().getSize(); c++) {
                final TextField field = new TextField();
                field.setText(this.checkStringValue(initialgrid[i]));
                i++;
                field.setEditable(false); 
                field.setCursor(Cursor.DEFAULT);
                field.setId(field.getText().isBlank() ? NOT_INITIAL : INITIAL);
                field.pseudoClassStateChanged(error, false);
                field.pseudoClassStateChanged(highlight, false);
                field.pseudoClassStateChanged(samevalue, false);
                field.pseudoClassStateChanged(right, c > FIRST_LINE && c <= SECOND_LINE && (r <= FIRST_LINE || r > SECOND_LINE));
                field.pseudoClassStateChanged(bottom, r > FIRST_LINE && r <= SECOND_LINE && (c <= FIRST_LINE || c > SECOND_LINE));
                field.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(final Event e) {
                        selectedTextField = (TextField) e.getSource();
                        if (handler.getSettings().assistsOn()) {
                            if (selectedTextField.getId().equals("notInitial")) {
                                allHighlights(selectedTextField);
                            } else {
                                deselectPseudoClass(highlight);
                            }
                            highlightSameDigits();
                        }
                    }
                });
                this.textFieldList.add(field);
                this.positionGrid.put(field, new Pair<Integer, Integer>(r, c));
                this.sudokuGrid.add(field, c, r);
            }
            this.setInputButtons(r);
        }
    }

    /**
     * Initialize input buttons.
     * @param value for the button
     */
    private void setInputButtons(final int value) {
        final Button numBtn = new Button(String.valueOf(value + 1));
        this.buttonsBox.getChildren().add(numBtn);
        numBtn.setOnAction(e -> {
            if (this.selectedTextField != null && this.selectedTextField.getText().isBlank()) {
                final Button bt = (Button) e.getSource();
                if (!bt.getText().equals(this.selectedTextField.getText())) {
                    final Pair<Integer, Integer> pair = this.positionGrid.get(this.selectedTextField);
                    this.handler.hitCell(pair.getKey(), pair.getValue(), Integer.parseInt(bt.getText().isBlank() ? EMPTY : bt.getText()));
                    this.selectedTextField = null;
                    this.deselectPseudoClass(highlight);
                }
            }
        });
    }

    /**
     * Initialize deleteBtn.
     */
    private void setDeleteButton() {
        this.delete.setOnAction(e -> {
            if (selectedTextField != null) {
                final Pair<Integer, Integer> pair = this.positionGrid.get(selectedTextField);
                this.handler.removeCell(pair.getKey(), pair.getValue());
                this.selectedTextField = null;
                this.deselectPseudoClass(highlight);
                this.deselectPseudoClass(samevalue);
            }
        });
    }

    /**
     * @param name of pseudoclass to deselect
     */
    private void deselectPseudoClass(final PseudoClass name) {
        this.textFieldList.forEach(elem -> elem.pseudoClassStateChanged(name, false));
    }
    /**
     * All possible highlights for a textfield.
     * @param text selected
     */
    private void allHighlights(final TextField text) {
        this.deselectPseudoClass(highlight);
        final int row = this.positionGrid.get(text).getKey();
        final int col = this.positionGrid.get(text).getValue();
        this.highlightSameRow(row);
        this.highlightSameColumn(col);
        this.highlightSameSquare(row, col);
    }

    /**
     * Highlights row of a cell.
     * @param row of the cell
     */
    private void highlightSameRow(final int row) {
        this.positionGrid.entrySet()
                         .stream()
                         .filter(e -> e.getValue().getKey().equals(row))
                         .map(e -> e.getKey())
                         .collect(Collectors.toList())
                         .forEach(elem -> elem.pseudoClassStateChanged(highlight, true));
    }

    /**
     * Highlights column of a cell.
     * @param col of the cell
     */
    private void highlightSameColumn(final int col) {
        this.positionGrid.entrySet()
                         .stream()
                         .filter(e -> e.getValue().getValue().equals(col))
                         .map(e -> e.getKey())
                         .collect(Collectors.toList()) 
                         .forEach(elem -> elem.pseudoClassStateChanged(highlight, true));
    }

    /**
     * Highlights subgrid of a cell.
     * @param row of the cell
     * @param col of the cell
     */
    private void highlightSameSquare(final int row, final int col) {
        final int subRow = row - row % this.handler.getLogic().getSquareSize();
        final int subCol = col - col % this.handler.getLogic().getSquareSize();
        this.positionGrid.entrySet()
                         .stream()
                         .filter(r -> r.getValue().getKey() >= subRow && r.getValue().getKey() < (subRow + this.handler.getLogic().getSquareSize()))
                         .filter(c -> c.getValue().getValue() >= subCol && c.getValue().getValue() < (subCol + this.handler.getLogic().getSquareSize()))
                         .map(e -> e.getKey())
                         .collect(Collectors.toList())
                         .forEach(elem -> elem.pseudoClassStateChanged(highlight, true));
    }

    @Override
    public final void setSudokuGameHandler(final SudokuGameHandler handler) {
        this.handler = handler;
        this.buildGrid();
    }

    @Override
    public final void setView(final View view) {
        this.view = view;
    }
}
