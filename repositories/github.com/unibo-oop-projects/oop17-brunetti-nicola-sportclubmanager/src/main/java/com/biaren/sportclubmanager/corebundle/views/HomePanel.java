package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.HomeController;
import com.biaren.sportclubmanager.corebundle.viewparts.HomeHeaderPanel;
import com.biaren.sportclubmanager.corebundle.viewparts.HomeMenuPanel;
import com.biaren.sportclubmanager.corebundle.views.interfaces.HomeView;
import com.biaren.sportclubmanager.utility.enums.HomeMenuLabel;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represent a panel view for home screen. Extends {@link BorderPane} to
 * split out each home screen parts in correct position.
 * Implements {@link HomeView}.
 * @author nbrunetti
 *
 */
public class HomePanel extends BorderPane implements HomeView{

    private final Label label;
    private final Pane currentContent;
    private final HomeMenuPanel menuPanel;
    private final HomeHeaderPanel headerPanel;
    private HomeController controller;
    
    /**
     * Creates a {@link HomePanel}
     */
    public HomePanel() {
        this.label = new Label("HOME PANEL");
        this.currentContent = new VBox();
        this.menuPanel = new HomeMenuPanel();
        this.headerPanel = new HomeHeaderPanel();
        this.setLayout();
        this.attachEvents();
    }
    
    /**
     * Attach a controller to control this panel
     */
    public void attachViewObserver(final HomeController controller) {
        this.controller = controller;
    }
    
    /**
     * Set current content, based on user click on menu
     */
    public void setCurrentContent(final Pane content) {
        this.currentContent.getChildren().clear();
        this.currentContent.getChildren().add(content);
    }
    
    private void attachEvents() {
        this.attachMenuEvents();
        this.attachHeaderEvents();
    }
    
    private void attachMenuEvents() {
        this.menuPanel.getMenuItems().get(HomeMenuLabel.HOME_LABEL).setOnMouseClicked(e -> this.controller.homeMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.PLAYERS_LABEL).setOnMouseClicked(e -> this.controller.playersMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.STAFF_LABEL).setOnMouseClicked(e -> this.controller.staffMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.MATCHES).setOnMouseClicked(e -> this.controller.matchesMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.STATISTICS).setOnMouseClicked(e -> this.controller.statisticsMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.SOCIETY_LABEL).setOnMouseClicked(e -> this.controller.societyMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.EMPLOYEES_LABEL).setOnMouseClicked(e -> this.controller.employeesMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.FACILITIES_LABEL).setOnMouseClicked(e -> this.controller.facilitiesMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.SPONSOR_LABEL).setOnMouseClicked(e -> this.controller.sponsorMenuAction());
        this.menuPanel.getMenuItems().get(HomeMenuLabel.CREATE_MATCH_PLAYERS_LIST).setOnMouseClicked(e -> this.controller.createListForMatch());
    }
    
    private void attachHeaderEvents() {
        this.headerPanel.getLogoutButton().setOnAction(e -> {
            this.controller.logoutAction();
            Stage stage = (Stage) this.getScene().getWindow();
            stage.close();
        });
    }
    
    private void setLayout() {
        this.setTopLayout();
        this.setRightLayout();
        this.setLeftLayout();
        this.setBottomLayout();
        this.setCenterLayout();
        this.getChildren().add(this.label);
    }
    
    private void setTopLayout() {
        this.setTop(this.headerPanel);
    }
    
    private void setBottomLayout() {
        
    }
    
    private void setLeftLayout() {
        this.setLeft(this.menuPanel);
    }
    
    private void setRightLayout() {
        
    }
    
    private void setCenterLayout() {
        this.currentContent.setStyle("-fx-background-color: white;");
        this.setCenter(this.currentContent);
    }

    /**
     * Get Menu Panel reference
     * @return {@link HomeMenuPanel} reference
     */
    public HomeMenuPanel getMenuPanel() {
        return this.menuPanel;
    }
    
    /**
     * Get Home Header Panel reference
     * @return {@link HomeHeaderPanel} reference
     */
    public HomeHeaderPanel getHeaderPanel() {
        return this.headerPanel;
    }
    
    /**
     * Get current content
     * @return {@link Pane} reference of current content
     */
    public Pane getCurrentContent() {
        return this.currentContent;
    }
}


