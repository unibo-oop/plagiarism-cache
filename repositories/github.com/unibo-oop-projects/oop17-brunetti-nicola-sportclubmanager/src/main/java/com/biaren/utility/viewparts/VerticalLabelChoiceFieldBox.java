package com.biaren.utility.viewparts;

import java.util.List;
import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Extension of {@link VerticalLabelFieldBox} for {@link Label} - {@link ChoiceBox} pair
 * @author nbrunetti
 *
 */
public class VerticalLabelChoiceFieldBox<C> extends VerticalLabelFieldBox {
    
//    private List<C> choices;
    private ObservableList<C> choices;
    
    /**
     * Constructor. Creates a {@link VBox} with {@link Label} and {@link TextField} as nodes.
     * @param labelText {@link String} of {@link Label} text
     * @param choices {@link List} of string for multiple choices
     */
    public VerticalLabelChoiceFieldBox(final String labelText, List<C> choices) {
        super(labelText, new ChoiceBox<C>());
        this.choices = FXCollections.<C>observableList(choices);
        this.setDefaultChoices();
    }
    
    /**
     * Sets default choices.
     */
    private void setDefaultChoices() {
        this.getField().getItems().addAll(this.choices);
    }
    
    /**
     * Get the field of the {@link VBox}.
     * @return {@link ChoiceBox} of the {@link VBox}
     */
    @SuppressWarnings("unchecked")
    public ChoiceBox<C> getField() {
        return (ChoiceBox<C>) this.field;
    }
    
    /**
     * Get field value
     * @return field value
     */
    public C getFieldValue() {
        return this.getField().getValue();
    }
    
    /**
     * Get list of choices
     * @return {@link List} of choices
     */
    public List<C> getChoices() {
        return this.choices;
    }
    
}
