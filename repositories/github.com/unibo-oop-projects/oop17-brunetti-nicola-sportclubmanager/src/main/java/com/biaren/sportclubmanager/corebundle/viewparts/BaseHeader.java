package com.biaren.sportclubmanager.corebundle.viewparts;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Base Header to add in a view. Extends {@link HBox}.
 * Presents a {@link Label} for the header title.
 * @author nbrunetti
 *
 */
public class BaseHeader extends HBox {

    private Label modalTitle;
    
    /**
     * Create a {@link BaseHeader}.
     * @param modalTitle string for modal title
     */
    public BaseHeader(final String modalTitle) {
        this.modalTitle = new Label(modalTitle);
        this.setLayout();
    }
    
    private void setLayout() {
        this.getChildren().add(this.modalTitle);
    }
}
