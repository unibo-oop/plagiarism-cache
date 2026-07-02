package com.biaren.sportclubmanager.corebundle.viewparts;

import java.util.LinkedHashMap;
import java.util.Map;
import com.biaren.sportclubmanager.utility.enums.HomeMenuLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Menu Panel: presents menu items as {@link HBox} that works
 * like buttons and show correct panel on click. 
 * @author nbrunetti
 *
 */
public class HomeMenuPanel extends VBox {

    private final Map<HomeMenuLabel, HBox> menuItems;
    
    public HomeMenuPanel() {
        this.menuItems = new LinkedHashMap<>();
        this.setLayout();
    }
    
    private void setLayout() {
        this.setMenuItems();
        this.getChildren().addAll(this.menuItems.values());
    }
    
    private void setMenuItems() {
        this.menuItems.put(HomeMenuLabel.HOME_LABEL, this.getNewMenuItem(HomeMenuLabel.HOME_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.PLAYERS_LABEL, this.getNewMenuItem(HomeMenuLabel.PLAYERS_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.STAFF_LABEL, this.getNewMenuItem(HomeMenuLabel.STAFF_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.MATCHES, this.getNewMenuItem(HomeMenuLabel.MATCHES.toString()));
        this.menuItems.put(HomeMenuLabel.STATISTICS, this.getNewMenuItem(HomeMenuLabel.STATISTICS.toString()));
        this.menuItems.put(HomeMenuLabel.SOCIETY_LABEL, this.getNewMenuItem(HomeMenuLabel.SOCIETY_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.EMPLOYEES_LABEL, this.getNewMenuItem(HomeMenuLabel.EMPLOYEES_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.FACILITIES_LABEL, this.getNewMenuItem(HomeMenuLabel.FACILITIES_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.SPONSOR_LABEL, this.getNewMenuItem(HomeMenuLabel.SPONSOR_LABEL.toString()));
        this.menuItems.put(HomeMenuLabel.CREATE_MATCH_PLAYERS_LIST, this.getNewMenuItem(HomeMenuLabel.CREATE_MATCH_PLAYERS_LIST.toString()));
    }
    
    private HBox getNewMenuItem(final String itemName) {
        HBox hb = new HBox(new Label(itemName));
        hb.setPrefHeight(60);
        hb.setPadding(new Insets(0, 20, 0, 20));
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setOnMouseEntered(e -> hb.setStyle(  "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n" +
                                                "-fx-border-color: lightBlue;\n" +
                                                "-gx-border-width: 3;\n"));
        hb.setOnMouseExited(e -> hb.setStyle(   "-fx-background-color: transparent;" +
                                                "-fx-border-color: lightBlue;\n" +
                                                "-gx-border-width: 3;\n"));
        return hb;
    }
    
    /**
     * Get the menu's items.
     * @return {@link Map} with pairs {@link HomeMenuLabel}-{@link HBox} (Label-MenuItem)
     */
    public Map<HomeMenuLabel, HBox> getMenuItems() {
        return this.menuItems;
    }
}
