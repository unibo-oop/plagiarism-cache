package view.playersetup;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * 
 * Class container for all controls that permit a user to choose player properties. It contains:
 * <ul>
 * <li>A {@link #nameField}.
 * <li>A {@link #colorPicker}.
 * <li>A {@link #isAICheckBox}.
 * <li>And {@link #addBtn}.
 * </ul>
 * <p>
 * Extends HBox. 
 * 
 */
public class HeaderPanel extends HBox {

    private static final double INSETS = 7;
    private static final double SPACING = 20.0;

    private final JFXSnackbar snackbar = new JFXSnackbar();

    private final JFXTextField nameField = new JFXTextField("Insert player name");
    private final ColorPicker colorPicker = new ColorPicker();
    private final JFXCheckBox isAICheckBox = new JFXCheckBox("CPU Controlled");
    private final JFXButton addBtn = new JFXButton("Add");

    /**
     * 
     * Class constructor.
     * 
     * @param mainPane
     *                  Container of this node.
     * 
     */
    public HeaderPanel(final BorderPane mainPane) {
        snackbar.registerSnackbarContainer(mainPane);

        /* Text Field */
        final RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Name can't be a blank space!");
        nameField.getValidators().add(validator);
        nameField.focusedProperty().addListener((o, oldVal, newVal)-> {
            if (!newVal) {
                nameField.validate();
            }
        });

        /* Add Button */
        final StackPane addBtnContainer = new StackPane();
        addBtnContainer.getChildren().add(addBtn);
        addBtnContainer.setAlignment(Pos.CENTER_RIGHT);
        addBtn.getStyleClass().add("add-button");

        /* Setting up container */
        this.setPadding(new Insets(INSETS));
        this.setSpacing(SPACING);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(nameField, colorPicker, isAICheckBox, addBtnContainer);

        HBox.setHgrow(nameField, Priority.ALWAYS);
        HBox.setHgrow(addBtnContainer, Priority.ALWAYS);
    }


    /**
     * 
     * Text filed getter.
     * 
     * @return
     *          The text specified into text filed object.
     * 
     */
    public JFXTextField getTextField() {
        return this.nameField;
    }

    /**
     * 
     * ColorPicker getter.
     * 
     * @return
     *          The ColorPicker object.
     * 
     */
    public ColorPicker getColorPicker() {
        return this.colorPicker;
    }

    /**
     * 
     * CheckBox getter.
     * 
     * @return
     *          isAi check box object.
     * 
     */
    public JFXCheckBox getCheckBox() {
        return this.isAICheckBox;
    }

    /**
     * 
     * Add button getter.
     * 
     * @return
     *          Add button.
     * 
     */
    public JFXButton getAddBtn() {
        return this.addBtn;
    }
}
