package com.biaren.utility.viewparts;

import com.biaren.utility.viewparts.abstractviewparts.VerticalLabelFieldBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

/**
 * Extension of {@link VerticalLabelFieldBox} for {@link Label} - {@link PasswordField} pair.
 * @author nbrunetti
 *
 */
public class VerticalLabelPasswordFieldBox extends VerticalLabelFieldBox {

    /**
     * Constructor. Creates a {@link VBox} for {@link Label} - {@link PasswordField} pair.
     */
    public VerticalLabelPasswordFieldBox() {
        super("Password", new PasswordField());
    }
    
    /**
     * Get the field of the {@link VBox}.
     * @return {@link PasswordField} of the {@link VBox}
     */
    @Override
    public PasswordField getField() {
        return (PasswordField) this.field;
    }
}
