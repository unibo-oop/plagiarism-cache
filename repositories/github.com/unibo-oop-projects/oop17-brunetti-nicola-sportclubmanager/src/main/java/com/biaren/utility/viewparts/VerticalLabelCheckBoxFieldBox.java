package com.biaren.utility.viewparts;

import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * Utility viewparts, extends class {@link VerticalLabelFieldBox} to
 * handle a {@link CheckBox}
 * @author nbrunetti
 *
 * @param <C> the type of data to handle
 */
public class VerticalLabelCheckBoxFieldBox<C> extends VerticalLabelFieldBox {
    
    /**
     * Create a box with a {@link Label} and a {@link CheckBox}
     * @param labelName for label
     * @param choices for check box choices
     */
    public VerticalLabelCheckBoxFieldBox(final String labelName, final C[] choices) {
        super(labelName, new CheckBox());
    }
    
    /**
     * Get {@link CheckBox} field
     * @return {@link CheckBox} field reference
     */
    @Override
    public CheckBox getField() {
        return (CheckBox) this.field;
    }
}
