package com.biaren.sportclubmanager.soccerbundle.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import com.biaren.sportclubmanager.corebundle.services.PdfCreator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel with fields to insert player list for match.
 * Allow to create a pdf with this list of player as user's inputs.
 * @author nbrunetti
 *
 */
public class MatchListPanel extends VBox {

    private final static int MAX_PLAYERS_LIST = 18;
    private final static int MAX_STARTER_PLAYER = 11;
    
    private List<HBox> playersRows;
    private List<HBox> reserveRows;
    private Button createPdf;
    private final ScrollPane content;
    
    /**
     * Create a {@link MatchListPanel}
     */
    public MatchListPanel() {
        this.playersRows = new ArrayList<>();
        this.reserveRows = new ArrayList<>();
        this.createPdf = new Button("Crea PDF Distinta");
        this.content = new ScrollPane();
        this.setLayout();
    }
    
    private void setLayout() {
        this.prepareRows();
        final VBox v = new VBox();
        v.getChildren().add(this.getPlayersHeader());
        v.getChildren().addAll(this.playersRows);
        v.getChildren().add(this.getReserveHeader());      
        v.getChildren().add(this.getPlayersHeader());      
        v.getChildren().addAll(this.reserveRows);
        v.getChildren().add(this.createPdf);
        this.setPadding(new Insets(50, 0, 0, 20));
        this.content.setContent(v);
        this.getChildren().add(this.content);
        this.attachEvents();
    }
    
    private void attachEvents() {
        this.createPdfActionHandler();
    }
    
    private void createPdfActionHandler() {
        System.out.println("handler");
        this.createPdf.setOnAction(e -> PdfCreator.createMatchPlayersListPdf(
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")),
                this.getStarters(), this.getReserves()));
    }
    
    private HBox getRowContent(final String text) {
        final HBox row = new HBox();
        row.getChildren().addAll(this.getShirtNumberLabel(text), this.getNameField());
        return row;
    }
    
    private void prepareRows() {
        IntStream.range(1, MAX_STARTER_PLAYER + 1).forEach(
                i -> this.playersRows.add(this.getRowContent(String.valueOf(i))));
        IntStream.range(MAX_STARTER_PLAYER + 1, MAX_PLAYERS_LIST + 1).forEach(
                i -> this.reserveRows.add(this.getRowContent(String.valueOf(i))));
    }
    
    private TextField getShirtNumberLabel(final String text) {
        return this.getFieldWithPrefSize(40, 40, true, text);
    }
    
    private TextField getNameField() {
        return this.getFieldWithPrefSize(40, 400, false, "");
    }
    
    private TextField getFieldWithPrefSize(final int height, final int width, final boolean disable, final String defText) {
        final TextField field = new TextField();
        field.setPrefWidth(width);
        field.setPrefHeight(height);
        field.setDisable(disable);
        field.setText(defText);
        return field;
    }
    
    private List<Label> getHeaderPlayersContent() {
        final List<Label> l = new ArrayList<>();
        Label n = new Label("N°");
        Label names = new Label("Nome e Cognome");
        n.setPrefWidth(40);
        names.setPrefWidth(400);
        l.add(n);
        l.add(names);
        return l;
    }
    
    private Label getReserveHeaderContent() {
        Label l = new Label("A disposizione");
        return l;
    }
    
    private HBox getPlayersHeader() {
        final HBox b = new HBox();
        b.getChildren().addAll(this.getHeaderPlayersContent());
        return b;
    }
    
    private HBox getReserveHeader() {
        final HBox b = new HBox();
        b.getChildren().addAll(this.getReserveHeaderContent());
        return b;
    }
    
    /**
     * Get a {@link Map} with key-value as shirt number - player's name as strings
     * @return {@link Map} with key-value as shirt number - player's name as strings
     */
    public Map<String, String> getStarters() {
        Map<String, String> map = new LinkedHashMap<>();
        this.playersRows.forEach(h -> map.put(((TextField)h.getChildren().get(0)).getText(),
                ((TextField)h.getChildren().get(1)).getText()));
        return map;
    }
    
    /**
     * Get a {@link Map} with key-value as shirt number - player's name as strings
     * @return {@link Map} with key-value as shirt number - player's name as strings
     */
    public Map<String, String> getReserves() {
        Map<String, String> map = new LinkedHashMap<>();
        this.reserveRows.forEach(h -> map.put(((TextField)h.getChildren().get(0)).getText(),
                ((TextField)h.getChildren().get(1)).getText()));
        return map;
    }
}
