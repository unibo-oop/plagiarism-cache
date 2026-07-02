package com.biaren.sportclubmanager.corebundle.viewparts;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Base form footer to add in a view. Extends {@link HBox} and present
 * classical submit, delete, cancel/close buttons.
 * @author nbrunetti
 *
 */
public class BaseFormFooter extends HBox {

    private Button submit;
    private Button close;
    private Button delete;
    
    /**
     * Creates a {@link BaseFormFooter}.
     */
    public BaseFormFooter() {
        this.submit = new Button("Invia");
        this.close = new Button("Annulla");
        this.delete = new Button("Elimina");
        this.setLayout();
    }
    
    private void setLayout() {
        this.getChildren().addAll(this.submit, this.delete, this.close);
    }
    
    /**
     * Get submit button reference.
     * @return submit {@link Button} reference
     */
    public Button getSubmitButton() {
        return this.submit;
    }
    
    /**
     * Get close button reference.
     * @return close {@link Button} reference
     */
    public Button getCloseButton() {
        return this.close;
    }
    
    /**
     * Get delete button reference.
     * @return delete {@link Button} reference
     */
    public Button getDeleteButton() {
        return this.delete;
    }
}
