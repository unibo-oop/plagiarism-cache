package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.utility.enums.TextStrings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represent a welcome screen panel.
 * Extends {@link VBox}.
 * @author nbrunetti
 *
 */
public class WelcomeScreenPanel extends VBox {
    
    private HBox headerBox;
    private VBox contentBox;
    private VBox currentContent;
    
    /**
     * Creates a {@link WelcomeScreenPanel}
     */
    public WelcomeScreenPanel() {
        this.contentBox = new VBox();
        this.setLayout();
    }
    
    /**
     * Get content box reference to manipulate content of welcome screen panel
     * @return {@link VBox} content box reference for welcome screen panel
     */
    public VBox getContentBox() {
        return this.contentBox;
    }
    
    /**
     * Get header box reference
     * @return {@link HBox} reference
     */
    public HBox getHeader() {
        return this.headerBox;
    }
    
    private void setLayout() {
        this.setHeaderLayout();
        this.setContentLayout();
        this.getChildren().addAll(this.headerBox, this.contentBox);
    }
    
    /**
     * Switch previous content to new content passed as argument
     * @param currentContent as a {@link VBox} o an extension of it.
     */
    public void switchContent(final VBox currentContent) {
        this.currentContent = currentContent;
        this.reloadContent();
    }
    
    private void setHeaderLayout() {
        this.headerBox = new HBox();
        this.headerBox.setPrefHeight(100);
        this.headerBox.setAlignment(Pos.CENTER);
        this.headerBox.setStyle("-fx-background-color: white;");
        this.headerBox.getChildren().add(new Label(TextStrings.WELCOME_LABEL.toString()));
    }
    
    private void setContentLayout() {
        this.contentBox.setAlignment(Pos.CENTER);
        this.contentBox.setStyle("-fx-background-color: lightGrey;");   
    }
    
    private void reloadContent() {
        this.contentBox.getChildren().clear();
        this.contentBox.getChildren().add(this.currentContent);
    }
}
