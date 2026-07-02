package com.biaren.sportclubmanager.corebundle.viewutil;

import java.util.ArrayList;
import java.util.List;
import com.biaren.sportclubmanager.corebundle.model.SponsorImpl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class SponsorTableUtil {

    /**
     * Get {@link TableColumn} for {@link SponsorImpl} id
     * @return table column of specified field
     */
    public static TableColumn<SponsorImpl, String> getIdColumn() {
        TableColumn<SponsorImpl, String> sponsorIdCol = new TableColumn<>("ID");
        sponsorIdCol.setCellValueFactory(new PropertyValueFactory<>("sponsorId"));
        return sponsorIdCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SponsorImpl} name
     * @return table column of specified field
     */
    public static TableColumn<SponsorImpl, String> getNameColumn() {
        TableColumn<SponsorImpl, String> sponsorNameCol = new TableColumn<>("Nome");
        sponsorNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return sponsorNameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link SponsorImpl} description
     * @return table column of specified field
     */
    
    public static TableColumn<SponsorImpl, String> getDescriptionColumn() {
        TableColumn<SponsorImpl, String> sponsorDescriptionCol = new TableColumn<>("Descrizione");
        sponsorDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        return sponsorDescriptionCol;
    }
    
    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<SponsorImpl, ?>> tableColumnList() {
        List<TableColumn<SponsorImpl, ?>> list = new ArrayList<>();
        list.add(getIdColumn());
        list.add(getNameColumn());
        list.add(getDescriptionColumn());
        return list;
    }
}
