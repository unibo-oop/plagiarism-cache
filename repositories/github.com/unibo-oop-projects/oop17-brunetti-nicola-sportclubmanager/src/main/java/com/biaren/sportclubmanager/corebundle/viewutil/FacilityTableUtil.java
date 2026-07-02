package com.biaren.sportclubmanager.corebundle.viewutil;

import java.util.ArrayList;
import java.util.List;

import com.biaren.sportclubmanager.corebundle.model.FacilityImpl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility class for {@link TableView}. Provides static methods to get {@link TableColumn} with
 * data from entities.
 * @author nbrunetti
 *
 */
public class FacilityTableUtil {
    
    /**
     * Get {@link TableColumn} for {@link FacilityImpl} id
     * @return table column of specified field
     */ 
    public static TableColumn<FacilityImpl, Integer> getIdColumn() {
        TableColumn<FacilityImpl, Integer> facilityIdCol = new TableColumn<>("ID");
        facilityIdCol.setCellValueFactory(new PropertyValueFactory<>("facilityId"));
        return facilityIdCol;
    }

    /**
     * Get {@link TableColumn} for {@link FacilityImpl} name
     * @return table column of specified field
     */ 
    public static TableColumn<FacilityImpl, String> getNameColumn() {
        TableColumn<FacilityImpl, String> facilityNameCol = new TableColumn<>("Nome");
        facilityNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return facilityNameCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link FacilityImpl} address
     * @return table column of specified field
     */ 
    public static TableColumn<FacilityImpl, String> getAddressColumn() {
        TableColumn<FacilityImpl, String> facilityAddressCol = new TableColumn<>("Indirizzo");
        facilityAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        return facilityAddressCol;
    }
    
    /**
     * Get {@link TableColumn} for {@link FacilityImpl} description
     * @return table column of specified field
     */ 
    public static TableColumn<FacilityImpl, String> getDescriptionColumn() {
        TableColumn<FacilityImpl, String> facilityDescriptionCol = new TableColumn<>("Descrizione");
        facilityDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        return facilityDescriptionCol;
    }
    
    /**
     * Get {@link TableColumn} list
     * @return table column list of all specified fields
     */
    public static List<TableColumn<FacilityImpl, ?>> tableColumnList() {
        List<TableColumn<FacilityImpl, ?>> list = new ArrayList<>();
        list.add(getIdColumn());
        list.add(getNameColumn());
        list.add(getAddressColumn());
        list.add(getDescriptionColumn());
        return list;
    }
}
