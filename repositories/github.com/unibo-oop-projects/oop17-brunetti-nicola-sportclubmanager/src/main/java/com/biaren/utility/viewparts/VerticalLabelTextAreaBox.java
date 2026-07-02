package com.biaren.utility.viewparts;

import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Extension of {@link VerticalLabelFieldBox} for {@link Label} - {@link TextArea} pair.
 * @author nbrunetti
 *
 */
public class VerticalLabelTextAreaBox extends VerticalLabelFieldBox {

    /**
     * Constructor. Creates a {@link VBox} with {@link Label} and {@link TextArea} as nodes.
     * @param labelText {@link String} of {@link Label} text
     */
    public VerticalLabelTextAreaBox(String labelText) {
        super(labelText, new TextArea());
        this.getField().setWrapText(true);
    }
    
    /**
     * Get the field of the {@link VBox}.
     * @return {@link TextArea} of the {@link VBox}
     */
    public TextArea getField() {
        return (TextArea) this.field;
    }
    
    /**
     * Get field value
     * @return field {@link String} value
     */
    public String getFieldValue() {
        return this.getField().getText();
    }
}
