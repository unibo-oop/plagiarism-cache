package com.biaren.utility.viewparts;

import java.util.List;
import org.controlsfx.control.CheckComboBox;
import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * Create a box with {@link Label} and {@link ComboBox}.
 * Extends a {@link VerticalLabelFieldBox}.
 * @author nbrunetti
 *
 * @param <C> the type of data to handle
 */
public class VerticalLabelComboBox<C> extends VerticalLabelFieldBox{

    private ObservableList<C> choices;
    
    /**
     * Creates {@link VerticalLabelComboBox}.
     * @param labelText for label
     * @param choices for combo box
     */
    public VerticalLabelComboBox(final String labelText, final List<C> choices) {
        super(labelText, new CheckComboBox<C>());
        this.choices = FXCollections.<C>observableArrayList(choices);
        this.setDefaultChoices();
    }
    
    private void setDefaultChoices() {
        this.getField().getItems().addAll(this.choices);
    }

    /**
     * Get field
     * @return {@link CheckComboBox} reference
     */
    @SuppressWarnings("unchecked")
    public CheckComboBox<C> getField() {
        return (CheckComboBox<C>) this.field;
    }
    
    /**
     * Get selected items list from combo box
     * @return {@link List} with selected items
     */
    public List<C> getFieldValue() {
        return this.getField().getCheckModel().getCheckedItems();
    }
        
    /**
     * Get choices
     * @return {@link List} with choices
     */
    public List<C> getChoices() {
        return this.choices;
    }
}
