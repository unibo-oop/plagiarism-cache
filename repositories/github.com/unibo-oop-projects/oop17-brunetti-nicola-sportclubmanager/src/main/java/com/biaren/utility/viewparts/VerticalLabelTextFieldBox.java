package com.biaren.utility.viewparts;

import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Extension of {@link VerticalLabelFieldBox} for {@link Label} - {@link TextField} pair.
 * @author nbrunetti
 *
 */
public class VerticalLabelTextFieldBox extends VerticalLabelFieldBox {

    /**
     * Constructor. Creates a {@link VBox} with {@link Label} and {@link TextField} as nodes.
     * @param labelText {@link String} of {@link Label} text
     */
    public VerticalLabelTextFieldBox(String labelText) {
        super(labelText, new TextField());
    }
    
    /**
     * Get the field of the {@link VBox}.
     * @return {@link DatePicker} of the {@link VBox}
     */
    public TextField getField() {
        return (TextField) this.field;
    }
    
    /**
     * Get field value
     * @return field {@link String} value
     */
    public String getFieldValue() {
        return this.getField().getText();
    }
}
