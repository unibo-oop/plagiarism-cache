package com.biaren.sportclubmanager.corebundle.views;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.viewparts.BaseListViewHeader;
import com.biaren.sportclubmanager.corebundle.views.interfaces.ListView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Abstract class for a list view.
 * Extends {@link VBox}.
 * Implements {@link ListView}.
 * @author nbrunetti
 *
 * @param <T> type of entity to show in list
 */
public abstract class BaseListView<T> extends VBox implements ListView<T> {

    /** Table view of {@link TableView} to show entities in table format */
    protected TableView<T> table;
    /** Header for list view panel */
    protected BaseListViewHeader header;
    /** Controller for panel */
    protected ListViewController<T> controller;
    
    /**
     * Constructor for {@link BaseListView}. Cannot use because abstract,
     * useful for share common fields and operation through extensions.
     * @param entityListName name of entity to show
     */ 
    public BaseListView(final String entityListName) {
        this.table = new TableView<>();
        this.header = new BaseListViewHeader(entityListName);
        this.attachEvents();
    }
    
    /**
     * Attach a view observer to control panel
     */
    public void attachViewObserver(final ListViewController<T> controller) {
        this.controller = controller;
    }
    
    /**
     * Attach events on panel's field.
     */
    protected void attachEvents() {
        this.addButtonAction();
    }
    
    private void addButtonAction() {
        this.header.getAddNewEntityButton().setOnAction(e -> this.showModal(this.controller.addEntityActionHandler()));
        this.selectRowHandler();
    }

    /**
     * Show modal form to insert data o view entity's data.
     * @param modal form to show
     */
    protected final void showModal(final BaseForm<T> modal) {
        final Stage dialog = new Stage();
        final Stage primaryStage = (Stage) this.getScene().getWindow();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        Scene dialogScene = new Scene(modal, 1000, 800);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    /**
     * Handler for table row {@link TableRow} clicked action.
     */
    protected void selectRowHandler() {
        this.table.setRowFactory(c -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(e -> this.rowClickEvent(row));
            return row;
        });
    }
    
    /**
     * Clear Table.
     */
    protected void clearTable() {
        this.table.getColumns().clear();
        this.table.getItems().clear();
    }
    
    /**
     * Update view by clearing table and updating table.
     */
    public void updateView() {
        this.clearTable();
        this.updateTable();
    }
    
    /**
     * Update table datas.
     */
    protected abstract void updateTable();
    
    /**
     * Set table row click event.
     * @param row to attach event
     */
    protected abstract void rowClickEvent(final TableRow<T> row) throws NullPointerException;
        
    /**
     * Set panel layout.
     */
    protected void setLayout() {
        this.getChildren().addAll(this.header, this.table);
    }
    
    /**
     * Set header layout.
     */
    protected void setHeaderLayout() {
        this.header.getChildren().add(this.header);
    }
    
    /**
     * Get {@link TableView} reference
     * @return table {@link TableView} reference
     */
    public TableView<T> getTable() {
        return this.table;
    }
    
    /**
     * Get header
     * @return header {@link BaseListView} reference
     */
    public BaseListViewHeader getHeader() {
        return this.header;
    }
    
    /**
     * Get add button.
     * @return {@link Button} reference
     */
    public Button getAddButton() {
        return this.getHeader().getAddNewEntityButton();
    }
}
