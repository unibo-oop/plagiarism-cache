package com.biaren.utility.viewparts;

import java.time.LocalDate;
import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Extension of {@link VerticalLabelFieldBox} for {@link Label} - {@link DatePicker} pair.
 * @author nbrunetti
 *
 */
public class VerticalLabelDatePickerBox extends VerticalLabelFieldBox {
    
    /**
     * Constructor. Creates a {@link VBox} with {@link Label} and {@link DatePicker} as nodes.
     * @param labelText {@link String} for {@link Label} text.
     */
    public VerticalLabelDatePickerBox(String labelText) {
        super(labelText, new DatePicker());
        this.disableEditDatePicker();
    }
    
    /**
     * Get the field of the {@link VBox}.
     * @return {@link DatePicker} of the {@link VBox}
     */
    @Override
    public DatePicker getField() {
        return (DatePicker) this.field;
    }
    
    /**
     * Get field value
     * @return {@link String} date value from field
     */
    public String getFieldValue() {
        LocalDate date = this.getField().getValue();
        return date.toString();
    }
    
    private void disableEditDatePicker() {
        ((DatePicker) this.field).getEditor().setEditable(false);
    }
}
