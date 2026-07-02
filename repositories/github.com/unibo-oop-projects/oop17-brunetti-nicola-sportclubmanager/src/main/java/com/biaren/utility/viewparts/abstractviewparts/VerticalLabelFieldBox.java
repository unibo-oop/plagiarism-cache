package com.biaren.utility.viewparts.abstractviewparts;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Abstract class for vertical box that contains a {@link Label} and a field, a subclass of {@link Control}.
 * Useful internal class. Specifies a standard layout in a Vertical Box {@link VBox}.
 * @author nbrunetti
 *
 */
public abstract class VerticalLabelFieldBox extends VBox{
    
    /** {@link Label} for field */
    protected Label label;
    /** {@link Control} as field **/
    protected Control field;
    
    /**
     * Constructor.
     * @param labelText {@link String} for {@link Label} text
     * @param field {@link Control}
     */
    public VerticalLabelFieldBox(final String labelText, final Control field) {
        this.label = new Label(labelText);
        this.field = field;
        this.setLayout();
    }
    
    /**
     * Get {@link Label} of the box
     * @return {@link Label} of the box
     */
    public Label getLabel() {
        return this.label;
    }
    
    /**
     * Get the {@link Control} of the box
     * @return {@link Control} of the box
     */
    public Control getField() {
        return this.field;
    }
    
    /**
     * Sets a specific layout for the box and nodes.
     */
    protected void setLayout() {
        this.getChildren().addAll(this.label, this.field);
        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
    }
}
