package com.biaren.sportclubmanager.corebundle.viewparts;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Specific header for list view.
 * Presents a button to performs some actions.
 * @author nbrunetti
 *
 */
public class BaseListViewHeader extends HBox {
    
    private Label title;
    private Button addNewEntity;
    
    /**
     * Creates a {@link BaseListViewHeader}.
     * @param title of list view
     */
    public BaseListViewHeader(final String title) {
        this.title = new Label(title);
        this.addNewEntity = new Button("Aggiungi");
        this.setLayout();
    }
    
    private void setLayout() {
        this.getChildren().addAll(this.title, this.addNewEntity);
    }
    
    /**
     * Get button reference.
     * @return {@link Button} reference.
     */
    public Button getAddNewEntityButton() {
        return this.addNewEntity;
    }
}
